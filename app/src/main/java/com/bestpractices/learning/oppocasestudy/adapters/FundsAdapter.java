package com.bestpractices.learning.oppocasestudy.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bestpractices.learning.oppocasestudy.R;
import com.bestpractices.learning.oppocasestudy.models.Funds;
import com.bestpractices.learning.oppocasestudy.models.FundsModel;
import com.bestpractices.learning.oppocasestudy.ui.MainActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class FundsAdapter extends RecyclerView.Adapter<FundsAdapter.FundsViewHolder> {
    public static final String TAG = FundsAdapter.class.getSimpleName();
    private List<Funds> funds;
    private Context context;
    public FundsAdapter(List<Funds> fundsModel, Context mContext) {
        this.context = mContext;
        funds = fundsModel;
    }

    @NonNull
    @Override
    public FundsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.funds_item_list,parent,false);
        return new FundsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FundsViewHolder holder, int position) {
        holder.fundTitle.setText(funds.get(position).getFundName());
        holder.fundCategory.setText(funds.get(position).getFundCategory());
        holder.ratingBar.setRating(valueOf(funds.get(position).getRating()));
        holder.fundsNAV.setText("NAV\n" + funds.get(position).getNav());
        holder.fundReturn.setText("3 year\n" + funds.get(position).getThreeYearReturns());

        Glide.with(this.context)
                .load(funds.get(position).getUrl())
                .placeholder(R.drawable.funds)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fundImage);

    }

    @Override
    public int getItemCount() {
        if(funds!=null){
            return  funds.size();
        }
        return 0;
    }

    class FundsViewHolder extends RecyclerView.ViewHolder{
        private TextView fundTitle;
        private TextView fundReturn;
        private TextView fundCategory;
        private TextView rating;
        private TextView fundsNAV;
        private ImageView fundImage;
        private RatingBar ratingBar;

        public FundsViewHolder(@NonNull View itemView) {
            super(itemView);
            fundTitle = itemView.findViewById(R.id.fund_title);
            fundReturn = itemView.findViewById(R.id.fund_return);
            fundCategory = itemView.findViewById(R.id.fund_category);
            rating = itemView.findViewById(R.id.fund_rating);
            fundsNAV = itemView.findViewById(R.id.fund_nav);
            fundImage = itemView.findViewById(R.id.fund_image);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }
    }
}
