package com.example.echoandlistapp.feature.forms;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.echoandlistapp.R;

public class RegistrationLiteActivity extends AppCompatActivity {
    private EditText etNameLite, etEmailLite;
    private Button btnSaveLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_lite);

        etNameLite  = findViewById(R.id.etNameLite);
        etEmailLite = findViewById(R.id.etEmailLite);
        btnSaveLite = findViewById(R.id.btnSaveLite);

        btnSaveLite.setOnClickListener(v -> {
            String n = etNameLite.getText().toString().trim();
            String e = etEmailLite.getText().toString().trim();

            if (n.isEmpty()) { etNameLite.setError("Required"); return; }
            if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()) { etEmailLite.setError("Invalid"); return; }

            Toast.makeText(this, "Saved: " + n + " <" + e + ">", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
