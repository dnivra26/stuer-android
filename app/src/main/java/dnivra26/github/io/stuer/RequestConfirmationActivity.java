package dnivra26.github.io.stuer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;

import java.util.HashMap;

public class RequestConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new GsonBuilder().create();
        final RequestMessage requestMessage = gson.fromJson(getIntent().getStringExtra("request_data"), RequestMessage.class);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you accept the request from " + requestMessage.getStudent_uuid() + " for the session " +
                requestMessage.getSession_id());
        alertDialogBuilder.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HashMap<String, Object> params = new HashMap<>();
                params.put("sessionId", requestMessage.getSession_id());
                params.put("student_uuid", requestMessage.getStudent_uuid());

                ParseCloud.callFunctionInBackground("confirmSession", params, new FunctionCallback<String>() {
                    @Override
                    public void done(String s, ParseException e) {
                        Toast.makeText(RequestConfirmationActivity.this, "Thank You. Session is confirmed", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }
        });

        alertDialogBuilder.setNegativeButton("REJECT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialogBuilder.create().show();
    }
}
