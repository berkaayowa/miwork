package info.androidhive.firebase.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import info.androidhive.firebase.R;
import info.androidhive.firebase.auth.AuthUser;
import info.androidhive.firebase.common.Display;
import info.androidhive.firebase.common.ErrorAlert;
import info.androidhive.firebase.common.Validation;
import info.androidhive.firebase.controller.callback.ApiStringRequest;

public class ConfirmPhone extends AppCompatActivity {

    private TextView txtMessage, txtError;
    private EditText edtCode;
    private String phone;
    private ErrorAlert errorAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_phone);

        txtMessage = (TextView) findViewById(R.id.txtMessage);
        edtCode = (EditText) findViewById(R.id.edtCode);
//        txtError = (TextView) findViewById(R.id.txtErrorMs);


        Bundle extras = getIntent().getExtras();
        if (!extras.get("message").toString().isEmpty()) {
            phone = extras.get("message").toString();
            txtMessage.setText("Code sent to " + phone);
        }
    }

    public void confirm(View view) {
        String code = edtCode.getText().toString();
        final String [] data = {phone, code};

        if (Validation.stringsNotEmpty(data)){

            Activity activity = this;
            Display.startLoading("Phone Number Verifying", activity);
            AuthUser authUser = new AuthUser(this);

            authUser.verification(data, new ApiStringRequest() {
                @Override
                public void onSuccess(String response) throws JSONException {
                    Display.endLoading();
                    JSONObject jsonObj = new JSONObject(response);
                    if (jsonObj.has("success")) {
                        alert("successVerification");
                    } else {
                        alert("default");
                    }

                    Log.e("error::->", jsonObj.getString("message"));
                }

                @Override
                public void onJsonError(JSONException e) {
                    Display.endLoading();
                }

                @Override
                public void onConnectingError(VolleyError error) {
                    Display.endLoading();
                }
            });

        }
    }

    public void alert(String alert) {
        ErrorAlert errorAlert = new ErrorAlert(this, this, alert);
        errorAlert.show();
    }

    public void closePop(View view) {
      //  errorAlert.dismiss();
    }

    public void gotToMenu(View view) {
        Intent viewMenu = new Intent(this, MainMenu.class);
        viewMenu.putExtra("message", "");
        this.startActivity(viewMenu);
    }
}
