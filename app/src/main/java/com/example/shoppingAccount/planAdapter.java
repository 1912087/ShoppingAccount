package com.example.shoppingAccount;/*package com.example.shoppingAccount;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class planAdapter extends BaseAdapter {
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
            "청자켓", "아기 아우터", "모직 코트", "캐쥬얼한 자켓", "베이직 바람막이", "짙은 청자켓",
            "검정 레깅스", "청바지", "통 큰 바지", "베이직 팬츠", "화이트 골덴 팬츠", "찢어진 회색 바지",
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
    public static ArrayList<ListItem> foodItemArrayList;

    @Override
    public int getCount() {
        return name1.length ;
    }

    @Override
    public Object getItem(int position) {
        return name1[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static void  filterList(ArrayList<ListItem> filteredList) {
        foodItemArrayList = filteredList;
        //notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        com.example.shoppingAccount.ListItem listItem = new com.example.shoppingAccount.ListItem(parent.getContext());
        listItem.setFirstItem(name1[position]);
        listItem.setSecondItem(account1[position]);
        listItem.setfiveItem(logo1[position]);
        listItem.setSixItem(search1[position]);
        return listItem;
    }
}
*/