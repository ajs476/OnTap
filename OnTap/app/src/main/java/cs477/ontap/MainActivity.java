package cs477.ontap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button)findViewById(R.id.button_mainLogin);
        Button registerButton = (Button)findViewById(R.id.button_mainRegister);

        assert loginButton != null;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Login Dialogue Popup HERE", Toast.LENGTH_SHORT).show();
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
