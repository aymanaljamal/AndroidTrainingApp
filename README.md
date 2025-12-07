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

## 🔍 Querying Content Providers: Examples

### مثال 1: قراءة جهات الاتصال

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

### مثال 2: قراءة الصور من المعرض

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

### مثال 3: استخدام WHERE clause (فلترة البيانات)
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

### مثال 4: قراءة أرقام الهواتف لجهة اتصال

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

### مثال 5: قراءة الرسائل SMS

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


# 📚 Android Complete Guide – Lifecycle, ViewModel, Gson, Volley, SharedPreferences

## 🟢 مقدمة

هذا الدليل يوضح لك كل شيء عن إدارة بيانات JSON و XML، التخزين المحلي، استخدام ViewModel و LiveData، وفهم **Activity/Fragment Lifecycle** مع سيناريوهات عملية كبيرة جداً، وطريقة طباعة **States على Logcat** لمراقبة كل الأحداث.

---

## 1️⃣ إعدادات Dependencies

```gradle
dependencies {
    implementation 'com.android.volley:volley:1.2.1'
    implementation 'com.google.code.gson:gson:2.10.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.2'
}
```

---

## 2️⃣ نموذج البيانات (Model Class)

```java
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
```

---

## 3️⃣ ViewModel + LiveData + SharedPreferences

```java
public class UserViewModel extends ViewModel {
    private MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public LiveData<User> getUser() { return userLiveData; }
    public void setUser(User user) { userLiveData.setValue(user); }

    public void saveUserToPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        prefs.edit().putString("user_data", gson.toJson(userLiveData.getValue())).apply();
        Log.d("UserViewModel", "Saved User to SharedPreferences: " + gson.toJson(userLiveData.getValue()));
    }

    public void loadUserFromPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String jsonString = prefs.getString("user_data", null);
        if(jsonString != null) {
            Gson gson = new Gson();
            userLiveData.setValue(gson.fromJson(jsonString, User.class));
            Log.d("UserViewModel", "Loaded User from SharedPreferences: " + jsonString);
        }
    }
}
```

---

## 4️⃣ JSON + Gson + Volley

```java
RequestQueue queue = Volley.newRequestQueue(this);
String url = "https://api.example.com/user";

JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
    response -> {
        Gson gson = new Gson();
        User user = gson.fromJson(response.toString(), User.class);
        Log.d("Volley", "User from API: " + user.getName() + ", " + user.getAge());

        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        prefs.edit().putString("user_data", response.toString()).apply();

        // تحديث ViewModel
        // userViewModel.setUser(user);
    },
    error -> Log.e("Volley", "Error: " + error.toString())
);

queue.add(request);
```

---

## 5️⃣ Activity Lifecycle + Logcat Tracking

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d("Lifecycle", "onCreate called");
}

@Override
protected void onStart() {
    super.onStart();
    Log.d("Lifecycle", "onStart called");
}

@Override
protected void onResume() {
    super.onResume();
    Log.d("Lifecycle", "onResume called");
}

@Override
protected void onPause() {
    super.onPause();
    Log.d("Lifecycle", "onPause called");
}

@Override
protected void onStop() {
    super.onStop();
    Log.d("Lifecycle", "onStop called");
}

@Override
protected void onRestart() {
    super.onRestart();
    Log.d("Lifecycle", "onRestart called");
}

@Override
protected void onDestroy() {
    super.onDestroy();
    Log.d("Lifecycle", "onDestroy called");
}
```

---

## 6️⃣ سيناريوهات كبيرة مع Logcat

### 6.1 الضغط على زر Back

```java
@Override
public void onBackPressed() {
    Log.d("LifecycleScenario", "Back button pressed");
    userViewModel.saveUserToPrefs(this);
    super.onBackPressed();
}
```

### 6.2 تغيير الاتجاه

```java
@Override
public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
        Log.d("LifecycleScenario", "Orientation: LANDSCAPE");
    } else {
        Log.d("LifecycleScenario", "Orientation: PORTRAIT");
    }
}
```

### 6.3 تشغيل فيديو والانتقال بين التطبيقات

```java
@Override
protected void onPause() {
    super.onPause();
    videoView.pause();
    Log.d("LifecycleScenario", "Video paused onPause");
}

