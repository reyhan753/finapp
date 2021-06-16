package ir.example.finapp2;

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
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frg_register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_register extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frg_register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_register.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_register newInstance(String param1, String param2) {
        frg_register fragment = new frg_register();
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
        return inflater.inflate(R.layout.frg_register, container, false);
    }

    FirebaseDatabase db;
    DatabaseReference ref;

    EditText et_username,et_password,et_name;
    TextView tv_back;
    Button btn_register;

    @Override
    public void onStart() {
        super.onStart();

        btn_register=getActivity().findViewById(R.id.btn_register);
        tv_back=getActivity().findViewById(R.id.tv_back);
        et_username=getActivity().findViewById(R.id.et_user);
        et_password=getActivity().findViewById(R.id.et_pass);
        et_name=getActivity().findViewById(R.id.et_name);

        db= FirebaseDatabase.getInstance();
        ref = db.getReference("users");

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frm_layout,new frg_login()).commit();
            }
        });

          btn_register.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(et_name.length()==0 || et_username.length()==0 || et_password.length()==0 )
                  {
                      Toast.makeText(getActivity(), "مشخصات خود وارد نمائید", Toast.LENGTH_SHORT).show();
                      return;
                  }
                  register();
              }
          });

        }

    ArrayList<cs_user> arrayList;
    boolean exist=false;

    private void register()
    {
        exist=false;
        arrayList=new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap:snapshot.getChildren())
                {
                    cs_user user=snap.getValue(cs_user.class);
                    if(user.username.equals(et_username.getText().toString()))
                    {
                        exist=true;
                        return;
                    }

                }

                if(exist==false) {
                    final int min = 111111;
                    final int max = 999999;
                    final int id = new Random().nextInt((max - min) + 1) + min;

                    cs_user mdl = new cs_user();
                    mdl.setId(id);
                    mdl.setUsername(et_username.getText().toString());
                    mdl.setPassword(et_password.getText().toString());
                    mdl.setName(et_name.getText().toString());

                    ref.child(String.valueOf(id)).setValue(mdl);


                    Toast.makeText(getActivity(), "حساب کاربری شما ایجاد گردید", Toast.LENGTH_SHORT).show();

                    getFragmentManager().beginTransaction().replace(R.id.frm_layout, new frg_login()).commit();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}