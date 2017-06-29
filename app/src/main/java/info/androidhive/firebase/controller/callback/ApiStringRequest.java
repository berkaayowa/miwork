package info.androidhive.firebase.controller.callback;

import org.json.JSONException;
import info.androidhive.firebase.auth.callback.CallBack;

/**
 * Created by berka on 6/18/17.
 */

public interface ApiStringRequest extends CallBack {
    void onSuccess(String response) throws JSONException;
}
