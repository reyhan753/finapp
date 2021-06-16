package ir.example.finapp2;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class atv_main extends AppCompatActivity {
    RecyclerView rv_list;
    ArrayList<cs_item> array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_main);

        fill();
    }

    private void fill()
    {
        array=new ArrayList<>();

        array.add(new cs_item(R.drawable.ic_outline_arrow_upward_24,"هزینه ها"));
        array.add(new cs_item(R.drawable.ic_outline_arrow_downward_24,"درآمد ها"));
        array.add(new cs_item(R.drawable.ic_outline_person_24,"تغییر پروفایل"));
        array.add(new cs_item(R.drawable.ic_outline_info_24,"درباره ما"));

        adp_items adapter=new adp_items(array,this);
        rv_list=findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new GridLayoutManager(this,2));

        rv_list.setHasFixedSize(true);
        rv_list.setAdapter(adapter);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}