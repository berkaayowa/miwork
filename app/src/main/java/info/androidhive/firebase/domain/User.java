package info.androidhive.firebase.domain;

/**
 * Created by berka on 6/10/17.
 */

public class User extends Pesrson{

    private String userID;
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
}
