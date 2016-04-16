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

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alex on 4/15/2016.
 */
public class HomeActivity extends AppCompatActivity {

    public EditText locationTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        locationTest = (EditText)findViewById(R.id.editText_locationTest);


        final Dialog locationDialog = new Dialog(this);
        locationDialog.setContentView(R.layout.location_select_alert);
        locationDialog.setTitle("Location Selection");
        locationDialog.setCancelable(false);
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
                //Toast.makeText(HomeActivity.this, "Location Selection Successful!", Toast.LENGTH_SHORT).show();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Menu");
                query.getInBackground("0toXQtPVHK", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, com.parse.ParseException e) {
                        if (e == null) {
                            // object will be your game score
                            String locationName = object.getString("locName");
                            locationTest.setText(locationName);

                        } else {
                            // something went wrong

                        }
                    }


                });
                Intent myIntent = new Intent(HomeActivity.this, MyTabActivity.class);
                startActivity(myIntent);
            }
        });

        Button orderDrinksButton = (Button)findViewById(R.id.button_orderDrinks);
        assert orderDrinksButton != null;
        orderDrinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeActivity.this, "Open OnTap Locations selector alert", Toast.LENGTH_SHORT).show();
                locationDialog.show();
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
