package com.example.shoppingAccount.orderList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingAccount.dao.DBHelper;
import com.example.shoppingAccount.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class order_planAdapter extends RecyclerView.Adapter<order_planAdapter.CustomViewHolder> {

    private ArrayList<Item> mList;
    private LayoutInflater mInflate;
    private order_planAdapter mAdapter;
    private ArrayList<Item> mArrayList = new ArrayList<>();
    private Context mContext;
    private DBHelper mydb;
    private View view;
    Context _ctx;

    private CustomViewHolder mCustomViewHolder;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView name;
        protected TextView amount;
        protected TextView account;
        protected Button return_btnn;

        public CustomViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.order_goods_list_img);
            this.name = (TextView) view.findViewById(R.id.order_goods_list_name);
            this.amount = (TextView) view.findViewById(R.id.order_goods_list_amount);
            this.account = (TextView) view.findViewById(R.id.order_goods_list_account);
            this.return_btnn = (Button) view.findViewById(R.id.return_btn);

        }
    }

    public order_planAdapter(Context context, ArrayList<Item> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflate = LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_list, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        //return mCustomViewHolder = viewHolder.CustomViewHolder(viewGroup);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, @SuppressLint("RecyclerView") final int position) {

        viewholder.return_btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "?????? ????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
                DBHelper mydb;
                mydb = new DBHelper(mContext);
                mydb.deleteData(String.valueOf(mList.get(position).getId()));
                mydb.getAllGoods();
                mList.remove(position);
                notifyItemRemoved(position);
                //mAdapter.setHasStableIds(true);
                //mAdapter.clear();
                //mArrayList = mydb.getAllGoods();
                //mAdapter.setHasStableds(true);
                //order_main.newList();
                notifyDataSetChanged();
                /*Intent intent = new Intent(R.layout.order_main, view, false);
                intent.setData(Uri.parse(sampleItemList.get(position).address));
                context.startActivity(intent);*/
            }
        });

        byte[] byteArray = mList.get(position).getImage();
        Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        viewholder.image.setImageBitmap(image);
        viewholder.name.setText(mList.get(position).getName());
        viewholder.amount.setText(mList.get(position).getAmount());

        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formattedStringPrice = myFormatter.format(Integer.parseInt(mList.get(position).getAccount()));

        viewholder.account.setText(formattedStringPrice);

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