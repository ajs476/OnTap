package cs477.ontap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Alex on 4/15/2016.
 */
public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


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
                Toast.makeText(HomeActivity.this, "Location Selection Successful!", Toast.LENGTH_SHORT).show();
                //Intent myIntent = new Intent(MainActivity.this, HomeActivity.class);
                //startActivity(myIntent);
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
