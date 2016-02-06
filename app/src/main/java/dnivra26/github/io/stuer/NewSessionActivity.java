package dnivra26.github.io.stuer;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;
import java.util.UUID;

import dnivra26.github.io.stuer.parsemodels.Session;

@EActivity(R.layout.activity_new_session)
public class NewSessionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @ViewById(R.id.session_name)
    EditText sessionName;

    @ViewById(R.id.session_date)
    EditText sessionDate;

    @ViewById(R.id.session_time)
    EditText sessionTime;

    @ViewById(R.id.session_fee)
    EditText sessionFee;

    @ViewById(R.id.session_location)
    EditText sessionLocation;

    @ViewById(R.id.session_duration)
    EditText sessionDuration;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @Click(R.id.session_date)
    public void pickDate() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Click(R.id.session_time)
    public void pickTime() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


    @Click(R.id.create_session)
    public void createSession() {
        final ProgressDialog progressDialog = UiUtil.buildProgressDialog(this);
        progressDialog.show();
        Session session = new Session();
        session.setActivity(sessionName.getText().toString().trim());
        session.setDuration(Integer.parseInt(sessionDuration.getText().toString()));
        session.setFare(Integer.parseInt(sessionFee.getText().toString()));
        session.setSidBool(true);
        session.setUserId(ParseUser.getCurrentUser().getObjectId());
        String[] location = sessionLocation.getText().toString().split(",");
        ParseGeoPoint parseGeoPoint = new ParseGeoPoint(Double.parseDouble(location[0]), Double.parseDouble(location[1]));
        session.setLocation(parseGeoPoint);
        Date date = new Date(year, month, day, hour, minute);
        session.setTime(date);
        session.setSid(UUID.randomUUID().toString());
        session.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "Session created successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Session creation failed", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
                finish();
            }
        });
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        sessionDate.setText(year + " - " + month + " - " + day);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        sessionTime.setText(hour + " : " + minute);
    }
}
