package dnivra26.github.io.stuer;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import dnivra26.github.io.stuer.parsemodels.Session;


public class StudentHomeFragment extends Fragment {


    ListView studentSessionList;


    EditText searchText;
    TextView emptyStudentSession;
    private StudentSessionListAdapterNormal adapter;

    public StudentHomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchText = (EditText) view.findViewById(R.id.search_text);
        studentSessionList = (ListView) view.findViewById(R.id.student_session_list);
        emptyStudentSession = (TextView) view.findViewById(R.id.empty_student_session);
        studentSessionList.setEmptyView(emptyStudentSession);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        init();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void init() {


        final ProgressDialog progressDialog = UiUtil.buildProgressDialog(getActivity());
        progressDialog.show();

        ParseQuery parseQuery = new ParseQuery("session");
        parseQuery.whereEqualTo("sid_state", true);
        parseQuery.whereNotEqualTo("user_uuid", ParseUser.getCurrentUser().getObjectId());

        parseQuery.findInBackground(new FindCallback() {
            @Override
            public void done(List list, ParseException e) {

            }

            @Override
            public void done(Object o, Throwable throwable) {
                List<Session> sessions = ((List) o) == null ? new ArrayList<Session>() : ((List) o);
                adapter = new StudentSessionListAdapterNormal(getActivity(), sessions);
                studentSessionList.setAdapter(adapter);
                progressDialog.dismiss();
            }
        });

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
