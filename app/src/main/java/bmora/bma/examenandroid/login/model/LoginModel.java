package bmora.bma.examenandroid.login.model;

import android.content.Context;
import android.util.Log;

import bmora.bma.examenandroid.R;
import bmora.bma.examenandroid.login.data.models.LoginPost;
import bmora.bma.examenandroid.login.data.models.SignInPost;
import bmora.bma.examenandroid.login.data.remote.APIService;
import bmora.bma.examenandroid.login.data.remote.ApiUtils;
import bmora.bma.examenandroid.login.interfaces.LoginModelInterface;
import bmora.bma.examenandroid.login.interfaces.PresenterLoginInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class LoginModel implements LoginModelInterface {
    private APIService mAPIService;
    private Boolean isLogin;
    private PresenterLoginInterface presenter;

    public LoginModel(PresenterLoginInterface loginPresenter){
        this.presenter=loginPresenter;
    }

    @Override
    public void tryLogin(String email, String pass, Context context) {
        mAPIService= ApiUtils.getApiService();
        sendPostLogin(email,pass, context);
    }

    @Override
    public void getLoginR() {

    }

    @Override
    public void noAccount(String email, String pass, Context context) {
        mAPIService= ApiUtils.getApiService();
        sendPostRegister(email,pass,context);
    }

    private void sendPostLogin(String email, String pass, Context context) {
        mAPIService.postLogin(email,pass).enqueue(new Callback<LoginPost>() {
            @Override
            public void onResponse(Call<LoginPost> call, Response<LoginPost> response) {
                if(response.isSuccessful()){
                    LoginPost loginPost= response.body();
                    Log.i("mierda","Resultado del POST: "+ loginPost);
                }else{
                    String error=context.getString(R.string.text_for_bad_credentials);
                    presenter.showError(error);

                }
            }

            @Override
            public void onFailure(Call<LoginPost> call, Throwable t) {
                presenter.showError(context.getString(R.string.text_for_conection_error));
            }
        });
    }

    private void sendPostRegister(String email, String pass, Context context) {
        mAPIService.postRegister(email,pass).enqueue(new Callback<SignInPost>() {
            @Override
            public void onResponse(Call<SignInPost> call, Response<SignInPost> response) {
                if(response.isSuccessful()){
                    presenter.showSignInSuccessFull();
                }else{
                    String error=context.getString(R.string.text_for_error_to_register);
                    presenter.showError(error);
                }
            }

            @Override
            public void onFailure(Call<SignInPost> call, Throwable t) {
                presenter.showError(context.getString(R.string.text_for_conection_error));
            }
        });
    }
}
