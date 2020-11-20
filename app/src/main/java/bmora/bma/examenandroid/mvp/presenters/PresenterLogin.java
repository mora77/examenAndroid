package bmora.bma.examenandroid.mvp.presenters;

import android.content.Context;

import bmora.bma.examenandroid.mvp.interfaces.LoginModelInterface;
import bmora.bma.examenandroid.mvp.interfaces.MainActivityInterface;
import bmora.bma.examenandroid.mvp.interfaces.PresenterLoginInterface;
import bmora.bma.examenandroid.mvp.model.LoginModel;

public class PresenterLogin implements PresenterLoginInterface {
    private MainActivityInterface view;
    private LoginModelInterface model;

    public PresenterLogin(MainActivityInterface view){
        this.view=view;
        this.model= new LoginModel(this);
    }

    @Override
    public void tryLogin(String email, String pass, Context context) {
        if(model!=null){
            model.tryLogin(email,pass, context);
        }
    }

    @Override
    public void noAccount(String email, String pass, Context context) {
        if(model!=null){
            model.noAccount(email,pass, context);
        }
    }

    @Override
    public void showSignInSuccessFull() {
        if (view!=null){
            view.showSignInSuccessFull();
        }
    }

    @Override
    public void getLoginR() {
        if(model!=null){
            model.getLoginR();
        }
    }

    @Override
    public void showError(String error) {
        if(view!=null){
            view.showError(error);
        }
    }

    @Override
    public void successLogin() {
        if(view!=null){
            view.successLogin();
        }
    }

    @Override
    public void showProgressBar() {
        if(view!=null){
            view.showProgressBar();
        }

    }
}
