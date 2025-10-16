package com.example.echoandlistapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class RegistrationFormActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirm;
    private RadioGroup rgGender;
    private Spinner spCity;
    private CheckBox cbTerms;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration_form);
        etName     = findViewById(R.id.etName);
        etEmail    = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirm  = findViewById(R.id.etConfirm);
        rgGender   = findViewById(R.id.rgGender);
        spCity     = findViewById(R.id.spCity);
        cbTerms    = findViewById(R.id.cbTerms);
        btnRegister= findViewById(R.id.btnRegister);


        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.city_icons, android.R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(adapter);

        btnRegister.setOnClickListener(v -> {
            String name  = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass  = etPassword.getText().toString();
            String conf  = etConfirm.getText().toString();

            if (TextUtils.isEmpty(name)) { etName.setError(getString(R.string.hint_full_name)); return; }
            if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError(getString(R.string.hint_email)); return;
            }
            if (pass.length() < 6) { etPassword.setError(getString(R.string.hint_password)); return; }
            if (!pass.equals(conf)) { etConfirm.setError(getString(R.string.hint_confirm_password)); return; }
            if (rgGender.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, getString(R.string.toast_gender), Toast.LENGTH_SHORT).show(); return;
            }
            if (!cbTerms.isChecked()) {
                Toast.makeText(this, getString(R.string.toast_terms), Toast.LENGTH_SHORT).show(); return;
            }

            String city = spCity.getSelectedItem().toString();
            Toast.makeText(this, getString(R.string.toast_registered, name, city), Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
