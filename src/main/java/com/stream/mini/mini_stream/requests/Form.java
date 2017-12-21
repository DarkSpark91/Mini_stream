package com.stream.mini.mini_stream.requests;

public class Form {
    String uname;
    String password;

    public String getUname() {
        return uname;
    }

    public String getPassword() {
        return password;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Form other) {
        return this.uname.equals(other.uname)
                && this.password.equals(other.password);
    }
}
