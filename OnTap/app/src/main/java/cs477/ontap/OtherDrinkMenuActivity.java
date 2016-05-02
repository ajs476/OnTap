package cs477.ontap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Alex on 4/17/2016.
 */
public class OtherDrinkMenuActivity extends AppCompatActivity {

    public int qty;
    public int itemPosition;
    public int newPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_drink_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView otherDrinkList = (ListView)findViewById(R.id.listView_otherDrinkList);

        String[] values = new String[] {"","","","","","","","","",""};

        final List<drinkObject> otherDrinkMenu = HomeActivity.otherDrinkMenu;
        for(int i = 0; i<otherDrinkMenu.size(); i++){
            values[i] = otherDrinkMenu.get(i).getDrinkName()+"\n"+"" +
                    "                                                                       $"+otherDrinkMenu.get(i).getDrinkPrice();
        }

        ArrayAdapter<String> otherDrinkListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        assert otherDrinkList != null;
        otherDrinkList.setAdapter(otherDrinkListAdapter);

        final Dialog addDrinkDialog = new Dialog(this);
        qty = 1;
        addDrinkDialog.setContentView(R.layout.drink_add_alert);
        addDrinkDialog.setTitle("Add Some Drinks");
        addDrinkDialog.setCancelable(false);
        final EditText drinkQtyText = (EditText)addDrinkDialog.findViewById(R.id.editText_drinkQty);
        final EditText drinkNameText = (EditText)addDrinkDialog.findViewById(R.id.editText_drinkAddName);
        final EditText totalCostText = (EditText)addDrinkDialog.findViewById(R.id.editText_totalCost);
        Button increaseQtyButton = (Button)addDrinkDialog.findViewById(R.id.button_increaseQty);
        increaseQtyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty++;
                drinkQtyText.setText(Integer.toString(qty));
                //int newQty = qty;
                int originPrice = otherDrinkMenu.get(itemPosition).getDrinkPrice();
                newPrice = originPrice * qty;
                //drinkQtyText.setText("1");
                totalCostText.setText("$"+Integer.toString(newPrice));


            }
        });
        Button decreaseQtyButton = (Button)addDrinkDialog.findViewById(R.id.button_decreaseQty);
        decreaseQtyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty>1){
                    qty--;
                    drinkQtyText.setText(Integer.toString(qty));
                    //int newQty = Integer.parseInt(drinkQtyText.getText().toString());
                    int originPrice = otherDrinkMenu.get(itemPosition).getDrinkPrice();
                    newPrice = originPrice * qty;
                    //drinkQtyText.setText("1");
                    totalCostText.setText("$"+Integer.toString(newPrice));
                }
                else{
                    Toast.makeText(OtherDrinkMenuActivity.this, "Cannot decrease amount from 1", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button addDrinkButton = (Button)addDrinkDialog.findViewById(R.id.button_addDrink);
        addDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OtherDrinkMenuActivity.this, "Added to order", Toast.LENGTH_SHORT).show();
                List<drinkObject> myTabOrder = HomeActivity.myTabOrder;
                drinkObject newDrink = new drinkObject(drinkNameText.getText().toString(),"Y","Beer",otherDrinkMenu.get(itemPosition).getDrinkPrice());
                newDrink.setDrinkQuantity(qty);
                myTabOrder.add(newDrink);
                Intent myIntent = new Intent(OtherDrinkMenuActivity.this, MyTabActivity.class);
                startActivity(myIntent);
            }
        });
        Button cancelAddDrinkButton = (Button)addDrinkDialog.findViewById(R.id.button_cancelAdd);
        cancelAddDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrinkDialog.cancel();
            }
        });


        // ListView Item Click Listener
        otherDrinkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                itemPosition = position;
                if (!(itemPosition > otherDrinkMenu.size() - 1)) {
                    // ListView Clicked item value
                    //String itemValue = (String) myBeerList.getItemAtPosition(position);
                    //newQty = Integer.parseInt(drinkQtyText.getText().toString());
                    int originPrice = otherDrinkMenu.get(position).getDrinkPrice();
                    newPrice = originPrice * qty;
                    //drinkQtyText.setText("1");
                    totalCostText.setText("$" + Integer.toString(newPrice));
                    drinkNameText.setText(otherDrinkMenu.get(position).getDrinkName());

                    addDrinkDialog.show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}

