package com.example.echoandlistapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton; // استخدام نوع الماتيريال باتن

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnText, btnSpinner, btnList, btnOpenNewScreen,
            btnRegister, btnRegisterLite, btnCustomForm, btnChat, btnLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
    }

    /**
     * ربط المتغيرات بعناصر الواجهة (View Binding)
     */
    private void initViews() {
        btnText             = findViewById(R.id.btnText);
        btnSpinner          = findViewById(R.id.btnSpinner);
        btnList             = findViewById(R.id.btnList);
        // تم تغيير الـ ID من btactvivty إلى btnOpenNewScreen
        btnOpenNewScreen    = findViewById(R.id.btnOpenNewScreen);
        btnRegister         = findViewById(R.id.btnRegister);
        btnRegisterLite     = findViewById(R.id.btnRegisterLite);
        btnCustomForm       = findViewById(R.id.btnCustomForm);
        btnChat             = findViewById(R.id.btnChat);
        btnLinks            = findViewById(R.id.btnLinks);
    }

    /**
     * تعيين مستمعي النقر لكل زر
     */
    private void setupListeners() {
        // المجموعة 1: الأمثلة البسيطة
        btnText.setOnClickListener(v -> startActivity(new Intent(this, TextEchoActivity.class)));
        btnSpinner.setOnClickListener(v -> startActivity(new Intent(this, SpinnerEchoActivity.class)));
        btnList.setOnClickListener(v -> startActivity(new Intent(this, ListNiceActivity.class)));

        // المجموعة 2: الفورمات والتسجيل
        btnRegister.setOnClickListener(v -> startActivity(new Intent(this, RegistrationFormActivity.class)));
        btnRegisterLite.setOnClickListener(v -> startActivity(new Intent(this, RegistrationLiteActivity.class)));
        btnCustomForm.setOnClickListener(v -> startActivity(new Intent(this, CustomFormActivity.class)));

        // المجموعة 3: الأنشطة الأخرى
        btnChat.setOnClickListener(v -> startActivity(new Intent(this, ChatActivity.class)));
        btnLinks.setOnClickListener(v -> startActivity(new Intent(this, LinksActivity.class)));

        // يجب استبدال Activity.class بالشاشة الجديدة التي تريد فتحها
        btnOpenNewScreen.setOnClickListener(v -> startActivity(new Intent(this, Activity.class)));
    }
}