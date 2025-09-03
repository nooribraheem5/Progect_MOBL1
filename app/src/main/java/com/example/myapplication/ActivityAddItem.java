package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityAddItemBinding;

public class ActivityAddItem extends AppCompatActivity {

    private ActivityAddItemBinding binding;
    private String nameBook, nameAuthor, BookType, descriptionBook, URLBook;
    private String dateshowBook = "";
    private String ratingBook = "";
    private String image; // الرقم/معرف الصورة المختارة
    private ImageView imageViewBook; // ImageView الرئيسي في الواجهة

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ضبط الحواف للتوافق مع EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageViewBook = binding.bookImage; // ImageView الرئيسي لعرض الصورة المختارة

        setupSpinners();
        setupButtons();
    }

    private void setupSpinners() {
        // Spinner نوع الكتاب
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(
                this, R.array.department_Book, android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerDep.setAdapter(adapterType);
        binding.spinnerDep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookType = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Spinner التقييم
        ArrayAdapter<CharSequence> adapterRating = ArrayAdapter.createFromResource(
                this, R.array.Rating_Book, android.R.layout.simple_spinner_item);
        adapterRating.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerRate.setAdapter(adapterRating);
        binding.spinnerRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ratingBook = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupButtons() {
        // زر اختيار الصورة
        binding.btnAddImage.setOnClickListener(v -> showImageSelectionDialog());

        // زر العودة
        binding.btnBack.setOnClickListener(v -> finish());

        // زر الحفظ
        binding.btnAddBook.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                collectData();
                sendResult();
            }
        });

        // اختيار التاريخ
        binding.edDate.setOnClickListener(v -> {
            DataFragment dataFragment = new DataFragment();
            dataFragment.show(getSupportFragmentManager(), "Date Picker");
        });
    }

    private void showImageSelectionDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_item_book, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();

        ImageView image1 = dialogView.findViewById(R.id.image1);
        ImageView image2 = dialogView.findViewById(R.id.image2);
        ImageView image3 = dialogView.findViewById(R.id.image3);
        ImageView image4 = dialogView.findViewById(R.id.image4);
        ImageView image5 = dialogView.findViewById(R.id.image5);
        ImageView image6 = dialogView.findViewById(R.id.image6);

        image1.setOnClickListener(v -> { image = "1"; dialog.dismiss(); imageViewBook.setImageResource(R.drawable.image1); });
        image2.setOnClickListener(v -> { image = "2"; dialog.dismiss(); imageViewBook.setImageResource(R.drawable.image2); });
        image3.setOnClickListener(v -> { image = "3"; dialog.dismiss(); imageViewBook.setImageResource(R.drawable.image3); });
        image4.setOnClickListener(v -> { image = "4"; dialog.dismiss(); imageViewBook.setImageResource(R.drawable.image4); });
        image5.setOnClickListener(v -> { image = "5"; dialog.dismiss(); imageViewBook.setImageResource(R.drawable.image5); });
        image6.setOnClickListener(v -> { image = "6"; dialog.dismiss(); imageViewBook.setImageResource(R.drawable.image6); });
    }

    private boolean checkEmptyFields() {
        if (binding.edBookName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter book name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.edAuthorName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter author name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.spinnerDep.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select book type", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.edDescription.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter description", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.edDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please select date", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.spinnerRate.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select rating", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.edUrl.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter URL", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (image == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void collectData() {
        nameBook = binding.edBookName.getText().toString();
        nameAuthor = binding.edAuthorName.getText().toString();
        descriptionBook = binding.edDescription.getText().toString();
        dateshowBook = binding.edDate.getText().toString();
        URLBook = binding.edUrl.getText().toString();
    }

    private void sendResult() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("nameBook", nameBook);
        resultIntent.putExtra("nameAuthor", nameAuthor);
        resultIntent.putExtra("type", BookType);
        resultIntent.putExtra("Description", descriptionBook);
        resultIntent.putExtra("Rating", ratingBook);
        resultIntent.putExtra("dateShow", dateshowBook);
        resultIntent.putExtra("URLBook", URLBook);
        resultIntent.putExtra("bakimage", image); // الرقم/معرف الصورة
        setResult(RESULT_OK, resultIntent);
        Toast.makeText(this, "Book added successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        dateshowBook = month_string + "/" + day_string + "/" + year_string;
        binding.edDate.setText(dateshowBook);
    }
}
