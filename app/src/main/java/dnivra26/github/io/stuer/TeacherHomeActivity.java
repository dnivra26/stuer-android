package dnivra26.github.io.stuer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import dnivra26.github.io.stuer.parsemodels.Session;

@EActivity(R.layout.activity_teacher_home)
public class TeacherHomeActivity extends AppCompatActivity {

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @ViewById(R.id.teacher_session_list)
    ListView teacherSessionList;

    @AfterViews
    public void init() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        TeacherSessionListAdapter teacherSessionListAdapter = new TeacherSessionListAdapter(this, ParseUser.getCurrentUser().getObjectId());
        final ProgressDialog progressDialog = UiUtil.buildProgressDialog(this);
        teacherSessionListAdapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<Session>() {
            @Override
            public void onLoading() {
                progressDialog.show();
            }

            @Override
            public void onLoaded(List<Session> objects, Exception e) {
                progressDialog.dismiss();
            }
        });
        teacherSessionList.setAdapter(teacherSessionListAdapter);

    }

    @Click(R.id.fab)
    public void createNewSession(View view) {
        startActivity(new Intent(TeacherHomeActivity.this, NewSessionActivity_.class));
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_teacher_home);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }

}
