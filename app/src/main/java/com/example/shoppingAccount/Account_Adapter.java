package com.example.shoppingAccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Account_Adapter extends BaseAdapter {

    public Context context;
    ArrayList<Account_Item> listViewItemList;
    int a = 0;

    public Account_Adapter(Context context, ArrayList<Account_Item> mArrayList) {
        this.context = context;
        this.listViewItemList = mArrayList;
    }

    // listviewitem 항목개수
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    // position 위치의 item 값을 리턴
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    // position 위치의 item 의 row id값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position 위치의 item항목을 View 형식으로 얻어온다.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // listview_item의 layout을 inflate하여 xml을 view로 만들고 convertView 참조 획득
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.account_item,parent,false);
        }
        TextView txt_ac = convertView.findViewById(R.id.account_order);
        TextView txt_mt = convertView.findViewById(R.id.moment);
        TextView total = convertView.findViewById(R.id.sum_month);


        // Data Set (listViewItemList) 에서 position에 위치한 데이터참조 획득
        Account_Item listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formattedStringPrice = myFormatter.format(Integer.parseInt(listViewItem.getAccount_ac()));
        txt_ac.setText(formattedStringPrice);
        txt_mt.setText(listViewItem.getDate());

        return convertView;
    }

    // item 데이터 추가
    public void addItem(String account, String date) {
        Account_Item item = new Account_Item(account, date);
        item.setAccount_ac(account);
        item.setDate(date);
        listViewItemList.add(item);
    }

    // item 삭제
    public void delItem(int position) {
        listViewItemList.remove(position);
    }

}
