package cs477.ontap;

import android.os.Bundle;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beers_menu);

        final ListView myBeerList = (ListView)findViewById(R.id.listView_beerList);

        String[] values = new String[] {"","","","","","","","","",""};

        List<drinkObject> beerMenu = HomeActivity.beerMenu;
        for(int i = 0; i<beerMenu.size(); i++){
            values[i] = beerMenu.get(i).getDrinkName()+"\n"+"" +
                    "                                                                       $"+beerMenu.get(i).getDrinkPrice();
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
