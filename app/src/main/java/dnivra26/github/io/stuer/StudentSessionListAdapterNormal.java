package dnivra26.github.io.stuer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dnivra26.github.io.stuer.parsemodels.Session;

public class StudentSessionListAdapterNormal extends ArrayAdapter<Session> implements Filterable {

    List<Session> filteredObject;
    List<Session> originalObject;

    public StudentSessionListAdapterNormal(Context context, List<Session> objects) {
        super(context, R.layout.student_session_row, objects);
        filteredObject = objects;
        originalObject = objects;
    }

    public int getCount() {
        return filteredObject.size();
    }

    public Session getItem(int position) {
        return filteredObject.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Session session = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_session_row, parent, false);
        }

        TextView sessionName = (TextView) convertView.findViewById(R.id.student_row_session_name);
        sessionName.setText(session.getActivityName());

        TextView instructorId = (TextView) convertView.findViewById(R.id.instructor_id);
        instructorId.setText(session.getUserId());

        instructorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UserDetailActivity_.class);
                intent.putExtra("user_id", session.getUserId());
                getContext().startActivity(intent);
            }
        });

        TextView sessionFare = (TextView) convertView.findViewById(R.id.student_row_session_fare);
        sessionFare.setText(String.valueOf("Rs. " + session.getFare()));

        TextView sessionLocation = (TextView) convertView.findViewById(R.id.student_row_session_location);


        sessionLocation.setText(session.getAddress());

        TextView sessionDuration = (TextView) convertView.findViewById(R.id.student_row_session_duration);
        sessionDuration.setText(String.valueOf(session.getDuration()) + " hrs");

        TextView sessionTime = (TextView) convertView.findViewById(R.id.student_row_session_datetime);
        sessionTime.setText(session.getTime());

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


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults results = new FilterResults();
                List<Session> filteredSessions = new ArrayList<>();

                for (Session session : originalObject) {
                    if (session.getActivityName().contains(charSequence)) {
                        filteredSessions.add(session);
                    }
                    results.values = filteredSessions;
                    results.count = filteredSessions.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredObject = (List<Session>) filterResults.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }
}
