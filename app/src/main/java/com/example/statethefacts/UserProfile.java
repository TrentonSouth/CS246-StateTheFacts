
package com.example.statethefacts;

public class UserProfile {
    private String userName;
    private String userEMail;

    public UserProfile(String userName, String userEMail) {
        setUserName(userName);
        setUserEMail(userEMail);
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEMail() {
        return userEMail;
    }
    public void setUserEMail(String userEMail) {
        this.userEMail = userEMail;
    }
}