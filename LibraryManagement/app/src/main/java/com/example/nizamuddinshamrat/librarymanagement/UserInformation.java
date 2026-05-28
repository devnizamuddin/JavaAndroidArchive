package com.example.nizamuddinshamrat.librarymanagement;

import java.io.Serializable;

/**
 * Created by Nizam Uddin Shamrat on 12/21/2017.
 */
public class UserInformation implements Serializable {

    private int userId;
    private String userName;
    private String userGender;
    private String userBirthdayDate;
    private String userEmail;
    private String userPassword;

    public UserInformation(int userId, String userName, String userGender, String userBirthdayDate, String userEmail, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userGender = userGender;
        this.userBirthdayDate = userBirthdayDate;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public UserInformation(String userName, String userGender, String userBirthdayDate, String userEmail, String userPassword) {
        this.userName = userName;
        this.userGender = userGender;
        this.userBirthdayDate = userBirthdayDate;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserBirthdayDate() {
        return userBirthdayDate;
    }

    public void setUserBirthdayDate(String userBirthdayDate) {
        this.userBirthdayDate = userBirthdayDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
