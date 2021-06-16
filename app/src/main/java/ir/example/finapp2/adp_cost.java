package ir.example.finapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adp_cost extends RecyclerView.Adapter<adp_cost.ViewHolder> {
    ArrayList<cs_cost> list;
    Context ctx;

    public adp_cost(@NonNull ArrayList<cs_cost> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list,parent,false);

        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
       cs_cost item=list.get(position);
       holder.tv_name.setText(item.getName());
       holder.tv_date.setText(item.getDate());
       holder.tv_text.setText(item.getText());
       holder.tv_amount.setText(String.valueOf(item.getAmount()) + " تومان ");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv_name,tv_amount,tv_text,tv_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_amount=itemView.findViewById(R.id.tv_amount);
            tv_text=itemView.findViewById(R.id.tv_text);
            tv_date=itemView.findViewById(R.id.tv_date);

        }
    }
}
