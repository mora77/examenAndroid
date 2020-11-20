package bmora.bma.examenandroid.mvp.interfaces;

import android.content.Context;

public interface LoginModelInterface {
    void tryLogin(String email, String pass, Context context);
    void noAccount(String email, String pass, Context context);
    void getLoginR();

}
