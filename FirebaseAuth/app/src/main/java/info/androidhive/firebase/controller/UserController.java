package info.androidhive.firebase.controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import info.androidhive.firebase.domain.User;

/**
 * Created by berka on 6/10/17.
 */

public class UserController {

    private FirebaseUser user;
    private FirebaseAuth auth;
    private Activity activity;
    private boolean isRegistered;
   // private DatabaseReference databaseReference;
  //  private FirebaseDatabase database;

    public UserController(Activity activity) {
        this.auth = FirebaseAuth.getInstance();
        this.activity = activity;

       // database = FirebaseDatabase.getInstance();
        //this.databaseReference = database.getReference().child("users");
    }

    public User login(String email, String password, final UserCallBack userCallBack) {

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            userCallBack.isSuccess(false);
                        } else {
                            userCallBack.isSuccess(true);
                        }
                    }
                });

        return null;
    }

    public void register(String email, String password, final UserCallBack userCallBack) {
        isRegistered = false;
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            userCallBack.isSuccess(false);
                        } else {
                           // databaseReference.child("name").setValue("berka");
                           // databaseReference.child("surname").setValue("ayowa");
                            userCallBack.isSuccess(true);
                        }
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
