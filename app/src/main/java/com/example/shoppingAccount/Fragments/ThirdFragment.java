package com.example.shoppingAccount.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoppingAccount.R;
import com.example.shoppingAccount.favorite.FavoriteMain;
import com.example.shoppingAccount.mypageAdapter;
import com.example.shoppingAccount.notice_page;
import com.example.shoppingAccount.orderList.order_main;

public class ThirdFragment extends Fragment {
    View pg3;
    FragmentContainer con;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pg3 = inflater.inflate(R.layout.fragment_third, container, false);
        return pg3;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView3 = pg3.findViewById(R.id.list3);
        mypageAdapter adapter3 = new mypageAdapter();
        listView3.setAdapter(adapter3);

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 메인액티비티의 함수를 호출해서 상세 fragment 로 position 값을 넘기면서 이동한다
                if(position == 0){
                    //Toast.makeText(getContext(), "Stop Clicking me1", Toast.LENGTH_SHORT).show();
                    getFragmentManager().beginTransaction().replace(R.id.host_fragment, new notice_page()).commit();
                }
                else if(position == 1){
                    getFragmentManager().beginTransaction().replace(R.id.host_fragment, new order_main()).commit();
                }else if(position == 2){
                    getFragmentManager().beginTransaction().replace(R.id.host_fragment, new FavoriteMain()).commit();
                }
            }
        });
    }
}
