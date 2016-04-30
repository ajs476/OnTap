package cs477.ontap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
public class AlcFreeCocktailMenuActivity extends AppCompatActivity {

    public int qty;
    public int itemPosition;
    public int newPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alc_free_cocktail_menu);

        final ListView alcFreeCocktailList = (ListView)findViewById(R.id.listView_alcFreeCocktailList);

        String[] values = new String[] {"","","","","","","","","",""};

        final List<drinkObject> alcFreeCocktailMenu = HomeActivity.alcFreeCocktailMenu;
        for(int i = 0; i<alcFreeCocktailMenu.size(); i++){
            values[i] = alcFreeCocktailMenu.get(i).getDrinkName()+"\n"+"" +
                    "                                                                       $"+alcFreeCocktailMenu.get(i).getDrinkPrice();
        }

        ArrayAdapter<String> alcFreeCocktailListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        assert alcFreeCocktailList != null;
        alcFreeCocktailList.setAdapter(alcFreeCocktailListAdapter);

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
                int originPrice = alcFreeCocktailMenu.get(itemPosition).getDrinkPrice();
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
                    int originPrice = alcFreeCocktailMenu.get(itemPosition).getDrinkPrice();
                    newPrice = originPrice * qty;
                    //drinkQtyText.setText("1");
                    totalCostText.setText("$"+Integer.toString(newPrice));
                }
                else{
                    Toast.makeText(AlcFreeCocktailMenuActivity.this, "Cannot decrease amount from 1", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button addDrinkButton = (Button)addDrinkDialog.findViewById(R.id.button_addDrink);
        addDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AlcFreeCocktailMenuActivity.this, "Added to order", Toast.LENGTH_SHORT).show();
                List<drinkObject> myTabOrder = HomeActivity.myTabOrder;
                drinkObject newDrink = new drinkObject(drinkNameText.getText().toString(),"Y","Beer",alcFreeCocktailMenu.get(itemPosition).getDrinkPrice());
                newDrink.setDrinkQuantity(qty);
                myTabOrder.add(newDrink);
                Intent myIntent = new Intent(AlcFreeCocktailMenuActivity.this, MyTabActivity.class);
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
        alcFreeCocktailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                itemPosition = position;
                if (!(itemPosition > alcFreeCocktailMenu.size() - 1)) {
                    // ListView Clicked item value
                    //String itemValue = (String) myBeerList.getItemAtPosition(position);
                    //newQty = Integer.parseInt(drinkQtyText.getText().toString());
                    int originPrice = alcFreeCocktailMenu.get(position).getDrinkPrice();
                    newPrice = originPrice * qty;
                    //drinkQtyText.setText("1");
                    totalCostText.setText("$" + Integer.toString(newPrice));
                    drinkNameText.setText(alcFreeCocktailMenu.get(position).getDrinkName());

                    addDrinkDialog.show();
                }
            }
        });
    }
}

