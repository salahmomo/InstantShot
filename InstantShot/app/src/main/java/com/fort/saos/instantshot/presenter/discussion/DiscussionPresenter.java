package com.fort.saos.instantshot.presenter.discussion;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fort.saos.instantshot.model.Message;
import com.fort.saos.instantshot.view.DiscussionActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saldemachki on 28/10/2016.
 */

public class DiscussionPresenter {
    private DiscussionActivity discussionActivity;

    private RecyclerView messagesRecyclerView;
    private DiscussionAdapter discussionAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditText messageEditText;
    private Button sendMessageButton;

    private List<Message> messages;

    public DiscussionPresenter(DiscussionActivity discussionActivity) {
        this.discussionActivity = discussionActivity;
    }

    public void bindComponants(RecyclerView messagesRecyclerView, EditText messageEditText, Button sendMessageButton) {
        this.messagesRecyclerView = messagesRecyclerView;
        this.messageEditText = messageEditText;
        this.sendMessageButton = sendMessageButton;
    }

    public void onCreate(Bundle savedInstanceState) {
        mLayoutManager = new LinearLayoutManager(discussionActivity.getBaseContext());
        messagesRecyclerView.setLayoutManager(mLayoutManager);
        //albums = AlbumSharedPreferenciesManager.getAlbums(albumsFragment.getContext());
        messages = new ArrayList<>();
        discussionAdapter = new DiscussionAdapter(discussionActivity.getBaseContext(), messages);
        discussionAdapter.setOnItemClickListener(new DiscussionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //go to movie element
/*                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.gotToVideoFragment(position, etag);*/
            }
        });

        messagesRecyclerView.setAdapter(discussionAdapter);
    }
}
