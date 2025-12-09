# 📱 دليل شامل: Android UI Components & Data Persistence

> دليل كامل يشرح Layouts، UI Components، وطرق حفظ البيانات في تطبيقات Android

---

## 📁 روابط المشاريع

1. [app_two_activities](https://github.com/szainbzu/app_two_activities) - التنقل بين Activities
2. [RvViewVolly_v](https://github.com/szainbzu/RvViewVolly_v) - RecyclerView مع Volley
3. [sharedprefs2](https://github.com/szainbzu/sharedprefs2) - استخدام SharedPreferences
4. [gson_v](https://github.com/szainbzu/gson_v) - تحويل JSON باستخدام Gson

---

## 📄 AndroidManifest.xml

ملف **AndroidManifest.xml** هو ملف التكوين الرئيسي لأي تطبيق Android. يحتوي على معلومات أساسية عن التطبيق:

```xml
<manifest package="com.example.app"> <!-- 1️⃣ الحزمة -->
    <uses-permission android:name="android.permission.INTERNET"/> <!-- 2️⃣ صلاحية الإنترنت -->
    
    <application 
        android:label="@string/app_name"          <!-- 3️⃣ اسم التطبيق -->
        android:icon="@mipmap/ic_launcher"        <!-- 4️⃣ الأيقونة -->
        android:theme="@style/AppTheme"           <!-- 7️⃣ السمات -->
        android:allowBackup="true"                <!-- 8️⃣ النسخ الاحتياطي -->
        android:supportsRtl="true">               <!-- 🔟 دعم RTL -->
        
        <activity 
            android:name=".MainActivity"          <!-- 5️⃣ النشاط الرئيسي -->
            android:screenOrientation="portrait"> <!-- 9️⃣ اتجاه الشاشة -->
            <intent-filter>                       <!-- 6️⃣ نقطة الدخول -->
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
    </application>
</manifest>
```

### الصلاحيات الشائعة:
```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
```

---

## 📐 Layouts (التخطيطات)

### 1. LinearLayout

**LinearLayout** يرتب العناصر بشكل خطي (عمودي أو أفقي).

```xml
<LinearLayout
    android:layout_width="match_parent"    <!-- 1. العرض - يملأ المساحة المتاحة -->
    android:layout_height="match_parent"   <!-- 2. الارتفاع -->
    android:orientation="vertical"         <!-- 3. الاتجاه: vertical أو horizontal -->
    android:gravity="center"               <!-- 4. محاذاة المحتوى داخل Layout -->
    android:padding="16dp"                 <!-- 5. الحشو الداخلي (مسافة من الحواف) -->
    android:layout_margin="8dp"            <!-- 6. الهامش الخارجي -->
    android:background="@color/white">     <!-- 7. لون الخلفية -->
    
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="العنصر الأول"
        android:layout_weight="0" />        <!-- الوزن - لا يأخذ مساحة إضافية -->
    
    <Button
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"           <!-- يأخذ كل المساحة المتبقية -->
        android:text="زر" />
</LinearLayout>
```

**استخدامات layout_weight:**
- `layout_weight="0"`: حجم طبيعي (wrap_content أو قيمة محددة)
- `layout_weight="1"`: يأخذ المساحة المتبقية
- عند استخدام weight، اجعل `layout_height="0dp"` للعمودي أو `layout_width="0dp"` للأفقي

---

### 2. Setting Margins (ضبط الهوامش)

**Margins** = المسافات الخارجية بين العنصر والعناصر المحيطة  
**Padding** = المسافات الداخلية بين حدود العنصر ومحتواه

```xml
<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="زر مع Margins"
    
    <!-- Margins (هوامش خارجية) -->
    android:layout_margin="16dp"          <!-- من جميع الجهات -->
    android:layout_marginTop="24dp"       <!-- من الأعلى فقط -->
    android:layout_marginBottom="12dp"    <!-- من الأسفل -->
    android:layout_marginStart="8dp"      <!-- من البداية (يسار في LTR) -->
    android:layout_marginEnd="8dp"        <!-- من النهاية (يمين في LTR) -->
    
    <!-- Padding (حشو داخلي) -->
    android:padding="12dp"                <!-- من جميع الجهات -->
    android:paddingTop="16dp"             <!-- من الأعلى فقط -->
    android:paddingHorizontal="20dp"      <!-- أفقي فقط -->
    android:paddingVertical="10dp" />     <!-- عمودي فقط -->
```

**الفرق بين Margin و Padding:**

```
┌─────────── Margin ───────────┐
│  ┌─────── Border ──────┐     │
│  │  ┌─── Padding ───┐  │     │
│  │  │   Content     │  │     │
│  │  └───────────────┘  │     │
│  └─────────────────────┘     │
└──────────────────────────────┘
```

---

### 3. FrameLayout: Stacks the Views

**FrameLayout** يضع العناصر فوق بعضها البعض (تكديس/stacking).

**الاستخدامات:**
- عرض عنصر واحد في المرة (مثل Fragments)
- وضع طبقات فوق بعضها (صورة خلفية + نص)
- إنشاء تأثيرات overlay

```xml
<FrameLayout
    android:layout_width="match_parent"
    android:layout_height="300dp">
    
    <!-- الطبقة الأولى: صورة خلفية -->
    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/background"
        android:scaleType="centerCrop" />
    
    <!-- الطبقة الثانية: نص فوق الصورة -->
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"       <!-- المحاذاة داخل FrameLayout -->
        android:text="نص فوق الصورة"
        android:textColor="@android:color/white"
        android:textSize="24sp"
        android:background="#80000000"        <!-- خلفية شفافة سوداء -->
        android:padding="16dp" />
    
    <!-- الطبقة الثالثة: زر في الأسفل -->
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|center_horizontal"  <!-- أسفل ووسط -->
        android:layout_marginBottom="16dp"
        android:text="اضغط هنا" />
</FrameLayout>
```

**قيم layout_gravity في FrameLayout:**
- `top`, `bottom`, `start`, `end`
- `center`, `center_horizontal`, `center_vertical`
- يمكن دمجها: `bottom|end` (أسفل يمين)

---

### 4. ScrollView

**ScrollView** يسمح بالتمرير العمودي عندما يتجاوز المحتوى حجم الشاشة.

**ملاحظات مهمة:**
- ✅ يحتوي على **عنصر واحد فقط** (عادةً LinearLayout)
- ✅ للتمرير الأفقي استخدم `HorizontalScrollView`
- ❌ لا تضع `RecyclerView` أو `ListView` داخل `ScrollView`

```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true"              <!-- يملأ الشاشة حتى لو المحتوى قصير -->
    android:scrollbars="vertical">           <!-- إظهار شريط التمرير -->
    
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">
        
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="محتوى طويل جداً..." />
        
        <ImageView
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:src="@drawable/image1" />
        
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="المزيد من المحتوى..." />
        
        <!-- يمكن إضافة المزيد من العناصر -->
        
    </LinearLayout>
</ScrollView>
```

**للتمرير الأفقي:**
```xml
<HorizontalScrollView
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    
    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal">
        
        <!-- عناصر أفقية -->
        
    </LinearLayout>
</HorizontalScrollView>
```

---

### 5. TableLayout

**TableLayout** يرتب العناصر في صفوف وأعمدة (مثل جدول Excel).

**المكونات:**
- `TableLayout`: الحاوية الرئيسية
- `TableRow`: يمثل صف واحد
- كل عنصر داخل `TableRow` = خلية في الجدول

```xml
<TableLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:stretchColumns="*"               <!-- مد جميع الأعمدة -->
    android:shrinkColumns="*"                <!-- تقليص جميع الأعمدة عند الحاجة -->
    android:padding="16dp">
    
    <!-- الصف الأول: Header -->
    <TableRow
        android:background="@color/primary"
        android:padding="8dp">
        <TextView
            android:text="الاسم"
            android:textStyle="bold"
            android:textColor="@android:color/white" />
        <TextView
            android:text="البريد"
            android:textStyle="bold"
            android:textColor="@android:color/white" />
    </TableRow>
    
    <!-- الصف الثاني: بيانات -->
    <TableRow android:padding="8dp">
        <TextView
            android:text="الاسم:"
            android:layout_marginEnd="8dp" />
        <EditText
            android:hint="أدخل الاسم"
            android:layout_weight="1" />
    </TableRow>
    
    <!-- الصف الثالث -->
    <TableRow android:padding="8dp">
        <TextView
            android:text="البريد:"
            android:layout_marginEnd="8dp" />
        <EditText
            android:hint="أدخل البريد"
            android:inputType="textEmailAddress"
            android:layout_weight="1" />
    </TableRow>
</TableLayout>
```

**خصائص مفيدة:**
- `stretchColumns="0,2"`: مد الأعمدة 0 و 2
- `shrinkColumns="1"`: تقليص العمود 1
- `collapseColumns="2"`: إخفاء العمود 2

---

### 6. RelativeLayout

**RelativeLayout** يرتب العناصر بالنسبة لبعضها أو للـ parent.

**الخصائص - بالنسبة للـ Parent:**
```xml
android:layout_alignParentTop="true"        <!-- محاذاة مع أعلى الـ parent -->
android:layout_alignParentBottom="true"     <!-- محاذاة مع أسفل الـ parent -->
android:layout_alignParentStart="true"      <!-- محاذاة مع بداية الـ parent -->
android:layout_alignParentEnd="true"        <!-- محاذاة مع نهاية الـ parent -->
android:layout_centerInParent="true"        <!-- في منتصف الـ parent -->
android:layout_centerHorizontal="true"      <!-- في المنتصف أفقياً -->
android:layout_centerVertical="true"        <!-- في المنتصف عمودياً -->
```

**الخصائص - بالنسبة للعناصر الأخرى:**
```xml
android:layout_below="@id/element1"         <!-- تحت عنصر آخر -->
android:layout_above="@id/element1"         <!-- فوق عنصر آخر -->
android:layout_toStartOf="@id/element1"     <!-- على يسار عنصر -->
android:layout_toEndOf="@id/element1"       <!-- على يمين عنصر -->
android:layout_alignTop="@id/element1"      <!-- محاذاة مع أعلى عنصر -->
android:layout_alignBottom="@id/element1"   <!-- محاذاة مع أسفل عنصر -->
android:layout_alignStart="@id/element1"    <!-- محاذاة مع بداية عنصر -->
android:layout_alignEnd="@id/element1"      <!-- محاذاة مع نهاية عنصر -->
```

**مثال كامل:**
```xml
<RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">
    
    <!-- عنصر في الأعلى والوسط -->
    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true"
        android:text="العنوان"
        android:textSize="24sp"
        android:textStyle="bold" />
    
    <!-- عنصر تحت العنوان -->
    <EditText
        android:id="@+id/inputField"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/title"
        android:layout_marginTop="16dp"
        android:hint="أدخل النص" />
    
    <!-- زر في الأسفل -->
    <Button
        android:id="@+id/submitButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:layout_marginBottom="16dp"
        android:text="إرسال" />
    
    <!-- زر على يمين زر الإرسال -->
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignTop="@id/submitButton"
        android:layout_toEndOf="@id/submitButton"
        android:layout_marginStart="8dp"
        android:text="إلغاء" />
</RelativeLayout>
```

---

### 7. ConstraintLayout

**ConstraintLayout** الأكثر مرونة وكفاءة للواجهات المعقدة.

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="زر"
        
        <!-- القيود (Constraints) -->
        app:layout_constraintStart_toStartOf="parent"    <!-- بداية -> بداية parent -->
        app:layout_constraintEnd_toEndOf="parent"        <!-- نهاية -> نهاية parent -->
        app:layout_constraintTop_toTopOf="parent"        <!-- أعلى -> أعلى parent -->
        app:layout_constraintBottom_toBottomOf="parent"  <!-- أسفل -> أسفل parent -->
        
        <!-- الانحياز (Bias) - التحكم في الموضع -->
        app:layout_constraintHorizontal_bias="0.5"       <!-- 0.5 = في المنتصف -->
        app:layout_constraintVertical_bias="0.3"         <!-- 0.3 = أقرب للأعلى -->
        
        android:layout_margin="16dp" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## 🧩 عناصر الواجهة (UI Components)

### TextView

```xml
<TextView
    android:id="@+id/textView"               <!-- 1. المعرف -->
    android:layout_width="wrap_content"      <!-- 2. العرض -->
    android:layout_height="wrap_content"     <!-- 3. الارتفاع -->
    android:text="@string/hello"             <!-- 4. النص -->
    android:textSize="16sp"                  <!-- 5. حجم النص -->
    android:textColor="@color/black"         <!-- 6. لون النص -->
    android:textStyle="bold"                 <!-- 7. نمط: bold, italic, normal -->
    android:gravity="center"                 <!-- 8. محاذاة النص داخل TextView -->
    android:maxLines="2"                     <!-- 9. حد أقصى للأسطر -->
    android:ellipsize="end"                  <!-- 10. نقاط في النهاية (...) -->
    android:fontFamily="@font/roboto" />     <!-- 11. نوع الخط -->
```

### EditText

```xml
<EditText
    android:id="@+id/editText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/enter_text"        <!-- النص التوضيحي -->
    android:textSize="14sp"
    android:inputType="text"                 <!-- نوع الإدخال -->
    android:maxLines="1"                     <!-- سطر واحد فقط -->
    android:imeOptions="actionDone"          <!-- زر "تم" في الكيبورد -->
    android:background="@drawable/edit_bg"   <!-- خلفية مخصصة -->
    android:padding="12dp"
    android:drawableStart="@drawable/ic_user" <!-- أيقونة في البداية -->
    android:drawablePadding="8dp" />         <!-- مسافة من الأيقونة -->
```

**أنواع الإدخال (Input Types):**
```xml
android:inputType="text"                     <!-- نص عادي -->
android:inputType="textPassword"             <!-- كلمة مرور (•••) -->
android:inputType="textEmailAddress"         <!-- بريد إلكتروني -->
android:inputType="number"                   <!-- أرقام فقط -->
android:inputType="phone"                    <!-- رقم هاتف -->
android:inputType="numberDecimal"            <!-- أرقام عشرية -->
android:inputType="textCapWords"             <!-- أول حرف كبير لكل كلمة -->
android:inputType="textMultiLine"            <!-- عدة أسطر -->
```

### Button

```xml
<Button
    android:id="@+id/button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/click_me"          <!-- نص الزر -->
    android:onClick="onButtonClick"          <!-- دالة عند الضغط (في Activity) -->
    android:backgroundTint="@color/primary"  <!-- لون الخلفية -->
    android:textColor="@android:color/white" <!-- لون النص -->
    android:textAllCaps="false"              <!-- لا تجعل النص كله كبير -->
    android:drawableStart="@drawable/ic_add" <!-- أيقونة -->
    android:drawablePadding="8dp" />
```

### ImageView

```xml
<ImageView
    android:id="@+id/imageView"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:src="@drawable/ic_launcher"      <!-- الصورة -->
    android:scaleType="centerCrop"           <!-- طريقة التحجيم -->
    android:background="@color/gray"
    android:contentDescription="@string/image_desc" /> <!-- للوصول لذوي الإعاقة -->
```

**أنواع scaleType:**
- `centerCrop`: قص الصورة لتملأ المساحة
- `fitCenter`: تصغير الصورة لتناسب المساحة
- `fitXY`: تمديد الصورة لتملأ المساحة
- `center`: وضع الصورة في المنتصف بحجمها الأصلي

### RecyclerView

```xml
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scrollbars="vertical"            <!-- أشرطة التمرير -->
    android:clipToPadding="false"            <!-- لا تقطع الحشو عند التمرير -->
    app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    tools:listitem="@layout/item_layout" />  <!-- معاينة في المصمم -->
```

### CardView

```xml
<androidx.cardview.widget.CardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:cardCornerRadius="8dp"               <!-- زوايا دائرية -->
    app:cardElevation="4dp"                  <!-- الظل/الارتفاع -->
    app:cardUseCompatPadding="true"          <!-- حشو متوافق -->
    app:cardBackgroundColor="@color/white"   <!-- لون الخلفية -->
    android:layout_margin="8dp">
    
    <!-- محتوى الكارد -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">
        
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="عنوان الكارد" />
    </LinearLayout>
</androidx.cardview.widget.CardView>
```

---

## 🎨 ملفات الموارد (Resources)

### styles.xml

```xml
<resources>
    <style name="AppTheme" parent="Theme.MaterialComponents.Light.DarkActionBar">
        <!-- 1️⃣ ألوان السمة -->
        <item name="colorPrimary">@color/purple_500</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorSecondary">@color/teal_200</item>
        <item name="colorOnPrimary">@android:color/white</item>
        
        <!-- 2️⃣ خلفية التطبيق -->
        <item name="android:windowBackground">@color/white</item>
        
        <!-- 3️⃣ شريط الحالة -->
        <item name="android:statusBarColor">?attr/colorPrimaryVariant</item>
        
        <!-- 4️⃣ النصوص -->
        <item name="android:textColor">@color/black</item>
        <item name="android:textSize">16sp</item>
        
        <!-- 5️⃣ شريط التنقل -->
        <item name="android:navigationBarColor">@color/white</item>
    </style>
    
    <!-- سمة مخصصة للأزرار -->
    <style name="CustomButton" parent="Widget.MaterialComponents.Button">
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:textAllCaps">false</item>
        <item name="cornerRadius">8dp</item>
    </style>
</resources>
```

### colors.xml

```xml
<resources>
    <!-- ألوان Material Design الأساسية -->
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    <color name="teal_200">#FF03DAC5</color>
    <color name="teal_700">#FF018786</color>
    
    <!-- ألوان النصوص -->
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
    <color name="gray">#FF9E9E9E</color>
    
    <!-- ألوان الحالة -->
    <color name="red">#FFFF0000</color>
    <color name="green">#FF00FF00</color>
    <color name="blue">#FF0000FF</color>
    
    <!-- ألوان شفافة -->
    <color name="transparent">#00000000</color>
    <color name="semi_transparent_black">#80000000</color>
</resources>
```

### strings.xml

```xml
<resources>
    <!-- النصوص الأساسية -->
    <string name="app_name">تطبيقي</string>
    <string name="hello">مرحباً</string>
    <string name="submit">إرسال</string>
    <string name="cancel">إلغاء</string>
    
    <!-- الرسائل -->
    <string name="loading">جاري التحميل...</string>
    <string name="error">حدث خطأ</string>
    <string name="success">تم بنجاح</string>
    
    <!-- نصوص مع متغيرات -->
    <string name="welcome">أهلاً %s</string>              <!-- %s للنصوص -->
    <string name="count">لديك %d عنصر</string>           <!-- %d للأرقام -->
    <string name="price">السعر: %.2f ريال</string>       <!-- %.2f للأرقام العشرية -->
</resources>
```

**استخدام النصوص مع المتغيرات في Java:**
```java
String welcome = getString(R.string.welcome, "أحمد");
String count = getString(R.string.count, 5);
String price = getString(R.string.price, 99.99);
```

---

## 📐 Adapting to Display Orientation

### التعامل مع تغيير اتجاه الشاشة

عند تدوير الجهاز، يتم **إعادة إنشاء Activity** بشكل كامل (onCreate يُستدعى من جديد).

### الطريقة 1: إنشاء Layouts مختلفة

**هيكل المجلدات:**
```
res/
  layout/                    ← Portrait (عمودي) - الافتراضي
    activity_main.xml
  layout-land/               ← Landscape (أفقي)
    activity_main.xml
  layout-sw600dp/            ← للأجهزة اللوحية
    activity_main.xml
```

### الطريقة 2: حفظ واستعادة الحالة

```java
public class MainActivity extends AppCompatActivity {
    
    private EditText editText;
    private int counter = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editText = findViewById(R.id.editText);
        
        // استعادة الحالة بعد التدوير
        if (savedInstanceState != null) {
            String savedText = savedInstanceState.getString("text_key");
            counter = savedInstanceState.getInt("counter_key", 0);
            editText.setText(savedText);
        }
    }
    
    // حفظ الحالة قبل إعادة الإنشاء
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text_key", editText.getText().toString());
        outState.putInt("counter_key", counter);
    }
}
```

---

## 🔒 Controlling the Orientation of the Activity

### التحكم في اتجاه الشاشة

يمكن قفل الـ Activity على اتجاه معين لمنع التدوير.

### في AndroidManifest.xml:
```xml
<activity
    android:name=".MainActivity"
    android:screenOrientation="portrait" />
```

### القيم المتاحة:
- `portrait`: عمودي فقط 📱
- `landscape`: أفقي فقط 📱↔️
- `sensor`: حسب المستشعر (افتراضي)
- `nosensor`: تجاهل المستشعر
- `user`: حسب إعدادات المستخدم
- `unspecified`: النظام يقرر
- `fullSensor`: جميع الاتجاهات الأربعة

### برمجياً (في الكود):


<p dir="ltr">

```java
// في Activity
import android.content.pm.ActivityInfo;

// قفل على عمودي
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

// قفل على أفقي
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

// إلغاء القفل
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
```

</p>
---

## 💾 Data Persistence

### طرق حفظ البيانات في Android

هناك **5 طرق رئيسية** لحفظ البيانات:

| الطريقة | الاستخدام | حجم البيانات |
|---------|-----------|--------------|
| **SharedPreferences** | إعدادات بسيطة (key-value) | صغير جداً |
| **Internal Storage** | ملفات خاصة بالتطبيق | متوسط-كبير |
| **External Storage** | ملفات عامة (صور، فيديو) | كبير |
| **SQLite Database** | بيانات منظمة (جداول) | متوسط-كبير |
| **Content Providers** | مشاركة بين التطبيقات | متنوع |

---

## 🔑 Persistence using Shared Preferences

**SharedPreferences** لحفظ بيانات بسيطة بصيغة **key-value** (مثل JSON بسيط).

### أنواع البيانات المدعومة:
- `String` - نص
- `int` - أرقام صحيحة
- `long` - أرقام صحيحة كبيرة
- `float` - أرقام عشرية
- `boolean` - صح/خطأ
- `Set<String>` - مجموعة نصوص

### مثال كامل:

#### 1️⃣ الكتابة (Saving Data):
```java
// الحصول على SharedPreferences
SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
SharedPreferences.Editor editor = prefs.edit();

// حفظ البيانات
editor.putString("username", "أحمد");
editor.putInt("age", 25);
editor.putBoolean("isLoggedIn", true);
editor.putFloat("rating", 4.5f);

// تطبيق التغييرات
editor.apply(); // أو editor.commit()
```

#### 2️⃣ القراءة (Reading Data):
```java
SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

// القيمة الثانية = القيمة الافتراضية إذا لم يُعثر على المفتاح
String username = prefs.getString("username", "ضيف");
int age = prefs.getInt("age", 0);
boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);
float rating = prefs.getFloat("rating", 0.0f);
```

#### 3️⃣ الحذف (Deleting Data):
```java
SharedPreferences.Editor editor = prefs.edit();

editor.remove("username");    // حذف مفتاح معين
editor.clear();               // حذف جميع البيانات

editor.apply();
```

#### 4️⃣ التحقق من وجود مفتاح:
```java
if (prefs.contains("username")) {
    String username = prefs.getString("username", "");
}
```

### الفرق بين apply() و commit():

| apply() | commit() |
|---------|----------|
| ✅ غير متزامن (Asynchronous) | ❌ متزامن (Synchronous) |
| ✅ أسرع | ❌ أبطأ |
| ❌ لا يُرجع نتيجة | ✅ يُرجع true/false |
| ✅ الأفضل في معظم الحالات | ⚠️ عند الحاجة لمعرفة النجاح |

###مثال عملي - نظام تسجيل دخول:


```java
public class LoginActivity extends AppCompatActivity {
    
    private static final String PREFS_NAME = "LoginPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    
    // حفظ بيانات الدخول
    private void saveLoginData(String username) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit()
            .putString(KEY_USERNAME, username)
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .apply();
    }


    // التحقق من تسجيل الدخول
    private boolean isLoggedIn() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }
    
    // تسجيل الخروج
    private void logout() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit()
            .remove(KEY_USERNAME)
            .putBoolean(KEY_IS_LOGGED_IN, false)
            .apply();
    }
}
```

---

## 📦 Content Providers

**Content Providers** تسمح بمشاركة البيانات بين التطبيقات بطريقة آمنة ومنظمة.

### الاستخدامات:
- 📞 الوصول إلى **جهات الاتصال**
- 🖼️ الوصول إلى **الصور والفيديوهات**
- 📅 الوصول إلى **التقويم**
- 📧 الوصول إلى **الرسائل**
- 🎵 الوصول إلى **الموسيقى**
- 🔄 مشاركة بيانات تطبيقك مع تطبيقات أخرى

### مكونات Content Provider:

#### 1. URI (Uniform Resource Identifier)
عنوان فريد للوصول إلى البيانات:
```
content://com.example.provider/table_name/id
   ↓         ↓                    ↓        ↓
 Protocol Authority           Path      ID
```

مثال:
```
content://com.android.contacts/contacts/5
        ← النطاق            ← الجدول  ← الصف رقم 5
```

#### 2. CRUD Operations (العمليات الأساسية):
- **query()**: قراءة البيانات 📖
- **insert()**: إضافة بيانات ➕
- **update()**: تحديث بيانات ✏️
- **delete()**: حذف بيانات 🗑️

---

## ~~🔍 Querying Content Providers: Examples~~

### ~~مثال 1: قراءة جهات الاتصال~~

#### إضافة الصلاحية في AndroidManifest.xml:
```xml
<uses-permission android:name="android.permission.READ_CONTACTS" />
```

#### الكود الكامل:

```java
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public void readContacts() {
    // 1️⃣ URI لجهات الاتصال
    Uri uri = ContactsContract.Contacts.CONTENT_URI;
    
    // 2️⃣ الأعمدة المطلوبة (null = جميع الأعمدة)
    String[] projection = {
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.DISPLAY_NAME,
        ContactsContract.Contacts.HAS_PHONE_NUMBER
    };
    
    // 3️⃣ الاستعلام
    Cursor cursor = getContentResolver().query(
        uri,           // URI
        projection,    // الأعمدة المطلوبة
        null,          // WHERE clause (شرط الفلترة)
        null,          // WHERE arguments
        null           // Sort order (ترتيب)
    );
    
    // 4️⃣ قراءة البيانات
    if (cursor != null && cursor.moveToFirst()) {
        do {
            String id = cursor.getString(
                cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(
                cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            int hasPhone = cursor.getInt(
                cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            
            Log.d("Contact", "ID: " + id + ", Name: " + name + 
                  ", Has Phone: " + (hasPhone > 0));
        } while (cursor.moveToNext());
        
        // 5️⃣ إغلاق Cursor (مهم جداً!)
        cursor.close();
    }
}
```

### ~~مثال 2: قراءة الصور من المعرض~~

#### الصلاحية:
```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

#### الكود:
```java
import android.content.ContentUris;
import android.provider.MediaStore;

public void readImages() {
    Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    
    String[] projection = {
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.DATE_ADDED,
        MediaStore.Images.Media.SIZE
    };
    
    // ترتيب حسب الأحدث
    String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";
    
    Cursor cursor = getContentResolver().query(
        uri,
        projection,
        null,
        null,
        sortOrder
    );
    
    if (cursor != null && cursor.moveToFirst()) {
        do {
            long id = cursor.getLong(
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
            String name = cursor.getString(
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
            long size = cursor.getLong(
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
            
            // بناء URI للصورة
            Uri imageUri = ContentUris.withAppendedId(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
            
            Log.d("Image", "Name: " + name + ", Size: " + size + 
                  " bytes, URI: " + imageUri);
        } while (cursor.moveToNext());
        
        cursor.close();
    }
}
```

### ~~مثال 3: استخدام WHERE clause (فلترة البيانات)~~
<p dir="ltr">

```java
// البحث عن جهة اتصال باسم معين
String selection = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
String[] selectionArgs = {"%أحمد%"};  // % = wildcard (أي شيء)

Cursor cursor = getContentResolver().query(
    ContactsContract.Contacts.CONTENT_URI,
    null,
    selection,      // WHERE DISPLAY_NAME LIKE ?
    selectionArgs,  // "%أحمد%"
    null
);
```

</p>

### ~~مثال كالمل~~
```java
private void getPhoneNumbers(String contactId) {
    Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    
    String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
    String[] selectionArgs = {contactId};
    
    Cursor cursor = getContentResolver().query(
        phoneUri,
        null,
        selection,
        selectionArgs,
        null
    );
    
    if (cursor != null && cursor.moveToFirst()) {
        do {
            String phoneNumber = cursor.getString(
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            int type = cursor.getInt(
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
            
            String typeLabel = getPhoneTypeLabel(type);
            Log.d("Phone", typeLabel + ": " + phoneNumber);
        } while (cursor.moveToNext());
        
        cursor.close();
    }
}

private String getPhoneTypeLabel(int type) {
    switch (type) {
        case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
            return "منزل";
        case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
            return "جوال";
        case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
            return "عمل";
        default:
            return "أخرى";
    }
}
```

### ~~مثال 5: قراءة الرسائل SMS~~

#### الصلاحية:
```xml
<uses-permission android:name="android.permission.READ_SMS" />
```

#### الكود:
```java
import android.provider.Telephony;

public void readSMS() {
    Uri uri = Telephony.Sms.CONTENT_URI;
    
    String[] projection = {
        Telephony.Sms._ID,
        Telephony.Sms.ADDRESS,    // رقم المرسل
        Telephony.Sms.BODY,        // نص الرسالة
        Telephony.Sms.DATE         // التاريخ
    };
    
    // قراءة آخر 10 رسائل فقط
    String sortOrder = Telephony.Sms.DATE + " DESC LIMIT 10";
    
    Cursor cursor = getContentResolver().query(
        uri,
        projection,
        null,
        null,
        sortOrder
    );
    
    if (cursor != null && cursor.moveToFirst()) {
        do {
            String address = cursor.getString(
                cursor.getColumnIndex(Telephony.Sms.ADDRESS));
            String body = cursor.getString(
                cursor.getColumnIndex(Telephony.Sms.BODY));
            long date = cursor.getLong(
                cursor.getColumnIndex(Telephony.Sms.DATE));
            
            Log.d("SMS", "From: " + address + ", Message: " + body);
        } while (cursor.moveToNext());
        
        cursor.close();
    }
}
```

---

## 📏 الوحدات (Units)

| الوحدة | الاستخدام | مثال |
|-------|----------|------|
| **dp** | الأبعاد (العرض، الارتفاع، الهوامش) | `android:layout_width="100dp"` |
| **sp** | حجم النص (يتكيف مع إعدادات المستخدم) | `android:textSize="16sp"` |
| **px** | بكسل (❌ تجنب استخدامه) | `android:layout_width="100px"` |

**لماذا نستخدم dp و sp؟**
- **dp (Density-independent Pixels)**: يضمن أن تبدو العناصر بنفس الحجم على جميع الأجهزة
- **sp (Scale-independent Pixels)**: يحترم إعدادات حجم الخط للمستخدم

---

## ✅ أفضل الممارسات (Best Practices)

### للـ Layouts:
1. ✅ استخدم **ConstraintLayout** للواجهات المعقدة (الأكثر كفاءة)
2. ✅ تجنب التعشيش العميق للـ layouts (3 مستويات كحد أقصى)
3. ✅ استخدم **`<merge>`** و **`<include>`** لإعادة استخدام الـ layouts
4. ✅ استخدم **dp** للأبعاد و **sp** للنصوص
5. ✅ استخدم **ConstraintLayout** بدلاً من RelativeLayout
6. ✅ استخدم **RecyclerView** بدلاً من ListView

### للـ Data Persistence:
1. 🔐 لا تحفظ بيانات حساسة في SharedPreferences بدون تشفير
2. ✅ استخدم **Room Database** بدلاً من SQLite المباشر
3. ✅ تأكد من إغلاق **Cursor** بعد الاستخدام (استخدم try-with-resources)
4. ✅ اطلب الصلاحيات في **Runtime** لـ Android 6.0+ (Marshmallow)
5. ✅ استخدم **apply()** بدلاً من **commit()** في SharedPreferences

### للـ Orientation:
1. ✅ احفظ الحالة دائماً في `onSaveInstanceState()`
2. ✅ استخدم **ViewModel** (من Architecture Components) لحفظ البيانات
3. ✅ لا تقفل الاتجاه إلا إذا كان ضرورياً (للألعاب مثلاً)

### للـ Performance:
1. ✅ استخدم **ViewBinding** أو **Data Binding** بدلاً من findViewById
2. ✅ استخدم **Glide** أو **Picasso** لتحميل الصور
3. ✅ لا تقم بعمليات طويلة في **UI Thread** (استخدم AsyncTask أو Coroutines)

---
# 📚 Android Complete Guide – Lifecycle, ViewModel, Gson, Volley, SharedPreferences, RecyclerView, Intents
---

## <a id="setup"></a>1️⃣ Setup & Dependencies

### build.gradle (Module: app)
```gradle
dependencies {
    implementation 'com.android.volley:volley:1.2.1'
    implementation 'com.google.code.gson:gson:2.10.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.2'
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
}
```

### AndroidManifest.xml
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.CALL_PHONE" />
```

---

## <a id="models"></a>2️⃣ Data Models

### User.java
```java
public class User implements Serializable {
    private String name;
    private int age;
    private String email;
    private Address address;
    private List<String> phones;

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    public List<String> getPhones() { return phones; }
    public void setPhones(List<String> phones) { this.phones = phones; }
}
```

### Address.java
```java
public class Address implements Serializable {
    private String street;
    private String city;
    private String zip;

    public Address(String street, String city, String zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }
}
```

---

## <a id="viewmodel"></a>3️⃣ ViewModel + LiveData + SharedPreferences

### UserViewModel.java
```java
public class UserViewModel extends ViewModel {
    private static final String TAG = "UserViewModel";
    private MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private MutableLiveData<List<User>> userListLiveData = new MutableLiveData<>();

    public LiveData<User> getUser() {
        return userLiveData;
    }

    public void setUser(User user) {
        userLiveData.setValue(user);
        Log.d(TAG, "User set: " + user.getName());
    }

    public LiveData<List<User>> getUserList() {
        return userListLiveData;
    }

    public void setUserList(List<User> users) {
        userListLiveData.setValue(users);
        Log.d(TAG, "User list updated: " + users.size() + " users");
    }

    public void saveUserToPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(userLiveData.getValue());
        prefs.edit().putString("user_data", json).apply();
        Log.d(TAG, "Saved User to SharedPreferences: " + json);
    }

    public void loadUserFromPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String jsonString = prefs.getString("user_data", null);
        if(jsonString != null) {
            Gson gson = new Gson();
            User user = gson.fromJson(jsonString, User.class);
            userLiveData.setValue(user);
            Log.d(TAG, "Loaded User from SharedPreferences: " + jsonString);
        } else {
            Log.d(TAG, "No saved user data found");
        }
    }

    public void saveUserListToPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(userListLiveData.getValue());
        prefs.edit().putString("user_list_data", json).apply();
        Log.d(TAG, "Saved User List to SharedPreferences");
    }

    public void loadUserListFromPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String jsonString = prefs.getString("user_list_data", null);
        if(jsonString != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<User>>(){}.getType();
            List<User> users = gson.fromJson(jsonString, listType);
            userListLiveData.setValue(users);
            Log.d(TAG, "Loaded User List from SharedPreferences: " + users.size() + " users");
        }
    }
}
```

---

## <a id="network"></a>4️⃣ Network Operations with Volley + Gson

### Fetch Single User from API
```java
RequestQueue queue = Volley.newRequestQueue(this);
String url = "https://api.example.com/user";

JsonObjectRequest request = new JsonObjectRequest(
    Request.Method.GET, 
    url, 
    null,
    response -> {
        Gson gson = new Gson();
        User user = gson.fromJson(response.toString(), User.class);
        Log.d("Volley", "User from API: " + user.getName() + ", Age: " + user.getAge());
        
        // Update ViewModel
        userViewModel.setUser(user);
        
        // Save to SharedPreferences
        userViewModel.saveUserToPrefs(this);
    },
    error -> {
        Log.e("Volley", "Error: " + error.toString());
        Toast.makeText(this, "Failed to fetch user", Toast.LENGTH_SHORT).show();
    }
);

queue.add(request);
```

### Fetch User List from API
```java
String url = "https://api.example.com/users";

JsonArrayRequest request = new JsonArrayRequest(
    Request.Method.GET,
    url,
    null,
    response -> {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>(){}.getType();
        List<User> users = gson.fromJson(response.toString(), listType);
        
        Log.d("Volley", "Fetched " + users.size() + " users");
        userViewModel.setUserList(users);
        userViewModel.saveUserListToPrefs(this);
    },
    error -> Log.e("Volley", "Error: " + error.toString())
);

queue.add(request);
```

### ~~POST Request Example~~
```java
String url = "https://api.example.com/user/create";
JSONObject jsonBody = new JSONObject();
try {
    jsonBody.put("name", "Ahmed");
    jsonBody.put("age", 25);
    jsonBody.put("email", "ahmed@example.com");
} catch (JSONException e) {
    e.printStackTrace();
}

JsonObjectRequest request = new JsonObjectRequest(
    Request.Method.POST,
    url,
    jsonBody,
    response -> Log.d("Volley", "User created: " + response.toString()),
    error -> Log.e("Volley", "Error: " + error.toString())
);

queue.add(request);
```

---

## <a id="lifecycle"></a>5️⃣ Activity Lifecycle with Logcat Tracking

### MainActivity.java
```java
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Lifecycle";
    private UserViewModel userViewModel;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate called");

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.loadUserFromPrefs(this);

        // Observe LiveData
        userViewModel.getUser().observe(this, user -> {
            if(user != null) {
                Log.d(TAG, "User updated: " + user.getName());
                // Update UI
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        if(videoView != null) {
            videoView.start();
            Log.d(TAG, "Video resumed");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
        if(videoView != null) {
            videoView.pause();
            Log.d(TAG, "Video paused");
        }
        userViewModel.saveUserToPrefs(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart called");
        userViewModel.loadUserFromPrefs(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d(TAG, "Orientation: LANDSCAPE");
        } else {
            Log.d(TAG, "Orientation: PORTRAIT");
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "Back button pressed");
        userViewModel.saveUserToPrefs(this);
        super.onBackPressed();
    }
}
```

### Lifecycle Flow Diagram
```
onCreate
   ↓
onStart
   ↓
onResume  ← (App Running)
   ↓
onPause   → (Home pressed / Call incoming)
   ↓
onStop    → (App not visible)
   ↓
onRestart → (Return to app)
   ↓
onStart
   ↓
onResume
   ↓
onPause   → (Back pressed / finish())
   ↓
onStop
   ↓
onDestroy
```

---

## <a id="recyclerview"></a>6️⃣ RecyclerView + Adapter + ViewHolder

### item_user.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    app:cardElevation="4dp"
    app:cardCornerRadius="8dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:id="@+id/tvName"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Name"
            android:textSize="18sp"
            android:textStyle="bold"/>

        <TextView
            android:id="@+id/tvEmail"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Email"
            android:layout_marginTop="4dp"/>

        <TextView
            android:id="@+id/tvCity"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="City"
            android:layout_marginTop="4dp"/>

        <TextView
            android:id="@+id/tvPhones"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Phones"
            android:layout_marginTop="4dp"/>
    </LinearLayout>
</androidx.cardview.widget.CardView>
```

### UserAdapter.java
```java
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private OnUserClickListener listener;

    public interface OnUserClickListener {
        void onUserClick(User user);
    }

    public UserAdapter(List<User> userList, OnUserClickListener listener) {
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user, listener);
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public void updateList(List<User> newList) {
        this.userList = newList;
        notifyDataSetChanged();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail, tvCity, tvPhones;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvPhones = itemView.findViewById(R.id.tvPhones);
        }

        public void bind(User user, OnUserClickListener listener) {
            tvName.setText(user.getName());
            tvEmail.setText(user.getEmail());
            
            if(user.getAddress() != null) {
                tvCity.setText(user.getAddress().getCity());
            }
            
            if(user.getPhones() != null && !user.getPhones().isEmpty()) {
                tvPhones.setText(TextUtils.join(", ", user.getPhones()));
            }

            itemView.setOnClickListener(v -> listener.onUserClick(user));
        }
    }
}
```

### Setup RecyclerView in Activity
```java
RecyclerView recyclerView = findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));

UserAdapter adapter = new UserAdapter(users, user -> {
    Toast.makeText(this, "Clicked: " + user.getName(), Toast.LENGTH_SHORT).show();
    // Open detail activity
    Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
    intent.putExtra("user", user);
    startActivity(intent);
});

recyclerView.setAdapter(adapter);

// Observe ViewModel
userViewModel.getUserList().observe(this, users -> {
    adapter.updateList(users);
});
```

---

## <a id="intents"></a>7️⃣ Intents & Navigation

### Explicit Intent Examples

#### Open Another Activity
```java
Intent intent = new Intent(MainActivity.this, SecondActivity.class);
startActivity(intent);
```

#### Pass Data Between Activities
```java
// Sender Activity
Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
intent.putExtra("username", "Ahmed");
intent.putExtra("age", 25);
intent.putExtra("user", userObject); // Serializable
startActivity(intent);

// Receiver Activity
String username = getIntent().getStringExtra("username");
int age = getIntent().getIntExtra("age", 0);
User user = (User) getIntent().getSerializableExtra("user");
```

#### Start Activity for Result (Old Way - Deprecated)
```java
// MainActivity.java - OLD METHOD (Still works but deprecated)
private static final int REQUEST_CODE = 1;

// Send Request
Intent intent = new Intent(MainActivity.this, SecondActivity.class);
startActivityForResult(intent, REQUEST_CODE);

// Receive Result
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
        String result = data.getStringExtra("result");
        Toast.makeText(this, "Result: " + result, Toast.LENGTH_SHORT).show();
    }
}

// SecondActivity.java - Return Result
Intent returnIntent = new Intent();
returnIntent.putExtra("result", "Success");
setResult(RESULT_OK, returnIntent);
finish();
```

### Implicit Intent Examples

#### Open Web Browser
```java
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse("https://www.google.com"));
startActivity(intent);
```

#### Share Text
```java
Intent intent = new Intent(Intent.ACTION_SEND);
intent.setType("text/plain");
intent.putExtra(Intent.EXTRA_TEXT, "Check out this app!");
startActivity(Intent.createChooser(intent, "Share via"));
```

#### Make Phone Call (Requires Permission)
```java
Intent intent = new Intent(Intent.ACTION_CALL);
intent.setData(Uri.parse("tel:123456789"));
if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) 
    == PackageManager.PERMISSION_GRANTED) {
    startActivity(intent);
}
```

#### Open Dialer
```java
Intent intent = new Intent(Intent.ACTION_DIAL);
intent.setData(Uri.parse("tel:123456789"));
startActivity(intent);
```

#### Send Email
```java
Intent intent = new Intent(Intent.ACTION_SENDTO);
intent.setData(Uri.parse("mailto:example@gmail.com"));
intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
intent.putExtra(Intent.EXTRA_TEXT, "Email body");
startActivity(intent);
```

#### ~~Pick Contact~~
```java
// NEW WAY - Using Activity Result API
private ActivityResultLauncher<Intent> pickContactLauncher;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    pickContactLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Uri contactUri = result.getData().getData();
                // Process contact
            }
        }
    );
}

