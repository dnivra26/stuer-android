package dnivra26.github.io.stuer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

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
//        startActivity(new Intent(HomeActivity.this, MapActivity_.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_wallet:
                startActivity(new Intent(this, WalletAmount_.class));
                return true;
            default:
                return true;
        }
    }

}
