package info.androidhive.firebase.services;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;

import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import info.androidhive.firebase.controller.UserController;

public class UserService extends Service {
    private final IBinder myBinder = new MyLocalBinder ( ) ;
    private UserController userController;

    public UserService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class MyLocalBinder extends Binder {
        public UserService getService(Activity activity) {
            Log.e("ERROR->>>>>:" ,"inservice");
            userController = new UserController(activity);
            return UserService.this;
        }
    }

    public String test() {
        return "works";
    }

//    public boolean register(String email, String password) {
//        return userController.register(email, password);
//    }


}
