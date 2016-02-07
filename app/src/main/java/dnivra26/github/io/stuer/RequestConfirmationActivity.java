package dnivra26.github.io.stuer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.HashMap;
import java.util.List;

import dnivra26.github.io.stuer.parsemodels.Transaction;

public class RequestConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new GsonBuilder().create();
        final RequestMessage requestMessage = gson.fromJson(getIntent().getStringExtra("request_data"), RequestMessage.class);

        if (requestMessage.getType().equalsIgnoreCase("sessionRequest")) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Do you accept the request from " + requestMessage.getStudent_uuid() + " for the session " +
                    requestMessage.getSession_id());
            alertDialogBuilder.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("sessionId", requestMessage.getSession_id());
                    params.put("student_uuid", requestMessage.getStudent_uuid());

                    ParseCloud.callFunctionInBackground("confirmSession", params, new FunctionCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, ParseException e) {
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

        } else if (requestMessage.getType().equalsIgnoreCase("sessionAcknowledge")) {
            finish();

        } else if (requestMessage.getType().equalsIgnoreCase("sessionComplete")) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Is the session " + requestMessage.getSession_id() + " over??");
            alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ParseQuery parseQuery = new ParseQuery("transaction");
                    parseQuery.whereEqualTo("session_id", requestMessage.getSession_id());
                    parseQuery.findInBackground(new FindCallback() {
                        @Override
                        public void done(List list, ParseException e) {

                        }

                        @Override
                        public void done(Object o, Throwable throwable) {
                            Transaction transaction = (Transaction) ((List) o).get(0);
                            transaction.setStudentConfirm(true);
                            transaction.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Toast.makeText(RequestConfirmationActivity.this, "Thank You. ", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(RequestConfirmationActivity.this, "Update failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    });
                    finish();
                }
            });

            alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertDialogBuilder.create().show();

        }


    }
}
