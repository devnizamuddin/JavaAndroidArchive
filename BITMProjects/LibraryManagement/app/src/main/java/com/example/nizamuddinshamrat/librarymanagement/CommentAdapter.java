package com.example.nizamuddinshamrat.librarymanagement;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nizam Uddin Shamrat on 12/27/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommnetListViewHolder> {

    private Context context;
    private ArrayList<FeedbackInfo> feedbackInfos;

    public CommentAdapter(Context context, ArrayList<FeedbackInfo> feedbackInfos) {
        this.context = context;
        this.feedbackInfos = feedbackInfos;

    }

    @Override
    public CommnetListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.comment_single_row,parent,false);
        return new CommnetListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommnetListViewHolder holder, int position) {

        holder.commentTV.setText(feedbackInfos.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return feedbackInfos.size();
    }

    public class CommnetListViewHolder extends RecyclerView.ViewHolder {
        TextView commentTV;
        public CommnetListViewHolder(View itemView) {
            super(itemView);
            commentTV = itemView.findViewById(R.id.commentTV);
        }
    }
    public void Update(ArrayList<FeedbackInfo> feedbackInfos){
        this.feedbackInfos=feedbackInfos;
        notifyDataSetChanged();
    }
}
