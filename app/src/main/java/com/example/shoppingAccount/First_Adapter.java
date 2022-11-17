package com.example.shoppingAccount;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingAccount.Fragments.FirstFragment;
import com.example.shoppingAccount.orderList.order_planAdapter;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class First_Adapter extends RecyclerView.Adapter<First_Adapter.CustomViewHolder> {


    private static ArrayList<First_Item> newList;
    private ArrayList<First_Item> mList;
    ArrayList<First_Item> searchArrayList, filteredList;
    First_Adapter firstAdapter;
    private LayoutInflater mInflate;
    private First_Adapter mAdapter;
    private Context mContext;
    private View view;
    int RESULT_CODE = -1;
    public static RecyclerView recyclerView;

    private CustomViewHolder mCustomViewHolder;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image_list, image_list1;
        protected TextView name_list, name_list1;
        protected TextView account_list, account_list1;
        protected TextView search_list, search_list1;
        protected ImageView category_img, category_img1, category_img2, category_img3, category_img4, category_img5, category_img6;
        protected TextView category, category1, category2, category3, category4, category5, category6;

        public CustomViewHolder(View view) {
            super(view);

            this.image_list = (ImageView) view.findViewById(R.id.shopping_image);
            this.name_list = (TextView) view.findViewById(R.id.shopping_name);
            this.account_list = (TextView) view.findViewById(R.id.shopping_account);
            this.search_list = (TextView) view.findViewById(R.id.shopping_search);

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

    public First_Adapter(Context context, ArrayList<First_Item> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflate = LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder viewholder, final int position) {

        ArrayList<First_Item> array_list = new ArrayList<>();

        viewholder.itemView.setTag(position);

        viewholder.image_list.setImageResource(mList.get(position).getImage_list());
        viewholder.name_list.setText(mList.get(position).getName_list());

        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formattedStringPrice = myFormatter.format(Integer.parseInt(mList.get(position).getAccount_list()));

        viewholder.account_list.setText(formattedStringPrice);
        viewholder.search_list.setText(mList.get(position).getSearch_list());

        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(mContext, goods_click.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ImageView image = (ImageView) v.findViewById(R.id.shopping_image);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                        Bitmap resize = Bitmap.createScaledBitmap(bitmap, 470, 470, true);
                        resize.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        intent.putExtra("goods_image", byteArray);
                        intent.putExtra("goods_name",mList.get(position).getName_list());
                        intent.putExtra("goods_account",mList.get(position).getAccount_list());
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