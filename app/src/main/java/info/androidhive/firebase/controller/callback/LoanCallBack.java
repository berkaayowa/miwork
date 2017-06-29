package info.androidhive.firebase.controller.callback;

import java.util.ArrayList;
import info.androidhive.firebase.auth.callback.CallBack;
import info.androidhive.firebase.domain.Loan;

/**
 * Created by berka on 6/28/17.
 */

public interface LoanCallBack extends CallBack{
    void onSuccess(ArrayList<Loan> loans);
}
