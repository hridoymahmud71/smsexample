package hridoy.aiz.smsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etText;
    EditText etPhone;
    int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    String SENT = "SMS SENT";
    String DELIVERED = "SMS DELIVERED";
    PendingIntent sent_PI, delivered_PI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = (EditText) findViewById(R.id.etText);
        etPhone = (EditText) findViewById(R.id.etPhone);

        sent_PI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        delivered_PI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
    }


    public void btn_SMS_onclick(View v) {
        String text = etText.getText().toString();
        String phone = etPhone.getText().toString();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);

        } else {
            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage(phone,null,text,sent_PI,delivered_PI);
        }
    }
}
