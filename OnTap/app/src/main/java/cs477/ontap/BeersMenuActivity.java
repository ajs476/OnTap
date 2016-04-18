package cs477.ontap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex on 4/16/2016.
 */
public class BeersMenuActivity extends AppCompatActivity {

    public int qty;
    public int itemPosition;
    public int newPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beers_menu);

        final ListView myBeerList = (ListView)findViewById(R.id.listView_beerList);

        String[] values = new String[] {"","","","","","","","","",""};

        final List<drinkObject> beerMenu = HomeActivity.beerMenu;
        for(int i = 0; i<beerMenu.size(); i++){
            values[i] = beerMenu.get(i).getDrinkName()+"\n"+"" +
                    "                                                                       $"+beerMenu.get(i).getDrinkPrice();
        }

        ArrayAdapter<String> beerListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        assert myBeerList != null;
        myBeerList.setAdapter(beerListAdapter);


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
                int originPrice = beerMenu.get(itemPosition).getDrinkPrice();
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
                    int originPrice = beerMenu.get(itemPosition).getDrinkPrice();
                    newPrice = originPrice * qty;
                    //drinkQtyText.setText("1");
                    totalCostText.setText("$"+Integer.toString(newPrice));
                }
                else{
                    Toast.makeText(BeersMenuActivity.this, "Cannot decrease amount from 1", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button addDrinkButton = (Button)addDrinkDialog.findViewById(R.id.button_addDrink);
        addDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BeersMenuActivity.this, "Added to order", Toast.LENGTH_SHORT).show();
                List<drinkObject> myTabOrder = HomeActivity.myTabOrder;
                drinkObject newDrink = new drinkObject(drinkNameText.getText().toString(),"Y","Beer",newPrice);
                newDrink.setDrinkQuantity(qty);
                myTabOrder.add(newDrink);
                Intent myIntent = new Intent(BeersMenuActivity.this, MyTabActivity.class);
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
        myBeerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                itemPosition = position;
                if (!(itemPosition > beerMenu.size() - 1)) {
                    // ListView Clicked item value
                    //String itemValue = (String) myBeerList.getItemAtPosition(position);
                    //newQty = Integer.parseInt(drinkQtyText.getText().toString());
                    int originPrice = beerMenu.get(position).getDrinkPrice();
                    newPrice = originPrice * qty;
                    drinkQtyText.setText("1");
                    totalCostText.setText("$" + Integer.toString(newPrice));
                    drinkNameText.setText(beerMenu.get(position).getDrinkName());

                    addDrinkDialog.show();
                }
            }
        });
    }
}
