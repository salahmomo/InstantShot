package com.fort.saos.instantshot.presenter.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fort.saos.instantshot.R;
import com.fort.saos.instantshot.model.User;
import com.fort.saos.instantshot.model.dummy.DummyContent.DummyItem;

import java.util.List;

public class MyContactsRecyclerViewAdapter extends RecyclerView.Adapter<MyContactsRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;
    private final ContactsPresenter.OnListFragmentInteractionListener mListener;

    public MyContactsRecyclerViewAdapter(List<User> items, ContactsPresenter.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nameView.setText(mValues.get(position).getFirstName());
        holder.numberView.setText(mValues.get(position).getPhoneNumber());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameView;
        public final TextView numberView;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nameView = (TextView) view.findViewById(R.id.contact_name);
            numberView = (TextView) view.findViewById(R.id.contact_number);
        }
    }
}