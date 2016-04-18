package cs477.ontap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Cherie on 4/15/2016.
 */
public class MyTabActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tab);

        setTitle(HomeActivity.currentLocationName + ": MyTab & Order Status");

        final Dialog viewMenuDialog = new Dialog(this);
        viewMenuDialog.setContentView(R.layout.drink_type_alert);
        viewMenuDialog.setTitle("Menu Selection");
        viewMenuDialog.setCancelable(false);
        Button alcMenuButton = (Button)viewMenuDialog.findViewById(R.id.button_alcoholicMenu);
        Button nonAlcMenuButton = (Button)viewMenuDialog.findViewById(R.id.button_nonAlcoholicMenu);
        alcMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMenuDialog.cancel();
                //Toast.makeText(MyTabActivity.this, "Open alc menu", Toast.LENGTH_SHORT).show();
                Intent toAlcMenu = new Intent(MyTabActivity.this, AlcoholicMenuActivity.class);
                startActivity(toAlcMenu);
            }
        });
        nonAlcMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMenuDialog.cancel();
                //Toast.makeText(MyTabActivity.this, "Open non-alc menu", Toast.LENGTH_SHORT).show();
                Intent toNonAlcMenu = new Intent(MyTabActivity.this, NonAlcoholicMenuActivity.class);
                startActivity(toNonAlcMenu);
            }
        });


        Button placeOrderButton = (Button)findViewById(R.id.button_placeOrder);
        Button addDrinkButton = (Button)findViewById(R.id.button_addItem);

        assert addDrinkButton != null;
        addDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyTabActivity.this, "Menu selection dialogue", Toast.LENGTH_SHORT).show();
                viewMenuDialog.show();
            }
        });

        assert placeOrderButton != null;
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyTabActivity.this, "Place order dialogue", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
