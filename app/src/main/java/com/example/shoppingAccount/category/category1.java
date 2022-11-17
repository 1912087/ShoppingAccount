package com.example.shoppingAccount.category;

import android.app.Activity;
import android.content.Context;
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

import com.example.shoppingAccount.First_Adapter;
import com.example.shoppingAccount.First_Item;
import com.example.shoppingAccount.R;

import java.util.ArrayList;

public class category1 extends Fragment {
    View v;
    private Activity activity;
    private ArrayList<category_Item> mArrayList = new ArrayList<>();
    category_Item item;
    public static category1_Adapter mAdapter;
    public static RecyclerView recyclerView;
    private Integer[] logo1 = {R.drawable.clothes_mantoman1, R.drawable.clothes_mantoman2, R.drawable.clothes_mantoman3,
            R.drawable.clothes_mantoman4, R.drawable.clothes_mantoman5, R.drawable.clothes_mantoman6,
            R.drawable.clothes_outer1, R.drawable.clothes_outer2, R.drawable.clothes_outer3,
            R.drawable.clothes_outer4,R.drawable.clothes_outer5,R.drawable.clothes_outer6,
            R.drawable.clothes_sweat_suit1,R.drawable.clothes_sweat_suit2,R.drawable.clothes_sweat_suit3,
            R.drawable.clothes_sweater1,R.drawable.clothes_sweater2,R.drawable.clothes_sweater3,
            R.drawable.clothes_sweater4,R.drawable.clothes_sweater5,R.drawable.clothes_sweater6};
    String[] name1 = {"검정 맨투맨", "심플 맨투맨", "차분한 후드티","검정 반팔티", "반팔티", "아동용 반팔티",
            "청자켓", "아기 아우터", "모직 코트", "캐쥬얼한 자켓", "베이직 바람막이", "짙은 청자켓",
            "심플한 츄리닝", "아디다스 블랙", "그린 앤 화이트",
            "심플 니트", "그린 스웨터", "아동용 스웨터", "화이트 니트", "체크무늬 스웨터", "레드와인 스웨터"};
    String[] account1 = {"13500", "57400", "32400", "43500", "20000", "35000",
            "32000", "35000", "32000", "46200", "45100", "56020",
            "45300", "12000", "45020", "35000", "32010", "41000",
            "35500", "23000", "24000",
            "47000", "45000", "32000", "31000", "24000", "55000"};
    String[] search1 = {"mantoman", "mantoman", "mantoman", "mantoman", "mantoman", "mantoman",
            "outer", "outer", "outer", "outer", "outer", "outer",
            "sweat_suit", "sweat_suit", "sweat_suit",
            "sweater", "sweater", "sweater", "sweater", "sweater", "sweater"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.category1, container, false);

        Context context = v.getContext();
        recyclerView = (RecyclerView) v.findViewById(R.id.category1_list);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        for(int position=0; position<name1.length; position++){
            item = new category_Item(logo1[position],name1[position],account1[position],search1[position]);
            mArrayList.add(item);
        }

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        //recyclerView.setItemViewCacheSize(35);
        mAdapter = new category1_Adapter(context, mArrayList);
        mAdapter.setHasStableIds(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
