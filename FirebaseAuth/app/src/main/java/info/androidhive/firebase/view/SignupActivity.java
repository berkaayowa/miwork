package info.androidhive.firebase.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import info.androidhive.firebase.R;
import info.androidhive.firebase.common.Display;
import info.androidhive.firebase.common.ErrorAlert;
import info.androidhive.firebase.controller.UserCallBack;
import info.androidhive.firebase.controller.UserController;
import info.androidhive.firebase.services.UserService;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private UserService userService;
    private boolean isBound;
    private  Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        activity = this;

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.edtEmail);
        inputPassword = (EditText) findViewById(R.id.edtPw);

        isBound = false;
        Intent intent = new Intent(this, UserService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void registerNow(View view) {
        UserController userController = new UserController(this);

        boolean state = true;
        if(state) {
            Display.startLoading("Registering", this);
            userController.register(inputEmail.getText().toString(), inputPassword.getText().toString(), new UserCallBack() {
                @Override
                public void isSuccess(boolean state) {
                    Display.endLoading();
                    if (state == true) {
                        alert();
                    } else {
                        ErrorAlert errorAlert = new ErrorAlert(getApplicationContext(), activity);
                        errorAlert.create().show();
                    }

                }
            }) ;
        } else {
            ErrorAlert errorAlert = new ErrorAlert(this,this);
            errorAlert.create().show();
        }
    }

    public void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (this).getLayoutInflater();
        builder.setCancelable(true);
        builder.setView(inflater.inflate(R.layout.alert_style, null));
        builder.create();
        builder.show();
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