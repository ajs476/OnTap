package cs477.ontap;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLDisplay;

/**
 * Created by Alex on 4/15/2016.
 */
public class HomeActivity extends AppCompatActivity {

    public String currentLocationName;
    public static String currentLocationMenu;

    public static List<drinkObject> beerMenu;
    public static List<drinkObject> wineMenu;
    public static List<drinkObject> cocktailMenu;
    public static List<drinkObject> softDrinkMenu;
    public static List<drinkObject> alcFreeCocktailMenu;
    public static List<drinkObject> otherDrinkMenu;

    public static List<drinkObject> myTabOrder = new ArrayList<drinkObject>();

    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        Button viewOrderHistoryButton = (Button)findViewById(R.id.button_orderHistory);
        assert viewOrderHistoryButton != null;
        viewOrderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this, OrderHistoryActivity.class);
                startActivity(myIntent);
            }
        });



        final String userOrderIDString = MyTabActivity.userOrderID;

        final Dialog tippingDialog = new Dialog(this);
        tippingDialog.setContentView(R.layout.tip_alert);
        tippingDialog.setTitle("Enjoy your order!");
        final EditText tipAmountText = (EditText)tippingDialog.findViewById(R.id.editText_tipAmount);
        Button submitTipButton = (Button)tippingDialog.findViewById(R.id.button_submitTip);
        //tippingDialog.setCancelable(false);
        submitTipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tipAmount = 0;
                tipAmount = Integer.parseInt(tipAmountText.getText().toString());
                Toast.makeText(HomeActivity.this, "Tip successfully submitted!", Toast.LENGTH_SHORT).show();
                tippingDialog.dismiss();
                ParseObject TipObject = new ParseObject("Tips");
                TipObject.put("orderID", userOrderIDString);
                TipObject.put("tipAmount", tipAmount);
                TipObject.saveInBackground();
            }
        });

        final boolean userCompletedOrder = MyTabActivity.userFinishedOrder;
        if(userCompletedOrder){
            myTabOrder.clear();
            tippingDialog.show();


        }


        final EditText currentLocationEditText = (EditText)findViewById(R.id.editText_currentLocationName);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String locationName = preferences.getString("CurrentLocationName", "NONE");
        assert currentLocationEditText != null;
        if(locationName.equals("NONE")){
            currentLocationEditText.setText("None");
        }
        else{
            currentLocationEditText.setText(locationName);
        }



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
                editor.putString("CurrentLocationCode", locationCodeString);
                editor.apply();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Menu");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> menus, com.parse.ParseException e) {
                        if (e == null) {
                            if (menus.size() == 0) {
                                Toast.makeText(HomeActivity.this, "No Locations Available..", Toast.LENGTH_SHORT).show();
                            } else {

                                boolean locationFound = false;
                                for (int i = 0; i < menus.size(); i++) {
                                    if (menus.get(i).getString("locID").equals(preferences.getString("CurrentLocationCode", "NONE"))) {
                                        //Toast.makeText(MainActivity.this, userID, Toast.LENGTH_SHORT).show();
                                        Toast.makeText(HomeActivity.this, "Location Match!", Toast.LENGTH_SHORT).show();
                                        currentLocationName = menus.get(i).getString("locName");
                                        currentLocationMenu = menus.get(i).getString("menu");


                                        //Toast.makeText(HomeActivity.this, currentLocationMenu, Toast.LENGTH_SHORT).show();
                                        currentLocationEditText.setText(currentLocationName);
                                        editor.putString("CurrentLocationName", currentLocationName);
                                        editor.apply();
                                        locationFound = true;

                                        Intent myIntent = new Intent(HomeActivity.this, MyTabActivity.class);
                                        startActivity(myIntent);
                                    }
                                }

                                if (locationFound) {
                                    //String currentLocationMenuRaw = HomeActivity.currentLocationMenu;
                                    String[] splitMenu = currentLocationMenu.split("-");

                                    List<drinkObject> entireDrinkMenu = new ArrayList<drinkObject>();
                                    beerMenu = new ArrayList<drinkObject>();
                                    wineMenu = new ArrayList<drinkObject>();
                                    cocktailMenu = new ArrayList<drinkObject>();
                                    softDrinkMenu = new ArrayList<drinkObject>();
                                    alcFreeCocktailMenu = new ArrayList<drinkObject>();
                                    otherDrinkMenu = new ArrayList<drinkObject>();


                                    //String[] values = new String[] {"","","","","","","","","","","","","","","","","","","","","","",""};

                                    //Toast.makeText(BeersMenuActivity.this, splitMenu[0], Toast.LENGTH_SHORT).show();
                                    int k = 0;
                                    for (int j = 0; j < (splitMenu.length); j += 4) {


                                        String drinkName = splitMenu[j];
                                        //Toast.makeText(HomeActivity.this, drinkName, Toast.LENGTH_SHORT).show();
                                        String drinkAlcohol = splitMenu[j + 1];
                                        //Toast.makeText(HomeActivity.this, drinkAlcohol, Toast.LENGTH_SHORT).show();
                                        String drinkType = splitMenu[j + 2];
                                        //Toast.makeText(HomeActivity.this, drinkType, Toast.LENGTH_SHORT).show();
                                        String drinkPrice = splitMenu[j + 3];
                                        //Toast.makeText(HomeActivity.this, drinkPrice, Toast.LENGTH_SHORT).show();
                                        //values[k] = drinkName;
                                        //k++;

                                        // create entire list of all drink objects
                                        drinkObject newDrink = new drinkObject(drinkName, drinkAlcohol, drinkType, Integer.parseInt(drinkPrice));
                                        entireDrinkMenu.add(newDrink);


                                    }

                                    // loop through entire drink list and group drink types in their respective menu-lists
                                    for (int x = 0; x < entireDrinkMenu.size(); x++) {
                                        if (entireDrinkMenu.get(x).getDrinkType().equals("Beer")) {
                                            beerMenu.add(entireDrinkMenu.get(x));
                                        }
                                        if (entireDrinkMenu.get(x).getDrinkType().equals("Wine")) {
                                            wineMenu.add(entireDrinkMenu.get(x));
                                        }
                                        if (entireDrinkMenu.get(x).getDrinkType().equals("Cocktail")) {
                                            cocktailMenu.add(entireDrinkMenu.get(x));
                                        }
                                        if (entireDrinkMenu.get(x).getDrinkType().equals("Soft Drink")) {
                                            softDrinkMenu.add(entireDrinkMenu.get(x));
                                        }
                                        if (entireDrinkMenu.get(x).getDrinkType().equals("AlcFreeCocktail")) {
                                            alcFreeCocktailMenu.add(entireDrinkMenu.get(x));
                                        }
                                        if (entireDrinkMenu.get(x).getDrinkType().equals("Other")) {
                                            otherDrinkMenu.add(entireDrinkMenu.get(x));
                                        }
                                    }
                                }


                                if (!locationFound) {
                                    Toast.makeText(HomeActivity.this, "Location code not valid/found. Try again.", Toast.LENGTH_SHORT).show();
                                    editor.putString("CurrentLocationCode", "NONE");
                                    editor.apply();
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

        final Button changeLocationButton = (Button)findViewById(R.id.button_changeLocation);
        assert changeLocationButton != null;
        changeLocationButton.setVisibility(View.INVISIBLE);
        if(!preferences.getString("CurrentLocationCode","NONE").equals("NONE")){
            changeLocationButton.setVisibility(View.VISIBLE);
        }
        changeLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("CurrentLocationCode","NONE");
                editor.apply();
                editor.putString("CurrentLocationName","NONE");
                editor.apply();
                changeLocationButton.setVisibility(View.INVISIBLE);
                currentLocationEditText.setText("None");
                locationDialog.show();
            }
        });

        Button orderDrinksButton = (Button)findViewById(R.id.button_orderDrinks);
        assert orderDrinksButton != null;
        orderDrinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeActivity.this, "Open OnTap Locations selector alert", Toast.LENGTH_SHORT).show();
                if(preferences.getString("CurrentLocationCode","NONE").equals("NONE")){
                    locationDialog.show();
                }
                else{
                    //Toast.makeText(HomeActivity.this, preferences.getString("CurrentLocationCode","NONE"), Toast.LENGTH_SHORT).show();
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Menu");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> menus, com.parse.ParseException e) {
                            if (e == null) {
                                if (menus.size() == 0) {
                                    Toast.makeText(HomeActivity.this, "No Locations Available..", Toast.LENGTH_SHORT).show();
                                } else {

                                    boolean locationFound = false;
                                    for (int i = 0; i < menus.size(); i++) {
                                        if (menus.get(i).getString("locID").equals(preferences.getString("CurrentLocationCode", "NONE"))) {
                                            //Toast.makeText(MainActivity.this, userID, Toast.LENGTH_SHORT).show();
                                            Toast.makeText(HomeActivity.this, "Location Match!", Toast.LENGTH_SHORT).show();
                                            currentLocationName = menus.get(i).getString("locName");
                                            currentLocationMenu = menus.get(i).getString("menu");


                                            //Toast.makeText(HomeActivity.this, currentLocationMenu, Toast.LENGTH_SHORT).show();
                                            currentLocationEditText.setText(currentLocationName);
                                            editor.putString("CurrentLocationName", currentLocationName);
                                            editor.apply();
                                            locationFound = true;

                                            Intent myIntent = new Intent(HomeActivity.this, MyTabActivity.class);
                                            startActivity(myIntent);
                                        }
                                    }

                                    if (locationFound) {
                                        //String currentLocationMenuRaw = HomeActivity.currentLocationMenu;
                                        String[] splitMenu = currentLocationMenu.split("-");

                                        List<drinkObject> entireDrinkMenu = new ArrayList<drinkObject>();
                                        beerMenu = new ArrayList<drinkObject>();
                                        wineMenu = new ArrayList<drinkObject>();
                                        cocktailMenu = new ArrayList<drinkObject>();
                                        softDrinkMenu = new ArrayList<drinkObject>();
                                        alcFreeCocktailMenu = new ArrayList<drinkObject>();
                                        otherDrinkMenu = new ArrayList<drinkObject>();


                                        //String[] values = new String[] {"","","","","","","","","","","","","","","","","","","","","","",""};

                                        //Toast.makeText(BeersMenuActivity.this, splitMenu[0], Toast.LENGTH_SHORT).show();
                                        int k = 0;
                                        for (int j = 0; j < (splitMenu.length); j += 4) {


                                            String drinkName = splitMenu[j];
                                            //Toast.makeText(HomeActivity.this, drinkName, Toast.LENGTH_SHORT).show();
                                            String drinkAlcohol = splitMenu[j + 1];
                                            //Toast.makeText(HomeActivity.this, drinkAlcohol, Toast.LENGTH_SHORT).show();
                                            String drinkType = splitMenu[j + 2];
                                            //Toast.makeText(HomeActivity.this, drinkType, Toast.LENGTH_SHORT).show();
                                            String drinkPrice = splitMenu[j + 3];
                                            //Toast.makeText(HomeActivity.this, drinkPrice, Toast.LENGTH_SHORT).show();
                                            //values[k] = drinkName;
                                            //k++;

                                            // create entire list of all drink objects
                                            drinkObject newDrink = new drinkObject(drinkName, drinkAlcohol, drinkType, Integer.parseInt(drinkPrice));
                                            entireDrinkMenu.add(newDrink);


                                        }

                                        // loop through entire drink list and group drink types in their respective menu-lists
                                        for (int x = 0; x < entireDrinkMenu.size(); x++) {
                                            if (entireDrinkMenu.get(x).getDrinkType().equals("Beer")) {
                                                beerMenu.add(entireDrinkMenu.get(x));
                                            }
                                            if (entireDrinkMenu.get(x).getDrinkType().equals("Wine")) {
                                                wineMenu.add(entireDrinkMenu.get(x));
                                            }
                                            if (entireDrinkMenu.get(x).getDrinkType().equals("Cocktail")) {
                                                cocktailMenu.add(entireDrinkMenu.get(x));
                                            }
                                            if (entireDrinkMenu.get(x).getDrinkType().equals("Soft Drink")) {
                                                softDrinkMenu.add(entireDrinkMenu.get(x));
                                            }
                                            if (entireDrinkMenu.get(x).getDrinkType().equals("AlcFreeCocktail")) {
                                                alcFreeCocktailMenu.add(entireDrinkMenu.get(x));
                                            }
                                            if (entireDrinkMenu.get(x).getDrinkType().equals("Other")) {
                                                otherDrinkMenu.add(entireDrinkMenu.get(x));
                                            }
                                        }
                                    }


                                    if (!locationFound) {
                                        Toast.makeText(HomeActivity.this, "Location code not valid/found. Try again.", Toast.LENGTH_SHORT).show();
                                        editor.putString("CurrentLocationCode", "NONE");
                                        editor.apply();
                                    }
                                }

                            } else {
                                //objectRetrievalFailed();
                                Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

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
