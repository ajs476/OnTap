package cs477.ontap;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UpdateBillingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_billing);
    }

    public void UpdateBillingInfo(View view){

        AlertDialog alertDialog = new AlertDialog.Builder(UpdateBillingActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Billing Info Successfully Updated");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
