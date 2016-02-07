package dnivra26.github.io.stuer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_map)
public class MapActivity extends AppCompatActivity {

    public void onMapLongClick(LatLng latLng) {
        Intent intent = new Intent(MapActivity.this, NewSessionActivity_.class);
        intent.putExtra("lat", latLng.latitude);
        intent.putExtra("lng", latLng.longitude);
        setResult(111, intent);
        finish();
    }
}
