package dnivra26.github.io.stuer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            Intent intent = new Intent(MainActivity.this,
                    LoginSignupActivity_.class);
            startActivity(intent);
            finish();
        } else {
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {
                Intent intent = new Intent(MainActivity.this, HomeActivity_.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(MainActivity.this,
                        LoginSignupActivity_.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
