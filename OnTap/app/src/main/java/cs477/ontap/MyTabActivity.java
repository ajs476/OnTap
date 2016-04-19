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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tab);

        final String userID = MainActivity.userID;

        final List<drinkObject> myTabOrder = HomeActivity.myTabOrder;
        final int orderSize = myTabOrder.size();

        final TextView statusTitleText = (TextView)findViewById(R.id.textView_statusTitle);
        final TextView orderStatusText = (TextView)findViewById(R.id.textView_orderStatus);
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

//        EditText item1Amount = (EditText)findViewById(R.id.editText_tabAmount1);
//        EditText item2Amount = (EditText)findViewById(R.id.editText_tabAmount2);
//        EditText item3Amount = (EditText)findViewById(R.id.editText_tabAmount3);
//        EditText item4Amount = (EditText)findViewById(R.id.editText_tabAmount4);
//        EditText item5Amount = (EditText)findViewById(R.id.editText_tabAmount5);
//        EditText item6Amount = (EditText)findViewById(R.id.editText_tabAmount6);
//        EditText item7Amount = (EditText)findViewById(R.id.editText_tabAmount7);
//        EditText item8Amount = (EditText)findViewById(R.id.editText_tabAmount8);
//        EditText item9Amount = (EditText)findViewById(R.id.editText_tabAmount9);
//        EditText item10Amount = (EditText)findViewById(R.id.editText_tabAmount10);
//        EditText item11Amount = (EditText)findViewById(R.id.editText_tabAmount11);
//        EditText item12Amount = (EditText)findViewById(R.id.editText_tabAmount12);

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

//        amountEditTextList.add(item1Amount);
//        amountEditTextList.add(item2Amount);
//        amountEditTextList.add(item3Amount);
//        amountEditTextList.add(item4Amount);
//        amountEditTextList.add(item5Amount);
//        amountEditTextList.add(item6Amount);
//        amountEditTextList.add(item7Amount);
//        amountEditTextList.add(item8Amount);
//        amountEditTextList.add(item9Amount);
//        amountEditTextList.add(item10Amount);
//        amountEditTextList.add(item11Amount);
//        amountEditTextList.add(item12Amount);

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

        final Dialog orderCompleteDialog = new Dialog(this);
        orderCompleteDialog.setContentView(R.layout.order_complete_alert);
        orderCompleteDialog.setTitle("Order Ready For Pickup!");
        orderCompleteDialog.setCancelable(false);
        Button okButton = (Button)orderCompleteDialog.findViewById(R.id.button_okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCompleteDialog.cancel();
                //Toast.makeText(MyTabActivity.this, "Open alc menu", Toast.LENGTH_SHORT).show();
//                Intent toAlcMenu = new Intent(MyTabActivity.this, AlcoholicMenuActivity.class);
//                startActivity(toAlcMenu);
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


        final Button placeOrderButton = (Button)findViewById(R.id.button_placeOrder);
        final Button addDrinkButton = (Button)findViewById(R.id.button_addItem);
        assert placeOrderButton != null;
        assert addDrinkButton != null;


        final Thread closeActivity = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
            }
        });

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                            if(!orderStatusText.getText().toString().equals("Complete")) {
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
                                                    if (objects.get(i).getString("userID").equals(userID) && objects.get(i).getInt("Status") == 1) {
                                                        orderStatusText.setText("Filling");
                                                        orderFound = true;
                                                    } else if (objects.get(i).getString("userID").equals(userID) && objects.get(i).getInt("Status") == 2) {
                                                        orderStatusText.setText("Complete");
                                                        orderFound = true;
                                                        orderCompleteDialog.show();
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

}
