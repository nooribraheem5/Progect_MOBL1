package com.example.myapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    private ArrayList<ItemBook> itemList;
    private  ItemBookAdapterextends itemBookAdapterextends;
    private static final int ADD_ITEM_REQUEST_CODE = 1;


    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "LayoutPrefs";
    private static final String KEY_LAYOUT_MANAGER = "LayoutManager";
    private static final String LAYOUT_LINEAR = "LinearLayout";
    private static final String LAYOUT_GRID = "GridLayout";
    boolean isGrid=false;

    private String currentLayoutManagerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // تهيئة SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);


        // تهيئة القائمة
        itemList = new ArrayList<ItemBook>();

        addItemLest();


//        Button Add Item
        mainBinding.actMainButAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityAddItem.class);
            startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
        });


        itemBookAdapterextends = new ItemBookAdapterextends(this, itemList,isGrid);
        mainBinding.actMainRecycler.setAdapter(itemBookAdapterextends);
        // تعيين مدير التخطيط إلى RecyclerView
        mainBinding.actMainRecycler.setLayoutManager(new LinearLayoutManager(this));
        // تحميل نوع التخطيط المحفوظ وتطبيقه
        loadLayoutManagerPreference();
        setLayoutManager(currentLayoutManagerType);


        mainBinding.buttonToggleLayout.setOnClickListener(v -> toggleLayoutManager());








    }

    // دالة لإضافة عناصر أولية إلى القائمة
    private void addItemLest() {
        itemList.add(new ItemBook(R.drawable.image1
                , getString(R.string.nameBook1)
                , getString(R.string.nameAuthorBook1)
                , getString(R.string.dateShowBook1)
                , getString(R.string.nameTypeBook1)
                , getString(R.string.DetelesBook1)
                , getString(R.string.RatingBook1)
                , getString(R.string.URLBook1)
        ));

        itemList.add(new ItemBook(R.drawable.image2
                , getString(R.string.nameBook2)
                , getString(R.string.nameAuthorBook2)
                , getString(R.string.dateShowBook2)
                , getString(R.string.nameTypeBook2)
                , getString(R.string.DetelesBook2)
                , getString(R.string.RatingBook2)
                , getString(R.string.URLBook2)
        ));

        itemList.add(new ItemBook(R.drawable.image3
                , getString(R.string.nameBook3)
                , getString(R.string.nameAuthorBook3)
                , getString(R.string.dateShowBook3)
                , getString(R.string.nameTypeBook3)
                , getString(R.string.DetelesBook3)
                , getString(R.string.RatingBook3)
                , getString(R.string.URLBook3)
        ));

        itemList.add(new ItemBook(R.drawable.image4
                , getString(R.string.nameBook4)
                , getString(R.string.nameAuthorBook4)
                , getString(R.string.dateShowBook4)
                , getString(R.string.nameTypeBook4)
                , getString(R.string.DetelesBook4)
                , getString(R.string.RatingBook4)
                , getString(R.string.URLBook4)

        ));

        itemList.add(new ItemBook(R.drawable.image5
                , getString(R.string.nameBook5)
                , getString(R.string.nameAuthorBook5)
                , getString(R.string.dateShowBook5)
                , getString(R.string.nameTypeBook5)
                , getString(R.string.DetelesBook5)
                , getString(R.string.RatingBook5)
                , getString(R.string.URLBook5)
        ));

        itemList.add(new ItemBook(R.drawable.image6
                , getString(R.string.nameBook6)
                , getString(R.string.nameAuthorBook6)
                , getString(R.string.dateShowBook6)
                , getString(R.string.nameTypeBook6)
                , getString(R.string.DetelesBook6)
                , getString(R.string.RatingBook6)
                , getString(R.string.URLBook6)
        ));
    }


    // دالة لتعيين مدير التخطيط (خطي أو شبكي)
    private void setLayoutManager(String layoutManagerType) {
        if (layoutManagerType.equals(LAYOUT_GRID)) {
            isGrid=true;
            mainBinding.actMainRecycler.setLayoutManager(new GridLayoutManager(this, 2)); // 2 هو عدد الأعمدة، يمكنك تغييره
            mainBinding.buttonToggleLayout.setImageResource(R.drawable.menu); // تغيير الأيقونة إلى "عرض ك قائمة"
            currentLayoutManagerType = LAYOUT_GRID;
        } else { // LAYOUT_LINEAR أو أي قيمة افتراضية
            isGrid=false;
            mainBinding.actMainRecycler.setLayoutManager(new LinearLayoutManager(this));
            mainBinding.buttonToggleLayout.setImageResource(R.drawable.grid); // تغيير الأيقونة إلى "عرض ك شبكة"
            currentLayoutManagerType = LAYOUT_LINEAR;
        }

        if (itemBookAdapterextends != null) {
            itemBookAdapterextends.setLayoutType(isGrid);
        }
    }

    // دالة لتبديل بين مديري التخطيط
    private void toggleLayoutManager() {
        if (currentLayoutManagerType.equals(LAYOUT_LINEAR)) {
            setLayoutManager(LAYOUT_GRID);
        } else {
            setLayoutManager(LAYOUT_LINEAR);
        }
        saveLayoutManagerPreference(currentLayoutManagerType);
    }

    // دالة لحفظ تفضيل مدير التخطيط في SharedPreferences
    private void saveLayoutManagerPreference(String layoutManagerType) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LAYOUT_MANAGER, layoutManagerType);
        editor.apply();
    }

    // دالة لتحميل تفضيل مدير التخطيط من SharedPreferences
    private void loadLayoutManagerPreference() {
        currentLayoutManagerType = sharedPreferences.getString(KEY_LAYOUT_MANAGER, LAYOUT_LINEAR); // الافتراضي هو LinearLayout
    }


    // هذه الدالة تُستدعى عند عودة نتيجة من نشاط آخر (مثل ActivityAddItem)

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == RESULT_OK){
            assert data != null;
            // استخراج البيانات المرسلة من ActivityAddItem
            String image = data.getStringExtra("bakimage");
            int bakimage = R.drawable.image1 ;
            // تحديد الصورة بناءً على القيمة المستلمة
            if (image.equals("1")){
                bakimage = R.drawable.image1 ;
            }else if (image.equals("2")){
                bakimage = R.drawable.image2 ;
            }else if (image.equals("3")){
                bakimage = R.drawable.image3 ;
            }else if (image.equals("4")){
                bakimage = R.drawable.image4 ;
            }else if (image.equals("5")){
                bakimage =R.drawable.image5;
            }else if (image.equals("6")) {
                bakimage = R.drawable.image6;
            }
            // إضافة عنصر جديد إلى القائمة بالبيانات المستلمة
            itemList.add(new ItemBook(
                    bakimage
                    ,data.getStringExtra("nameBook")
                    ,data.getStringExtra("nameAuthor")
                    ,data.getStringExtra("dateShow")
                    ,data.getStringExtra("type")
                    ,data.getStringExtra("Description")
                    ,data.getStringExtra("Rating")
                    ,data.getStringExtra("URLBook")
            ));



        }       // تحديث الـ Adapter لإعلامه بالتغيير في البيانات
        // من الأفضل استخدام notifyItemInserted لتأثيرات أفضل إذا كان الـ Adapter يدعمها
        itemBookAdapterextends.notifyItemInserted(itemList.size() - 1);

        // إعادة تعيين الـ Adapter والمخطط للـ RecyclerView لتحديث العرض
        mainBinding.actMainRecycler.setAdapter(itemBookAdapterextends);
        mainBinding.actMainRecycler.setLayoutManager(new LinearLayoutManager(this));



    }




}