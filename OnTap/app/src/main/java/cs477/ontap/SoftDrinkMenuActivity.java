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
public class SoftDrinkMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_drink_menu);

        final ListView mySoftDrinkList = (ListView)findViewById(R.id.listView_softDrinkList);

        String[] values = new String[] {"","","","","","","","","",""};

        List<drinkObject> softDrinkMenu = HomeActivity.softDrinkMenu;
        for(int i = 0; i<softDrinkMenu.size(); i++){
            values[i] = softDrinkMenu.get(i).getDrinkName()+"\n"+"" +
                    "                                                                       $"+softDrinkMenu.get(i).getDrinkPrice();
        }

        ArrayAdapter<String> softDrinkListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        assert mySoftDrinkList != null;
        mySoftDrinkList.setAdapter(softDrinkListAdapter);

        // ListView Item Click Listener
        mySoftDrinkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) mySoftDrinkList.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });
    }
}
