package com.example.shoppingAccount.favorite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingAccount.First_Adapter;
import com.example.shoppingAccount.R;
import com.example.shoppingAccount.dao.DBHelper;
import com.example.shoppingAccount.dao.ProductDao;
import com.example.shoppingAccount.dto.First_Item;
import com.example.shoppingAccount.orderList.Item;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Favorite_Adapter extends RecyclerView.Adapter<Favorite_Adapter.CustomViewHolder> {

    public static ArrayList<First_Item> mList;
    private LayoutInflater mInflate;
    private Context mContext;
    private DBHelper mydb;
    private View view;
    Context _ctx;

    private CustomViewHolder mCustomViewHolder;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView name;
        protected TextView account;
        protected TextView category;
        protected ImageView favorite;

        public CustomViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.fvImage);
            this.name = (TextView) view.findViewById(R.id.fvName);
            this.account = (TextView) view.findViewById(R.id.fvAccount);
            this.category = (TextView) view.findViewById(R.id.fvCategory);
            this.favorite = (ImageView) view.findViewById(R.id.fvFavorite);
        }
    }

    public Favorite_Adapter(Context context) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mList = First_Adapter.productDao.favoriteItem();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.favorite_list, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        if(!mList.isEmpty()) {
            viewHolder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    First_Adapter.productDao.favoriteUpdate(mList.get(position).getPid());
                    mList.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                }
            });

            viewHolder.image.setImageResource(mList.get(position).getImage_list());
            viewHolder.name.setText(mList.get(position).getName_list());

            DecimalFormat myFormatter = new DecimalFormat("###,###");
            String formattedStringPrice = myFormatter.format(Integer.parseInt(mList.get(position).getAccount_list()));

            viewHolder.account.setText(formattedStringPrice);
        }

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
}