package cs477.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Alex on 4/10/2016.
 */
public class AccountCreation2Activity  extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation_2);

        Button finishAccountCreationButton = (Button)findViewById(R.id.button_finishAccountCreation);
        assert finishAccountCreationButton != null;
        finishAccountCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountCreation2Activity.this, "Account Creation Success!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(AccountCreation2Activity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
