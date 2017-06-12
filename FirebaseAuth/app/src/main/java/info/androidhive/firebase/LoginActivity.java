package info.androidhive.firebase;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import info.androidhive.firebase.common.Display;
import info.androidhive.firebase.common.ErrorAlert;
import info.androidhive.firebase.controller.UserCallBack;
import info.androidhive.firebase.controller.UserController;
import info.androidhive.firebase.view.MainActivity;
import info.androidhive.firebase.view.ResetPasswordActivity;
import info.androidhive.firebase.view.SignupActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

    }

    public void openRegister(View view) {
        Intent viewSignup = new Intent(this, SignupActivity.class);
        viewSignup.putExtra("options","");
        this.startActivity(viewSignup);
    }

    public void login(View view) {

        String password = inputPassword.getText().toString();
        String email = inputEmail.getText().toString();

        ArrayList<String> inputData = new ArrayList<>();
        inputData.add(password);
        inputData.add(email);

        if (validation(inputData)) {

            Display.startLoading("Login...", this);
            UserController userController = new UserController(this);

            userController.login(email, password, new UserCallBack() {
                @Override
                public void isSuccess(boolean state) {
                    Display.endLoading();
                    if (state == true) {
                        Intent viewLogin = new Intent(getApplicationContext(), MainActivity.class);
                        viewLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(viewLogin);
                    } else {
                        //error
                    }
                }
            });

        } else {
            ErrorAlert errorAlert = new ErrorAlert(this, this);
            errorAlert.create().show();
        }
    }

    private boolean validation(ArrayList<String> data) {
        boolean valid = true;
        for (int i = 0; i< data.size();i++) {
            if (data.get(i).isEmpty()) {
                valid = false;
                break;
            }
        }
        return valid;
    }

}

