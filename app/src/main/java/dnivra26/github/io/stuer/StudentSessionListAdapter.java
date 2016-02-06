package dnivra26.github.io.stuer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.HashMap;

import dnivra26.github.io.stuer.parsemodels.Session;

public class StudentSessionListAdapter extends ParseQueryAdapter<Session> {

    public StudentSessionListAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Session>() {

            @Override
            public ParseQuery<Session> create() {
                ParseQuery query = new ParseQuery("session");
                query.whereEqualTo("sid_state", true);
                return query;
            }
        });
    }

    @Override
    public View getItemView(final Session session, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.student_session_row, null);
        }
        super.getItemView(session, v, parent);

        TextView sessionName = (TextView) v.findViewById(R.id.student_row_session_name);
        sessionName.setText(session.getActivityName());

        TextView sessionFare = (TextView) v.findViewById(R.id.student_row_session_fare);
        sessionFare.setText(String.valueOf(session.getFare()) + "");

        TextView sessionLocation = (TextView) v.findViewById(R.id.student_row_session_location);
        sessionLocation.setText(session.getLocation().toString());

        TextView sessionDuration = (TextView) v.findViewById(R.id.student_row_session_duration);
        sessionDuration.setText(String.valueOf(session.getDuration()) + "");

        TextView sessionTime = (TextView) v.findViewById(R.id.student_row_session_datetime);
        sessionTime.setText(session.getTime().toString());

        Button request = (Button) v.findViewById(R.id.request_session);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> params = new HashMap<>();
                params.put("sessionId", session.getSid());
                params.put("student_uuid", ParseUser.getCurrentUser().getObjectId());

                ParseCloud.callFunctionInBackground("requestSession", params, new FunctionCallback<String>() {
                    @Override
                    public void done(String s, ParseException e) {
                        Toast.makeText(getContext(), "Session requested. You will get a confirmation soon.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return v;
    }
}
