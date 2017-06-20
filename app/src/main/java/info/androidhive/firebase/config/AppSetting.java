package info.androidhive.firebase.config;

/**
 * Created by berka on 6/16/17.
 */

public class AppSetting {
    public static String USER_URL_SIGNUP = "http://www.berka-ayowa.com/users/signup/";
    public static String USER_URL_SIGNIN = "http://www.berka-ayowa.com/users/applogin/";

    public static String signIn(String [] data) {
        return USER_URL_SIGNIN + "?user_key=" + data[0] + "&user_email=" + data[1];
    }

}
