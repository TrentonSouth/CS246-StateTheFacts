
package com.example.statethefacts;

/**
 * UserProfile is a class to hold user information, including
 * name, email and age. It is a basic class with just the three
 * fields, getters, setters, and two constructors.
 */
public class UserProfile {
    private String userName;
    private String userEMail;
    private int userAge;

    // constructors
    public UserProfile(String userName, String userEMail) {
        setUserName(userName);
        setUserEMail(userEMail);
        setUserAge(0);
    }
    public UserProfile(String userName, String userEMail, int userAge) {
        setUserName(userName);
        setUserEMail(userEMail);
        setUserAge(userAge);
    }

    // getters and setters
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

    public int getUserAge() { return userAge; }
    public void setUserAge(int userAge) { this.userAge = userAge; }
}