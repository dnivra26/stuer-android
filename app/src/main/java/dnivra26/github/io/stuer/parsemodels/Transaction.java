package dnivra26.github.io.stuer.parsemodels;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("transaction")
public class Transaction extends ParseObject {
    public void setTeacherConfirm(boolean confirm) {
        put("teacher_confirm", confirm);
    }
}
