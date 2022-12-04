package com.example.shoppingAccount;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class mypageAdapter extends BaseAdapter {
    private Integer[] icon1 = {R.drawable.notice, R.drawable.order_request, R.drawable.page3_heart};
    String[] mypage_title = {"공지사항", "주문목록", "찜한 상품"};
    @Override
    public int getCount() {
        return mypage_title.length ;
    }

    @Override
    public Object getItem(int position) {
        return mypage_title[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mypageItem listItem = new mypageItem(parent.getContext());
        listItem.iconItem(icon1[position]);
        listItem.mypageItem(mypage_title[position]);
        return listItem;
    }
}
