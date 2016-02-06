package dnivra26.github.io.stuer.parsemodels;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.util.Date;

@ParseClassName("session")
public class Session extends ParseObject {
    public void setActivity(String activityName) {
        put("activity", activityName);
    }

    public String getActivityName() {
        return getString("activity");
    }

    public void setSidBool(boolean sidBool) {
        put("sid_state", sidBool);
    }

    public boolean getSidBool() {
        return getBoolean("sid_state");
    }

    public void setFare(int fare) {
        put("fare", fare);
    }

    public int getFare() {
        return getInt("fare");
    }

    public void setLocation(ParseGeoPoint parseGeoPoint) {
        put("location", parseGeoPoint);
    }

    public ParseGeoPoint getLocation() {
        return getParseGeoPoint("location");
    }

    public void setDuration(int duration) {
        put("duration", duration);
    }

    public int getDuration() {
        return getInt("duration");
    }

    public void setUserId(String uuid) {
        put("user_uuid", uuid);
    }

    public String getUserId() {
        return getString("user_uuid");
    }

    public void setTime(Date date) {
        put("time", date);
    }

    public Date getTime() {
        return getDate("time");
    }

    public void setSid(String uuid){
        put("sid",uuid);
    }

    public String getSid(){
        return getString("sid");
    }
}
