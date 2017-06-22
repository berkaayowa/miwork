package info.androidhive.firebase.config;

/**
 * Created by berka on 6/16/17.
 */

public class AppSetting {

    private static String URL = "http://www.berka-ayowa.com";
    public static String USER_URL_SIGNUP = URL + "/users/signup/";
    public static String USER_URL_SIGNIN = URL + "/users/applogin/";
    public static String USER_URL_VERIFICATION = URL + "/users/verify/";

    public static String signIn(String [] data) {
        return USER_URL_SIGNIN + "?user_key=" + data[0] + "&user_email=" + data[1];
    }

}
