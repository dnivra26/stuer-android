package dnivra26.github.io.stuer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {

    @Click(R.id.teach)
    public void navigateToTeachersHome() {
        startActivity(new Intent(HomeActivity.this, TeacherHomeActivity_.class));
    }

    @Click(R.id.learn)
    public void navigateToStudentsHome() {
        startActivity(new Intent(HomeActivity.this, StudentHomeActivity_.class));
    }

}
