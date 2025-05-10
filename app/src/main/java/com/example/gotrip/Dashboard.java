package com.example.gotrip;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    private EditText editTextFrom, editTextTo;
    private ImageButton changeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize the views
        editTextFrom = findViewById(R.id.ediTextFrom);
        editTextTo = findViewById(R.id.editTextTo);
        changeIcon = findViewById(R.id.changeicon);

        // Set the changeIcon button's onClick listener
        changeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText fields
                String fromText = editTextFrom.getText().toString();
                String toText = editTextTo.getText().toString();

                // Swap the text between the two EditText fields
                editTextFrom.setText(toText);
                editTextTo.setText(fromText);
            }
        });
    }
}
