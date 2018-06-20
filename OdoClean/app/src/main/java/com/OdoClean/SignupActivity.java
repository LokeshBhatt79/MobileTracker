package com.example.mayank.odoclean;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity
{
    private static final String TAG = "SignupActivity";

    EditText input_name,input_address,input_email,input_mobile,input_password,input_reEnterPassword;
    TextView link_login;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        //getSupportActionBar().hide();
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          //      WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        input_name=(EditText)findViewById(R.id.input_name);
        input_address=(EditText)findViewById(R.id.input_address);
        input_email=(EditText)findViewById(R.id.input_email);
        input_mobile =(EditText)findViewById(R.id.input_mobile);
        input_password=(EditText)findViewById(R.id.input_password);
        input_reEnterPassword=(EditText)findViewById(R.id.input_reEnterPassword);
        link_login=(TextView)findViewById(R.id.link_login);
        btn_signup=(Button)findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        btn_signup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = input_name.getText().toString();
        String address = input_address.getText().toString();
        String email = input_email.getText().toString();
        String mobile = input_mobile.getText().toString();
        String password = input_password.getText().toString();
        String reEnterPassword = input_reEnterPassword.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        btn_signup.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn_signup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = input_name.getText().toString();
        String address = input_address.getText().toString();
        String email = input_email.getText().toString();
        String mobile = input_mobile.getText().toString();
        String password = input_password.getText().toString();
        String reEnterPassword = input_reEnterPassword.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            input_name.setError("at least 3 characters");
            valid = false;
        } else {
            input_name.setError(null);
        }

        if (address.isEmpty()) {
            input_address.setError("Enter Valid Address");
            valid = false;
        } else {
            input_address.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.setError("enter a valid email address");
            valid = false;
        } else {
            input_email.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            input_mobile.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            input_mobile.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            input_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            input_password.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            input_reEnterPassword.setError("Password Do not match");
            valid = false;
        } else {
            input_reEnterPassword.setError(null);
        }

        return valid;
    }

    }





