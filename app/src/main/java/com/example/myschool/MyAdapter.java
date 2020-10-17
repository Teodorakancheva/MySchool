package com.example.myschool;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.myschool.student.DetailHomework;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.HolderItem>{

    private List<Model>mListItem;
    private Context context;
    private RequestOptions option;

    public MyAdapter(List<Model> mListItem, Context context) {
        this.mListItem = mListItem;
        this.context = context;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.ic_andoid).error(R.drawable.ic_andoid);
    }

    @NonNull
    @Override
    public HolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rows,parent,false);
       final HolderItem holder = new HolderItem(layout);
       holder.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

          Intent intent = new Intent(context, DetailHomework.class);
          intent.putExtra("id_subject", mListItem.get(holder.getAdapterPosition()).getSubject());
          intent.putExtra("grade", mListItem.get(holder.getAdapterPosition()).getGrade());
          intent.putExtra("id_fullname", mListItem.get(holder.getAdapterPosition()).getFullname());
          intent.putExtra("homework", mListItem.get(holder.getAdapterPosition()).getImg());
          context.startActivity(intent);
           }
       });
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderItem holder, final int position) {
        final Model mlist = mListItem.get(position);
        holder.key_homework.setText(mlist.getId()+"");
        holder.tv_name.setText(mlist.getFullname());
        holder.tv_subject.setText(mlist.getSubject());
        holder.tv_grade.setText(mlist.getGrade());
        Glide.with(context).load(mlist.getImg()).apply(option).into((holder.thubnali));

        Glide.with(context).load(mlist.getImg()).thumbnail(0.5f)
                .transition(new DrawableTransitionOptions()
                        .crossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thubnali);

    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }



    public class HolderItem extends RecyclerView.ViewHolder{

        ImageView thubnali;
        TextView tv_name, tv_subject,tv_grade,key_homework;
        LinearLayout linearLayout;

        public HolderItem(View v){
            super(v);

            thubnali = (ImageView) v.findViewById(R.id.img_cover);
            tv_name = v.findViewById(R.id.tv_fullname);
            tv_grade = v.findViewById(R.id.tv_grade);
            tv_subject = v.findViewById(R.id.tv_title_subject);
            key_homework = v.findViewById(R.id.key_homework);
            linearLayout = v.findViewById(R.id.linear_layout);
            linearLayout = v.findViewById(R.id.linear_layout);

        }

    }
}
