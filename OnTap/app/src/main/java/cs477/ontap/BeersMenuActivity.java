package cs477.ontap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex on 4/16/2016.
 */
public class BeersMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beers_menu);

        final ListView myBeerList = (ListView)findViewById(R.id.listView_beerList);

        String currentLocationMenuRaw = HomeActivity.currentLocationMenu;
        String[] splitMenu = currentLocationMenuRaw.split("-");

        List<drinkObject> drinkObjects = new ArrayList<drinkObject>();

        String[] values = new String[] {"","","","","","","","","",""};

        //Toast.makeText(BeersMenuActivity.this, splitMenu[0], Toast.LENGTH_SHORT).show();
        int k = 0;
        for(int i = 0; i<( splitMenu.length); i+=4){


            String drinkName = splitMenu[i];
            Toast.makeText(BeersMenuActivity.this, drinkName, Toast.LENGTH_SHORT).show();
            String drinkAlcohol = splitMenu[i+1];
            Toast.makeText(BeersMenuActivity.this, drinkAlcohol, Toast.LENGTH_SHORT).show();
            String drinkType = splitMenu[i+2];
            Toast.makeText(BeersMenuActivity.this, drinkType, Toast.LENGTH_SHORT).show();
            String drinkPrice = splitMenu[i+3];
            Toast.makeText(BeersMenuActivity.this, drinkPrice, Toast.LENGTH_SHORT).show();
            values[k] = drinkName;
            k++;


            drinkObject newDrink = new drinkObject(drinkName,drinkAlcohol,drinkType,Integer.parseInt(drinkPrice));
            drinkObjects.add(newDrink);



        }



        ArrayAdapter<String> beerListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        assert myBeerList != null;
        myBeerList.setAdapter(beerListAdapter);

        // ListView Item Click Listener
        myBeerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) myBeerList.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });


    }
}
