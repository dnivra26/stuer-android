package dnivra26.github.io.stuer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import dnivra26.github.io.stuer.parsemodels.Session;

public class TeacherSessionListAdapter extends ParseQueryAdapter<Session> {

    public TeacherSessionListAdapter(Context context, final String userId) {
        super(context, new ParseQueryAdapter.QueryFactory<Session>() {

            @Override
            public ParseQuery<Session> create() {
                ParseQuery query = new ParseQuery("session");
                query.whereEqualTo("user_uuid", userId);
                return query;
            }
        });
    }

    @Override
    public View getItemView(Session session, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.teacher_session_row, null);
        }
        super.getItemView(session, v, parent);

        TextView sessionName = (TextView) v.findViewById(R.id.row_session_name);
        sessionName.setText(session.getActivityName());

        TextView sessionValid = (TextView) v.findViewById(R.id.row_session_valid);
        sessionValid.setText(session.getSidBool() + "");

        TextView sessionFare = (TextView) v.findViewById(R.id.row_session_fare);
        sessionFare.setText(String.valueOf(session.getFare()) + "");

        TextView sessionLocation = (TextView) v.findViewById(R.id.row_session_location);
        sessionLocation.setText(session.getLocation().toString());

        TextView sessionDuration = (TextView) v.findViewById(R.id.row_session_duration);
        sessionDuration.setText(String.valueOf(session.getDuration()) + "");

        TextView sessionTime = (TextView) v.findViewById(R.id.row_session_datetime);
        sessionTime.setText(session.getTime().toString());

        return v;
    }
}
