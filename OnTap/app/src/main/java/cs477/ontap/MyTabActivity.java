package cs477.ontap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Cherie on 4/15/2016.
 */
public class MyTabActivity extends AppCompatActivity {

    public int totalCost = 0;
    public CountDownTimer mytimer;
    public static String userOrderID = "";
    public Dialog orderCompleteDialog, tableServiceOrderCompleteDialog;
    public static boolean userFinishedOrder = false;
    public int userTableNumber = 0;
    public TextView orderStatusText;
    public ImageView xItem1,xItem2,xItem3,xItem4,xItem5,xItem6,xItem7,xItem8,xItem9,xItem10,xItem11,xItem12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tab);


        // initialize x images
        xItem1 = (ImageView)findViewById(R.id.imageView_xItem1);
        xItem2 = (ImageView)findViewById(R.id.imageView_xItem2);
        xItem3 = (ImageView)findViewById(R.id.imageView_xItem3);
        xItem4 = (ImageView)findViewById(R.id.imageView_xItem4);
        xItem5 = (ImageView)findViewById(R.id.imageView_xItem5);
        xItem6 = (ImageView)findViewById(R.id.imageView_xItem6);
        xItem7 = (ImageView)findViewById(R.id.imageView_xItem7);
        xItem8 = (ImageView)findViewById(R.id.imageView_xItem8);
        xItem9 = (ImageView)findViewById(R.id.imageView_xItem9);
        xItem10 = (ImageView)findViewById(R.id.imageView_xItem10);
        xItem11 = (ImageView)findViewById(R.id.imageView_xItem11);
        xItem12 = (ImageView)findViewById(R.id.imageView_xItem12);

        // initialize x images to be invisible initially
        xItem2.setVisibility(View.INVISIBLE);
        xItem3.setVisibility(View.INVISIBLE);
        xItem4.setVisibility(View.INVISIBLE);
        xItem5.setVisibility(View.INVISIBLE);
        xItem6.setVisibility(View.INVISIBLE);
        xItem7.setVisibility(View.INVISIBLE);
        xItem8.setVisibility(View.INVISIBLE);
        xItem9.setVisibility(View.INVISIBLE);
        xItem10.setVisibility(View.INVISIBLE);
        xItem11.setVisibility(View.INVISIBLE);
        xItem12.setVisibility(View.INVISIBLE);

        orderCompleteDialog = new Dialog(this);

        tableServiceOrderCompleteDialog = new Dialog(this);

        final String userID = MainActivity.userID;

        final List<drinkObject> myTabOrder = HomeActivity.myTabOrder;
        final int orderSize = myTabOrder.size();

        final TextView statusTitleText = (TextView)findViewById(R.id.textView_statusTitle);
        orderStatusText = (TextView)findViewById(R.id.textView_orderStatus);
        TextView orderTotalCostText = (TextView)findViewById(R.id.textView_orderTotalCost);

        assert statusTitleText != null;
        assert orderStatusText != null;
        statusTitleText.setVisibility(View.INVISIBLE);
        orderStatusText.setVisibility(View.INVISIBLE);




        ArrayList<EditText> editTextList = new ArrayList<EditText>();
        ArrayList<EditText> amountEditTextList = new ArrayList<EditText>();
        ArrayList<EditText> priceEditTextList = new ArrayList<EditText>();

        EditText item1Name = (EditText)findViewById(R.id.editText_tabItem1);
        EditText item2Name = (EditText)findViewById(R.id.editText_tabItem2);
        EditText item3Name = (EditText)findViewById(R.id.editText_tabItem3);
        EditText item4Name = (EditText)findViewById(R.id.editText_tabItem4);
        EditText item5Name = (EditText)findViewById(R.id.editText_tabItem5);
        EditText item6Name = (EditText)findViewById(R.id.editText_tabItem6);
        EditText item7Name = (EditText)findViewById(R.id.editText_tabItem7);
        EditText item8Name = (EditText)findViewById(R.id.editText_tabItem8);
        EditText item9Name = (EditText)findViewById(R.id.editText_tabItem9);
        EditText item10Name = (EditText)findViewById(R.id.editText_tabItem10);
        EditText item11Name = (EditText)findViewById(R.id.editText_tabItem11);
        EditText item12Name = (EditText)findViewById(R.id.editText_tabItem12);


        EditText item1Cost = (EditText)findViewById(R.id.editText_tabCost1);
        EditText item2Cost = (EditText)findViewById(R.id.editText_tabCost2);
        EditText item3Cost = (EditText)findViewById(R.id.editText_tabCost3);
        EditText item4Cost = (EditText)findViewById(R.id.editText_tabCost4);
        EditText item5Cost = (EditText)findViewById(R.id.editText_tabCost5);
        EditText item6Cost = (EditText)findViewById(R.id.editText_tabCost6);
        EditText item7Cost = (EditText)findViewById(R.id.editText_tabCost7);
        EditText item8Cost = (EditText)findViewById(R.id.editText_tabCost8);
        EditText item9Cost = (EditText)findViewById(R.id.editText_tabCost9);
        EditText item10Cost = (EditText)findViewById(R.id.editText_tabCost10);
        EditText item11Cost = (EditText)findViewById(R.id.editText_tabCost11);
        EditText item12Cost = (EditText)findViewById(R.id.editText_tabCost12);

        editTextList.add(item1Name);
        editTextList.add(item2Name);
        editTextList.add(item3Name);
        editTextList.add(item4Name);
        editTextList.add(item5Name);
        editTextList.add(item6Name);
        editTextList.add(item7Name);
        editTextList.add(item8Name);
        editTextList.add(item9Name);
        editTextList.add(item10Name);
        editTextList.add(item11Name);
        editTextList.add(item12Name);


        priceEditTextList.add(item1Cost);
        priceEditTextList.add(item2Cost);
        priceEditTextList.add(item3Cost);
        priceEditTextList.add(item4Cost);
        priceEditTextList.add(item5Cost);
        priceEditTextList.add(item6Cost);
        priceEditTextList.add(item7Cost);
        priceEditTextList.add(item8Cost);
        priceEditTextList.add(item9Cost);
        priceEditTextList.add(item10Cost);
        priceEditTextList.add(item11Cost);
        priceEditTextList.add(item12Cost);

        final Button placeOrderButton = (Button)findViewById(R.id.button_placeOrder);
        final Button addDrinkButton = (Button)findViewById(R.id.button_addItem);
        assert placeOrderButton != null;
        assert addDrinkButton != null;




        if(myTabOrder.size()>0){
            for(int i = 0; i<orderSize; i++){
                if(myTabOrder.get(i).getDrinkQuantity()>1){
                    editTextList.get(i).setText(myTabOrder.get(i).getDrinkName()+"      Qty: "+Integer.toString(myTabOrder.get(i).getDrinkQuantity()));
                }
                else{
                    editTextList.get(i).setText(myTabOrder.get(i).getDrinkName());
                }

                totalCost += myTabOrder.get(i).getDrinkPrice();

                //amountEditTextList.get(i).setText(Integer.toString(myTabOrder.get(i).getDrinkQuantity()));
                priceEditTextList.get(i).setText(Integer.toString(myTabOrder.get(i).getDrinkPrice()));
            }
            orderTotalCostText.setText("$ "+ Integer.toString(totalCost));
        }

        // check if there are items that need to be edited, display X
        if(!item2Name.getText().toString().equals("")){
            xItem2.setVisibility(View.VISIBLE);
        }
        if(!item3Name.getText().toString().equals("")){
            xItem3.setVisibility(View.VISIBLE);
        }
        if(!item4Name.getText().toString().equals("")){
            xItem4.setVisibility(View.VISIBLE);
        }
        if(!item5Name.getText().toString().equals("")){
            xItem5.setVisibility(View.VISIBLE);
        }
        if(!item6Name.getText().toString().equals("")){
            xItem6.setVisibility(View.VISIBLE);
        }
        if(!item7Name.getText().toString().equals("")){
            xItem7.setVisibility(View.VISIBLE);
        }
        if(!item8Name.getText().toString().equals("")){
            xItem8.setVisibility(View.VISIBLE);
        }
        if(!item9Name.getText().toString().equals("")){
            xItem9.setVisibility(View.VISIBLE);
        }
        if(!item10Name.getText().toString().equals("")){
            xItem10.setVisibility(View.VISIBLE);
        }
        if(!item11Name.getText().toString().equals("")){
            xItem11.setVisibility(View.VISIBLE);
        }
        if(!item12Name.getText().toString().equals("")){
            xItem12.setVisibility(View.VISIBLE);
        }



        setTitle(HomeActivity.currentLocationName + ": MyTab & Order Status");


        final Dialog tableSelectionDialog = new Dialog(this);
        tableSelectionDialog.setContentView(R.layout.table_selection_alert);
        tableSelectionDialog.setTitle("Table Selection");
        tableSelectionDialog.setCancelable(false);
        final EditText tableNumberText = (EditText)tableSelectionDialog.findViewById(R.id.editText_tableNumber);
        Button submitTableButton = (Button)tableSelectionDialog.findViewById(R.id.button_submitTableNumber);
        Button cancelButton = (Button)tableSelectionDialog.findViewById(R.id.button_cancelTableNumber);
        submitTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableSelectionDialog.cancel();
                userTableNumber = Integer.parseInt(tableNumberText.getText().toString());

                userFinishedOrder = false;

                statusTitleText.setVisibility(View.VISIBLE);
                orderStatusText.setVisibility(View.VISIBLE);
                Toast.makeText(MyTabActivity.this, "Order Placed Successfully!", Toast.LENGTH_SHORT).show();
                placeOrderButton.setVisibility(View.INVISIBLE);
                //addDrinkButton.setVisibility(View.INVISIBLE);

                ParseObject OrderObject = new ParseObject("Order");
                String orderString = "";
                for(int i = 0; i<myTabOrder.size(); i++){
                    orderString += (myTabOrder.get(i).getDrinkName()+"-"+myTabOrder.get(i).getDrinkQuantity()+"-");
                }
                OrderObject.put("Order", orderString);
                OrderObject.put("Status", 0);
                OrderObject.put("Table", userTableNumber);
                OrderObject.put("userID", userID);
                OrderObject.saveInBackground();



                //if(orderStatusText.getText().toString().equals("Processing")){
                mytimer = new CountDownTimer(180000, 5000) {

                    public void onTick(long millisUntilFinished) {
                        //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                        //here you can have your logic to set text to edittext

                        ParseQuery<ParseObject> orderIDQuery = ParseQuery.getQuery("Order");
                        orderIDQuery.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if(e == null){
                                    //Toast.makeText(MyTabActivity.this, "NO ORDERS FOUND FOR THIS USER", Toast.LENGTH_SHORT).show();
                                }
                                for (int i = 0; i<objects.size(); i++){
                                    if(objects.get(i).getString("userID").equals(userID) && objects.get(i).getInt("Status") == 0){
                                        userOrderID = (objects.get(i).getObjectId());
                                        createTableServiceOrderCompleteDialog();
                                    }
                                }
                            }
                        });

                        if (!orderStatusText.getText().toString().equals("Complete")) {
                            ParseQuery<ParseObject> orderQuery = ParseQuery.getQuery("Order");
                            orderQuery.findInBackground(new FindCallback<ParseObject>() {
                                public void done(List<ParseObject> objects, ParseException e) {
                                    if (e == null) {
                                        if (objects.size() == 0) {
                                            Toast.makeText(MyTabActivity.this, "Nothing", Toast.LENGTH_SHORT).show();
                                        } else {
                                            //Toast.makeText(MainActivity.this, objects.get(0).getString("email"), Toast.LENGTH_SHORT).show();
                                            boolean orderFound = false;
                                            for (int i = 0; i < objects.size(); i++) {
                                                if (objects.get(i).getObjectId().equals(userOrderID) && objects.get(i).getInt("Status") == 1) {
                                                    orderStatusText.setText("Filling");
                                                    orderFound = true;
                                                } else if (objects.get(i).getObjectId().equals(userOrderID) && objects.get(i).getInt("Status") == 2) {
                                                    orderStatusText.setText("Complete");
                                                    orderFound = true;
                                                    //orderStatusText.setText("");
                                                    tableServiceOrderCompleteDialog.show();
                                                    mytimer.cancel();

                                                }
                                            }
                                            if (!orderFound) {
                                                Toast.makeText(MyTabActivity.this, "Did not find order", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    } else {
                                        //objectRetrievalFailed();
                                        Toast.makeText(MyTabActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                    }

                    public void onFinish() {
                        //Toast.makeText(MyTabActivity.this, "DONE CHECKING SERVER", Toast.LENGTH_SHORT).show();
                    }

                }.start();

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableSelectionDialog.cancel();

            }
        });



        final Dialog tableServiceDialog = new Dialog(this);
        tableServiceDialog.setContentView(R.layout.order_type_alert);
        tableServiceDialog.setTitle("Order Type Selection");
        tableServiceDialog.setCancelable(false);
        Button yesTableServiceButton = (Button)tableServiceDialog.findViewById(R.id.button_yesTableService);
        Button noTableServiceButton = (Button)tableServiceDialog.findViewById(R.id.button_noTableService);
        yesTableServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableServiceDialog.cancel();
                tableSelectionDialog.show();

            }
        });
        noTableServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tableServiceDialog.cancel();

                userFinishedOrder = false;

                statusTitleText.setVisibility(View.VISIBLE);
                orderStatusText.setVisibility(View.VISIBLE);
                Toast.makeText(MyTabActivity.this, "Order Placed Successfully!", Toast.LENGTH_SHORT).show();
                placeOrderButton.setVisibility(View.INVISIBLE);
                //addDrinkButton.setVisibility(View.INVISIBLE);

                ParseObject OrderObject = new ParseObject("Order");
                String orderString = "";
                for(int i = 0; i<myTabOrder.size(); i++){
                    orderString += (myTabOrder.get(i).getDrinkName()+"-"+myTabOrder.get(i).getDrinkQuantity()+"-");
                }
                OrderObject.put("Order", orderString);
                OrderObject.put("Status", 0);
                OrderObject.put("Table", 0);
                OrderObject.put("userID", userID);
                OrderObject.saveInBackground();



                //if(orderStatusText.getText().toString().equals("Processing")){
                mytimer = new CountDownTimer(180000, 5000) {

                    public void onTick(long millisUntilFinished) {
                        //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                        //here you can have your logic to set text to edittext

                        ParseQuery<ParseObject> orderIDQuery = ParseQuery.getQuery("Order");
                        orderIDQuery.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if(e == null){
                                    //Toast.makeText(MyTabActivity.this, "NO ORDERS FOUND FOR THIS USER", Toast.LENGTH_SHORT).show();
                                }
                                for (int i = 0; i<objects.size(); i++) {
                                    if (objects.get(i).getString("userID").equals(userID) && objects.get(i).getInt("Status") == 0) {
                                        userOrderID = (objects.get(i).getObjectId());
                                        createOrderCompleteDialogue();
                                    }
                                }
                            }
                        });

                        if (!orderStatusText.getText().toString().equals("Complete")) {
                            ParseQuery<ParseObject> orderQuery = ParseQuery.getQuery("Order");
                            orderQuery.findInBackground(new FindCallback<ParseObject>() {
                                public void done(List<ParseObject> objects, ParseException e) {
                                    if (e == null) {
                                        if (objects.size() == 0) {
                                            Toast.makeText(MyTabActivity.this, "Nothing", Toast.LENGTH_SHORT).show();
                                        } else {

                                            boolean orderFound = false;
                                            for (int i = 0; i < objects.size(); i++) {
                                                if (objects.get(i).getObjectId().equals(userOrderID) && objects.get(i).getInt("Status") == 1) {
                                                    orderStatusText.setText("Filling");
                                                    orderFound = true;
                                                } else if (objects.get(i).getObjectId().equals(userOrderID) && objects.get(i).getInt("Status") == 2) {
                                                    orderStatusText.setText("Complete");
                                                    orderFound = true;
                                                    //orderStatusText.setText("");
                                                    orderCompleteDialog.show();
                                                    mytimer.cancel();

                                                }
                                            }
                                            if (!orderFound) {
                                                Toast.makeText(MyTabActivity.this, "Did not find order", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    } else {
                                        //objectRetrievalFailed();
                                        Toast.makeText(MyTabActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                    }

                    public void onFinish() {
                        //Toast.makeText(MyTabActivity.this, "DONE CHECKING SERVER", Toast.LENGTH_SHORT).show();
                    }

                }.start();

            }
        });




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

        if(myTabOrder.size() == 0){
            viewMenuDialog.show();
        }








        nonAlcMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMenuDialog.cancel();
                //Toast.makeText(MyTabActivity.this, "Open non-alc menu", Toast.LENGTH_SHORT).show();
                Intent toNonAlcMenu = new Intent(MyTabActivity.this, NonAlcoholicMenuActivity.class);
                startActivity(toNonAlcMenu);
            }
        });




        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableServiceDialog.show();
                }


            //}
        });

        addDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MyTabActivity.this, "Menu selection dialogue", Toast.LENGTH_SHORT).show();
                viewMenuDialog.show();
            }
        });



    }



    public void createOrderCompleteDialogue(){

        orderCompleteDialog.setContentView(R.layout.order_complete_alert);
        orderCompleteDialog.setTitle("Order Ready For Pickup!");
        EditText orderIDText = (EditText)orderCompleteDialog.findViewById(R.id.editText_orderID);
        orderIDText.setText(userOrderID);
        orderCompleteDialog.setCancelable(false);
        Button okButton = (Button)orderCompleteDialog.findViewById(R.id.button_okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCompleteDialog.cancel();
                userFinishedOrder = true;
                userOrderID = "";
                orderStatusText.setText("");
                Intent myIntent = new Intent(MyTabActivity.this, HomeActivity.class);
                startActivity(myIntent);

            }
        });
    }

    public void createTableServiceOrderCompleteDialog(){

        tableServiceOrderCompleteDialog.setContentView(R.layout.order_on_its_way_alert);
        tableServiceOrderCompleteDialog.setTitle("Order On Its Way!");
        tableServiceOrderCompleteDialog.setCancelable(false);
        Button okOrderOnItsWayButton = (Button)tableServiceOrderCompleteDialog.findViewById(R.id.button_okOrderOnItsWay);
        okOrderOnItsWayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableServiceOrderCompleteDialog.cancel();
                userFinishedOrder = true;
                userOrderID = "";
                orderStatusText.setText("");
                Intent myIntent = new Intent(MyTabActivity.this, HomeActivity.class);
                startActivity(myIntent);

            }
        });
    }

}
