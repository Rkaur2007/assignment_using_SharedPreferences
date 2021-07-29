package com.example.ass;

public class profile {
    protected String name;
    protected String age;
    protected String id;

    public profile(String name,String age, String id)
    {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean checkValidInput(profile profile)
    {
        if (profile.getName() == null)                          //Profile name should not be null
            return false;
        else if (profile.getAge() == null)                      //Profile age should not be null
            return false;
        else if (profile.getId() == null)                       //Profile ID should not be null
            return false;
        else if (profile.getName().equals(""))                  //Profile name should not be empty
            return false;
        else if (profile.getAge().equals(""))                   //Profile age should not be empty
            return false;
        else if (profile.getId().equals(""))                    //Profile ID should not be empty
            return false;
        else //Profile age should be less than or equal to 99
            if (Integer.parseInt(profile.getAge()) < 18)       //Profile age should be greater than or equal to 18
            return false;
        else return Integer.parseInt(profile.getAge()) <= 99;
    }
}
