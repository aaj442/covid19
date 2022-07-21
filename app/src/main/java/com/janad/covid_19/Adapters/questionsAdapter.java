package com.janad.covid_19.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.janad.covid_19.Models.mdlQuestions;
import com.janad.covid_19.Models.mdlVideo;
import com.janad.covid_19.R;

import java.util.ArrayList;

public class questionsAdapter extends RecyclerView.Adapter<questionsAdapter.MyHolder> {


    ArrayList<mdlQuestions> list = new ArrayList<>();
    questionsAdapter.onQuestionsClickListener mOnQuesClickListener;

    public questionsAdapter(ArrayList<mdlQuestions> list, questionsAdapter.onQuestionsClickListener onQuestionsClickListener) {
        this.list = list;
        mOnQuesClickListener = onQuestionsClickListener;
    }

    @NonNull
    @Override
    public questionsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_questions,parent, false);
        questionsAdapter.MyHolder holder = new questionsAdapter.MyHolder(view, mOnQuesClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull questionsAdapter.MyHolder holder, int position) {

        String txtQuestions = list.get(position).getTxtQuestions();
        String txtAnswers = list.get(position).getTxtAnswers();

        holder.mtxtQuestions.setText(txtQuestions);
        holder.mtxtAnsweres.setText(txtAnswers);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mtxtQuestions, mtxtAnsweres;
        //CardView mCdVideo;

        questionsAdapter.onQuestionsClickListener mOnQuesClickListener;

        public MyHolder(@NonNull View itemView, questionsAdapter.onQuestionsClickListener mOnQuesClickListener) {
            super(itemView);

            mtxtQuestions = (TextView) itemView.findViewById(R.id.txtQuestions);
            mtxtAnsweres = (TextView) itemView.findViewById(R.id.txtAnswers);
            //
            //
            // mCdVideo = (CardView) itemView.findViewById(R.id.cdVideo);


            this.mOnQuesClickListener = mOnQuesClickListener;

            //mCdVideo.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            mOnQuesClickListener.onClick(getAdapterPosition());
        }
    }


    public interface onQuestionsClickListener{

        void onClick(int position);
    }
}
