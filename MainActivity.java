package com.example.ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button profileNameDisplayButton;
    SharedPreferenceHelper sharedpreferencehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Creating Instance of Java file
        sharedpreferencehelper = new SharedPreferenceHelper(MainActivity.this);

        // Instantiating Button
        profileNameDisplayButton = findViewById(R.id.showmyprofileNameDisplay);
        setupOnClickListeners();

        if (checkNameEmpty())
        {
            Intent intent = new Intent(MainActivity.this,profileActivity.class);
            startActivity(intent);
        }
    }



    // Button will open child activity when name is found
    private void setupOnClickListeners()
    {
        profileNameDisplayButton.setOnClickListener(v -> {
            sharedpreferencehelper.savingEditMode(false);
            Intent intent = new Intent(MainActivity.this,profileActivity.class);
            startActivity(intent);
        });
    }

    // Open child activity if no name entry found
    @Override
    protected void onStart() {
        super.onStart();
        String profilename = sharedpreferencehelper.getProName();
        profileNameDisplayButton.setText(profilename);
        if (checkNameEmpty())
        {
            Intent intent = new Intent(MainActivity.this,profileActivity.class);
            startActivity(intent);
        }
    }

    //check if there is no entry for name
    private boolean checkNameEmpty()
    {
        String profilename = sharedpreferencehelper.getProName();

        return profilename == null;
    }


}