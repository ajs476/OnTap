package cs477.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Alex on 4/10/2016.
 */
public class AccountCreation1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation_1);

        Button continueButton = (Button)findViewById(R.id.button_continueToAccount2);
        assert continueButton != null;
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntentToAccountCreation2 = new Intent(AccountCreation1Activity.this, AccountCreation2Activity.class);
                startActivity(myIntentToAccountCreation2);
            }
        });

    }
}
