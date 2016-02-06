package dnivra26.github.io.stuer;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import dnivra26.github.io.stuer.parsemodels.Wallet;

@EActivity(R.layout.activity_wallet_amount)
public class WalletAmount extends AppCompatActivity {

    @ViewById(R.id.wallet_balance)
    TextView walletBalance;

    @ViewById(R.id.recharge_amount)
    EditText rechargeAmount;

    int currentWalletBalance;

    @AfterViews
    public void init() {
        final ProgressDialog progressDialog = UiUtil.buildProgressDialog(this);
        progressDialog.show();
        ParseQuery parseQuery = new ParseQuery("wallet");
        parseQuery.whereEqualTo("user_uuid", ParseUser.getCurrentUser().getObjectId());
        parseQuery.findInBackground(new FindCallback() {
            @Override
            public void done(List list, ParseException e) {

            }

            @Override
            public void done(Object o, Throwable throwable) {
                if (((List) o).size() == 0) {
                    currentWalletBalance = 0;
                } else {
                    Wallet wallet = (Wallet) ((List) o).get(0);
                    currentWalletBalance = wallet.getBalance();
                }
                walletBalance.setText("Rs " + currentWalletBalance);
                progressDialog.dismiss();
            }

        });

    }

    @Click(R.id.recharge)
    public void rechargeWallet() {
        int amount = Integer.parseInt(rechargeAmount.getText().toString());
        final Wallet wallet = new Wallet();
        wallet.setBalance(currentWalletBalance + amount);
        wallet.setUserId(ParseUser.getCurrentUser().getObjectId());
        final ProgressDialog progressDialog = UiUtil.buildProgressDialog(this);
        progressDialog.show();
        wallet.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(WalletAmount.this, "Amount recharged!", Toast.LENGTH_LONG).show();
                    currentWalletBalance = wallet.getBalance();
                    walletBalance.setText("Rs " + currentWalletBalance);
                } else {
                    Toast.makeText(WalletAmount.this, "Wallet recharge failed!", Toast.LENGTH_LONG).show();
                }

                progressDialog.dismiss();
            }
        });
    }

}
