package com.example.echoandlistapp.feature.echo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.echoandlistapp.databinding.ActivityTextEchoBinding;

public class TextEchoActivity extends AppCompatActivity {
    private ActivityTextEchoBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityTextEchoBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.btnPrint.setOnClickListener(v -> {
            String txt = b.etInput.getText() == null ? "" : b.etInput.getText().toString().trim();
            b.tvOutput.setText(txt.isEmpty() ? "Type something then press Print" : txt);
        });
    }
}
