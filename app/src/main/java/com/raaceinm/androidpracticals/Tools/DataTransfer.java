package com.raaceinm.androidpracticals.Tools;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DataTransfer implements Parcelable {

    private Float phone;
    private String email;
    private String password;
    private String name;
    private Integer age;

    public DataTransfer(Float phone, String email, String password, String name, int age) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    protected DataTransfer(Parcel in) {
        if (in.readByte() == 0) {
            phone = null;
        } else {
            phone = in.readFloat();
        }
        email = in.readString();
        password = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            age = null;
        } else {
            age = in.readInt();
        }

        //  for list it will be
        //  favoriteColors = new ArrayList<>();
        //  in.readStringList(favoriteColors);
    }

    public static final Creator<DataTransfer> CREATOR = new Creator<DataTransfer>() {
        @Override
        public DataTransfer createFromParcel(Parcel in) {
            return new DataTransfer(in);
        }

        @Override
        public DataTransfer[] newArray(int size) {
            return new DataTransfer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (phone == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(phone);
        }
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(name);
        if (age == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(age);
        }
    }

    public Float getPhone() {return phone;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public Integer getAge() {return age;}
}
