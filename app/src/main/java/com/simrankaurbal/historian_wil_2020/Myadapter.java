package com.simrankaurbal.historian_wil_2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

   private List<Listitem> listitems;
   private Context context;

    public Myadapter(List<Listitem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ViewHolder(v);

//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Listitem listitem = listitems.get(position);

        holder.headingtextview.setText(listitem.getHead());
        holder.description.setText(listitem.getDesp());
//        holder.moredata.setText(listitem.getMore());

        Picasso.with(context).load(listitem.getImageurl()).into(holder.image);




    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView headingtextview;
        public TextView description;
//        public  TextView moredata;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headingtextview = (TextView) itemView.findViewById(R.id.headingtextview);
            description = (TextView) itemView.findViewById(R.id.description);
//            moredata = (TextView) itemView.findViewById(R.id.moredata);
            image = (ImageView) itemView.findViewById(R.id.image);



        }
    }
}
