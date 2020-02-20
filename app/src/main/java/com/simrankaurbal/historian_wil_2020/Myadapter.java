package com.simrankaurbal.historian_wil_2020;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

   private List<Listitem> listitems;
   private Context context;
   LayoutInflater inflater;

    public Myadapter(List<Listitem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        View v = inflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);

//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Listitem listitem = listitems.get(position);

        holder.headingtextview.setText(listitem.getHead());
        holder.description.setText(listitem.getDesp());
//        holder.moredata.setText(listitem.getMore());
        holder.reviewbutton.setText(listitem.getReview());
        holder.reviewbutton.setText("Review");

//        holder.reviewbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, PageReview.class);
//                intent.putExtra("heading", headingtextview);
//
//                context.startActivity(intent);
//            }
//        });

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
        public Button reviewbutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headingtextview = (TextView) itemView.findViewById(R.id.headingtextview);
            description = (TextView) itemView.findViewById(R.id.description);
//            moredata = (TextView) itemView.findViewById(R.id.moredata);
            image = (ImageView) itemView.findViewById(R.id.image);
            reviewbutton = (Button) itemView.findViewById(R.id.reviewbutton);
            reviewbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,Demo.class);
                    intent.putExtra("heading", String.valueOf(headingtextview));

                    context.startActivity(intent);

                }
            });



        }
    }
}
