package dnivra26.github.io.stuer;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import dnivra26.github.io.stuer.parsemodels.Session;

@EActivity(R.layout.activity_user_detail)
public class UserDetailActivity extends AppCompatActivity {

    @ViewById(R.id.user_session_list)
    ListView userSessionList;

    @ViewById(R.id.user_name)
    TextView userName;


    @AfterViews
    public void init() {
        getSupportActionBar().setTitle("Instructor detail");
        final String user_id = getIntent().getStringExtra("user_id");
        ParseQuery userQuery = new ParseQuery("_User");
        userQuery.whereEqualTo("objectId", user_id);
        userQuery.findInBackground(new FindCallback() {
            @Override
            public void done(List list, ParseException e) {

            }

            @Override
            public void done(Object o, Throwable throwable) {
                ParseUser parseUser = (ParseUser) ((List) o).get(0);
                userName.setText("Name: " + parseUser.getUsername());

                StudentSessionListAdapter studentSessionListAdapter = new StudentSessionListAdapter(UserDetailActivity.this, user_id);
                final ProgressDialog progressDialog = UiUtil.buildProgressDialog(UserDetailActivity.this);
                studentSessionListAdapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<Session>() {
                    @Override
                    public void onLoading() {
                        progressDialog.show();
                    }

                    @Override
                    public void onLoaded(List<Session> objects, Exception e) {
                        progressDialog.dismiss();
                    }
                });


                userSessionList.setAdapter(studentSessionListAdapter);


            }
        });
    }

}
