package ir.example.finapp2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class atv_costs_add extends AppCompatActivity {

    EditText et_name,et_date,et_text,et_amount;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_costs_add);

        et_name=findViewById(R.id.et_name);
        et_date=findViewById(R.id.et_date);
        et_text=findViewById(R.id.et_text);
        et_amount=findViewById(R.id.et_amount);
        btn_save=findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_name.length()==0 || et_date.length()==0 || et_text.length()==0 || et_amount.length()==0)
                {
                    Toast.makeText(atv_costs_add.this, "تمامی فیلدها را پر نمائید", Toast.LENGTH_SHORT).show();
                    return;
                }

                save();
            }
        });
    }
    FirebaseDatabase db;
    DatabaseReference ref;

    private void save()
    {
        db= FirebaseDatabase.getInstance();
        ref = db.getReference("costs");

        final int min = 111111;
        final int max = 999999;
        final int id = new Random().nextInt((max - min) + 1) + min;

        cs_cost mdl = new cs_cost();
        mdl.setId(id);
        mdl.setDate(et_date.getText().toString());
        mdl.setName(et_name.getText().toString());
        mdl.setText(et_text.getText().toString());
        mdl.setUser(app_global.user.getId());

        mdl.setAmount(Integer.valueOf(et_amount.getText().toString()));

        ref.child(String.valueOf(id)).setValue(mdl);

        finish();


    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}