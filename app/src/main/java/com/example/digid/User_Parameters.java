package com.example.digid;

public class User_Parameters {

    private String Email, Name, Address, Password, City, State, Zipcode;
    private String Vcode, Type;

    public User_Parameters() {

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) { Type = type; }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getVcode() { return Vcode; }

    public void setVcode(String vcode) {
        Vcode = vcode;
    }



}
