package ir.example.finapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frg_login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frg_login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_login.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_login newInstance(String param1, String param2) {
        frg_login fragment = new frg_login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frg_login, container, false);
    }

    EditText et_username,et_password;
    TextView tv_forget,tv_register;
    Button btn_login;

    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    public void onStart() {
        super.onStart();

        db= FirebaseDatabase.getInstance();
        ref = db.getReference("users");

        et_username=getActivity().findViewById(R.id.et_username);
        et_password=getActivity().findViewById(R.id.et_password);
        tv_register=getActivity().findViewById(R.id.tv_register);
        btn_login=getActivity().findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_username.length()==0 || et_password.length()==0)
                {
                    Toast.makeText(getActivity(), "نام کاربری و رمز عبور خود را وارد نمائید", Toast.LENGTH_SHORT).show();
                    return;
                }

                login();
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frm_layout,new frg_register()).addToBackStack("").commit();
            }
        });
    }

    ArrayList<cs_user> arrayList;
    boolean exist=false;
    private void login()
    {
        exist=false;
        arrayList=new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap:snapshot.getChildren())
                {
                    cs_user user=snap.getValue(cs_user.class);
                    if(user.username.equals(et_username.getText().toString()) && user.password.equals(et_password.getText().toString()))
                    {
                        app_global.user=user;
                        exist=true;
                        startActivity(new Intent(getActivity(), atv_main.class));
                    }
                }

                if(exist==false)
                {

                    Toast.makeText(getActivity(), "نام کاربری یا رمز عبور اشتباه می باشد", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}