package ir.example.finapp2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adp_items extends RecyclerView.Adapter<adp_items.ViewHolder> {
    ArrayList<cs_item> list;
    Context ctx;

    public adp_items(@NonNull ArrayList<cs_item> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);

        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
       cs_item item=list.get(position);
       holder.tv_name.setText(item.getName());
       holder.img_icon.setImageResource(item.getIcon());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               switch (position)
               {
                   case 0:
                       ctx.startActivity(new Intent(ctx, atv_costs.class));
                       break;
                   case 1:
                       ctx.startActivity(new Intent(ctx, atv_income.class));
                       break;
                   case 2:
                       ctx.startActivity(new Intent(ctx, atv_profile.class));
                       break;
                   case 3:
                       ctx.startActivity(new Intent(ctx, atv_about.class));
                       break;

               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img_icon;
        TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_icon=itemView.findViewById(R.id.img_pic);
            tv_name=itemView.findViewById(R.id.tv_name);
        }
    }
}
