package cs477.ontap;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseObject;
import android.widget.Toast;

import bolts.Task;

public class MainActivity extends AppCompatActivity {

    public boolean dbInitialized = false;

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
                Intent myIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(myIntent);
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

    public void initParseDB(){
        if(!dbInitialized){
            Parse.initialize(this, "5kXSp6t4KzPgArEbUA16ZJflsknXoNnmoPkygySR", "yEFdo6cxbOYcmCcG7gouaXst7kNZwr9lH58rArTY");
            dbInitialized = true;
        }
    }
}
