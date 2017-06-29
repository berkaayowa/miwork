package info.androidhive.firebase.auth;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import info.androidhive.firebase.controller.callback.UserCallBack;

/**
 * Created by berka on 6/15/17.
 */

public class AuthFirebase {

    private FirebaseUser user;
    private FirebaseAuth auth;
    private Activity activity;

    public AuthFirebase(Activity activity) {
        this.auth = FirebaseAuth.getInstance();
        this.activity = activity;
    }

    public void login(String email, String password, final UserCallBack userCallBack) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            userCallBack.isSuccess(false);
                        } else {
                            userCallBack.isSuccess(true);
                            userCallBack.userUniqueId(task.getResult().getUser().getUid());
                        }
                    }
                });
    }

    public void register(String email, String password, final UserCallBack userCallBack) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            userCallBack.isSuccess(false);
                        } else {
                            userCallBack.isSuccess(true);
                            userCallBack.userUniqueId(task.getResult().getUser().getUid());
                        }
                    }
                });

    }
}
