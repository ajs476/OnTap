package cs477.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Cherie on 4/15/2016.
 */
public class NonAlcoholicMenuActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_alcoholic_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final List<drinkObject> myTabOrder = HomeActivity.myTabOrder;


        Button softDrinkButton = (Button)findViewById(R.id.button_softDrinksButton);
        Button waterButton = (Button)findViewById(R.id.button_waterButton);
        Button alcFreeCocktailButton = (Button)findViewById(R.id.button_alcFreeCocktailButton);
        Button otherDrinkButton = (Button)findViewById(R.id.button_otherDrink);

        assert softDrinkButton != null;
        assert waterButton != null;
        assert alcFreeCocktailButton != null;
        assert otherDrinkButton != null;

        softDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NonAlcoholicMenuActivity.this, SoftDrinkMenuActivity.class);
                startActivity(myIntent);
            }
        });
        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NonAlcoholicMenuActivity.this, "WATERRRRR", Toast.LENGTH_SHORT).show();
                drinkObject newDrink = new drinkObject("Water","N","Beer",0);
                myTabOrder.add(newDrink);
                Intent myIntent = new Intent(NonAlcoholicMenuActivity.this, MyTabActivity.class);
                startActivity(myIntent);
            }
        });
        alcFreeCocktailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NonAlcoholicMenuActivity.this, AlcFreeCocktailMenuActivity.class);
                startActivity(myIntent);
            }
        });
        otherDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NonAlcoholicMenuActivity.this, OtherDrinkMenuActivity.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
