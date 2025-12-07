
## 📁 **روابط المشاريع**
1. [app_two_activities](https://github.com/szainbzu/app_two_activities)
2. [RvViewVolly_v](https://github.com/szainbzu/RvViewVolly_v)
3. [sharedprefs2](https://github.com/szainbzu/sharedprefs2)
4. [gson_v](https://github.com/szainbzu/gson_v)



## 📄 **AndroidManifest.xml**
```xml
<manifest package="com.example.app"> <!-- 1️⃣ الحزمة -->
<uses-permission android:name="android.permission.INTERNET"/> <!-- 2️⃣ الإنترنت -->
<application android:label="@string/app_name"> <!-- 3️⃣ اسم التطبيق -->
<application android:icon="@mipmap/ic_launcher"> <!-- 4️⃣ الأيقونة -->
<activity android:name=".MainActivity"> <!-- 5️⃣ النشاط الرئيسي -->
<intent-filter> <!-- 6️⃣ نقطة الدخول -->
    <action android:name="android.intent.action.MAIN"/>
    <category android:name="android.intent.category.LAUNCHER"/>
</intent-filter>
<application android:theme="@style/AppTheme"> <!-- 7️⃣ السمات -->
<application android:allowBackup="true"> <!-- 8️⃣ النسخ الاحتياطي -->
<activity android:screenOrientation="portrait"> <!-- 9️⃣ اتجاه الشاشة -->
<application android:supportsRtl="true"> <!-- 🔟 دعم RTL -->
```



## 📐 **التخطيطات (Layouts)**

### **LinearLayout**
```xml
<LinearLayout
    android:layout_width="match_parent"    <!-- 1. العرض -->
    android:layout_height="match_parent"   <!-- 2. الارتفاع -->
    android:orientation="vertical"         <!-- 3. الاتجاه -->
    android:gravity="center"               <!-- 4. محاذاة المحتوى -->
    android:padding="16dp">                <!-- 5. الحشو الداخلي -->
```

### **ConstraintLayout**
```xml
<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <Button
        app:layout_constraintStart_toStartOf="parent"    <!-- 1. بداية إلى بداية -->
        app:layout_constraintEnd_toEndOf="parent"        <!-- 2. نهاية إلى نهاية -->
        app:layout_constraintTop_toTopOf="parent"        <!-- 3. أعلى إلى أعلى -->
        app:layout_constraintBottom_toBottomOf="parent"  <!-- 4. أسفل إلى أسفل -->
        app:layout_constraintHorizontal_bias="0.5"       <!-- 5. الانحياز الأفقي -->
        app:layout_constraintVertical_bias="0.5"         <!-- 6. الانحياز العمودي -->
        android:layout_margin="16dp"                     <!-- 7. الهامش -->
        android:layout_width="wrap_content"              <!-- 8. العرض -->
        android:layout_height="wrap_content"/>
</androidx.constraintlayout.widget.ConstraintLayout>
```



## 🧩 **عناصر الواجهة**

### **TextView**
```xml
<TextView
    android:id="@+id/textView"               <!-- 1. المعرف -->
    android:layout_width="wrap_content"      <!-- 2. العرض -->
    android:layout_height="wrap_content"     <!-- 3. الارتفاع -->
    android:text="@string/hello"             <!-- 4. النص -->
    android:textSize="16sp"                  <!-- 5. حجم النص -->
    android:textColor="@color/black"         <!-- 6. لون النص -->
    android:textStyle="bold"                 <!-- 7. نمط النص -->
    android:gravity="center"/>               <!-- 8. محاذاة -->
```

### **EditText**
```xml
<EditText
    android:id="@+id/editText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/enter_text"        <!-- 1. النص التوضيحي -->
    android:textSize="14sp"
    android:inputType="text"                 <!-- 2. نوع الإدخال -->
    android:maxLines="1"                     <!-- 3. الحد الأقصى للأسطر -->
    android:imeOptions="actionDone"          <!-- 4. خيارات الكيبورد -->
    android:background="@drawable/edit_bg"   <!-- 5. الخلفية -->
    android:padding="12dp"/>
```

### **أنواع الإدخال (Input Types)**
```xml
android:inputType="text"                     <!-- نص عادي -->
android:inputType="textPassword"             <!-- كلمة مرور -->
android:inputType="number"                   <!-- أرقام -->
android:inputType="phone"                    <!-- هاتف -->
android:inputType="textEmailAddress"         <!-- بريد إلكتروني -->
```

### **Button**
```xml
<Button
    android:id="@+id/button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/click_me"          <!-- نص الزر -->
    android:onClick="onButtonClick"          <!-- دالة عند الضغط -->
    android:backgroundTint="@color/primary"  <!-- لون الخلفية -->
    android:textAllCaps="false"/>            <!-- لا تجعل النص كبير -->
```

### **ImageView**
```xml
<ImageView
    android:id="@+id/imageView"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:src="@drawable/ic_launcher"      <!-- الصورة -->
    android:scaleType="centerCrop"           <!-- طريقة التحجيم -->
    android:background="@color/gray"         <!-- خلفية -->
    android:contentDescription="@string/image_desc"/> <!-- للقراءة -->
```

### **RecyclerView**
```xml
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scrollbars="vertical"            <!-- أشرطة التمرير -->
    android:clipToPadding="false"            <!-- لا تقطع الحشو -->
    app:layoutManager="LinearLayoutManager"/> <!-- مدير التخطيط -->
```

### **CardView**
```xml
<androidx.cardview.widget.CardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:cardCornerRadius="8dp"               <!-- زوايا دائرية -->
    app:cardElevation="4dp"                  <!-- الارتفاع -->
    app:cardUseCompatPadding="true"          <!-- استخدام حشو متوافق -->
    app:cardBackgroundColor="@color/white">  <!-- لون الخلفية -->
```

---

## 🎨 **ملفات الموارد**

### **styles.xml**
```xml
<style name="AppTheme" parent="Theme.MaterialComponents">
    <!-- 1️⃣ ألوان السمة -->
    <item name="colorPrimary">@color/purple_500</item>
    <item name="colorPrimaryVariant">@color/purple_700</item>
    <item name="colorSecondary">@color/teal_200</item>
    
    <!-- 2️⃣ خلفية -->
    <item name="android:windowBackground">@color/white</item>
    
    <!-- 3️⃣ شريط الحالة -->
    <item name="android:statusBarColor">?attr/colorPrimaryVariant</item>
    
    <!-- 4️⃣ النصوص -->
    <item name="android:textColor">@color/black</item>
    <item name="android:textSize">16sp</item>
    
    <!-- 5️⃣ شريط التنقل -->
    <item name="android:navigationBarColor">@color/white</item>
</style>
```

### **colors.xml**
```xml
<!-- ألوان Material Design الأساسية -->
<color name="purple_500">#FF6200EE</color>
<color name="purple_700">#FF3700B3</color>
<color name="teal_200">#FF03DAC5</color>
<color name="teal_700">#FF018786</color>

<!-- ألوان النصوص -->
<color name="black">#FF000000</color>
<color name="white">#FFFFFFFF</color>

<!-- ألوان الحالة -->
<color name="red">#FF0000</color>
<color name="green">#00FF00</color>
<color name="blue">#0000FF</color>
```

### **strings.xml**
```xml
<!-- النصوص الأساسية -->
<string name="app_name">تطبيقي</string>
<string name="hello">مرحباً</string>
<string name="submit">إرسال</string>
<string name="cancel">إلغاء</string>

<!-- الرسائل -->
<string name="loading">جاري التحميل...</string>
<string name="error">حدث خطأ</string>
<string name="success">تم بنجاح</string>

<!-- مع متغيرات -->
<string name="welcome">أهلاً %s</string>
<string name="count">لديك %d عنصر</string>
```

---

## 📋 **الخصائص الأساسية**

### **الأبعاد والهيكل**
- `android:layout_width` - العرض
- `android:layout_height` - الارتفاع
- `android:id` - المعرف
- `android:text` - النص
- `android:hint` - النص التوضيحي
- `android:src` - المصدر (للصور)
- `android:background` - الخلفية
- `android:padding` - الحشو الداخلي
- `android:layout_margin` - الهامش الخارجي
- `android:gravity` - محاذاة المحتوى

### **النصوص**
- `android:textSize` - حجم النص
- `android:textColor` - لون النص
- `android:textStyle` - نمط النص
- `android:inputType` - نوع الإدخال
- `android:maxLines` - الحد الأقصى للأسطر
- `android:ellipsize` - تقصير النص
- `android:textAllCaps` - كل الحروف كبيرة
- `android:singleLine` - سطر واحد

### **التخطيط والموضع**
- `android:orientation` - الاتجاه
- `android:layout_weight` - الوزن
- `app:layout_constraint...` - القيود
- `android:layout_gravity` - الجاذبية
- `android:layout_alignParent...` - محاذاة للأصل
- `android:layout_below` - تحت عنصر
- `android:layout_toRightOf` - يمين عنصر

### **الرؤية والمظهر**
- `android:visibility` - الرؤية
- `android:alpha` - الشفافية
- `android:elevation` - الارتفاع
- `android:rotation` - الدوران
- `android:scaleX` - المقياس الأفقي
- `android:scaleY` - المقياس العمودي
- `android:translationX` - التحريك الأفقي
- `android:translationY` - التحريك العمودي

### **التفاعل**
- `android:onClick` - عند النقر
- `android:clickable` - قابل للنقر
- `android:focusable` - قابل للتركيز
- `android:enabled` - مفعل
- `android:selected` - محدد
- `android:checked` - محدد (للـ CheckBox)
- `android:state_...` - حالات مختلفة

### **مساحات الأسماء**
- `xmlns:android` - مساحة أسماء Android
- `xmlns:app` - مساحة أسماء التطبيق
- `xmlns:tools` - مساحة أسماء الأدوات
- `tools:context` - السياق
- `tools:text` - نص للأدوات فقط
- `tools:ignore` - تجاهل التحذيرات
- `tools:showIn` - إظهار في
- `tools:layout_height` - ارتفاع للأدوات
- `tools:visibility` - رؤية للأدوات
- `tools:targetApi` - API المستهدف

---

## 📏 **الوحدات**
- **dp** → للأبعاد (العرض، الارتفاع، الهامش)
- **sp** → لحجم النص (تتكيف مع إعدادات المستخدم)
- **px** → بكسل (تجنب استخدامه)

---
