package com.janad.covid_19.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.janad.covid_19.Models.mdlVideo;
import com.janad.covid_19.R;

import java.util.ArrayList;

public class videoPasAdapter extends RecyclerView.Adapter<videoPasAdapter.MyHolder> {

    ArrayList<mdlVideo> list = new ArrayList<>();
    onVideoClickListener mOnVideoClickListener;

    public videoPasAdapter(ArrayList<mdlVideo> list, onVideoClickListener onVideoClickListener) {
        this.list = list;
        mOnVideoClickListener = onVideoClickListener;
    }

    @NonNull
    @Override
    public videoPasAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_video,parent, false);
        MyHolder holder = new MyHolder(view, mOnVideoClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull videoPasAdapter.MyHolder holder, int position) {

        String txtVideoTitle = list.get(position).getTitle();

        holder.mtxtVideo.setText(txtVideoTitle);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mtxtVideo;
        CardView mCdVideo;

        onVideoClickListener mOnVideoClickListener;

        public MyHolder(@NonNull View itemView, onVideoClickListener mOnVideoClickListener) {
            super(itemView);

            mtxtVideo = (TextView) itemView.findViewById(R.id.txtVideo);
            mCdVideo = (CardView) itemView.findViewById(R.id.cdVideo);


            this.mOnVideoClickListener = mOnVideoClickListener;

            mCdVideo.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            mOnVideoClickListener.onClick(getAdapterPosition());
        }
    }


    public interface onVideoClickListener{

        void onClick(int position);
    }
}
