package dnivra26.github.io.stuer;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class StuerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "JvudinD8Q7F5SINjpZyYvU83b8AmjJQ7V3T86vmT", "ly2vSAIs9xOShFzqN8qEug48AbzxrjAx3srR1Gg9");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

    }
}