Button btnPickContact = findViewById(R.id.btnPickContact);
btnPickContact.setOnClickListener(v -> {
    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    pickContactLauncher.launch(intent);
});
```

### Intent Actions Reference

| Action | Purpose | Example |
|--------|---------|---------|
| `ACTION_VIEW` | View content | Open URL, image, file |
| `ACTION_SEND` | Share content | Share text, image |
| `ACTION_DIAL` | Open dialer | Show phone dialer |
| `ACTION_CALL` | Make call | Direct call (needs permission) |
| `ACTION_SENDTO` | Send message | Email, SMS |
| `ACTION_PICK` | Pick item | Select contact, photo |
| `ACTION_EDIT` | Edit data | Edit contact |
| `ACTION_MAIN` | Main entry | Launcher activity |

---

## <a id="scenarios"></a>8️⃣ Practical Scenarios with Logcat
![Lifecycle Diagram](https://raw.githubusercontent.com/aymanaljamal/AndroidTrainingApp/master/59d40f71d715436.png)

### Scenario 1: First App Launch
```
2025-12-07 14:00:00.000 D/Lifecycle: onCreate called
2025-12-07 14:00:00.050 D/Lifecycle: onStart called
2025-12-07 14:00:00.100 D/Lifecycle: onResume called
2025-12-07 14:00:00.150 D/UserViewModel: No saved user data found
2025-12-07 14:00:00.200 D/Volley: Sending GET request
2025-12-07 14:00:00.500 D/Volley: User from API: Ahmed, Age: 25
2025-12-07 14:00:00.550 D/UserViewModel: User set: Ahmed
2025-12-07 14:00:00.600 D/UserViewModel: Saved User to SharedPreferences
```

### Scenario 2: Press Home Button
```
2025-12-07 14:05:00.000 D/Lifecycle: onPause called
2025-12-07 14:05:00.050 D/UserViewModel: Saved User to SharedPreferences
2025-12-07 14:05:00.100 D/Lifecycle: onStop called
```

### Scenario 3: Return to App
```
2025-12-07 14:10:00.000 D/Lifecycle: onRestart called
2025-12-07 14:10:00.050 D/UserViewModel: Loaded User from SharedPreferences
2025-12-07 14:10:00.100 D/Lifecycle: onStart called
2025-12-07 14:10:00.150 D/Lifecycle: onResume called
```

### Scenario 4: Orientation Change
```
2025-12-07 14:15:00.000 D/Lifecycle: onPause called
2025-12-07 14:15:00.050 D/Lifecycle: onStop called
2025-12-07 14:15:00.100 D/Lifecycle: onDestroy called
2025-12-07 14:15:00.150 D/Lifecycle: onCreate called
2025-12-07 14:15:00.200 D/Lifecycle: onStart called
2025-12-07 14:15:00.250 D/Lifecycle: onResume called
2025-12-07 14:15:00.300 D/Lifecycle: Orientation: LANDSCAPE
2025-12-07 14:15:00.350 D/UserViewModel: Loaded User from SharedPreferences
```

### Scenario 5: Press Back Button
```
2025-12-07 14:20:00.000 D/Lifecycle: Back button pressed
2025-12-07 14:20:00.050 D/UserViewModel: Saved User to SharedPreferences
2025-12-07 14:20:00.100 D/Lifecycle: onPause called
2025-12-07 14:20:00.150 D/Lifecycle: onStop called
2025-12-07 14:20:00.200 D/Lifecycle: onDestroy called
```

### Scenario 6: Network Update While Running
```
2025-12-07 14:25:00.000 D/Volley: Sending GET request
2025-12-07 14:25:00.300 D/Volley: User from API: Ali, Age: 30
2025-12-07 14:25:00.350 D/UserViewModel: User set: Ali
2025-12-07 14:25:00.400 D/UserViewModel: Saved User to SharedPreferences
```

### Scenario 7: Video Playing + Incoming Call
```
2025-12-07 14:30:00.000 D/Lifecycle: onResume called
2025-12-07 14:30:00.050 D/VideoView: Video started playing
2025-12-07 14:30:00.100 D/Lifecycle: Video resumed
2025-12-07 14:32:00.000 D/PhoneCall: Incoming call detected
2025-12-07 14:32:00.050 D/Lifecycle: onPause called
2025-12-07 14:32:00.100 D/VideoView: Video paused
2025-12-07 14:32:00.150 D/UserViewModel: Saved User to SharedPreferences
2025-12-07 14:32:00.200 D/Lifecycle: onStop called
--- Call Ends ---
2025-12-07 14:35:00.000 D/Lifecycle: onRestart called
2025-12-07 14:35:00.050 D/UserViewModel: Loaded User from SharedPreferences
2025-12-07 14:35:00.100 D/Lifecycle: onStart called
2025-12-07 14:35:00.150 D/Lifecycle: onResume called
2025-12-07 14:35:00.200 D/VideoView: Video resumed from pause
```

### Scenario 8: Navigate to Second Activity and Back
```
2025-12-07 14:40:00.000 D/MainActivity: User clicks button to open ProfileActivity
2025-12-07 14:40:00.050 D/Intent: Starting ProfileActivity with User data
2025-12-07 14:40:00.100 D/MainActivity-Lifecycle: onPause called
2025-12-07 14:40:00.150 D/MainActivity-Lifecycle: onStop called
2025-12-07 14:40:00.200 D/ProfileActivity-Lifecycle: onCreate called
2025-12-07 14:40:00.250 D/ProfileActivity-Lifecycle: onStart called
2025-12-07 14:40:00.300 D/ProfileActivity-Lifecycle: onResume called
2025-12-07 14:40:00.350 D/ProfileActivity: Received User: Ahmed, Age: 25
--- User presses Back ---
2025-12-07 14:45:00.000 D/ProfileActivity-Lifecycle: onPause called
2025-12-07 14:45:00.050 D/MainActivity-Lifecycle: onRestart called
2025-12-07 14:45:00.100 D/MainActivity-Lifecycle: onStart called
2025-12-07 14:45:00.150 D/MainActivity-Lifecycle: onResume called
2025-12-07 14:45:00.200 D/ProfileActivity-Lifecycle: onStop called
2025-12-07 14:45:00.250 D/ProfileActivity-Lifecycle: onDestroy called
```

### Scenario 9: Low Memory - System Kills App in Background
```
2025-12-07 14:50:00.000 D/Lifecycle: onPause called
2025-12-07 14:50:00.050 D/UserViewModel: Saved User to SharedPreferences
2025-12-07 14:50:00.100 D/Lifecycle: onStop called
2025-12-07 14:52:00.000 D/System: Low memory detected
2025-12-07 14:52:00.050 D/System: Killing MainActivity process
2025-12-07 14:52:00.100 D/Lifecycle: onDestroy called (not guaranteed to be called)
--- User returns to app ---
2025-12-07 14:55:00.000 D/Lifecycle: onCreate called (fresh start)
2025-12-07 14:55:00.050 D/Lifecycle: onStart called
2025-12-07 14:55:00.100 D/Lifecycle: onResume called
2025-12-07 14:55:00.150 D/UserViewModel: Loaded User from SharedPreferences
2025-12-07 14:55:00.200 D/UserViewModel: User restored: Ahmed, Age: 25
```

### Scenario 10: RecyclerView Data Update
```
2025-12-07 15:00:00.000 D/MainActivity: User clicks Refresh button
2025-12-07 15:00:00.050 D/Volley: Sending GET request to /users endpoint
2025-12-07 15:00:00.100 D/RecyclerView: Showing loading indicator
2025-12-07 15:00:00.400 D/Volley: Fetched 10 users from API
2025-12-07 15:00:00.450 D/UserViewModel: User list updated: 10 users
2025-12-07 15:00:00.500 D/UserViewModel: Saved User List to SharedPreferences
2025-12-07 15:00:00.550 D/UserAdapter: notifyDataSetChanged called
2025-12-07 15:00:00.600 D/RecyclerView: Displaying 10 items
2025-12-07 15:00:00.650 D/RecyclerView: Hiding loading indicator
```

### Scenario 11: Share Text to Another App
```
2025-12-07 15:05:00.000 D/MainActivity: User clicks Share button
2025-12-07 15:05:00.050 D/Intent: ACTION_SEND with text/plain type
2025-12-07 15:05:00.100 D/Intent: Extra text: "Check out this app!"
2025-12-07 15:05:00.150 D/System: Showing chooser dialog
2025-12-07 15:05:00.200 D/MainActivity-Lifecycle: onPause called
2025-12-07 15:05:05.000 D/System: User selected WhatsApp
2025-12-07 15:05:05.050 D/WhatsApp: Received shared text
--- User shares and returns ---
2025-12-07 15:05:10.000 D/MainActivity-Lifecycle: onResume called
```

### Scenario 12: Network Error Handling
```
2025-12-07 15:10:00.000 D/Volley: Sending GET request
2025-12-07 15:10:00.050 D/NetworkMonitor: No internet connection
2025-12-07 15:10:00.100 E/Volley: Error: NetworkError
2025-12-07 15:10:00.150 D/MainActivity: Showing error toast
2025-12-07 15:10:00.200 D/UserViewModel: Loading cached data from SharedPreferences
2025-12-07 15:10:00.250 D/UserViewModel: Loaded User: Ahmed, Age: 25 (offline)
2025-12-07 15:10:00.300 D/MainActivity: Displaying offline indicator
```

### Scenario 13: Pick Contact and Display
```
2025-12-07 15:15:00.000 D/MainActivity: User clicks Pick Contact button
2025-12-07 15:15:00.050 D/Intent: ACTION_PICK - Contacts
2025-12-07 15:15:00.100 D/ActivityResultLauncher: Launching contact picker
2025-12-07 15:15:00.150 D/MainActivity-Lifecycle: onPause called
2025-12-07 15:15:00.200 D/MainActivity-Lifecycle: onStop called
2025-12-07 15:15:00.250 D/ContactsApp: Contact picker opened
--- User selects contact ---
2025-12-07 15:15:05.000 D/ActivityResultLauncher: Result received - RESULT_OK
2025-12-07 15:15:05.050 D/MainActivity-Lifecycle: onRestart called
2025-12-07 15:15:05.100 D/MainActivity-Lifecycle: onStart called
2025-12-07 15:15:05.150 D/MainActivity-Lifecycle: onResume called
2025-12-07 15:15:05.200 D/ContactPicker: Selected contact: Ali Mohammed
2025-12-07 15:15:05.250 D/MainActivity: Displaying contact name: Ali Mohammed
```

### Scenario 14: Form Input + Activity Result
```
2025-12-07 15:20:00.000 D/MainActivity: Opening EditProfileActivity
2025-12-07 15:20:00.050 D/ActivityResultLauncher: Launching EditProfileActivity
2025-12-07 15:20:00.100 D/MainActivity-Lifecycle: onPause called
2025-12-07 15:20:00.150 D/MainActivity-Lifecycle: onStop called
2025-12-07 15:20:00.200 D/EditProfileActivity-Lifecycle: onCreate called
2025-12-07 15:20:00.250 D/EditProfileActivity-Lifecycle: onStart called
2025-12-07 15:20:00.300 D/EditProfileActivity-Lifecycle: onResume called
2025-12-07 15:20:00.350 D/EditProfileActivity: Loaded existing data
--- User edits and saves ---
2025-12-07 15:22:00.000 D/EditProfileActivity: Save button clicked
2025-12-07 15:22:00.050 D/EditProfileActivity: Name changed: Ahmed -> Ali
2025-12-07 15:22:00.100 D/EditProfileActivity: Email changed: ahmed@x.com -> ali@x.com
2025-12-07 15:22:00.150 D/Intent: Returning result to MainActivity
2025-12-07 15:22:00.200 D/EditProfileActivity-Lifecycle: onPause called
2025-12-07 15:22:00.250 D/EditProfileActivity-Lifecycle: onStop called
2025-12-07 15:22:00.300 D/EditProfileActivity-Lifecycle: onDestroy called
2025-12-07 15:22:00.350 D/MainActivity-Lifecycle: onRestart called
2025-12-07 15:22:00.400 D/MainActivity-Lifecycle: onStart called
2025-12-07 15:22:00.450 D/MainActivity-Lifecycle: onResume called
2025-12-07 15:22:00.500 D/ActivityResultLauncher: Result received - RESULT_OK
2025-12-07 15:22:00.550 D/MainActivity: Profile updated: Ali, ali@x.com
2025-12-07 15:22:00.600 D/UserViewModel: User set: Ali
2025-12-07 15:22:00.650 D/UserViewModel: Saved User to SharedPreferences
```

### Scenario 15: App in Background - Push Notification Arrives
```
2025-12-07 15:25:00.000 D/MainActivity-Lifecycle: onPause called
2025-12-07 15:25:00.050 D/MainActivity-Lifecycle: onStop called
2025-12-07 15:26:00.000 D/FCM: Push notification received
2025-12-07 15:26:00.050 D/NotificationManager: Displaying notification
2025-12-07 15:26:00.100 D/NotificationManager: Title: New Message
2025-12-07 15:26:00.150 D/NotificationManager: Body: You have 3 new messages
--- User taps notification ---
2025-12-07 15:26:05.000 D/Intent: Opening MainActivity from notification
2025-12-07 15:26:05.050 D/MainActivity-Lifecycle: onRestart called
2025-12-07 15:26:05.100 D/MainActivity-Lifecycle: onStart called
2025-12-07 15:26:05.150 D/MainActivity-Lifecycle: onResume called
2025-12-07 15:26:05.200 D/MainActivity: Handling notification intent
2025-12-07 15:26:05.250 D/MainActivity: Opening messages screen
```

### Scenario 16a: Orientation Change During Network Request (With ViewModel)
```
2025-12-07 15:30:00.000 D/MainActivity: User clicks Load Data button
2025-12-07 15:30:00.050 D/Volley: Sending GET request
2025-12-07 15:30:00.100 D/MainActivity: Showing loading spinner
--- User rotates device during loading ---
2025-12-07 15:30:01.000 D/MainActivity-Lifecycle: onPause called
2025-12-07 15:30:01.050 D/MainActivity-Lifecycle: onStop called
2025-12-07 15:30:01.100 D/MainActivity-Lifecycle: onDestroy called
2025-12-07 15:30:01.150 D/MainActivity-Lifecycle: onCreate called
2025-12-07 15:30:01.200 D/MainActivity-Lifecycle: onStart called
2025-12-07 15:30:01.250 D/MainActivity-Lifecycle: onResume called
2025-12-07 15:30:01.300 D/MainActivity: ViewModel retained during rotation
2025-12-07 15:30:01.350 D/MainActivity: Loading spinner still visible
--- Network response arrives ---
2025-12-07 15:30:02.000 D/Volley: User from API: Ahmed, Age: 25
2025-12-07 15:30:02.050 D/UserViewModel: User set: Ahmed
2025-12-07 15:30:02.100 D/MainActivity: Loading spinner hidden
2025-12-07 15:30:02.150 D/MainActivity: Data displayed successfully after rotation
```

✅ البيانات محفوظة، الـActivity الجديد يستقبلها مباشرة.

### Scenario 16b: Orientation Change During Network Request (Without ViewModel)
```
2025-12-07 15:30:00.000 D/MainActivity: User clicks Load Data button
2025-12-07 15:30:00.050 D/Volley: Sending GET request
2025-12-07 15:30:00.100 D/MainActivity: Showing loading spinner
--- User rotates device during loading ---
2025-12-07 15:30:01.000 D/MainActivity-Lifecycle: onPause called
2025-12-07 15:30:01.050 D/MainActivity-Lifecycle: onStop called
2025-12-07 15:30:01.100 D/MainActivity-Lifecycle: onDestroy called
2025-12-07 15:30:01.150 D/MainActivity-Lifecycle: onCreate called
2025-12-07 15:30:01.200 D/MainActivity-Lifecycle: onStart called
2025-12-07 15:30:01.250 D/MainActivity-Lifecycle: onResume called
2025-12-07 15:30:01.350 D/MainActivity: Loading spinner visible, لكن البيانات السابقة مفقودة
--- Network response arrives ---
2025-12-07 15:30:02.000 D/Volley: User from API: Ahmed, Age: 25
2025-12-07 15:30:02.050 D/MainActivity: Attempting to update UI
2025-12-07 15:30:02.100 D/MainActivity: Loading spinner hidden
2025-12-07 15:30:02.150 D/MainActivity: Data displayed successfully
```

❌ البيانات مفقودة مؤقتاً عند إنشاء الـActivity الجديد، حتى يصل Response مرة أخرى.

هذا يسبب فلاش أو فقدان بيانات مؤقت.

الحل الأمثل: استخدام ViewModel أو SavedStateHandle للاحتفاظ بالبيانات خلال إعادة إنشاء Activity.

## ~~Scenario 17: Multiple Fragment Transactions~~
```
2025-12-07 15:35:00.000 D/MainActivity-Lifecycle: onCreate called
2025-12-07 15:35:00.050 D/FragmentManager: Adding HomeFragment
2025-12-07 15:35:00.100 D/HomeFragment-Lifecycle: onAttach called
2025-12-07 15:35:00.150 D/HomeFragment-Lifecycle: onCreate called
2025-12-07 15:35:00.200 D/HomeFragment-Lifecycle: onCreateView called
2025-12-07 15:35:00.250 D/HomeFragment-Lifecycle: onStart called
2025-12-07 15:35:00.300 D/HomeFragment-Lifecycle: onResume called
--- User navigates to Profile tab ---
2025-12-07 15:36:00.000 D/MainActivity: Switching to ProfileFragment
2025-12-07 15:36:00.050 D/HomeFragment-Lifecycle: onPause called
2025-12-07 15:36:00.100 D/ProfileFragment-Lifecycle: onCreate called
2025-12-07 15:36:00.150 D/ProfileFragment-Lifecycle: onCreateView called
2025-12-07 15:36:00.200 D/ProfileFragment-Lifecycle: onStart called
2025-12-07 15:36:00.250 D/ProfileFragment-Lifecycle: onResume called
2025-12-07 15:36:00.300 D/HomeFragment-Lifecycle: onStop called
```

### Scenario 18: Camera Intent and Photo Capture
```
2025-12-07 15:40:00.000 D/MainActivity: User clicks Take Photo button
2025-12-07 15:40:00.050 D/Intent: ACTION_IMAGE_CAPTURE
2025-12-07 15:40:00.100 D/ActivityResultLauncher: Launching camera
2025-12-07 15:40:00.150 D/MainActivity-Lifecycle: onPause called
2025-12-07 15:40:00.200 D/MainActivity-Lifecycle: onStop called
2025-12-07 15:40:00.250 D/CameraApp: Camera opened
--- User takes photo ---
2025-12-07 15:40:10.000 D/CameraApp: Photo captured
2025-12-07 15:40:10.050 D/ActivityResultLauncher: Result received - RESULT_OK
2025-12-07 15:40:10.100 D/MainActivity-Lifecycle: onRestart called
2025-12-07 15:40:10.150 D/MainActivity-Lifecycle: onStart called
2025-12-07 15:40:10.200 D/MainActivity-Lifecycle: onResume called
2025-12-07 15:40:10.250 D/MainActivity: Photo received as Bitmap
2025-12-07 15:40:10.300 D/MainActivity: Displaying photo in ImageView
2025-12-07 15:40:10.350 D/MainActivity: Photo size: 1024x768
```

---

## 📝 Best Practices

### 1. Data Management
- Always use ViewModel to survive configuration changes
- Use LiveData for reactive UI updates
- Save important data in onPause()
- Load data in onCreate() or onRestart()

### 2. Network Operations
- Use Volley for simple HTTP requests
- Parse JSON with Gson
- Handle errors gracefully
- Show loading indicators

### 3. Lifecycle Management
- Log all lifecycle events for debugging
- Release resources in onPause()/onStop()
- Handle orientation changes properly
- Save state before destruction

### 4. RecyclerView Optimization
- Use ViewHolder pattern
- Implement efficient data updates
- Handle click events properly
- Use DiffUtil for large lists

### 5. Intent Usage
- Use Explicit Intents for internal navigation
- Use Implicit Intents for external actions
- Check permissions before sensitive operations
- Handle Intent resolution failures

---

## 🔍 Common Issues & Solutions

### Issue 1: Data Lost on Rotation
**Solution**: Use ViewModel to retain data
```java
userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
```

### Issue 2: Memory Leaks
**Solution**: Use LiveData and proper lifecycle awareness
```java
userViewModel.getUser().observe(this, user -> {
    // Update UI
});
```

### Issue 3: Network on Main Thread
**Solution**: Volley handles threading automatically
```java
RequestQueue queue = Volley.newRequestQueue(this);
// Volley executes on background thread
```

### Issue 4: RecyclerView Not Updating
**Solution**: Call notifyDataSetChanged() or use DiffUtil
```java
adapter.notifyDataSetChanged();
```

---

## 📚 مصادر إضافية

- [Android Lifecycle Documentation](https://developer.android.com/guide/components/activities/activity-lifecycle)
- [ViewModel Overview](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [RecyclerView Guide](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [Volley Documentation](https://developer.android.com/training/volley)
- [Gson User Guide](https://github.com/google/gson/blob/master/UserGuide.md)



### الوثائق الرسمية:
- [Android Developers - Layouts](https://developer.android.com/guide/topics/ui/declaring-layout)
- [Android Developers - Data Storage](https://developer.android.com/training/data-storage)
- [Android Developers - Content Providers](https://developer.android.com/guide/topics/providers/content-providers)
- [Material Design Guidelines](https://material.io/design)

### دروس مفيدة:
- [Codelabs - Android Basics](https://developer.android.com/courses/android-basics-kotlin/course)
- [Vogella - Android Tutorials](https://www.vogella.com/tutorials/android.html)

---


## 🎯 ملخص سريع

| الموضوع | النقاط الرئيسية |
|---------|-----------------|
| **LinearLayout** | ترتيب خطي (عمودي/أفقي)، استخدم `layout_weight` للتوزيع |
| **FrameLayout** | تكديس العناصر فوق بعضها، مثالي للـ Fragments |
| **RelativeLayout** | ترتيب نسبي للعناصر، استخدم ConstraintLayout بدلاً منه |
| **ScrollView** | للتمرير العمودي، عنصر واحد فقط بداخله |
| **Margins vs Padding** | Margin خارجي، Padding داخلي |
| **Orientation** | احفظ الحالة في `onSaveInstanceState()` |
| **SharedPreferences** | بيانات بسيطة (key-value)، استخدم `apply()` |
| **Content Providers** | مشاركة البيانات بين التطبيقات، أغلق Cursor دائماً |

---


💡 **نصيحة أخيرة:** مارس بكتابة الكود بنفسك! لا تكتفي بالقراءة فقط.
