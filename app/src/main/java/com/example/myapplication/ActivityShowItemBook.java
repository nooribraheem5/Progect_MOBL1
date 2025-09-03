package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityShowItemBookBinding;

public class ActivityShowItemBook extends AppCompatActivity {
    private String urlBook;
    private ActivityShowItemBookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // تفعيل View Binding
        binding = ActivityShowItemBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//        // التعامل مع الـ system bars بشكل آمن
//        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        // زر الرجوع
        binding.btnBack.setOnClickListener(v -> finish());

        // الحصول على البيانات من Intent
        Intent intent = getIntent();
        String nameBook = intent.getStringExtra("nameBook");
        String nameAuthor = intent.getStringExtra("nameAuthor");
        int image = intent.getIntExtra("bakimage", 0);
        String dateShow = intent.getStringExtra("dateShow");
        String type = intent.getStringExtra("type");
        String description = intent.getStringExtra("Description");
        String ratingStr = intent.getStringExtra("Rating");
        urlBook = intent.getStringExtra("URLBook");

        // عرض البيانات في الـ UI
        binding.tvBookname.setText(nameBook != null ? nameBook : "");
        binding.tvAuthor.setText(nameAuthor != null ? nameAuthor : "");
        binding.tvPubdate.setText(dateShow != null ? dateShow : "");
        binding.tvDepartment.setText(type != null ? type : "");
        binding.tvDescrition.setText(description != null ? description : "");
        binding.tvRate.setText(ratingStr != null ? ratingStr : "0");

        if (ratingStr != null) {
            try {
                binding.rateBar.setRating(Float.parseFloat(ratingStr));
            } catch (NumberFormatException e) {
                binding.rateBar.setRating(0f);
            }
        }

        binding.imageBook.setImageResource(image);

        // فتح الرابط عند الضغط على الزر
        binding.btnOpenURL.setOnClickListener(v -> {
            if (urlBook != null && !urlBook.isEmpty()) {
                if (!urlBook.startsWith("http://") && !urlBook.startsWith("https://"))
                    urlBook = "http://" + urlBook;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlBook));
                startActivity(browserIntent);
            }
        });
    }
}
