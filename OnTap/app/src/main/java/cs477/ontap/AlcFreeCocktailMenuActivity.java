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
public class AlcFreeCocktailMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alc_free_cocktail_menu);

        final ListView alcFreeCocktailList = (ListView)findViewById(R.id.listView_alcFreeCocktailList);

        String[] values = new String[] {"","","","","","","","","",""};

        List<drinkObject> alcFreeCocktailMenu = HomeActivity.alcFreeCocktailMenu;
        for(int i = 0; i<alcFreeCocktailMenu.size(); i++){
            values[i] = alcFreeCocktailMenu.get(i).getDrinkName()+"\n"+"" +
                    "                                                                       $"+alcFreeCocktailMenu.get(i).getDrinkPrice();
        }

        ArrayAdapter<String> alcFreeCocktailListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        assert alcFreeCocktailList != null;
        alcFreeCocktailList.setAdapter(alcFreeCocktailListAdapter);

        // ListView Item Click Listener
        alcFreeCocktailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) alcFreeCocktailList.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });
    }
}

