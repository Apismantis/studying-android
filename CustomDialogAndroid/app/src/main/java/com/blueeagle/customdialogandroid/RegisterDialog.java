package com.blueeagle.customdialogandroid;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterDialog extends BaseDialog {

    private TextView tvDialogTitle;
    private EditText etFullName, etEmail, etPassword;
    private Button btnRegister;

    public RegisterDialog(final Context context, String title, View.OnClickListener callBackSubmit) {
        super(context);

        // set layout content
        setContentView(R.layout.layout_dialog_custom);

        tvDialogTitle = (TextView) findViewById(R.id.tvDialogTitle);
        etFullName = (EditText) findViewById(R.id.etFullName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        // set title
        tvDialogTitle.setText(title);

        // set click listener for register button
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(callBackSubmit);
    }

    public String getData() {
        return etFullName.getText() + " - " + etEmail.getText();
    }
}
