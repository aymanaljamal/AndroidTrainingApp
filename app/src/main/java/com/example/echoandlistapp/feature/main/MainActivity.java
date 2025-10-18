package com.example.echoandlistapp.feature.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.example.echoandlistapp.feature.chat.ChatActivity2;
import com.example.echoandlistapp.feature.playground.Activity;
import com.example.echoandlistapp.feature.chat.ChatActivity;
import com.example.echoandlistapp.feature.forms.CustomFormActivity;
import com.example.echoandlistapp.feature.links.LinksActivity;
import com.example.echoandlistapp.feature.listnice.ListNiceActivity;
import com.example.echoandlistapp.R;
import com.example.echoandlistapp.feature.forms.RegistrationFormActivity;
import com.example.echoandlistapp.feature.forms.RegistrationLiteActivity;
import com.example.echoandlistapp.feature.echo.SpinnerEchoActivity;
import com.example.echoandlistapp.feature.echo.TextEchoActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnText, btnSpinner, btnList, btnOpenNewScreen,
            btnRegister, btnRegisterLite, btnCustomForm, btnChat, btnChat2,btnLinks;
    private NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupAnimations();
        setupListeners();
        setupBackPressed();
    }

    /**
     * ربط المتغيرات بعناصر الواجهة
     */
    private void initViews() {
        btnText = findViewById(R.id.btnText);
        btnSpinner = findViewById(R.id.btnSpinner);
        btnList = findViewById(R.id.btnList);
        btnOpenNewScreen = findViewById(R.id.btnOpenNewScreen);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegisterLite = findViewById(R.id.btnRegisterLite);
        btnCustomForm = findViewById(R.id.btnCustomForm);
        btnChat = findViewById(R.id.btnChat);
        btnChat2 = findViewById(R.id.btnChat2);
        btnLinks = findViewById(R.id.btnLinks);
        scrollView = findViewById(R.id.scrollView);
    }

    /**
     * إعداد الحركات والأنيميشن
     */
    private void setupAnimations() {
        try {
            if (scrollView != null) {
                LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(
                        this, R.anim.layout_animation_fall_down
                );
                scrollView.setLayoutAnimation(animation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * إعداد معالج زر الرجوع الحديث
     */
    private void setupBackPressed() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // الخروج من التطبيق عند الضغط على الرجوع في الشاشة الرئيسية
                if (isTaskRoot()) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
    }

    /**
     * تعيين مستمعي النقر لكل زر مع أنيميشن
     */
    private void setupListeners() {
        setupButtonWithAnimation(btnText, TextEchoActivity.class);
        setupButtonWithAnimation(btnSpinner, SpinnerEchoActivity.class);
        setupButtonWithAnimation(btnList, ListNiceActivity.class);
        setupButtonWithAnimation(btnRegister, RegistrationFormActivity.class);
        setupButtonWithAnimation(btnRegisterLite, RegistrationLiteActivity.class);
        setupButtonWithAnimation(btnCustomForm, CustomFormActivity.class);
        setupButtonWithAnimation(btnChat, ChatActivity.class);
        setupButtonWithAnimation(btnChat2, ChatActivity2.class);
        setupButtonWithAnimation(btnLinks, LinksActivity.class);
        setupButtonWithAnimation(btnOpenNewScreen, Activity.class);
    }

    /**
     * دالة مساعدة لإعداد الأزرار مع الأنيميشن
     */
    private void setupButtonWithAnimation(MaterialButton button, Class<?> targetActivity) {
        if (button == null) return;

        button.setOnClickListener(v -> {
            v.animate()
                    .scaleX(0.95f)
                    .scaleY(0.95f)
                    .setDuration(100)
                    .withEndAction(() -> {
                        v.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                                .start();

                        startActivity(new Intent(this, targetActivity));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    })
                    .start();
        });
    }

    /**
     * إضافة الأنيميشن عند إنهاء النشاط
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}