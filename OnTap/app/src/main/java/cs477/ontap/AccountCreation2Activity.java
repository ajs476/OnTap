package cs477.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;

/**
 * Created by Alex on 4/10/2016.
 */
public class AccountCreation2Activity  extends AppCompatActivity{

    public String cardNumString;
    public String cardExpString;
    public String cardCCVString;
    public String cardNameString;
    public String cardStreetString;
    public String cardCityString;
    public String cardStateString;
    public String cardZipString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation_2);

        final String emailString = AccountCreation1Activity.userEmailString;
        final String passwordString = AccountCreation1Activity.userPasswordString;

        final EditText cardNumberEditText = (EditText)findViewById(R.id.editText_cardNumber);
        final EditText cardExpEditText = (EditText)findViewById(R.id.editText_cardExp);
        final EditText cardCCVEditText = (EditText)findViewById(R.id.editText_cardSecurity);
        final EditText cardNameEditText = (EditText)findViewById(R.id.editText_cardName);
        final EditText cardStreetAddrEditText = (EditText)findViewById(R.id.editText_cardStreetAddr);
        final EditText cardCityAddrEditText = (EditText)findViewById(R.id.editText_cardCityAddr);
        final EditText cardStateAddrEditText = (EditText)findViewById(R.id.editText_cardStateAddr);
        final EditText cardZipEditText = (EditText)findViewById(R.id.editText_cardZipAddr);

        assert cardNumberEditText != null;
        assert cardExpEditText != null;
        assert cardCCVEditText != null;
        assert cardNameEditText != null;
        assert cardStreetAddrEditText != null;
        assert cardCityAddrEditText != null;
        assert cardStateAddrEditText != null;
        assert cardZipEditText != null;


        Button finishAccountCreationButton = (Button)findViewById(R.id.button_finishAccountCreation);
        assert finishAccountCreationButton != null;
        finishAccountCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNumString = cardNumberEditText.getText().toString();
                cardExpString = cardExpEditText.getText().toString();
                cardCCVString = cardCCVEditText.getText().toString();
                cardNameString = cardNameEditText.getText().toString();
                cardStreetString = cardStreetAddrEditText.getText().toString();
                cardCityString = cardCityAddrEditText.getText().toString();
                cardStateString = cardStateAddrEditText.getText().toString();
                cardZipString = cardZipEditText.getText().toString();

                ParseObject UserObject = new ParseObject("User");
                UserObject.put("email", emailString);
                UserObject.put("password", passwordString);
                UserObject.put("ccNum", cardNumString);
                UserObject.put("ccExpDate", cardExpString);
                UserObject.put("ccCCV", cardCCVString);
                UserObject.put("ccName", cardNameString);
                UserObject.put("ccAddress", (cardStreetString+","+cardCityString+","+cardStateString+","+cardZipString));
                UserObject.saveInBackground();

                Toast.makeText(AccountCreation2Activity.this, "Account Creation Success!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(AccountCreation2Activity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
