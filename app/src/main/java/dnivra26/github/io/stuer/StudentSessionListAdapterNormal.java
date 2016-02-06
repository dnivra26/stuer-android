package dnivra26.github.io.stuer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.HashMap;
import java.util.List;

import dnivra26.github.io.stuer.parsemodels.Session;

public class StudentSessionListAdapterNormal extends ArrayAdapter<Session> {

    public StudentSessionListAdapterNormal(Context context, List<Session> objects) {
        super(context, R.layout.student_session_row, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Session session = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_session_row, parent, false);
        }

        TextView sessionName = (TextView) convertView.findViewById(R.id.student_row_session_name);
        sessionName.setText(session.getActivityName());

        TextView sessionFare = (TextView) convertView.findViewById(R.id.student_row_session_fare);
        sessionFare.setText(String.valueOf(session.getFare()) + "");

        TextView sessionLocation = (TextView) convertView.findViewById(R.id.student_row_session_location);
        sessionLocation.setText(session.getLocation().toString());

        TextView sessionDuration = (TextView) convertView.findViewById(R.id.student_row_session_duration);
        sessionDuration.setText(String.valueOf(session.getDuration()) + "");

        TextView sessionTime = (TextView) convertView.findViewById(R.id.student_row_session_datetime);
        sessionTime.setText(session.getTime().toString());

        Button request = (Button) convertView.findViewById(R.id.request_session);
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
        return convertView;
    }
}
