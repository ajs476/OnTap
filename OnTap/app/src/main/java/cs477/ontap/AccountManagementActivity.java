package cs477.ontap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AccountManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
    }

    public void AccountInfoClick(View view){
        Intent intent = new Intent(this, UpdateAccountActivity.class);
        startActivity(intent);
    }

    public void BillingInfoClick(View view){
        Intent intent = new Intent(this, UpdateBillingActivity.class);
        startActivity(intent);
    }

    public void DeleteClick(View view){
        Intent intent = new Intent(this, AccountDeletionActivity.class);
        startActivity(intent);
    }
}
