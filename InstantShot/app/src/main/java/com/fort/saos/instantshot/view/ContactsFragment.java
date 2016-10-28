package com.fort.saos.instantshot.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fort.saos.instantshot.R;
import com.fort.saos.instantshot.presenter.contacts.ContactsPresenter;
import com.fort.saos.instantshot.presenter.contacts.MyContactsRecyclerViewAdapter;
import com.fort.saos.instantshot.model.dummy.DummyContent;
import com.fort.saos.instantshot.model.dummy.DummyContent.DummyItem;

public class ContactsFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private ContactsPresenter contactsPresenter = null;

    public ContactsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ContactsFragment newInstance(int columnCount) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        if (contactsPresenter == null){
            contactsPresenter = new ContactsPresenter(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts_list, container, false);

        // Set the adapter
        contactsPresenter.initializeContactRecyclerView(view);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        contactsPresenter.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        contactsPresenter.onDetach();
    }
}
