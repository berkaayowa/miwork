package info.androidhive.firebase;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;

import java.util.ArrayList;

import info.androidhive.firebase.auth.AuthUser;
import info.androidhive.firebase.auth.callback.AuthUserCallBack;
import info.androidhive.firebase.common.Display;
import info.androidhive.firebase.common.ErrorAlert;
import info.androidhive.firebase.common.Validation;
import info.androidhive.firebase.controller.UserCallBack;
import info.androidhive.firebase.controller.UserController;
import info.androidhive.firebase.domain.User;
import info.androidhive.firebase.view.ConfirmPhone;
import info.androidhive.firebase.view.LoanHistory;
import info.androidhive.firebase.view.MainActivity;
import info.androidhive.firebase.view.MainMenu;
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

        String password = inputPassword.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();

        final String[] data = new String[3];
        data[1] = email;
        data[2] = password;
        data[0] = "dam";

        if (Validation.stringsNotEmpty(data)) {

            Display.startLoading("Login", this);
            UserController userController = new UserController(this);
            final AuthUser authUser = new AuthUser(this);

            userController.login(email, password, new UserCallBack() {
                @Override
                public void isSuccess(boolean state) {

                    if (state == true) {
                        Display.endLoading();
                    } else {
                        Display.endLoading();
                        alert("invalidUser");
                    }

                }

                @Override
                public void userUniqueId(String id) {
                    if (!id.isEmpty()) {
                        Log.e("ID---->" , id);
                        data[0] = id;
                        authUser.requestUserData(data, new AuthUserCallBack() {
                            @Override
                            public void onSuccess(User user) {
                                Log.e("USER---->" , user.getContact().getEmailAddress());
                                Display.endLoading();

                                if (user != null) {
                                    if (user.isPhoneVerified()) {
                                        Intent viewLogin = new Intent(getApplicationContext(), MainMenu.class);
                                        viewLogin.putExtra("user", user);
                                        viewLogin.putExtra("type", "user");
                                        viewLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        getApplicationContext().startActivity(viewLogin);
                                    } else {

                                        Intent viewConfirmPhone = new Intent(getApplicationContext(), ConfirmPhone.class);
                                        viewConfirmPhone.putExtra("message",user.getContact().getCellPhone());
                                        viewConfirmPhone.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        getApplicationContext().startActivity(viewConfirmPhone);
                                    }
                                }
                            }

                            @Override
                            public void onError(boolean isError, String error) {
                                Display.endLoading();
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
            });

        } else {
            alert("emptyField");
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

    public void alert(String alert) {
        ErrorAlert errorAlert = new ErrorAlert(this, this, alert);
        errorAlert.create().show();
    }

}

