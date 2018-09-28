package lms.autovista.com.crossleadmanagement.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;

import lms.autovista.com.crossleadmanagement.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    TextView signIn_btn, signUpHeading_tv, signInHeading_tv;
    RelativeLayout signUpHeader_rl, signInHeader_rl;

    EditText emailAddress_et,password_et;

    //SignUp Form
    EditText nameAddressSignUp_et,emailAddressSignUp_et,addressSignUp_et,phoneSignUp_et,passwordSignUp_et,confirmPasswordasswordSignUp_et;
    EditText areaOfLawIdSignUp_et;
    TextView sighUp_btn, signUp_tv, sighUpFb_btn;

    RelativeLayout fbSignUp_rl;

    //SignUp Fb login
    String username_fb,emailId_fb,login_type_fb,social_login_tyoe_fb ,social_api_fb;

    //Fb implementation
    LoginButton loginButton, facebook_sinUp;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);

        //Login Button for fb
        loginButton = (LoginButton) findViewById(R.id.facebook_login);
        facebook_sinUp = (LoginButton) findViewById(R.id.facebook_sinUp);

        //Fb Intergration code
        fbSignUp_rl = (RelativeLayout) findViewById(R.id.fbSignUp_rl);
        fbSignUp_rl.setOnClickListener(this);

        sighUpFb_btn = (TextView) findViewById(R.id.sighUpFb_btn);
        sighUpFb_btn.setOnClickListener(this);

        //singInHeading
        signInHeading_tv = (TextView) findViewById(R.id.signInHeading_tv);
        signInHeading_tv.setOnClickListener(this);

        signInHeader_rl = (RelativeLayout) findViewById(R.id.signInHeader_rl);
        signInHeader_rl.setVisibility(View.VISIBLE);

        //signUpHeading
        signUpHeading_tv = (TextView) findViewById(R.id.signUpHeading_tv);
        signUpHeading_tv.setOnClickListener(this);

        signUp_tv = (TextView) findViewById(R.id.signUp_tv);
        signUp_tv.setOnClickListener(this);

        signUpHeader_rl=(RelativeLayout) findViewById(R.id.signUpHeader_rl);
        signUpHeader_rl.setVisibility(View.GONE);

        sighUp_btn = (TextView) findViewById(R.id.sighUp_btn);
        sighUp_btn.setOnClickListener(this);

        //SignUpForm
        nameAddressSignUp_et = (EditText) findViewById(R.id.nameSignUp_et);
        emailAddressSignUp_et = (EditText) findViewById(R.id.emailAddressSignUp_et);
        addressSignUp_et = (EditText) findViewById(R.id.addressSignUp_et);
        phoneSignUp_et = (EditText) findViewById(R.id.phoneSignUp_et);
        areaOfLawIdSignUp_et= (EditText) findViewById(R.id.areaOfLawIdSignUp_et);
        passwordSignUp_et = (EditText) findViewById(R.id.passwordSignUp_et);
        confirmPasswordasswordSignUp_et = (EditText) findViewById(R.id.confirmPasswordasswordSignUp_et);

        //SignIn Form
        emailAddress_et = (EditText) findViewById(R.id.emailAddress_et);
        password_et =(EditText) findViewById(R.id.password_et);

        signIn_btn = (TextView) findViewById(R.id.signIn_btn);
        signIn_btn.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.signIn_btn:
                Intent intent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                startActivity(intent);
             //   getLoginFunction();
                break;

            case R.id.signUp_tv:
                signUpHeader_rl.setVisibility(View.VISIBLE);
                signInHeader_rl.setVisibility(View.GONE);
                break;

            case R.id.fbSignUp_rl:

                break;

            case R.id.signUpHeading_tv:
                signUpHeader_rl.setVisibility(View.VISIBLE);
                signInHeader_rl.setVisibility(View.GONE);
                break;

            case R.id.signInHeading_tv:
                signInHeader_rl.setVisibility(View.VISIBLE);
                signUpHeader_rl.setVisibility(View.GONE);
                break;
        }
    }
}
