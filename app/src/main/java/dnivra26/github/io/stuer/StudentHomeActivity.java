package dnivra26.github.io.stuer;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import dnivra26.github.io.stuer.parsemodels.Session;

@EActivity(R.layout.activity_student_home)
public class StudentHomeActivity extends AppCompatActivity {

    @ViewById(R.id.student_session_list)
    ListView studentSessionList;

    @ViewById(R.id.search_text)
    EditText searchText;
    private StudentSessionListAdapterNormal adapter;

    @AfterViews
    public void init() {


        final ProgressDialog progressDialog = UiUtil.buildProgressDialog(this);
        progressDialog.show();

        ParseQuery parseQuery = new ParseQuery("session");
        parseQuery.whereEqualTo("sid_state", true);
        parseQuery.whereNotEqualTo("user_uuid", ParseUser.getCurrentUser().getObjectId());

        parseQuery.findInBackground(new FindCallback() {
            @Override
            public void done(List list, ParseException e) {

            }

            @Override
            public void done(Object o, Throwable throwable) {
                List<Session> sessions = ((List) o);
                adapter = new StudentSessionListAdapterNormal(StudentHomeActivity.this, sessions);
                studentSessionList.setAdapter(adapter);
                progressDialog.dismiss();
            }
        });

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


}
