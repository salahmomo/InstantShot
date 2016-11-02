package com.fort.saos.instantshot.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.fort.saos.instantshot.R;
import com.fort.saos.instantshot.presenter.discussion.DiscussionPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscussionActivity extends AppCompatActivity {

    private DiscussionPresenter discussionPresenter = null;

    @Bind(R.id.messages_recyclerview)
    RecyclerView messagesRecyclerView;

    @Bind(R.id.message_edit_text)
    EditText messageEditText;

    @Bind(R.id.send_message_button)
    Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        if (discussionPresenter == null) {
            discussionPresenter = new DiscussionPresenter(this);
        }

        ButterKnife.bind(this);
        discussionPresenter.bindComponants(messagesRecyclerView, messageEditText, sendMessageButton);
        discussionPresenter.onCreate(savedInstanceState);
    }
}
