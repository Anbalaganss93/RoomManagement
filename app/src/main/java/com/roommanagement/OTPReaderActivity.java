package com.roommanagement;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hololo.library.otpview.OTPListener;
import com.hololo.library.otpview.OTPView;

import java.util.ArrayList;
import java.util.List;

public class OTPReaderActivity extends AppCompatActivity {

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private OTPView otpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpreader);
        init();
        if (checkAndRequestPermissions()) {
            // carry on the normal flow, as the case of  permissions  granted.
        }
    }

    private void init() {
        otpView = (OTPView) findViewById(R.id.readotp);
        otpView.setListener(new OTPListener() {
            @Override
            public void otpFinished(String otp) {
                Toast.makeText(OTPReaderActivity.this, "OTP finished, the otp is " + otp, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);

        int receiveSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS);

        int readSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_MMS);
        }
        if (readSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}
