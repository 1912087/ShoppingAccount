package com.example.shoppingAccount.orderList;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingAccount.dao.DBHelper;
import com.example.shoppingAccount.R;
//import com.example.shoppingAccount.RecyclerViewAdapter;
//import com.example.shoppingAccount.mypageAdapter;
//import com.example.shoppingAccount.notice_page;

import java.util.ArrayList;

public class order_main extends Fragment {

    public static ArraySet<Item> items;
    View or_main;
    private ArrayList<Item> mArrayList = new ArrayList<>();
    public static order_planAdapter mAdapter;
    public static RecyclerView recyclerView;
    private Button return_btn;
    //public static RecyclerViewAdapter adapter;
    DBHelper mydb;

    public order_main() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        or_main = inflater.inflate(R.layout.order_main, container, false);
        return_btn = (Button) or_main.findViewById(R.id.return_btn);

        mydb = new DBHelper(getContext());
        //ArrayList array_list = mydb.getAllGoods();

        Context context = or_main.getContext();
        recyclerView = (RecyclerView) or_main.findViewById(R.id.recycler_view);
        //recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //mArrayList = new ArrayList<>();
        //mAdapter = new order_planAdapter(mArrayList);
        //recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        //Integer a = R.drawable.clothes_mantoman1;

        /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.sweat_suit);
        //Bitmap bitmap = ((BitmapDrawable)icon.getDrawable()).getBitmap();
        Bitmap resize = Bitmap.createScaledBitmap(icon, 470, 470, true);
        resize.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();*/

        mArrayList = mydb.getAllGoods(); // RecyclerView의 마지막 줄에 삽입
        //Item data = new Item(byteArray,"이름","수량","가격");
        //mArrayList.add(data);
        //recyclerView.setItemViewCacheSize(35);
        mAdapter = new order_planAdapter(context, mArrayList);
        mAdapter.setHasStableIds(true);

        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return or_main;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ListView listView3 = or_main.findViewById(R.id.recycler_view);
        //mypageAdapter adapter3 = new mypageAdapter();
        //listView3.setAdapter(adapter3);
    }
}