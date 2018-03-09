package com.roommanagement;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.model.ChipInterface;
import com.roommanagement.Conts.Constants;
import com.roommanagement.Utils.ContactChip;

import java.util.ArrayList;
import java.util.List;

import static com.roommanagement.Conts.Constants.MY_PERMISSIONS_REQUEST_READ_CONTACTS;


public class RegisterScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner mUserType;
    private SpinnerAdapter adapter;
    private ArrayList<String> Userarraylist = new ArrayList<>();
    private ChipsInput chipsInput;
    private List<ContactChip> mContactList;
    private Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        init();
        mUserType = (Spinner) findViewById(R.id.usertype_spinner);
        Userarraylist.add("Room member");
        Userarraylist.add("Room admin");
        adapter = new SpinnerAdapter(RegisterScreenActivity.this, R.layout.spinner_layout, Userarraylist);

        // Set adapter to spinner
        mUserType.setAdapter(adapter);


        // Listener called when spinner item selected
        mUserType.setSelection(0);
        mUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



// build the ContactChip list
        mContactList = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //this code will be executed on devices running Lollipop or later
            checkReadContactPermission(this);
        } else {
            getContactList();
        }
        chipsInput.addChipsListener(new ChipsInput.ChipsListener() {
            @Override
            public void onChipAdded(ChipInterface chip, int newSize) {
                // chip added
                // newSize is the size of the updated selected chip list
            }

            @Override
            public void onChipRemoved(ChipInterface chip, int newSize) {
                // chip removed
                // newSize is the size of the updated selected chip list
            }

            @Override
            public void onTextChanged(CharSequence text) {
                // text changed
            }
        });
    }

    private void init() {
        mRegister = (Button) findViewById(R.id.bt_register);
        chipsInput = (ChipsInput) findViewById(R.id.chips_input);

        mRegister.setOnClickListener(this);
    }

    private void checkReadContactPermission(Activity mActivityContext) {
        if (ContextCompat.checkSelfPermission(mActivityContext,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivityContext,
                    Manifest.permission.READ_CONTACTS)) {
                getContactList();
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(mActivityContext,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            getContactList();
            // Permission has already been granted
        }
    }

    private void getContactList() {
        Cursor phones = this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        // loop over all contacts
        if (phones != null) {
            while (phones.moveToNext()) {
                // get contact info
                String phoneNumber = null;
                String id = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));
                String name = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String avatarUriString = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
                Uri avatarUri = null;
                if (avatarUriString != null)
                    avatarUri = Uri.parse(avatarUriString);

                // get phone number
                if (Integer.parseInt(phones.getString(phones.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    while (pCur != null && pCur.moveToNext()) {
                        phoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }

                    pCur.close();

                }

                ContactChip contactChip = new ContactChip(id, avatarUri, name, phoneNumber);
                // add contact to the list
                mContactList.add(contactChip);
            }
            phones.close();
        }

        // pass contact list to chips input
        chipsInput.setFilterableList(mContactList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                Intent dailyintent = new Intent(RegisterScreenActivity.this, MainActivity.class);
                startActivity(dailyintent);
                break;
        }
    }

    public class SpinnerAdapter extends ArrayAdapter<String> {
        ArrayList<String> arraylist;

        public SpinnerAdapter(RegisterScreenActivity context, int spinner_layout, ArrayList<String> userarraylist) {
            super(context, spinner_layout, userarraylist);
            this.arraylist = userarraylist;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            //LayoutInflater inflater=getActivity.getLayoutInflater();//for fragment
            convertView = inflater.inflate(R.layout.spinner_layout, parent, false);
            final TextView label = (TextView) convertView.findViewById(R.id.usertype);
            label.setText(arraylist.get(position));
            return convertView;
        }
    }

    public class UserTypeAdapter extends ArrayAdapter<String> {
        Context activity;
        private ArrayList data;
        LayoutInflater inflater;

        public UserTypeAdapter(@NonNull Context context, int resource, ArrayList<String> userarraylist) {
            super(context, resource);
            this.activity = context;
            this.data = userarraylist;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
            View row = inflater.inflate(R.layout.spinner_layout, parent, false);
            TextView usertype = (TextView) findViewById(R.id.usertype);
            String userdata = String.valueOf(data.get(position));
            usertype.setText(userdata);
            return row;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
