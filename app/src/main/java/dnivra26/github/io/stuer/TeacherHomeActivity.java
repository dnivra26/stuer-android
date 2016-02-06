package dnivra26.github.io.stuer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;

@EActivity(R.layout.activity_teacher_home)
public class TeacherHomeActivity extends AppCompatActivity {

    @ViewById(R.id.fab)
    FloatingActionButton fab;


    @Click(R.id.fab)
    public void createNewSession(View view){
        startActivity(new Intent(TeacherHomeActivity.this, NewSessionActivity_.class));
        HashMap<String, Object> params = new HashMap<>();
        params.put("sessionId", "sid1");

        ParseCloud.callFunctionInBackground("requestSession", params, new FunctionCallback<String>() {
            @Override
            public void done(String s, ParseException e) {
                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();
            }
        });
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
