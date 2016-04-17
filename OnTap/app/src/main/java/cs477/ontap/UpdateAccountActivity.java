package cs477.ontap;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UpdateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
    }

    public void ChangePassword(View view){

        AlertDialog alertDialog = new AlertDialog.Builder(UpdateAccountActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Your Password Has Been Changed");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    public void UpdateEmail (View view){

        AlertDialog alertDialog = new AlertDialog.Builder(UpdateAccountActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Your Email Has Been Updated ");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
