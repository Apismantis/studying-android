package com.blueeagle.customdialogandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnOpenCustomDialog;
    private RegisterDialog registerDialog;

    private View.OnClickListener callBackSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String data = registerDialog.getData();
            Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
            registerDialog.dismiss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenCustomDialog = (Button) findViewById(R.id.btnOpenCustomDialog);
        btnOpenCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDialog = new RegisterDialog(MainActivity.this, "Register new account", callBackSubmit);
                registerDialog.show();
            }
        });
    }


}
