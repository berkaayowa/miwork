package info.androidhive.firebase.controller;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import info.androidhive.firebase.auth.entityparse.EntityParse;
import info.androidhive.firebase.config.AppSetting;
import info.androidhive.firebase.controller.callback.LoanCallBack;

/**
 * Created by berka on 6/28/17.
 */

public class LoanController {
    private RequestQueue requestQueue;

    public LoanController(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void getLoans(String userKey, String userEmail, final LoanCallBack loanCallBack) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(AppSetting.loanUrl(userKey, userEmail),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            loanCallBack.onSuccess(EntityParse.jsonToLoan(response));

                        } catch (JSONException e) {
                            loanCallBack.onJsonError(e);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loanCallBack.onConnectingError(error);
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
