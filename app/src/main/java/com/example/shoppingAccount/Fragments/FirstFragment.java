package com.example.shoppingAccount.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.shoppingAccount.First_Item;
import com.example.shoppingAccount.R;
//import com.example.shoppingAccount.planAdapter;

import java.util.ArrayList;

public class FirstFragment extends Fragment {
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

    private Integer[] logo1 = {R.drawable.clothes_mantoman1, R.drawable.clothes_mantoman2, R.drawable.clothes_mantoman3,
            R.drawable.clothes_mantoman4, R.drawable.clothes_mantoman5, R.drawable.clothes_mantoman6,
            R.drawable.clothes_pants1, R.drawable.clothes_pants2, R.drawable.clothes_pants3,
            R.drawable.clothes_pants4,R.drawable.clothes_pants5,R.drawable.clothes_pants6,
            R.drawable.clothes_outer1, R.drawable.clothes_outer2, R.drawable.clothes_outer3,
            R.drawable.clothes_outer4,R.drawable.clothes_outer5,R.drawable.clothes_outer6,
            R.drawable.clothes_skirt1,R.drawable.clothes_skirt2,R.drawable.clothes_skirt3,
            R.drawable.clothes_skirt4,R.drawable.clothes_skirt5,
            R.drawable.clothes_sweat_suit1,R.drawable.clothes_sweat_suit2,R.drawable.clothes_sweat_suit3,
            R.drawable.clothes_sweater1,R.drawable.clothes_sweater2,R.drawable.clothes_sweater3,
            R.drawable.clothes_sweater4,R.drawable.clothes_sweater5,R.drawable.clothes_sweater6};
    String[] name1 = {"검정 맨투맨", "심플 맨투맨", "차분한 후드티","검정 반팔티", "반팔티", "아동용 반팔티",
            "검정 레깅스", "청바지", "통 큰 바지", "베이직 팬츠", "화이트 골덴 팬츠", "찢어진 회색 바지",
            "청자켓", "아기 아우터", "모직 코트", "캐쥬얼한 자켓", "베이직 바람막이", "짙은 청자켓",
            "주름 치마", "라인 스커트", "심플 원피스", "플레어 스커트", "체크무늬 스커트",
            "심플한 츄리닝", "아디다스 블랙", "그린 앤 화이트",
            "심플 니트", "그린 스웨터", "아동용 스웨터", "화이트 니트", "체크무늬 스웨터", "레드와인 스웨터"};
    String[] account1 = {"13500", "57400", "32400", "43500", "20000", "35000", "32000",
            "35000", "32000", "46200", "45100", "56020", "45300",
            "12000", "45020", "35000", "32010", "41000", "39990",
            "45000", "32400", "36100", "29990", "35500",
            "23000", "24000", "47000",
            "45000", "32000", "31000", "24000", "55000", "32000"};
    String[] search1 = {"mantoman", "mantoman", "mantoman", "mantoman", "mantoman", "mantoman",
            "pants", "pants", "pants", "pants", "pants", "pants",
            "outer", "outer", "outer", "outer", "outer", "outer",
            "skirt", "skirt", "skirt", "skirt", "skirt",
            "sweat_suit", "sweat_suit", "sweat_suit",
            "sweater", "sweater", "sweater", "sweater", "sweater", "sweater"};

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

        for(int position=0; position<name1.length; position++){
            item = new First_Item(logo1[position],name1[position],account1[position],search1[position]);
            mArrayList.add(item);
        }

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        //recyclerView.setItemViewCacheSize(35);
        mAdapter = new First_Adapter(context, mArrayList);
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
                for (int i = 0; i < a; i++) {
                    item = new First_Item(logo1[i], name1[i], account1[i], search1[i]);
                    mArrayList.add(item);
                }
                mAdapter = new First_Adapter(getContext(), mArrayList);
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
                for (int i = 0; i < a; i++) {
                    if (search1[i] == "mantoman") {
                        item = new First_Item(logo1[i], name1[i], account1[i], search1[i]);
                        mArrayList.add(item);
                    }
                }
                mAdapter = new First_Adapter(getContext(), mArrayList);
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
                for (int i = 0; i < a; i++) {
                    if (search1[i] == "pants") {
                        item = new First_Item(logo1[i], name1[i], account1[i], search1[i]);
                        mArrayList.add(item);
                    }
                }
                mAdapter = new First_Adapter(getContext(), mArrayList);
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
                for (int i = 0; i < a; i++) {
                    if (search1[i] == "outer") {
                        item = new First_Item(logo1[i], name1[i], account1[i], search1[i]);
                        mArrayList.add(item);
                    }
                }
                mAdapter = new First_Adapter(getContext(), mArrayList);
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
                for (int i = 0; i < a; i++) {
                    if (search1[i] == "skirt") {
                        item = new First_Item(logo1[i], name1[i], account1[i], search1[i]);
                        mArrayList.add(item);
                    }
                }
                mAdapter = new First_Adapter(getContext(), mArrayList);
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
                for (int i = 0; i < a; i++) {
                    if (search1[i] == "sweat_suit") {
                        item = new First_Item(logo1[i], name1[i], account1[i], search1[i]);
                        mArrayList.add(item);
                    }
                }
                mAdapter = new First_Adapter(getContext(), mArrayList);
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
                for (int i = 0; i < a; i++) {
                    if (search1[i] == "sweater") {
                        item = new First_Item(logo1[i], name1[i], account1[i], search1[i]);
                        mArrayList.add(item);
                    }
                }
                mAdapter = new First_Adapter(getContext(), mArrayList);
                mAdapter.setHasStableIds(true);

                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });


    }
}
