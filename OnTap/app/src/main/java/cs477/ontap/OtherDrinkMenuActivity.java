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
public class OtherDrinkMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_drink_menu);

        final ListView otherDrinkList = (ListView)findViewById(R.id.listView_otherDrinkList);

        String[] values = new String[] {"","","","","","","","","",""};

        List<drinkObject> otherDrinkMenu = HomeActivity.otherDrinkMenu;
        for(int i = 0; i<otherDrinkMenu.size(); i++){
            values[i] = otherDrinkMenu.get(i).getDrinkName()+"\n"+"" +
                    "                                                                       $"+otherDrinkMenu.get(i).getDrinkPrice();
        }

        ArrayAdapter<String> otherDrinkListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        assert otherDrinkList != null;
        otherDrinkList.setAdapter(otherDrinkListAdapter);

        // ListView Item Click Listener
        otherDrinkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) otherDrinkList.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });
    }
}

