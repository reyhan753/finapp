package ir.example.finapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class atv_income extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_income);
        Button btn_add=findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(atv_income.this, atv_income_add.class));
            }
        });

        fill();
    }

    FirebaseDatabase db;
    DatabaseReference ref;
    RecyclerView rv_list;
    ArrayList<cs_income> arrayList;
    private void fill()
    {

        db= FirebaseDatabase.getInstance();
        ref = db.getReference("income");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList=new ArrayList<>();
                for(DataSnapshot snap:snapshot.getChildren())
                {
                    cs_income cs=snap.getValue(cs_income.class);
                    if(cs.user== app_global.user.getId())
                    {
                        arrayList.add(cs);
                    }
                }

                adp_income adapter=new adp_income(arrayList, atv_income.this);

                rv_list=findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(atv_income.this,RecyclerView.VERTICAL,false));
                rv_list.setHasFixedSize(true);
                rv_list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}