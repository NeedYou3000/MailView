package com.example.mailview;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<EmailModel> mailModels;
    List<Drawable> listBackgrounds;

    public RecycleViewAdapter(List<EmailModel> mailModels, List<Drawable> listBackgrounds) {
        this.mailModels = mailModels;
        this.listBackgrounds = listBackgrounds;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mail_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmailModel mailModel = mailModels.get(position);
        Drawable drawable = listBackgrounds.get(getRandomColor());
        ImageView imageView = ((MyViewHolder)holder).like;

        ((MyViewHolder)holder).avatar.setText(""+mailModel.getUser().charAt(0));
        ((MyViewHolder)holder).avatar.setBackground(drawable);
        ((MyViewHolder)holder).user.setText(mailModel.getUser());
        ((MyViewHolder)holder).hour.setText(mailModel.getHour());
        ((MyViewHolder)holder).title.setText(formatTitle(mailModel.getTitle()));
        ((MyViewHolder)holder).content.setText(formatContent(mailModel.getContent()));
        // check email is liked or unliked to set icon layout
        if(!mailModel.isLiked()){
            imageView.setImageResource(R.drawable.ic_non_like);
        }else{
            imageView.setImageResource(R.drawable.ic_like);
        }

        // check email is like clicked or unlike clicked
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mailModel.isLiked()){
                    mailModel.setLiked(true);
                    imageView.setImageResource(R.drawable.ic_like);
                }else{
                    mailModel.setLiked(false);
                    imageView.setImageResource(R.drawable.ic_non_like);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mailModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView avatar;
        TextView user;
        TextView hour;
        TextView title;
        TextView content;
        ImageView like;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            user = itemView.findViewById(R.id.sender);
            hour = itemView.findViewById(R.id.send_time);
            title = itemView.findViewById(R.id.send_title);
            content = itemView.findViewById(R.id.send_content);
            like = itemView.findViewById(R.id.like);
        }
    }

    // get random position of avatar background
    private int getRandomColor(){
        return (int) ((Math.random() * (4 - 0)) + 0);
    }

    // Reformat string for title to avoid title length longer than textview width
    private String formatTitle(String title){
        if(title.length() >= 40) return title.substring(0, 40) + "...";
        return title;
    }

    // Reformat string for content to avoid content length longer than textview width
    private String formatContent(String content){
        if(content.length() >= 35) return content.substring(0, 35) + "...";
        return content;
    }
}
