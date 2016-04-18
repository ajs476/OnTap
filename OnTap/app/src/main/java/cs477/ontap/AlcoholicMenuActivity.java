package cs477.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Cherie on 4/15/2016.
 */
public class AlcoholicMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alcoholic_menu);

        Button beerButton = (Button)findViewById(R.id.button_softDrinksButton);
        Button wineButton = (Button)findViewById(R.id.button_alcFreeCocktailButton);
        Button cocktailButton = (Button)findViewById(R.id.button_waterButton);

        assert wineButton != null;
        assert beerButton != null;
        assert cocktailButton != null;

        cocktailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AlcoholicMenuActivity.this, CocktailMenuActivity.class);
                startActivity(myIntent);
            }
        });
        beerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AlcoholicMenuActivity.this, BeersMenuActivity.class);
                startActivity(myIntent);
            }
        });
        wineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AlcoholicMenuActivity.this, WineMenuActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
