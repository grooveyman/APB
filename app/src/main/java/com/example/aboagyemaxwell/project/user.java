package com.example.aboagyemaxwell.project;

public class user {


    public String email;
    public String username;
    public String full_name;
    public String ref_number;
    public String index_number;
    public String status;
    public String alert_phrase;
    public String gender;



    public user() {

    }

    public user(String email, String username, String full_name, String ref_number, String index_number, String status,String alert_phrase, String gender) {
        this.email = email;
        this.username = username;
        this.full_name = full_name;
        this.ref_number = ref_number;
        this.index_number = index_number;
        this.status = status;
        this.gender = gender;
        this.alert_phrase = alert_phrase;
    }
}
