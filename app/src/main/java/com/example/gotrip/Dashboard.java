package com.example.gotrip;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Center the SearchView's text and icon
        SearchView searchView = findViewById(R.id.search_view);

        // Center the input/hint text
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setGravity(Gravity.CENTER);
        searchEditText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // Remove default paddings from the text container
        View searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        searchPlate.setPadding(0, 0, 0, 0);

        // Center the search icon
        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        searchIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        searchIcon.setPadding(0, 0, 0, 0);
    }
}
