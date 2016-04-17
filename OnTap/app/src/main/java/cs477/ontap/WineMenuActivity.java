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
public class WineMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wine_menu);

        final ListView myWineList = (ListView)findViewById(R.id.listView_wineList);

        String[] values = new String[] {"","","","","","","","","",""};

        List<drinkObject> wineMenu = HomeActivity.wineMenu;
        for(int i = 0; i<wineMenu.size(); i++){
            values[i] = wineMenu.get(i).getDrinkName()+"\n"+"" +
                    "                                                                       $"+wineMenu.get(i).getDrinkPrice();
        }

        ArrayAdapter<String> wineListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        assert myWineList != null;
        myWineList.setAdapter(wineListAdapter);

        // ListView Item Click Listener
        myWineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) myWineList.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });
    }
}
