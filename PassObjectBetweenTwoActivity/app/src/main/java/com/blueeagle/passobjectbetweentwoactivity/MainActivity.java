package com.blueeagle.passobjectbetweentwoactivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etEmail, etBirthday, etCity;
    private Button btnPass1, btnPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init view
        initView();

        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etBirthday.setText(dayOfMonth + "-" + month + "-" + year);
                    }
                }, 2016, 10, 13).show();
            }
        });

        btnPass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passObject1();
            }
        });

        btnPass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passObject2();
            }
        });
    }

    public void initView() {
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etBirthday = (EditText) findViewById(R.id.etBirthday);
        etCity = (EditText) findViewById(R.id.etCity);

        btnPass1 = (Button) findViewById(R.id.btnPassObject1);
        btnPass2 = (Button) findViewById(R.id.btnPassObject2);
    }

    public Date getBirthday() {

        String birthday = etBirthday.getText().toString();

        if (birthday.equals(""))
            return null;

        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            return dateFormatter.parse(birthday);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUser() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        Date birthday = getBirthday();
        String city = etCity.getText().toString();

        if (name.equals("") || email.equals("") || birthday == null || city.equals(""))
            return null;

        return new User(name, email, birthday, city);
    }

    public User1 getUser1() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        Date birthday = getBirthday();
        String city = etCity.getText().toString();

        if (name.equals("") || email.equals("") || birthday == null || city.equals(""))
            return null;

        return new User1(name, email, birthday, city);
    }

    public void passObject1() {
        User user = getUser();

        if (user == null) {
            Toast.makeText(this, "Bạn chưa điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, Activity2.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void passObject2() {
        User1 user = getUser1();

        if (user == null) {
            Toast.makeText(this, "Bạn chưa điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, UsingParcelable.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