@Override
protected void onResume() {
    super.onResume();
    videoView.start();
    Log.d("LifecycleScenario", "Video resumed onResume");
}
```

### 6.4 استقبال بيانات من الشبكة أثناء Lifecycle

```java
JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
    response -> {
        Gson gson = new Gson();
        User user = gson.fromJson(response.toString(), User.class);
        Log.d("LifecycleScenario", "Fetched User: " + user.getName());

        userViewModel.setUser(user);
        Log.d("LifecycleScenario", "User updated in ViewModel");
    },
    error -> Log.e("LifecycleScenario", "Error fetching user", error)
);
queue.add(request);
```

### 6.5 إعادة فتح التطبيق بعد الضغط على Home

```java
@Override
protected void onRestart() {
    super.onRestart();
    Log.d("LifecycleScenario", "App restarted");
    userViewModel.loadUserFromPrefs(this);
}
```

---

## 7️⃣ ملاحظات عملية

1. طباعة كل خطوة في **Logcat** تساعد على تتبع مشاكل Lifecycle والشبكة.
2. استخدم **ViewModel + LiveData** للحفاظ على البيانات عند تغييرات الشاشة.
3. احفظ دائمًا البيانات المهمة قبل الخروج أو عند onPause.
4. تعامل مع الشبكة و JSON بعناية لتجنب Crash.
5. يمكن توسيع السيناريوهات لتشمل **Fragment Lifecycle** و **Multiple Activities**.

---


# 📖 Android Lifecycle & Data Flow – Logcat Scenario

## 🔹 1. فتح التطبيق لأول مرة

```
2025-12-07 14:00:00.000 D/Lifecycle: onCreate called
2025-12-07 14:00:00.050 D/Lifecycle: onStart called
2025-12-07 14:00:00.100 D/Lifecycle: onResume called
2025-12-07 14:00:00.150 D/UserViewModel: Loaded User from SharedPreferences: null
2025-12-07 14:00:00.200 D/Volley: Sending GET request to https://api.example.com/user
2025-12-07 14:00:00.500 D/Volley: User from API: Ahmed, 25
2025-12-07 14:00:00.550 D/UserViewModel: User updated in ViewModel
2025-12-07 14:00:00.600 D/UserViewModel: Saved User to SharedPreferences: {"name":"Ahmed","age":25}
```

---

## 🔹 2. المستخدم يضغط زر Home (App goes to background)

```
2025-12-07 14:05:00.000 D/Lifecycle: onPause called
2025-12-07 14:05:00.050 D/Lifecycle: onStop called
2025-12-07 14:05:00.100 D/UserViewModel: Video paused onPause
```

---

## 🔹 3. المستخدم يعود للتطبيق (onRestart + onResume)

```
2025-12-07 14:10:00.000 D/Lifecycle: onRestart called
2025-12-07 14:10:00.050 D/UserViewModel: Loaded User from SharedPreferences: {"name":"Ahmed","age":25}
2025-12-07 14:10:00.100 D/Lifecycle: onStart called
2025-12-07 14:10:00.150 D/Lifecycle: onResume called
2025-12-07 14:10:00.200 D/UserViewModel: Video resumed onResume
```

---

## 🔹 4. المستخدم يغير اتجاه الشاشة (Orientation Change)

```
2025-12-07 14:15:00.000 D/LifecycleScenario: Orientation: PORTRAIT -> LANDSCAPE
2025-12-07 14:15:00.050 D/Lifecycle: onPause called
2025-12-07 14:15:00.100 D/Lifecycle: onStop called
2025-12-07 14:15:00.150 D/Lifecycle: onDestroy called
2025-12-07 14:15:00.200 D/Lifecycle: onCreate called
2025-12-07 14:15:00.250 D/Lifecycle: onStart called
2025-12-07 14:15:00.300 D/Lifecycle: onResume called
2025-12-07 14:15:00.350 D/UserViewModel: Loaded User from SharedPreferences: {"name":"Ahmed","age":25}
```

---

## 🔹 5. المستخدم يضغط زر Back للخروج

```
2025-12-07 14:20:00.000 D/LifecycleScenario: Back button pressed
2025-12-07 14:20:00.050 D/UserViewModel: Saved User to SharedPreferences: {"name":"Ahmed","age":25}
2025-12-07 14:20:00.100 D/Lifecycle: onPause called
2025-12-07 14:20:00.150 D/Lifecycle: onStop called
2025-12-07 14:20:00.200 D/Lifecycle: onDestroy called
```

---

## 🔹 6. تشغيل فيديو، ثم استقبال مكالمة واردة

```
2025-12-07 14:25:00.000 D/Lifecycle: onPause called
2025-12-07 14:25:00.050 D/UserViewModel: Video paused onPause
2025-12-07 14:25:00.100 D/Lifecycle: onStop called
```

بعد انتهاء المكالمة:

```
2025-12-07 14:30:00.000 D/Lifecycle: onRestart called
2025-12-07 14:30:00.050 D/Lifecycle: onStart called
2025-12-07 14:30:00.100 D/Lifecycle: onResume called
2025-12-07 14:30:00.150 D/UserViewModel: Video resumed onResume
```

---

## 🔹 7. تحديث بيانات من الشبكة أثناء تشغيل التطبيق

```
2025-12-07 14:35:00.000 D/Volley: Sending GET request to https://api.example.com/user
2025-12-07 14:35:00.300 D/Volley: User from API: Ali, 30
2025-12-07 14:35:00.350 D/UserViewModel: User updated in ViewModel
2025-12-07 14:35:00.400 D/UserViewModel: Saved User to SharedPreferences: {"name":"Ali","age":30}
```

---

## 🔹 8. التطبيق يعمل في الخلفية ويستقبل إشعار Network

```
2025-12-07 14:40:00.000 D/Lifecycle: onPause called
2025-12-07 14:40:00.050 D/Lifecycle: onStop called
2025-12-07 14:40:00.100 D/NotificationHandler: Received push notification
2025-12-07 14:40:00.150 D/NotificationHandler: User data fetched in background: {"name":"Ali","age":30}
```

---

## 🔹 9. التطبيق يعود ويستأنف الفيديو + تحديث بيانات JSON من SharedPreferences

```
2025-12-07 14:45:00.000 D/Lifecycle: onRestart called
2025-12-07 14:45:00.050 D/Lifecycle: onStart called
2025-12-07 14:45:00.100 D/Lifecycle: onResume called
2025-12-07 14:45:00.150 D/UserViewModel: Loaded User from SharedPreferences: {"name":"Ali","age":30}
2025-12-07 14:45:00.200 D/UserViewModel: Video resumed onResume
```

---
## 🔹10.  التطبيق مفتوح أول مرة

```
2025-12-07 15:00:00.000 D/Lifecycle: onCreate called
2025-12-07 15:00:00.050 D/Lifecycle: onStart called
2025-12-07 15:00:00.100 D/Lifecycle: onResume called
2025-12-07 15:00:00.150 D/UserViewModel: Loaded User from SharedPreferences: null
2025-12-07 15:00:00.200 D/Volley: Sending GET request to https://api.example.com/user
2025-12-07 15:00:00.500 D/Volley: User from API: Ahmed, 25
2025-12-07 15:00:00.550 D/UserViewModel: User updated in ViewModel
2025-12-07 15:00:00.600 D/UserViewModel: Saved User to SharedPreferences: {"name":"Ahmed","age":25}
2025-12-07 15:00:00.650 D/UserViewModel: Video started
```

---

## 🔹 11. المستخدم يضغط زر Home

```
2025-12-07 15:05:00.000 D/Lifecycle: onPause called
2025-12-07 15:05:00.050 D/UserViewModel: Video paused
2025-12-07 15:05:00.100 D/Lifecycle: onStop called
```

---

## 🔹 12.  أثناء وجود التطبيق في الخلفية – مكالمة واردة

```
2025-12-07 15:06:00.000 D/Lifecycle: (App is stopped)
2025-12-07 15:06:00.050 D/PhoneCall: Incoming call detected
2025-12-07 15:06:00.100 D/UserViewModel: Pausing background tasks / notifications
```

---

## 🔹 13.  المستخدم ينهي المكالمة ويعود للتطبيق

```
2025-12-07 15:10:00.000 D/Lifecycle: onRestart called
2025-12-07 15:10:00.050 D/Lifecycle: onStart called
2025-12-07 15:10:00.100 D/Lifecycle: onResume called
2025-12-07 15:10:00.150 D/UserViewModel: Loaded User from SharedPreferences: {"name":"Ahmed","age":25}
2025-12-07 15:10:00.200 D/UserViewModel: Video resumed
```

---

## 🔹 14.  المستخدم يغير اتجاه الشاشة (Portrait → Landscape)

```
2025-12-07 15:15:00.000 D/LifecycleScenario: Orientation change detected
2025-12-07 15:15:00.050 D/Lifecycle: onPause called
2025-12-07 15:15:00.100 D/Lifecycle: onStop called
2025-12-07 15:15:00.150 D/Lifecycle: onDestroy called
2025-12-07 15:15:00.200 D/Lifecycle: onCreate called
2025-12-07 15:15:00.250 D/Lifecycle: onStart called
2025-12-07 15:15:00.300 D/Lifecycle: onResume called
2025-12-07 15:15:00.350 D/UserViewModel: Loaded User from SharedPreferences: {"name":"Ahmed","age":25}
2025-12-07 15:15:00.400 D/UserViewModel: Video resumed
```

---

## 🔹 15.  المستخدم يضغط زر Back داخل Activity

```
2025-12-07 15:20:00.000 D/LifecycleScenario: Back button pressed
2025-12-07 15:20:00.050 D/UserViewModel: Saved User to SharedPreferences: {"name":"Ahmed","age":25}
2025-12-07 15:20:00.100 D/Lifecycle: onPause called
2025-12-07 15:20:00.150 D/Lifecycle: onStop called
2025-12-07 15:20:00.200 D/Lifecycle: onDestroy called
```

---

## 🔹 16.  المستخدم يفتح التطبيق مرة ثانية، البيانات محدثة من الشبكة

```
2025-12-07 15:25:00.000 D/Lifecycle: onCreate called
2025-12-07 15:25:00.050 D/Lifecycle: onStart called
2025-12-07 15:25:00.100 D/Lifecycle: onResume called
2025-12-07 15:25:00.150 D/UserViewModel: Loaded User from SharedPreferences: {"name":"Ahmed","age":25}
2025-12-07 15:25:00.200 D/Volley: Sending GET request to https://api.example.com/user
2025-12-07 15:25:00.500 D/Volley: User from API: Ali, 30
2025-12-07 15:25:00.550 D/UserViewModel: User updated in ViewModel
2025-12-07 15:25:00.600 D/UserViewModel: Saved User to SharedPreferences: {"name":"Ali","age":30}
```

---

```
          onCreate
             |
             v
          onStart
             |
             v
          onResume -------------------------+
             |                               |
             |                               |
             v                               |
          onPause                             |
             |                               |
             v                               |
          onStop ----------------------------+
             |
             v
          onRestart
             |
             v
          onStart
             |
             v
          onResume
             |
          (Back/Home/Orientation/Call)
             |
         onPause -> onStop -> onDestroy
