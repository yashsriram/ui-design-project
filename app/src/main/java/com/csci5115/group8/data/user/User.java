package com.csci5115.group8.data.user;

public class User {
    public String email;
    public String password;
    public String name;
    public String gender;
    public int age;
    public int maxBudget;
    public boolean doesSmoke;
    public boolean drugsOkay;
    public boolean hasPets;
    public boolean partiesOkay;
    public boolean canCook;
    public boolean needsPrivateBedroom;
    public String nativeLanguage;
    public boolean hasCar;
    public boolean isVerified;

    public User(String email, String password, String name, String gender, int age, int maxBudget, boolean doesSmoke, boolean drugsOkay, boolean hasPets, boolean partiesOkay, boolean canCook, boolean needsPrivateBedroom, String nativeLanguage, boolean hasCar, boolean isVerified) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.maxBudget = maxBudget;
        this.doesSmoke = doesSmoke;
        this.drugsOkay = drugsOkay;
        this.hasPets = hasPets;
        this.partiesOkay = partiesOkay;
        this.canCook = canCook;
        this.needsPrivateBedroom = needsPrivateBedroom;
        this.nativeLanguage = nativeLanguage;
        this.hasCar = hasCar;
        this.isVerified = isVerified;
    }

    public User(String email, String password, String name, String gender, int age) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.maxBudget = -1;
        this.doesSmoke = false;
        this.drugsOkay = false;
        this.hasPets = false;
        this.partiesOkay = false;
        this.canCook = false;
        this.needsPrivateBedroom = false;
        this.nativeLanguage = "";
        this.hasCar = false;
    }
}
