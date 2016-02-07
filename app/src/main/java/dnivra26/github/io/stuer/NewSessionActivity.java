package dnivra26.github.io.stuer;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

import org.androidannotations.annotations.AfterViews;
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
    private String address = "";

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

    @Click(R.id.pickLocation)
    public void pickLocation() {
        startActivityForResult(new Intent(NewSessionActivity.this, MapActivity_.class), 111);
    }


    @AfterViews
    public void init() {
        getSupportActionBar().setTitle("New Session");
    }

    @Click(R.id.create_session)
    public void createSession() {
        String sessionName = this.sessionName.getText().toString().trim();
        String duration = sessionDuration.getText().toString();
        String fare = sessionFee.getText().toString();
        String[] location = sessionLocation.getText().toString().split(",");

        if (sessionName.isEmpty() || duration.isEmpty() || fare.isEmpty() || location.length != 2) {
            Toast.makeText(getApplicationContext(), "Please check the details provided", Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog progressDialog = UiUtil.buildProgressDialog(this);
        progressDialog.show();

        Session session = new Session();


        session.setActivity(sessionName);
        session.setDuration(Integer.parseInt(duration));
        session.setFare(Integer.parseInt(fare));
        session.setSidBool(true);
        session.setUserId(ParseUser.getCurrentUser().getObjectId());
        ParseGeoPoint parseGeoPoint = new ParseGeoPoint(Double.parseDouble(location[0]), Double.parseDouble(location[1]));
        session.setLocation(parseGeoPoint);
        session.setAddress(address);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {
            if (data != null) {
                sessionLocation.setText(data.getDoubleExtra("lat", 0.0) + "," + data.getDoubleExtra("lng", 0.0));
                address = data.getStringExtra("address");
            }
        }
    }
}