```
---

# 📚 شرح RecyclerView Adapter + ViewHolder + Object + XML

---

## 1️⃣ ليش نستخدم **RecyclerView + Adapter + ViewHolder**

### RecyclerView:

* هو عنصر عرض **قابل للتمرير بكفاءة عالية**.
* يعرض قائمة من العناصر (List) أو شبكة (Grid).
* يقوم **إعادة استخدام Views** لتوفير أداء أفضل بدل إنشاء كل عنصر من جديد.

### Adapter:

* هو الوسيط بين **مصدر البيانات (Data Source)** و **RecyclerView**.
* مسؤول عن:

  1. إنشاء العناصر (`onCreateViewHolder`)
  2. ربط البيانات بالعنصر (`onBindViewHolder`)
  3. معرفة عدد العناصر (`getItemCount`)

### ViewHolder:

* يمثل **عنصر واحد في القائمة**.
* يحتوي على **References لكل View داخل العنصر** (TextView, ImageView, إلخ).
* سبب استخدامه:

  * تحسين الأداء لأننا لا نعيد استدعاء `findViewById` لكل مرة.
  * كل ViewHolder مرتبط بعنصر واحد فقط، ويعاد استخدامه أثناء التمرير.

---

## 2️⃣ Object + XML Layout

### Object (Model Class)

* يمثل **البنية المنطقية للبيانات**.
* مثال: `User` يحتوي على `name, age, email`.
* كل عنصر في RecyclerView سيمثل **كائن User واحد**.

```java
public class User {
    private String name;
    private int age;
    private String email;
}
```

### XML Layout

* يمثل **المظهر المرئي لكل عنصر** في القائمة.
* لماذا منفصل؟:

  1. فصل الـ **UI** عن البيانات والـ **logic**.
  2. يمكن إعادة استخدامه لعناصر متعددة.
  3. يمكن التعديل على المظهر بدون تغيير الكود.

مثال: `item_user.xml`

```xml
<LinearLayout
    android:orientation="vertical"
    android:padding="12dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <TextView android:id="@+id/tvName"/>
    <TextView android:id="@+id/tvAge"/>
    <TextView android:id="@+id/tvEmail"/>
