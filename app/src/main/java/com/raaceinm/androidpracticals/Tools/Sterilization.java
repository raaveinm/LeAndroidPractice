package com.raaceinm.androidpracticals.Tools;
import java.io.Serializable;
public class Sterilization implements Serializable {
    private Float phone;
    private String email;
    private String password;
    private String name;
    private Integer age;
    private String username;
    private String loginPassword;

    public Sterilization() {}

    public void setPersonalData(Float phone, String email, String password, String name, int age) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public void setLoginData(String username, String loginPassword) {
        this.username = username;
        this.loginPassword = loginPassword;
    }

    public Float getPhone() {return phone;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public Integer getAge() {return age;}
    public String getUsername() {return username;}
    public String getLoginPassword(){return loginPassword;}
}
