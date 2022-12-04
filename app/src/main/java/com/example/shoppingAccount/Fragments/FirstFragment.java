package com.example.shoppingAccount.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingAccount.First_Adapter;
import com.example.shoppingAccount.dao.ProductDao;
import com.example.shoppingAccount.dto.First_Item;
import com.example.shoppingAccount.R;
//import com.example.shoppingAccount.planAdapter;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    String tableName = "product";
    public static ProductDao productDao;

    View v;
    private Activity activity;
    private ArrayList<First_Item> mList;
    ImageView goods_image;
    ImageView category_img, category_img1, category_img2, category_img3, category_img4, category_img5, category_img6;
    TextView goods_name, goods_account, goods_search;
    TextView category, category1, category2, category3, category4, category5, category6;
    String[] search;
    Intent intent;
    First_Item item;
    private ArrayList<First_Item> mArrayList = new ArrayList<>();
    public static First_Adapter mAdapter;
    public static RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    int a;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_first, container, false);

        Context context = v.getContext();
        recyclerView = (RecyclerView) v.findViewById(R.id.list1);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        //recyclerView.setItemViewCacheSize(35);

        mAdapter = new First_Adapter(context, "all");
        mAdapter.setHasStableIds(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        a = mArrayList.size();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView search_check = (TextView) view.findViewById(R.id.shopping_search);

        category = v.findViewById(R.id.category);
        category1 = v.findViewById(R.id.category1);
        category2 = v.findViewById(R.id.category2);
        category3 = v.findViewById(R.id.category3);
        category4 = v.findViewById(R.id.category4);
        category5 = v.findViewById(R.id.category5);
        category6 = v.findViewById(R.id.category6);

        category_img = v.findViewById(R.id.category_img);
        category_img1 = v.findViewById(R.id.category_img1);
        category_img2 = v.findViewById(R.id.category_img2);
        category_img3 = v.findViewById(R.id.category_img3);
        category_img4 = v.findViewById(R.id.category_img4);
        category_img5 = v.findViewById(R.id.category_img5);
        category_img6 = v.findViewById(R.id.category_img6);

        category_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();
                mAdapter = new First_Adapter(getContext(), "all");
                /*for (int i = 0; i < a; i++) {
                    item = new First_Item(logo1[i], name1[i], account1[i], search1[i]);
                    mArrayList.add(item);
                }
                mAdapter = new First_Adapter(getContext(), mArrayList);*/
                mAdapter.setHasStableIds(true);

                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
        category_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();
                mAdapter = new First_Adapter(getContext(), "mantoman");
                mAdapter.setHasStableIds(true);

                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
        category_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();
                mAdapter = new First_Adapter(getContext(), "pants");
                mAdapter.setHasStableIds(true);

                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
        category_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();
                mAdapter = new First_Adapter(getContext(), "outer");
                mAdapter.setHasStableIds(true);

                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
        category_img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();
                mAdapter = new First_Adapter(getContext(), "skirt");
                mAdapter.setHasStableIds(true);

                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
        category_img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();
                mAdapter = new First_Adapter(getContext(), "sweat_suit");
                mAdapter.setHasStableIds(true);

                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
        category_img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();
                mAdapter = new First_Adapter(getContext(), "sweater");
                mAdapter.setHasStableIds(true);

                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });


    }
}
