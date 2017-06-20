package info.androidhive.firebase.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LoanService extends Service {
    private final IBinder myBinder = new MyLocalBinder( ) ;

    public LoanService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return myBinder;
    }

    public class MyLocalBinder extends Binder {
        public LoanService getService() {
            Log.e("ERROR->>>>>:" ,"inservice");
            return LoanService.this;
        }
    }

    public String test() {
        return "works";
    }
}
