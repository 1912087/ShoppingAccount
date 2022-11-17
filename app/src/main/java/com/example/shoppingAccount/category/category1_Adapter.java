package com.example.shoppingAccount.category;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingAccount.First_Adapter;
import com.example.shoppingAccount.First_Item;
import com.example.shoppingAccount.R;
import com.example.shoppingAccount.goods_click;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class category1_Adapter extends RecyclerView.Adapter<category1_Adapter.CustomViewHolder> {


    private static ArrayList<category_Item> newList;
    private ArrayList<category_Item> mList;
    ArrayList<category_Item> searchArrayList, filteredList;
    category1_Adapter firstAdapter;
    private LayoutInflater mInflate;
    private category1_Adapter mAdapter;
    private Context mContext;
    private View view;
    public static RecyclerView recyclerView;

    private CustomViewHolder mCustomViewHolder;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image_list;
        protected TextView name_list;
        protected TextView account_list;
        protected TextView search_list;

        public CustomViewHolder(View view) {
            super(view);

            this.image_list = (ImageView) view.findViewById(R.id.category1_image);
            this.name_list = (TextView) view.findViewById(R.id.category1_name);
            this.account_list = (TextView) view.findViewById(R.id.category1_account);
            this.search_list = (TextView) view.findViewById(R.id.category1_search);

        }
    }

    public category1_Adapter(Context context, ArrayList<category_Item> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflate = LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category1_item, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder viewholder, final int position) {

        viewholder.image_list.setImageResource(mList.get(position).getImage_list());
        viewholder.name_list.setText(mList.get(position).getName_list());

        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formattedStringPrice = myFormatter.format(Integer.parseInt(mList.get(position).getAccount_list()));

        viewholder.account_list.setText(formattedStringPrice);
        viewholder.search_list.setText(mList.get(position).getSearch_list());
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