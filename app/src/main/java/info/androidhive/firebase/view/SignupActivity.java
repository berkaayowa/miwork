package info.androidhive.firebase.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import info.androidhive.firebase.R;
import info.androidhive.firebase.auth.AuthUser;
import info.androidhive.firebase.common.Display;
import info.androidhive.firebase.common.ErrorAlert;
import info.androidhive.firebase.common.Validation;
import info.androidhive.firebase.controller.callback.ApiStringRequest;
import info.androidhive.firebase.controller.callback.UserCallBack;
import info.androidhive.firebase.controller.UserController;
import info.androidhive.firebase.services.UserService;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, edtName, edtSurname, edtPhone;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private UserService userService;
    private boolean isBound;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        activity = this;

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);

        inputEmail = (EditText) findViewById(R.id.edtEmailS);
        inputPassword = (EditText) findViewById(R.id.edtPw);
        edtName = (EditText) findViewById(R.id.edtName);
        edtSurname = (EditText) findViewById(R.id.edtSurname);
        edtPhone = (EditText) findViewById(R.id.edtPhone);

        isBound = false;
        Intent intent = new Intent(this, UserService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void registerNow(View view) {
        UserController userController = new UserController(this);
        final AuthUser authUser = new AuthUser(this);

        String name = edtName.getText().toString().trim();
        String surname = edtSurname.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        final String[] data = new String[6];
        data[0] = name;
        data[1] = surname;
        data[2] = phone;
        data[3] = email;
        data[4] = password;
        data[5] = "dam";

        boolean validation = Validation.stringsNotEmpty(data);

        if(validation) {

            final Activity activityLoading = this;
            Display.startLoading("Registering step 1 ", activityLoading);

            userController.register(email, password, new UserCallBack() {
                @Override
                public void isSuccess(boolean state) {

                    if (state == true) {
                        Display.endLoading();
                    } else {
                        Display.endLoading();
                        alert("error");
                        //create style for
                    }
                }

                @Override
                public void userUniqueId(String id) {
                    if (!id.isEmpty()) {

                        data[5] = id;
                        Display.startLoading("Registering step 2 ", activityLoading);

                        authUser.signUp(data, new ApiStringRequest() {
                            @Override
                            public void onSuccess(String response) throws JSONException {
                                Display.endLoading();
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.has("success")) {
                                    alert("successReg");
                                } else {
                                    alert("error");
                                }
                                Log.e("error::->", jsonObj.getString("message"));
                            }

                            @Override
                            public void onJsonError(JSONException e) {
                                Display.endLoading();
                                alert("error");
                            }

                            @Override
                            public void onConnectingError(VolleyError error) {
                                Display.endLoading();
                                alert("error");
                            }
                        });
                    }
                }
            }) ;

        } else {
            alert("emptyField");
        }
    }

    public void alert(String alert) {
        ErrorAlert errorAlert = new ErrorAlert(this,this, alert);
        errorAlert.create().show();
    }

    public void confirmPhone(View view) {
        Intent viewConfirmPhone = new Intent(this, ConfirmPhone.class);
        viewConfirmPhone.putExtra("message", edtPhone.getText().toString().trim());
        this.startActivity(viewConfirmPhone);
    }

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            UserService.MyLocalBinder binder = (UserService.MyLocalBinder) service;
            userService = binder.getService(SignupActivity.this);
            isBound = true;

            Log.e("ERROR->>>>>:" ,userService.test());

            if (isBound) {
                //displayJobs();
                Log.e("ERROR->>>>>:" ,"bound");
            }
            else {
                Log.e("ERROR->>>>>:" ,"no bound");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected  void onStop()
    {
        super.onStop();

        if(isBound)
        {
            unbindService(serviceConnection);
            isBound = false;
        }

    }
}