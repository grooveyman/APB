package com.example.aboagyemaxwell.project;

public class message {
    public String username,full_name,ref_number,gender,user_uid;


    public message() {
    }

    public message(String username, String full_name, String ref_number, String gender,String user_uid) {
        this.username = username;
        this.full_name = full_name;
        this.ref_number = ref_number;
        this.gender = gender;
        this.user_uid = user_uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRef_number() {
        return ref_number;
    }

    public void setRef_number(String ref_number) {
        this.ref_number = ref_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }
}
