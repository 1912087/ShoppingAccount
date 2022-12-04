package com.example.shoppingAccount.favorite;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingAccount.First_Adapter;
import com.example.shoppingAccount.R;
import com.example.shoppingAccount.dao.DBHelper;
import com.example.shoppingAccount.dto.First_Item;
import com.example.shoppingAccount.orderList.Item;
import com.example.shoppingAccount.orderList.order_planAdapter;

import java.util.ArrayList;

public class FavoriteMain extends Fragment {

    public static ArraySet<First_Item> items;
    View favoriteView;
    private ArrayList<First_Item> mArrayList = new ArrayList<>();
    public static Favorite_Adapter mAdapter;
    public static RecyclerView recyclerView;
    private ImageView fvFavorite;
    private TextView favorite_noList;
    //public static RecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favoriteView = inflater.inflate(R.layout.favorite_main, container, false);
        fvFavorite = (ImageView) favoriteView.findViewById(R.id.fvFavorite);

        mAdapter = new Favorite_Adapter(getContext());
        mAdapter.setHasStableIds(true);

        Context context = favoriteView.getContext();

        recyclerView = (RecyclerView) favoriteView.findViewById(R.id.favorite_view);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        return favoriteView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}