</LinearLayout>
```

---

## 3️⃣ العلاقة بين Object + Adapter + ViewHolder + XML

```
[User Object]  -> [Adapter] -> [ViewHolder] -> [XML Layout] -> [RecyclerView Display]
```

### كيف تعمل؟

1. Adapter يأخذ **قائمة من Objects** (مثلاً List<User>)
2. Adapter ينشئ **ViewHolder** لكل عنصر
3. ViewHolder يحمل **References** للـ Views في XML
4. Adapter يربط بيانات كل Object بالـ Views (`onBindViewHolder`)
5. RecyclerView يعرض العناصر، ويعيد استخدام ViewHolders أثناء التمرير

---

## 4️⃣ مثال عملي:

### Adapter + ViewHolder:

```java
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;

    public UserAdapter(List<User> userList) { this.userList = userList; }

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
        holder.tvName.setText(user.getName());
        holder.tvAge.setText("Age: " + user.getAge());
        holder.tvEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() { return userList.size(); }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAge, tvEmail;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
```

### RecyclerView في Activity:

```java
RecyclerView recyclerView = findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
UserAdapter adapter = new UserAdapter(users);
recyclerView.setAdapter(adapter);
```

---


# 📖 XML Object + Java Object + Adapter + ViewHolder

## 🔹 1️⃣ XML Object – Users

```xml
<USERS>
    <USER>
        <NAME>Ahmed</NAME>
        <AGE>25</AGE>
        <EMAIL>ahmed@example.com</EMAIL>
        <ADDRESS>
            <STREET>123 Main St</STREET>
            <CITY>Ramallah</CITY>
            <ZIP>12345</ZIP>
        </ADDRESS>
        <PHONES>
            <PHONE>0591234567</PHONE>
            <PHONE>0597654321</PHONE>
        </PHONES>
    </USER>

    <USER>
        <NAME>Ali</NAME>
        <AGE>30</AGE>
        <EMAIL>ali@example.com</EMAIL>
        <ADDRESS>
            <STREET>456 Side St</STREET>
            <CITY>Jerusalem</CITY>
            <ZIP>67890</ZIP>
        </ADDRESS>
        <PHONES>
            <PHONE>0593334444</PHONE>
        </PHONES>
    </USER>
