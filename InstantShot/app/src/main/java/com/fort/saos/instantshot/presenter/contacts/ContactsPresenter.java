package com.fort.saos.instantshot.presenter.contacts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fort.saos.instantshot.model.User;
import com.fort.saos.instantshot.model.dummy.DummyContent;
import com.fort.saos.instantshot.usecase.ContactUseCase;
import com.fort.saos.instantshot.view.ContactsFragment;
import com.fort.saos.instantshot.view.DiscussionActivity;

import java.util.List;

/**
 * Created by saldemachki on 25/10/2016.
 */

public class ContactsPresenter {
    private int mColumnCount = 1;

    ContactsFragment contactsFragment = null;
    List<User> contacts = null;
    RecyclerView contactRecyclerView = null;

    private OnListFragmentInteractionListener mListener;

    public ContactsPresenter(ContactsFragment contactsFragment){
        this.contactsFragment = contactsFragment;
    }

    public void onAttach(Context context) {
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    public void onDetach() {
        mListener = null;
    }


    public List<User> getAllcontacts() {
        if (contacts == null) {
            ContactUseCase contactUseCase = new ContactUseCase();
            contacts = contactUseCase.getAllContactFromPhone(contactsFragment.getContext());
        }

        return contacts;
    }

    public void initializeContactRecyclerView(View view) {

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            contactRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                contactRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                contactRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            MyContactsRecyclerViewAdapter myContactsRecyclerViewAdapter = new MyContactsRecyclerViewAdapter(getAllcontacts(), mListener);

            myContactsRecyclerViewAdapter.setOnItemClickListener(new MyContactsRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    goToDiscussionActivity();
                }
            });
            contactRecyclerView.setAdapter(myContactsRecyclerViewAdapter);
        }
    }

    public void goToDiscussionActivity() {
        Intent intent = new Intent(contactsFragment.getContext(), DiscussionActivity.class);
        contactsFragment.startActivity(intent);
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(User user);
    }
}
