package com.raaceinm.androidpracticals.Tools;
import java.io.Serializable;
public class Sterilization implements Serializable {
    private Float phone;
    private String email;
    private String password;
    private String name;
    private Integer age;

    public Sterilization(Float phone, String email, String password, String name, int age){
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public Float getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
}
