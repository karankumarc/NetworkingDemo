package com.techpalle.karan.networkingdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.techpalle.karan.networkingdemo.R;
import com.techpalle.karan.networkingdemo.model.Contact;
import java.util.List;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.MyViewHolder> {

    public static final String TAG = ContactsRecyclerAdapter.class.getSimpleName();

    private Context context;
    private List<Contact> mData;
    private LayoutInflater mInflater;

    public ContactsRecyclerAdapter(Context context, List<Contact> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "OnCreateViewHolderCalled ");
        View view = mInflater.inflate(R.layout.contacts_row, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "OnBindViewHolderCalled " + position);
        Contact currentContact = mData.get(position);
        holder.setData(currentContact, position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextName, mTextEmail, mTextContact;
        private int position;
        private Contact currentContact;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.row_name);
            mTextEmail = (TextView) itemView.findViewById(R.id.row_email);
            mTextContact = (TextView) itemView.findViewById(R.id.row_mobile);

            mTextName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, currentContact.getEmail(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(Contact currentContact, int position) {
            this.position = position;
            this.currentContact = currentContact;
            mTextName.setText(currentContact.getName());
            mTextEmail.setText(currentContact.getEmail());
            mTextContact.setText(currentContact.getPhone());

        }
    }
}
