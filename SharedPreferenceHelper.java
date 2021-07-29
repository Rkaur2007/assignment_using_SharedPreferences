package com.example.ass;

import android.content.Context;
import android.content.SharedPreferences;

//the controller of the application
public class SharedPreferenceHelper {
    private final SharedPreferences sharedPreferences;
    private final Context context;
    public SharedPreferenceHelper(Context context)
    {
        sharedPreferences = context.getSharedPreferences("ProfilePreference",
                Context.MODE_PRIVATE );
        this.context=context;
    }
    public void saveProName(String name)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit(); editor.putString("profileName",name );
        editor.apply();
    }
    public String getProName()
    {
        return sharedPreferences.getString("profileName", null);
    }

    public void saveProAge(String age)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.profile_age_key),age);
        editor.apply();
    }

    public String getProAge()
    {
        return sharedPreferences.getString(context.getString(R.string.profile_age_key),"0");
    }

    public void saveProStudentID(String id)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.student_id_key),id);
        editor.apply();
    }

    public String getProStudentID()
    {
        return sharedPreferences.getString(context.getString(R.string.student_id_key), "0");
    }

    public void savingEditMode(boolean editmode)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("editMode",editmode);
        editor.apply();
    }

    public boolean gettingEditMode()
    {
        return sharedPreferences.getBoolean("editMode",true);
    }

    public void savingProfile(profile profile) //Takes a profile class as a parameter and saves the name, age and ID in Shared Preferences
    {
        saveProName(profile.getName());
        saveProAge(profile.getAge());
        saveProStudentID(profile.getId());
    }

    public profile gettingProfile() //Returns a reference to a profile, based on the values saved in SharedPreferences
    {
        return new profile(getProName(),getProAge(),getProStudentID()); //Takes a reference to the Profile
    }


}
