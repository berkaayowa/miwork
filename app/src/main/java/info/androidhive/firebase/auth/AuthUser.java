package info.androidhive.firebase.auth;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import info.androidhive.firebase.auth.callback.AuthUserCallBack;
import info.androidhive.firebase.auth.entityparse.EntityParse;
import info.androidhive.firebase.config.AppSetting;
import info.androidhive.firebase.controller.ApiStringRequest;

/**
 * Created by berka on 6/16/17.
 */

public class AuthUser {

    private RequestQueue requestQueue;

    public AuthUser(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void requestUserData(String [] data,final AuthUserCallBack callBack) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(AppSetting.signIn(data),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject userObject) {
                        try {

                            if (userObject.has("error")) {
                                callBack.onError(true, userObject.getString("error"));
                            } else if(userObject.has("user")) {
                                callBack.onSuccess(EntityParse.jsonToUser(userObject));
                            } else {
                                //callBack.onError(true, "Error from the server, try again");
                            }

                        } catch (JSONException e) {
                            callBack.onJsonError(e);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.onConnectingError(error);
                    }


                }
        );

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    public void signUp(final String[] data, final ApiStringRequest callback ) {
        StringRequest request = new StringRequest(1,AppSetting.USER_URL_SIGNUP,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            callback.onSuccess(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("Error--> : ", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onConnectingError(error);
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("user_name", data[0]);
                params.put("user_surname", data[1]);
                params.put("user_phone", data[2]);
                params.put("user_email", data[3]);
                params.put("user_password", data[4]);
                params.put("user_key", data[5]);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

//        request.setRetryPolicy(new DefaultRetryPolicy(20000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(request);
    }


}
