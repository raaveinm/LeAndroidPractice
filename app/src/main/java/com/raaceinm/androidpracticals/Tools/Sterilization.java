package com.raaceinm.androidpracticals.Tools;

import java.io.Serializable;

public class Sterilization implements Serializable {
    private static Float phone;
    private static String email;
    private static String password;
    private static String name;
    private static Integer age;
    private static String username;
    private static String loginPassword;

    public Sterilization() {}

    public void setPersonalData(Float phone, String email, String password, String name, int age) {
        Sterilization.phone = phone;
        Sterilization.email = email;
        Sterilization.password = password;
        Sterilization.name = name;
        Sterilization.age = age;
    }

    public void setLoginData(String username, String loginPassword) {
        Sterilization.username = username;
        Sterilization.loginPassword = loginPassword;
    }

    public static Float getPhone() {return phone;}
    public static String getEmail() {return email;}
    public static String getPassword() {return password;}
    public static String getName() {return name;}
    public static Integer getAge() {return age;}
    public String getUsername() {return username;}
    public String getLoginPassword(){return loginPassword;}
}
