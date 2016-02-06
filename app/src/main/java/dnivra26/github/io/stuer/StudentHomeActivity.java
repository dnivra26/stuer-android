package dnivra26.github.io.stuer;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.parse.ParseQueryAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import dnivra26.github.io.stuer.parsemodels.Session;

@EActivity(R.layout.activity_student_home)
public class StudentHomeActivity extends AppCompatActivity {

    @ViewById(R.id.student_session_list)
    ListView studentSessionList;

    @AfterViews
    public void init() {
        StudentSessionListAdapter studentSessionListAdapter = new StudentSessionListAdapter(this);
        final ProgressDialog progressDialog = UiUtil.buildProgressDialog(this);
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
        studentSessionList.setAdapter(studentSessionListAdapter);
    }


}
