package cs477.ontap;

import android.app.Dialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Alex on 4/15/2016.
 */
public class HomeActivity extends AppCompatActivity {

    public String currentLocationName;
    public String currentLocationMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        final EditText currentLocationEditText = (EditText)findViewById(R.id.editText_currentLocationName);
        assert currentLocationEditText != null;

        final Dialog locationDialog = new Dialog(this);
        locationDialog.setContentView(R.layout.location_select_alert);
        locationDialog.setTitle("Location Selection");
        locationDialog.setCancelable(false);
        final EditText locationCodeText = (EditText)locationDialog.findViewById(R.id.editText_locationCode);
        Button cancelLocationSelectionButton = (Button)locationDialog.findViewById(R.id.button_locationCancel);
        cancelLocationSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationDialog.cancel();
            }
        });
        Button submitLocationAlertButton = (Button)locationDialog.findViewById(R.id.button_locationSubmit);
        assert submitLocationAlertButton != null;
        submitLocationAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String locationCodeString = locationCodeText.getText().toString();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Menu");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> menus, com.parse.ParseException e) {
                        if (e == null) {
                            if (menus.size() == 0) {
                                Toast.makeText(HomeActivity.this, "No Locations Available..", Toast.LENGTH_SHORT).show();
                            } else {

                                boolean locationFound = false;
                                for (int i = 0; i < menus.size(); i++) {
                                    if (menus.get(i).getString("locID").equals(locationCodeString)) {
                                        //Toast.makeText(MainActivity.this, userID, Toast.LENGTH_SHORT).show();
                                        Toast.makeText(HomeActivity.this, "Location Match!", Toast.LENGTH_SHORT).show();
                                        currentLocationName = menus.get(i).getString("locName");
                                        currentLocationMenu = menus.get(i).getString("menu");
                                        currentLocationEditText.setText(currentLocationName);
                                        locationFound = true;

                                        Intent myIntent = new Intent(HomeActivity.this, MyTabActivity.class);
                                        startActivity(myIntent);
                                    }
                                }
                                if (!locationFound) {
                                    Toast.makeText(HomeActivity.this, "Did not find location", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } else {
                            //objectRetrievalFailed();
                            Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                Intent myIntent = new Intent(HomeActivity.this, MyTabActivity.class);
//                startActivity(myIntent);
            }
        });

        Button orderDrinksButton = (Button)findViewById(R.id.button_orderDrinks);
        assert orderDrinksButton != null;
        orderDrinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeActivity.this, "Open OnTap Locations selector alert", Toast.LENGTH_SHORT).show();
                if(currentLocationName == null){
                    locationDialog.show();
                }
                else{
                    Intent myIntent = new Intent(HomeActivity.this, MyTabActivity.class);
                    startActivity(myIntent);
                }

            }
        });

        Button manageAccountButton = (Button)findViewById(R.id.button_manageAccount);
        assert manageAccountButton != null;
        manageAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this, AccountManagementActivity.class);
                startActivity(myIntent);
            }
        });


    }
}
