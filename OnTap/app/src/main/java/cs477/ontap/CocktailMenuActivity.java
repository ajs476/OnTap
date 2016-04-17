package cs477.ontap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Alex on 4/17/2016.
 */
public class CocktailMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cocktail_menu);

        final ListView myCocktailList = (ListView)findViewById(R.id.listView_cocktailList);

        String[] values = new String[] {"","","","","","","","","",""};

        List<drinkObject> cocktailMenu = HomeActivity.cocktailMenu;
        for(int i = 0; i<cocktailMenu.size(); i++){
            values[i] = cocktailMenu.get(i).getDrinkName()+"\n"+"" +
                    "                                                                       $"+cocktailMenu.get(i).getDrinkPrice();
        }

        ArrayAdapter<String> cocktailListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        assert myCocktailList != null;
        myCocktailList.setAdapter(cocktailListAdapter);

        // ListView Item Click Listener
        myCocktailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) myCocktailList.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });
    }
}
