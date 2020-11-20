package bmora.bma.examenandroid.mvp.interfaces;

import android.content.Context;

public interface PresenterLoginInterface {
    void tryLogin(String email, String pass, Context context);
    void noAccount(String email, String pass, Context context);
    void showSignInSuccessFull();
    void getLoginR();
    void showError(String error);
    void successLogin();
    void showProgressBar();
}
