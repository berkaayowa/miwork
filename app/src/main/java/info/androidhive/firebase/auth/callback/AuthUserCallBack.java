package info.androidhive.firebase.auth.callback;

import java.util.ArrayList;

import info.androidhive.firebase.domain.User;

/**
 * Created by berka on 6/16/17.
 */

public interface AuthUserCallBack  extends CallBack{
    void onSuccess(User user);
    void onError(boolean isError,String error);

}
