package dnivra26.github.io.stuer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login_signup)
public class LoginSignupActivity extends AppCompatActivity {

    @ViewById(R.id.username)
    EditText usernameEditText;

    @ViewById(R.id.password)
    EditText passwordEditText;


    @Click(R.id.login)
    public void login() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        loginToParse(username, password);

    }

    public void loginToParse(String username, String password) {

        final ProgressDialog progressDialog = UiUtil.buildProgressDialog(this);
        progressDialog.show();
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(),
                            "Successfully Logged In",
                            Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginSignupActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Log in Error", Toast.LENGTH_LONG)
                            .show();
                }
                progressDialog.dismiss();
            }
        });
    }

    @Click(R.id.signup)
    public void signUp() {
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        if (username.equals("") && password.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please complete the sign up form",
                    Toast.LENGTH_LONG).show();

        } else {
            ParseUser user = new ParseUser();

            user.setUsername(username);
            user.setPassword(password);

            final ProgressDialog progressDialog = UiUtil.buildProgressDialog(this);
            progressDialog.show();

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                        installation.put("username", username);
                        installation.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Signed up, logging in.",
                                            Toast.LENGTH_LONG).show();
                                    loginToParse(username, password);
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Sign up Error", Toast.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Sign up Error", Toast.LENGTH_LONG)
                                .show();
                    }
                    progressDialog.dismiss();
                }
            });

        }
    }


}
