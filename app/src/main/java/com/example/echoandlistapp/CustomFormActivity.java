package com.example.echoandlistapp;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CustomFormActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirm;
    private RadioGroup rgGender;
    private Spinner spCity;
    private CheckBox cbTerms, cbSubscribe;
    private Button btnSubmit;
    private ImageButton btnPwdHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_form);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);
        rgGender = findViewById(R.id.rgGender);
        spCity = findViewById(R.id.spCity);
        cbTerms = findViewById(R.id.cbTerms);
        cbSubscribe = findViewById(R.id.cbSubscribe);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnPwdHint = findViewById(R.id.btnPwdHint);


        String[] cityNames = getResources().getStringArray(R.array.city_names);
        TypedArray cityIcons = getResources().obtainTypedArray(R.array.city_icons);
        CitiesAdapter adapter = new CitiesAdapter(this, cityNames, cityIcons);
        spCity.setAdapter(adapter);


        btnPwdHint.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.btn_pwd_hint))
                    .setMessage(getString(R.string.pwd_hint_text))
                    .setPositiveButton(getString(R.string.dialog_close), null)
                    .show();
        });


        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString();
            String conf = etConfirm.getText().toString();

            if (TextUtils.isEmpty(name)) { etName.setError(getString(R.string.hint_full_name)); return; }
            if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError(getString(R.string.hint_email)); return;
            }
            if (pass.length() < 6) { etPassword.setError(getString(R.string.hint_password)); return; }
            if (!pass.equals(conf)) { etConfirm.setError(getString(R.string.hint_confirm_password)); return; }
            if (rgGender.getCheckedRadioButtonId() == -1) {
                new AlertDialog.Builder(this).setMessage("Choose gender").setPositiveButton("OK", null).show();
                return;
            }
            if (!cbTerms.isChecked()) {
                new AlertDialog.Builder(this).setMessage("You must accept the Terms").setPositiveButton("OK", null).show();
                return;
            }

            String gender = (rgGender.getCheckedRadioButtonId() == R.id.rbMale) ? "Male" : "Female";
            String city = (String) spCity.getSelectedItem();
            boolean subscribed = cbSubscribe.isChecked();

            String info = "Name: " + name
                    + "\nEmail: " + email
                    + "\nGender: " + gender
                    + "\nCity: " + city
                    + "\nSubscribed: " + (subscribed ? "Yes" : "No");

            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.dialog_title_info))
                    .setMessage(info)
                    .setPositiveButton(getString(R.string.dialog_close), null)
                    .show();
        });
    }
}
