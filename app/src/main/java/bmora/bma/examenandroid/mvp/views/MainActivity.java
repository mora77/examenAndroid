package bmora.bma.examenandroid.mvp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import bmora.bma.examenandroid.R;
import bmora.bma.examenandroid.mvp.interfaces.MainActivityInterface;
import bmora.bma.examenandroid.mvp.interfaces.PresenterLoginInterface;
import bmora.bma.examenandroid.mvp.presenters.PresenterLogin;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {
    private EditText etEmail, editTextPass;
    private Button bntLogin;
    private TextView bntSignIn;
    private TextView etWelcome;
    private PresenterLoginInterface presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.et_email);
        editTextPass = findViewById(R.id.et_pass);
        bntLogin = findViewById(R.id.btn_login);
        etWelcome = findViewById(R.id.tv_welcome);
        presenter = new PresenterLogin(this);
        bntSignIn = findViewById(R.id.tv_noAccount);

        bntLogin.setOnClickListener(view -> doLogin());

        /*bntSigin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    bntLogin.setBackgroundColor(getColor(R.color.colorAccent));
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    bntLogin.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                }
                return false;
            }
        });*/

        bntSignIn.setOnClickListener(view -> {
            bntSignIn.setTextColor(getColor(R.color.colorClickedText));
            doNoAccount();
        });

        /*bntSigin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    bntLogin.setBackgroundColor(getColor(R.color.colorPrimary));
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    bntLogin.setBackgroundColor(getColor(R.color.colorClickedText));
                }
                return false;
            }
        });*/
    }

    public void doLogin() {
        try {
            String email = etEmail.getText().toString();
            String pass = editTextPass.getText().toString();
            presenter.tryLogin(email, pass, this.getApplicationContext());
        } catch (Exception e) {
            showError(getString(R.string.text_for_general_error));
        }
    }

    public void doNoAccount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.text_for_sing_up_title);

        final EditText inputEmail = new EditText(this);
        final EditText inputPass = new EditText(this);
        LinearLayout lay = new LinearLayout(this);
        lay.setOrientation(LinearLayout.VERTICAL);
        inputEmail.setInputType(InputType.TYPE_CLASS_TEXT);
        inputEmail.setHint(getText(R.string.text_for_email));
        lay.addView(inputEmail);
        inputPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        inputPass.setHint(getString(R.string.text_for_pass));
        lay.addView(inputPass);
        builder.setView(lay);
        builder.setPositiveButton(R.string.text_for_button_sign_up, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    String email = inputEmail.getText().toString();
                    String pass = inputPass.getText().toString();
                    presenter.noAccount(email,pass,getApplicationContext());
                } catch (Exception e) {
                    showError(getString(R.string.text_sign_up_error));
                }
            }
        });
        builder.setNegativeButton(R.string.text_for_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bntSignIn.setTextColor(getColor(R.color.colorPrimary));
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void showError(String error) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(R.string.text_for_error);
        alertDialog.setMessage(error);
        alertDialog.setButton(getString(R.string.text_for_ok), (dialog, which) -> alertDialog.cancel());
        alertDialog.show();
    }

    @Override
    public void showSignInSuccessFull() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(R.string.text_for_successFull);
        alertDialog.setMessage(getString(R.string.text_for_sign_in_succesfull));
        alertDialog.setButton(getString(R.string.text_for_ok), (dialog, which) -> alertDialog.cancel());
        alertDialog.show();
    }

    @Override
    public void successLogin() {
        Intent intent= new Intent(this,UsersList.class);
        startActivity(intent);
    }

    @Override
    public void showProgressBar() {

    }
}
