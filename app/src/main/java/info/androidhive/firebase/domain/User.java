package info.androidhive.firebase.domain;

/**
 * Created by berka on 6/10/17.
 */

public class User extends Pesrson{

    private String userID, userKey;
    private boolean isPhoneVerified;

    public User(String userID, String name, String surname) {
        super(name, surname);
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}
