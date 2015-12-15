package com.denver.lionfriend.entity;

/**
 * Created by Malindu Warapitiya on 12/13/15.
 */
public class User {

    /**
     * User singleton object
     */
    private static User instance;

    /**
     * Name of the user
     */
    private String nickname;

    /**
     * Host of the user
     */
    private String hostname;

    private User() {
    }

    /**
     * Get instance method
     *
     * @return
     */
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
