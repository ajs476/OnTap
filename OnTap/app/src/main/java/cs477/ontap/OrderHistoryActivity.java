package cs477.ontap;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 5/1/2016.
 */
public class OrderHistoryActivity extends AppCompatActivity {

    public String[] values;
    public ListView orderHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String userID = MainActivity.userID;

        final List<String> orderList = new ArrayList<>();



        orderHistoryList = (ListView)findViewById(R.id.listView_orderHistory);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Order");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> orders, com.parse.ParseException e) {
                if (e == null) {
                    if (orders.size() == 0) {
                        Toast.makeText(OrderHistoryActivity.this, "No Orders Found..", Toast.LENGTH_SHORT).show();
                    } else {
                        //boolean orderFound = false;
                        for (int i = 0; i < orders.size(); i++) {
                            if (orders.get(i).getString("userID").equals(userID)) {
                                String venueName = orders.get(i).getString("venueName");
                                int orderPrice = orders.get(i).getInt("orderPrice");
                                String orderDate = orders.get(i).getCreatedAt().toString();
                                String listEntry = "Location: "+venueName + "\nAmount: $" + Integer.toString(orderPrice)+ "\nDate: "+orderDate;
                                orderList.add(listEntry);

                            }
                        }

                        int orderListLength = orderList.size();

                        values = new String[orderListLength];
                        int j = orderListLength-1;
                        for (int i = 0; i < orderListLength; i++) {
                            values[i] = orderList.get(j);
                            j--;
                        }

                        doStuff();
                    }
                }
            }

        });




        //String[] values2 = new String[]{"asdf","asdf"};
        //Toast.makeText(OrderHistoryActivity.this, "orderList size: "+orderList.size(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(OrderHistoryActivity.this, userID, Toast.LENGTH_SHORT).show();






    }

    public void doStuff(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        assert orderHistoryList != null;
        orderHistoryList.setAdapter(adapter);

        orderHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) orderHistoryList.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

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

