package cs477.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Alex on 4/10/2016.
 */
public class AccountCreation1Activity extends AppCompatActivity {

    public static String userPasswordString;
    public static String userEmailString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation_1);

        final EditText userPassword = (EditText)findViewById(R.id.editText_userPassword);
        final EditText userEmail = (EditText)findViewById(R.id.editText_userEmailAddress);

        Button continueButton = (Button)findViewById(R.id.button_continueToAccount2);
        assert continueButton != null;
        assert userPassword != null;
        assert userEmail != null;
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPasswordString = userPassword.getText().toString();
                userEmailString = userEmail.getText().toString();
//                ParseObject UserObject = new ParseObject("User");
//                UserObject.put("email", userEmailString);
//                UserObject.put("password", userPasswordString);
//                UserObject.saveInBackground();

                Intent myIntentToAccountCreation2 = new Intent(AccountCreation1Activity.this, AccountCreation2Activity.class);
                startActivity(myIntentToAccountCreation2);
            }
        });







    }
}
