package cs477.ontap;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import bolts.Task;

public class MainActivity extends AppCompatActivity {

    public boolean dbInitialized = false;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       try{
           Parse.initialize(this, "5kXSp6t4KzPgArEbUA16ZJflsknXoNnmoPkygySR", "yEFdo6cxbOYcmCcG7gouaXst7kNZwr9lH58rArTY");
       }
       catch (IllegalStateException e)
       {

       }





        final Dialog loginDialog = new Dialog(this);
        loginDialog.setContentView(R.layout.login_alert);
        loginDialog.setTitle("Account Login");
        loginDialog.setCancelable(false);
        final EditText emailLogin = (EditText)loginDialog.findViewById(R.id.editText_emailLogin);
        final EditText passwordLogin = (EditText)loginDialog.findViewById(R.id.editText_passwordLogin);
        Button cancelLoginAlertButton = (Button)loginDialog.findViewById(R.id.button_loginCancel);
        cancelLoginAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog.cancel();
            }
        });
        Button loginAlertButton = (Button)loginDialog.findViewById(R.id.button_loginAlert);
        assert loginAlertButton != null;
        loginAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                final String emailLoginString = emailLogin.getText().toString();
                final String emailPasswordString = passwordLogin.getText().toString();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() == 0) {
                                Toast.makeText(MainActivity.this, "Nothing", Toast.LENGTH_SHORT).show();
                            } else {
                                //Toast.makeText(MainActivity.this, objects.get(0).getString("email"), Toast.LENGTH_SHORT).show();
                                boolean accountFound = false;
                                for (int i = 0; i < objects.size(); i++) {
                                    if (objects.get(i).getString("email").equals(emailLoginString) && objects.get(i).getString("password").equals(emailPasswordString)) {
                                        userID = objects.get(i).getObjectId();
                                        //Toast.makeText(MainActivity.this, userID, Toast.LENGTH_SHORT).show();
                                        Toast.makeText(MainActivity.this, "Account Match!", Toast.LENGTH_SHORT).show();
                                        accountFound = true;

                                        Intent myIntent = new Intent(MainActivity.this, HomeActivity.class);
                                        startActivity(myIntent);
                                    }
                                }
                                if (!accountFound) {
                                    Toast.makeText(MainActivity.this, "Did not find account", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } else {
                            //objectRetrievalFailed();
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });

        Button loginButton = (Button)findViewById(R.id.button_mainLogin);
        Button registerButton = (Button)findViewById(R.id.button_mainRegister);

        assert loginButton != null;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Login Dialogue Popup HERE", Toast.LENGTH_SHORT).show();
                loginDialog.show();
            }
        });

        assert registerButton != null;
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "GOTO account creation page 1", Toast.LENGTH_SHORT).show();
                Intent myIntentToAccountCreation1 = new Intent(MainActivity.this, AccountCreation1Activity.class);
                startActivity(myIntentToAccountCreation1);
            }
        });


    }



}
