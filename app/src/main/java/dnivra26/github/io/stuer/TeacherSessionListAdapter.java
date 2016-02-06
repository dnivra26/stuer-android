package dnivra26.github.io.stuer;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;

import java.util.List;

import dnivra26.github.io.stuer.parsemodels.Session;
import dnivra26.github.io.stuer.parsemodels.Transaction;

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
    public View getItemView(final Session session, View v, ViewGroup parent) {
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

        Button doneSession = (Button) v.findViewById(R.id.done_session);
        doneSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = UiUtil.buildProgressDialog(getContext());
                progressDialog.show();
                ParseQuery parseQuery = new ParseQuery("transaction");
                parseQuery.whereEqualTo("session_id", session.getSid());
                parseQuery.findInBackground(new FindCallback() {
                    @Override
                    public void done(List list, ParseException e) {

                    }

                    @Override
                    public void done(Object o, Throwable throwable) {
                        Transaction transaction = (Transaction) ((List) o).get(0);
                        transaction.setTeacherConfirm(true);
                        transaction.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(getContext(),
                                            "Update successful",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(),
                                            "Update failed",
                                            Toast.LENGTH_LONG).show();
                                }
                                progressDialog.dismiss();
                            }
                        });
                    }
                });

            }
        });

        return v;
    }
}
