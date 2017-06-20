package info.androidhive.firebase.auth.callback;

import com.android.volley.VolleyError;
import org.json.JSONException;

/**
 * Created by berka on 6/16/17.
 */

public interface CallBack {
    void onJsonError(JSONException e);
    void onConnectingError(VolleyError error);

}
