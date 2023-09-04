package com.myapplication.Picky;

public class userHelper {
    String username,password,email,mobile,Type,Age,symp,comment;

    userHelper(){

    }
// it is made to create a new user in the doctor and passant side with (name, mobile,age, symptoms ,email, password)

    public userHelper(String comment) {
        this.comment = comment;
    }

    public userHelper(String username, String mobile, String age) {
        this.username = username;
        this.mobile = mobile;
        Age = age;
    }
    public userHelper(String username, String mobile, String age, String symp) {
        this.username = username;
        this.mobile = mobile;
        Age = age;
        this.symp = symp;
    }

    public userHelper(String username, String password, String email, String mobile, String Type, String age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.Type = Type;
        Age=age;

    }

    public String getSymp() {
        return symp;
    }

    public String getAge() {
        return Age;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
