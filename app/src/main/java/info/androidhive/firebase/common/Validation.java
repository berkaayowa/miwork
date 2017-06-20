package info.androidhive.firebase.common;

/**
 * Created by berka on 6/18/17.
 */

public class Validation {

    public static boolean stringsNotEmpty(String [] data) {
        boolean valid = true;
        for (int i = 0; i< data.length;i++) {
            if (data[i].isEmpty()) {
                valid = false;
                break;
            }
        }
        return valid;
    }
}
