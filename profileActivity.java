package com.example.ass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class profileActivity extends AppCompatActivity {

    protected profile userProfile;
    protected EditText getmynameEditText;
    protected EditText getmyageEditText;
    protected EditText getmystudentID_EditText;
    protected Button getmysaveProfileButton;
    protected boolean editMode; //Will be true if we are in edit mode
    SharedPreferenceHelper sharedpreferencehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedpreferencehelper = new SharedPreferenceHelper(profileActivity.this);
        setupUI();
        updateUI();
        setupOnClickListeners();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editMode = sharedpreferencehelper.gettingEditMode();
    }
    @Override
    protected void onStart() {
        super.onStart();
        userProfile = sharedpreferencehelper.gettingProfile();
        updateUI();
        updateEditTexts(); //This makes sure the EditText will always display the profile name when onStart() is called
    }

    private void setupUI() //Links references to view id's
    {
        getmynameEditText = findViewById(R.id.mynameEdit);
        getmysaveProfileButton = findViewById(R.id.saveProfile);
        getmyageEditText = findViewById(R.id.myageEdit);
        getmystudentID_EditText = findViewById(R.id.myStudentID_Edit);
    }

    private void setupOnClickListeners()
    {
        getmysaveProfileButton.setOnClickListener(v -> {
            profile profileCheck = new profile(getmynameEditText.getText().toString(),getmyageEditText.getText().toString(),getmystudentID_EditText.getText().toString());
            if (profile.checkValidInput(profileCheck)) //We check if the profile meets the criteria
            {
                sharedpreferencehelper.savingProfile(profileCheck);
                saveProfile(profileCheck);
                Toast.makeText(getApplicationContext(),"Saved successfully",Toast.LENGTH_LONG).show();
                sharedpreferencehelper.savingEditMode(false);
                editMode = false;
                updateUI();
                getmysaveProfileButton.setVisibility(View.GONE); //if we want to get rid of button
            }

            else
            {
                Toast.makeText(getApplicationContext(),"Check Input please",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateEditTexts()
    {
        getmynameEditText.setText(userProfile.getName());
        getmyageEditText.setText(userProfile.getAge());
        getmystudentID_EditText.setText(userProfile.getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            sharedpreferencehelper.savingEditMode(true);
            enableEditTexts();
            getmysaveProfileButton.setVisibility(View.VISIBLE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveProfile(profile profile)
    {
        sharedpreferencehelper.saveProName(profile.getName());
        sharedpreferencehelper.saveProAge(profile.getAge());
        sharedpreferencehelper.saveProStudentID(profile.getId());
    }

    private void enableEditTexts() //This function enables all the EditText in the activity
    {
        getmynameEditText.setEnabled(true);
        getmyageEditText.setEnabled(true);
        getmystudentID_EditText.setEnabled(true);
    }

    private void disableEditTexts() //This function enables all the EditText in the activity
    {
        getmynameEditText.setEnabled(false);
        getmyageEditText.setEnabled(false);
        getmystudentID_EditText.setEnabled(false);
    }

    private void updateUI()
    {
        if (editMode)
        {
            enableEditTexts();
            getmysaveProfileButton.setVisibility(View.VISIBLE);
        }
        else
        {
            disableEditTexts();
            getmysaveProfileButton.setVisibility(View.GONE);
        }
    }

}