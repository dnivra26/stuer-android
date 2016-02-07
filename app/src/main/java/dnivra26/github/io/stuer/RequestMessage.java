package dnivra26.github.io.stuer;

public class RequestMessage {
    public String getType() {
        return type;
    }

    private final String type;
    String alert;

    public RequestMessage(String alert, String session_id, String student_uuid, String type) {
        this.alert = alert;
        this.session_id = session_id;
        this.student_uuid = student_uuid;
        this.type = type;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getStudent_uuid() {
        return student_uuid;
    }

    public void setStudent_uuid(String student_uuid) {
        this.student_uuid = student_uuid;
    }

    String session_id;
    String student_uuid;
}
