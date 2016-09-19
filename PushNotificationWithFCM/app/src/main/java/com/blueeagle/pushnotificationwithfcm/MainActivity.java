package com.blueeagle.pushnotificationwithfcm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "MainActivity";
    private String idToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvTokenString = (TextView) findViewById(R.id.tvTokenStr);
        Button btnGetTokenString = (Button) findViewById(R.id.btnGetTokenString);
        Button btnSubcribeTopic = (Button) findViewById(R.id.btnSubcribeTopic);
        Button btnUnsubcribeTopic = (Button) findViewById(R.id.btnUnsubcribeTopic);
        Button btnGetGroupTokenID = (Button) findViewById(R.id.btnGetGroupTokenID);
        Button btnAddMeToGroup = (Button) findViewById(R.id.btnAddMeToGroup);
        Button btnRemoveMeFromGroup = (Button) findViewById(R.id.btnRemoveMeFromGroup);


        // Set view listener
        btnGetTokenString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = FirebaseInstanceId.getInstance().getToken();
                tvTokenString.setText(token);
            }
        });

        btnSubcribeTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("news");
            }
        });

        btnUnsubcribeTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("news");
            }
        });

        btnGetGroupTokenID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGroupTokenID();
            }
        });

        btnAddMeToGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addMeToGroup();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        btnRemoveMeFromGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    removeMeFromGroup();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Get group id token from client
    public void getGroupTokenID() {
        MyAccountManager accountManager = new MyAccountManager(this);
        String gAccount = accountManager.getGoogleAccount();
        Log.d(TAG, "Google account: " + gAccount);

        final String scope = "audience:server:client_id:"
                + "889816407703-b2f1rvo9cgk8a2bkifl9umqb54f2vecv.apps.googleusercontent.com";

        try {
            idToken = GoogleAuthUtil.getToken(this, gAccount, scope);
            Toast.makeText(this, idToken, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.e(TAG, "Exception while getting idToken " + e);
        }
    }

    // Add a device to a group
    public void addMeToGroup() throws IOException, JSONException {

        // Get registration id
        String registrationIds = FirebaseInstanceId.getInstance().getToken();

        // Get notification key
        String notificationKey = MyHTTPRequest.sendAddSenderIDToGroupRequest("889816407703",
                "nguyenvantuan.itus@gmail.com", registrationIds, idToken);

        Toast.makeText(this, notificationKey, Toast.LENGTH_LONG).show();
        Log.d(TAG, "Notififcation key: " + notificationKey);
    }

    // Remove a device from a group
    public void removeMeFromGroup() throws IOException, JSONException {
        // Get registration id
        String registrationIds = FirebaseInstanceId.getInstance().getToken();

        // Get notifcation key
        String notificationKey = MyHTTPRequest.sendRemoveSenderIDToGroupRequest("889816407703",
                "nguyenvantuan.itus@gmail.com", registrationIds, idToken);

        Toast.makeText(this, notificationKey, Toast.LENGTH_LONG).show();
        Log.d(TAG, "Notififcation key: " + notificationKey);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkGooglePlayServiceAvailable();

        /*// Get register token => Error
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, token);
        Toast.makeText(this, token, Toast.LENGTH_LONG).show();*/
    }

    public boolean checkGooglePlayServiceAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        // Google play service is not available
        if(status != ConnectionResult.SUCCESS) {
            Log.e("Check GP Service", GooglePlayServicesUtil.getErrorString(status));

            // Show dialog to require user update Google play service
            GooglePlayServicesUtil.getErrorDialog(status, this, 1).show();
            return false;
        } else {
            Log.e("Check GP Service", GooglePlayServicesUtil.getErrorString(status));
            return true;
        }
    }
}
