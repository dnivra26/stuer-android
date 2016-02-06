package dnivra26.github.io.stuer;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

import dnivra26.github.io.stuer.parsemodels.Session;

public class StuerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "JvudinD8Q7F5SINjpZyYvU83b8AmjJQ7V3T86vmT", "ly2vSAIs9xOShFzqN8qEug48AbzxrjAx3srR1Gg9");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

        ParseObject.registerSubclass(Session.class);

    }
}
