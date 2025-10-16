package com.example.echoandlistapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.echoandlistapp.databinding.ActivitySpinnerEchoBinding;

public class SpinnerEchoActivity extends AppCompatActivity {
    private ActivitySpinnerEchoBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivitySpinnerEchoBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        String[] items = {"Palestine", "Jordan", "Egypt", "Saudi Arabia", "Morocco"};
        b.spinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, items));

        b.btnPrint.setOnClickListener(v -> {
            String choice = (String) b.spinner.getSelectedItem();
            b.tvOutput.setText("You selected: " + choice);
        });
    }
}
