package info.androidhive.firebase.controller;

import android.app.Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import info.androidhive.firebase.auth.AuthFirebase;
import info.androidhive.firebase.controller.callback.UserCallBack;
import info.androidhive.firebase.domain.User;

/**
 * Created by berka on 6/10/17.
 */

public class UserController {

    private FirebaseUser user;
    private FirebaseAuth auth;
    private Activity activity;
    private AuthFirebase authFirebase;

    public UserController(Activity activity) {

        this.auth = FirebaseAuth.getInstance();
        this.activity = activity;
        this.authFirebase = new AuthFirebase(activity);

    }

    public void login(String email, String password, final UserCallBack userCallBack) {

        authFirebase.login(email, password, new UserCallBack() {
            @Override
            public void isSuccess(boolean state) {
                userCallBack.isSuccess(state);
            }

            @Override
            public void userUniqueId(String id) {
                userCallBack.userUniqueId(id);
            }
        });

    }

    public void register(String email, String password, final UserCallBack userCallBack) {

        authFirebase.register(email, password, new UserCallBack() {
            @Override
            public void isSuccess(boolean state) {
                userCallBack.isSuccess(state);
            }

            @Override
            public void userUniqueId(String id) {
                userCallBack.userUniqueId(id);
            }
        });

    }

    public User getCurrentUser() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }
        return null;
    }
}
