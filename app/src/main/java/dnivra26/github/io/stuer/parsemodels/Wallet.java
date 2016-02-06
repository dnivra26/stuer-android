package dnivra26.github.io.stuer.parsemodels;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("wallet")
public class Wallet extends ParseObject {
    public void setBalance(int balance) {
        put("balance", balance);
    }

    public int getBalance() {
        return getInt("balance");
    }

    public void setUserId(String userId) {
        put("user_uuid", userId);
    }

    public String getUserId() {
        return getString("user_uuid");
    }
}
