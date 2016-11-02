package com.fort.saos.instantshot.presenter.discussion;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fort.saos.instantshot.R;
import com.fort.saos.instantshot.model.Message;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saldemachki on 28/10/2016.
 */

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.ViewHolder>{
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textMessage;

        public ViewHolder(View v) {
            super(v);
            textMessage = (TextView) v.findViewById(R.id.txtMessage);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (myOnItemClickListener != null)
                myOnItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    private Context context;
    private List<Message> messages = new ArrayList<>();
    OnItemClickListener myOnItemClickListener;

    public DiscussionAdapter (Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0 :
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.receive_message_discussion_item, parent, false);
                ViewHolder vh = new ViewHolder(v);
                return vh;
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mymessage_discussion_item, parent, false);
                vh = new ViewHolder(v);
                return vh;

        }
        /*View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mymessage_discussion_item, parent, false);
        ViewHolder vh = new ViewHolder(v);*/
//        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textMessage.setText(messages.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.myOnItemClickListener = onItemClickListener;
    }
}
