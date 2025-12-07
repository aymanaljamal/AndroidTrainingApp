

````markdown
# 📚 Android XML Reference — ملخص شامل

ملف مرجعي مرتب يحتوي على أهم خصائص Android XML، القيم الافتراضية، أمثلة جاهزة، وروابط مشاريع.


## 📁 روابط المشاريع
- https://github.com/szainbzu/app_two_activities  
- https://github.com/szainbzu/RvViewVolly_v  
- https://github.com/szainbzu/sharedprefs2  
- https://github.com/szainbzu/gson_v  



# 📄 AndroidManifest.xml

<manifest package="com.example.app"> <!-- 1️⃣ الحزمة -->
    <uses-permission android:name="android.permission.INTERNET"/> <!-- 2️⃣ الإنترنت -->
    
    <application
        android:label="@string/app_name"         <!-- 3️⃣ اسم التطبيق -->
        android:icon="@mipmap/ic_launcher"        <!-- 4️⃣ الأيقونة -->
        android:theme="@style/AppTheme"           <!-- 7️⃣ السمة -->
        android:allowBackup="true"                <!-- 8️⃣ النسخ الاحتياطي -->
        android:supportsRtl="true">               <!-- 🔟 دعم RTL -->
        
        <activity
            android:name=".MainActivity"
            android:screenOrientation="portrait"> <!-- 9️⃣ اتجاه الشاشة -->
            
            <intent-filter> <!-- 6️⃣ نقطة الدخول -->
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
    </application>
</manifest>
````



# 📐 Layouts

## **LinearLayout**


<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="16dp">
```

## **ConstraintLayout**


<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintVertical_bias="0.5"/>
</androidx.constraintlayout.widget.ConstraintLayout>
```



# 🧩 عناصر الواجهة (UI Widgets)

## **TextView**


<TextView
    android:id="@+id/textView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/hello"
    android:textSize="16sp"
    android:textColor="@color/black"
    android:textStyle="bold"
    android:gravity="center"/>
```

## **EditText**


<EditText
    android:id="@+id/editText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/enter_text"
    android:textSize="14sp"
    android:inputType="text"
    android:maxLines="1"
    android:imeOptions="actionDone"
    android:background="@drawable/edit_bg"
    android:padding="12dp"/>
```

### **أنواع الإدخال**


android:inputType="text"
android:inputType="textPassword"
android:inputType="number"
android:inputType="phone"
android:inputType="textEmailAddress"
```

## **Button**


<Button
    android:id="@+id/button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/click_me"
    android:onClick="onButtonClick"
    android:backgroundTint="@color/primary"
    android:textAllCaps="false"/>
```

## **ImageView**


<ImageView
    android:id="@+id/imageView"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:src="@drawable/ic_launcher"
    android:scaleType="centerCrop"
    android:background="@color/gray"
    android:contentDescription="@string/image_desc"/>
```

## **RecyclerView**

<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scrollbars="vertical"
    android:clipToPadding="false"
    app:layoutManager="LinearLayoutManager"/>
```

## **CardView**

<androidx.cardview.widget.CardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:cardCornerRadius="8dp"
    app:cardElevation="4dp"
    app:cardUseCompatPadding="true"
    app:cardBackgroundColor="@color/white">
```

---

# 🎨 ملفات الموارد

## **styles.xml**


<style name="AppTheme" parent="Theme.MaterialComponents">
    <item name="colorPrimary">@color/purple_500</item>
    <item name="colorPrimaryVariant">@color/purple_700</item>
    <item name="colorSecondary">@color/teal_200</item>
    <item name="android:windowBackground">@color/white</item>
    <item name="android:statusBarColor">?attr/colorPrimaryVariant</item>
    <item name="android:textColor">@color/black</item>
    <item name="android:textSize">16sp</item>
</style>
```

## **colors.xml**


<color name="purple_500">#FF6200EE</color>
<color name="purple_700">#FF3700B3</color>
<color name="teal_200">#FF03DAC5</color>
<color name="black">#FF000000</color>
<color name="white">#FFFFFFFF</color>
```

## **strings.xml**


<string name="app_name">تطبيقي</string>
<string name="hello">مرحباً</string>
<string name="submit">إرسال</string>
<string name="loading">جاري التحميل...</string>
<string name="welcome">أهلاً %s</string>
```

---

# 📋 الخصائص الأساسية

## الأبعاد

* `android:layout_width`
* `android:layout_height`
* `android:layout_margin`
* `android:padding`

## النصوص

* `android:textSize`
* `android:textColor`
* `android:textStyle`
* `android:inputType`
* `android:maxLines`

## التفاعل

* `android:onClick`
* `android:clickable`
* `android:focusable`
* `android:enabled`

## الصور

* `android:src`
* `android:scaleType`
* `android:adjustViewBounds`



# 📱 القيم الافتراضية في Android XML

## أهم القيم الافتراضية

| الخاصية       | القيمة الافتراضية    |
| ------------- | -------------------- |
| layout_width  | wrap_content         |
| layout_height | wrap_content         |
| visibility    | visible              |
| textSize      | 14sp                 |
| textColor     | @android:color/black |
| textStyle     | normal               |
| inputType     | text                 |
| scaleType     | fitCenter            |
| clickable     | false                |
| enabled       | true                 |
| singleLine    | false                |

---

# 🔧 أمثلة على القيم الافتراضية

## TextView


<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textSize="14sp"
    android:textColor="@android:color/black"
    android:textStyle="normal"
    android:visibility="visible"/>
```

## EditText


<EditText
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:inputType="text"
    android:maxLines="999999"
    android:enabled="true"/>
```

## Button


<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textAllCaps="true"/>
```


# 📏 الوحدات

* **dp** → للأبعاد
* **sp** → للنصوص
* **px** → غير مفضل




