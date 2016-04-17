package cs477.ontap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
    }

    public void AccountInfoClick(View v){
        Intent intent = new Intent(AccountManagementActivity.this, UpdateAccountActivity.class);
        startActivity(intent);
    }

    public void BillingInfoClick(View v){
        Intent intent = new Intent(AccountManagementActivity.this, UpdateBillingActivity.class);
        startActivity(intent);
    }

    public void DeleteClick(View v){
        Intent intent = new Intent(AccountManagementActivity.this, AccountDeletionActivity.class);
        startActivity(intent);
    }
}