</USERS>
```

**شرح :**

* `<USERS>` → القائمة الكاملة للمستخدمين
* `<USER>` → كائن مستخدم واحد
* `<ADDRESS>` → كائن داخلي يحتوي على العنوان
* `<PHONES>` → قائمة أرقام الهاتف
* هذا التصميم مشابه تمامًا لنموذج JSON Object، فقط بصيغة XML

---

## 🔹 2️⃣ Java Object – User + Address

```java
public class Address {
    private String street;
    private String city;
    private String zip;
    // getters & setters
}

public class User {
    private String name;
    private int age;
    private String email;
    private Address address;
    private List<String> phones;
    // getters & setters
}
```

**شرح :**

* يعكس **هيكل الـ XML**
* يمكن قراءة البيانات من XML وتحويلها إلى هذه الكائنات

---

## 🔹 3️⃣ Adapter + ViewHolder

```java
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;

    public UserAdapter(List<User> userList) { this.userList = userList; }

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
        holder.tvName.setText(user.getName());
        holder.tvEmail.setText(user.getEmail());
        holder.tvCity.setText(user.getAddress().getCity());
        holder.tvPhones.setText(TextUtils.join(", ", user.getPhones()));
    }

    @Override
    public int getItemCount() { return userList.size(); }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail, tvCity, tvPhones;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvPhones = itemView.findViewById(R.id.tvPhones);
        }
    }
}
```

**شرح :**

* **Adapter**: يربط البيانات من Java Objects إلى RecyclerView
* **ViewHolder**: يحافظ على References للـ Views لتحسين الأداء
* عند تمرير العناصر، RecyclerView **يعيد استخدام ViewHolders** لتوفير الذاكرة

---

## 🔹 4️⃣ XML Layout لكل عنصر (`item_user.xml`)

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="12dp"
    android:background="?android:attr/selectableItemBackground">

    <TextView android:id="@+id/tvName" android:textSize="18sp" android:textStyle="bold"/>
    <TextView android:id="@+id/tvEmail"/>
    <TextView android:id="@+id/tvCity"/>
    <TextView android:id="@+id/tvPhones"/>
</LinearLayout>
```

**شرح :**

* كل عنصر User يعرض **Name, Email, City, Phones**
* XML منفصل عن الكود لضمان **إعادة الاستخدام والفصل بين UI وLogic**

---

## 🔹 5️⃣ ملاحظات مهمة

1. **XML Object** مشابه جدًا للـ JSON Object، الفرق فقط في الصياغة
2. **Adapter + ViewHolder** يحافظ على الأداء أثناء عرض القائمة
3. **Java Object** يمثل البيانات بشكل منطقي، ويستعمل مع أي مصدر: JSON أو XML

---


## 📚 مصادر إضافية

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

**تم إنشاء هذا الدليل لمساعدة المطورين على فهم أساسيات Android UI و Data Persistence** 🚀

💡 **نصيحة أخيرة:** مارس بكتابة الكود بنفسك! لا تكتفي بالقراءة فقط.
