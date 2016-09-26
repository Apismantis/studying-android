package com.blueeagle.passobjectbetweentwoactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class UsingParcelable extends AppCompatActivity {

    private EditText etName, etEmail, etBirthday, etCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_parcelable);

        initView();
        showUser();
    }

    private void showUser() {
        Intent intent = getIntent();
        User1 user = (User1) intent.getParcelableExtra("user");

        etName.setText(user.getName());
        etEmail.setText(user.getEmail());
        etBirthday.setText(user.getBirthday() + "");
        etCity.setText(user.getCity());
    }

    public void initView() {
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etBirthday = (EditText) findViewById(R.id.etBirthday);
        etCity = (EditText) findViewById(R.id.etCity);
    }
}
