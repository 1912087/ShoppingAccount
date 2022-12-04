package com.example.shoppingAccount;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingAccount.dao.ProductDao;
import com.example.shoppingAccount.dto.First_Item;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class First_Adapter extends RecyclerView.Adapter<First_Adapter.CustomViewHolder> {

    SQLiteDatabase database;
    String tableName = "product";
    public static ProductDao productDao;

    private static ArrayList<First_Item> newList;
    private ArrayList<First_Item> mList;
    ArrayList<First_Item> searchArrayList, filteredList;
    First_Item item;
    First_Adapter firstAdapter;
    private LayoutInflater mInflate;
    private First_Adapter mAdapter;
    public static Context mContext;
    private View view;
    int RESULT_CODE = -1;
    public static RecyclerView recyclerView;
    String category;

    private CustomViewHolder mCustomViewHolder;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image_list, image_list1;
        protected TextView name_list, name_list1;
        protected TextView account_list, account_list1;
        protected TextView search_list, search_list1;
        protected TextView productId;
        protected ImageView shopping_favorite;
        protected ImageView category_img, category_img1, category_img2, category_img3, category_img4, category_img5, category_img6;
        protected TextView category, category1, category2, category3, category4, category5, category6;

        public CustomViewHolder(View view) {
            super(view);

            this.productId = (TextView) view.findViewById(R.id.shopping_productId);
            this.image_list = (ImageView) view.findViewById(R.id.shopping_image);
            this.name_list = (TextView) view.findViewById(R.id.shopping_name);
            this.account_list = (TextView) view.findViewById(R.id.shopping_account);
            this.search_list = (TextView) view.findViewById(R.id.shopping_search);
            this.shopping_favorite = (ImageView) view.findViewById(R.id.shopping_favorite);

            this.category = view.findViewById(R.id.category);
            this.category1 = view.findViewById(R.id.category1);
            this.category2 = view.findViewById(R.id.category2);
            this.category3 = view.findViewById(R.id.category3);
            this.category4 = view.findViewById(R.id.category4);
            this.category5 = view.findViewById(R.id.category5);
            this.category6 = view.findViewById(R.id.category6);

            this.category_img = view.findViewById(R.id.category_img);
            this.category_img1 = view.findViewById(R.id.category_img1);
            this.category_img2 = view.findViewById(R.id.category_img2);
            this.category_img3 = view.findViewById(R.id.category_img3);
            this.category_img4 = view.findViewById(R.id.category_img4);
            this.category_img5 = view.findViewById(R.id.category_img5);
            this.category_img6 = view.findViewById(R.id.category_img6);

        }
    }

    public First_Adapter(Context context, String category) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.category = category;
        productDao = new ProductDao(tableName);
        productDao.table_setup();
        this.mList = productDao.productList(category);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder viewholder, @SuppressLint("RecyclerView") final int position) {

        //database = SQLiteDatabase.openOrCreateDatabase("Goods_list",null);
        if(productDao.data_count() == 0) productDao.data_insert();

        //Log.d("result", "result --- > " + String.valueOf(mList.get(position).getPid()));

        //mList = productDao.productList();
        if(!mList.isEmpty()){
            for(First_Item dto : mList) {
                viewholder.productId.setText(String.valueOf(mList.get(position).getPid()));
                viewholder.itemView.setTag(position);

                viewholder.image_list.setImageResource(mList.get(position).getImage_list());
                viewholder.name_list.setText(mList.get(position).getName_list());

                DecimalFormat myFormatter = new DecimalFormat("###,###");
                String formattedStringPrice = myFormatter.format(Integer.parseInt(mList.get(position).getAccount_list()));

                viewholder.account_list.setText(formattedStringPrice);
                viewholder.search_list.setText(mList.get(position).getSearch_list());

                if(mList.get(position).getFavorite() == 0){
                    viewholder.shopping_favorite.setImageResource(R.drawable.heart);
                }else{
                    viewholder.shopping_favorite.setImageResource(R.drawable.heart_border);
                }

                viewholder.shopping_favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        productDao.favoriteUpdate(mList.get(position).getPid());
                        if(productDao.favoriteCheck(mList.get(position).getPid()) == 0){
                            viewholder.shopping_favorite.setImageResource(R.drawable.heart);
                        }else{
                            viewholder.shopping_favorite.setImageResource(R.drawable.heart_border);
                        }
                    }
                });

                /*viewholder.image_list.setImageResource(mList.get(position).getImage_list());
                viewholder.name_list.setText(mList.get(position).getName_list());

                DecimalFormat myFormatter = new DecimalFormat("###,###");
                String formattedStringPrice = myFormatter.format(Integer.parseInt(mList.get(position).getAccount_list()));

                viewholder.account_list.setText(formattedStringPrice);
                viewholder.search_list.setText(mList.get(position).getSearch_list());*/
            }
        }



        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(mContext, goods_click.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    /*ImageView image = (ImageView) v.findViewById(R.id.shopping_image);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                        Bitmap resize = Bitmap.createScaledBitmap(bitmap, 470, 470, true);
                        resize.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        intent.putExtra("goods_image", byteArray);
                        intent.putExtra("goods_name",mList.get(position).getName_list());
                        intent.putExtra("goods_account",mList.get(position).getAccount_list());*/
                    intent.putExtra("pid", viewholder.productId.getText().toString());
                    mContext.startActivity(intent);
               // }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public static void filterList(ArrayList<First_Item> filteredList) {
        newList = filteredList;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}