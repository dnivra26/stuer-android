package dnivra26.github.io.stuer;

import android.content.Context;
import android.content.Intent;

import com.parse.ParsePushBroadcastReceiver;

public class PushBroadcastReceiver extends ParsePushBroadcastReceiver {

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);
        String jsonString = intent.getExtras().get("com.parse.Data").toString();
        Intent intent1 = new Intent(context, RequestConfirmationActivity.class);
        intent1.putExtra("request_data", jsonString);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent1);

    }
}
