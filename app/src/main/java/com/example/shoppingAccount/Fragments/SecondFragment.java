package com.example.shoppingAccount.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingAccount.Account_Adapter;
import com.example.shoppingAccount.Account_Item;
import com.example.shoppingAccount.DBHelper;
import com.example.shoppingAccount.First_Item;
import com.example.shoppingAccount.Popup.Popup_goal;
import com.example.shoppingAccount.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class SecondFragment extends Fragment {
    View v;
    Button btn1, add_btn, minus_btn;
    TextView month_txt, txt1, txt2, emotion, emotion_txt, account_order;
    ImageView emotion_img;
    private Integer add_count, count_goods, total_account;
    DBHelper mydb;
    private ArrayList<Account_Item> mArrayList = new ArrayList<>();
    private ListView myListView;
    Account_Adapter mAdapter;
    Account_Item item;
    int a;
    long mNow;
    Date mDate;
    String id, s;
    private boolean saveLoginData;
    DecimalFormat myFormatter = new DecimalFormat("###,###");
    private SharedPreferences appData;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat md = new SimpleDateFormat("MM");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_second, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 설정값 불러오기
        appData = this.getActivity().getSharedPreferences("appData", MODE_PRIVATE);
        load();

        btn1 = v.findViewById(R.id.button_set_budget);
        month_txt = v.findViewById(R.id.text_monthly);
        txt1 = v.findViewById(R.id.money_goal);
        txt2 = v.findViewById(R.id.sum_month);
        emotion = v.findViewById(R.id.money_emotion);
        emotion_img = v.findViewById(R.id.emotion);
        emotion_txt = v.findViewById(R.id.emotion_text);
        add_btn = v.findViewById(R.id.month_add_btn);
        minus_btn = v.findViewById(R.id.month_minus_btn);

        // 이전에 로그인 정보를 저장시킨 기록이 있다면
        if (saveLoginData) {
            txt1.setText(id);
            if(txt1.getText().toString() == "0"){
                saveLoginData = false;
            }
        }

        mydb = new DBHelper(getContext());
        //String sa = String.format("%02d", getMonth());
        //mArrayList = mydb.getAccount();
        mArrayList = (ArrayList<Account_Item>) mydb.getTop(getMonth());

        mAdapter = new Account_Adapter(getContext(), mArrayList);
        myListView = v.findViewById(R.id.account_list);

        for (int i = 0; i < mArrayList.size(); i++) {
            String split_date = "-"+getMonth()+"-";
            if(mArrayList.get(i).date.contains(split_date)) {
                String strpoint = mArrayList.get(i).account_ac;
                a += Integer.parseInt(strpoint);
            }
        }
        String formattedStringPrice = myFormatter.format(a);
        txt2.setText(formattedStringPrice);

        emotion_check();
        star();
        //Account_Adapter adapter = new Account_Adapter();
        myListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        if(savedInstanceState != null) {
            String account1 = savedInstanceState.getString("goal_account");
            txt1.setText(account1);
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popup_goal dialog = new Popup_goal();
                dialog.show(getActivity().getSupportFragmentManager(), "tag");
                dialog.setDialogResult(new Popup_goal.OnMyDialogResult() {
                    @Override
                    public void finish(String result) {
                        txt1.setText(result);
                        //goalin(result);
                        //emotion_check();
                        emotion_check();
                        save();
                    }
                });
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();
                add_count += 1;
                if(add_count > 12){
                    add_count = 12;
                }
                s = String.format("%02d", add_count);
                month_txt.setText(s);
                Month_checking();
                month_total(s);
                emotion_check();
                //mydb.getTop(s);
            }
        });
        minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();
                add_count -= 1;
                if(add_count < 1){
                    add_count = 1;
                }
                s = String.format("%02d", add_count);
                month_txt.setText(s);
                Month_checking();
                month_total(s);
                emotion_check();
                //mydb.getTop(s);
                //month_txt.setText(add_count.toString());
            }
        });
    }
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    private String getMonth(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return md.format(mDate);
    }
    private String star(){
        add_count = Integer.parseInt(getMonth());
        String s = String.format("%02d", add_count);
        month_txt.setText(s);
        return s;
    }
    private String month_total(String s){
        a=0;
        for (int i = 0; i < mArrayList.size(); i++) {
            String split_date = "-"+s+"-";
            if(mArrayList.get(i).date.contains(split_date)) {
                String strpoint = mArrayList.get(i).account_ac;
                a += Integer.parseInt(strpoint);
            }
        }
        String formattedStringPrice = myFormatter.format(a);
        txt2.setText(formattedStringPrice);
        return Integer.toString(a);
    }
    public void Month_checking(){
        String month_ch = "-" + s + "-";
        mArrayList = (ArrayList<Account_Item>) mydb.getTop(s);
        mAdapter = new Account_Adapter(getContext(), mArrayList);
        myListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    public void onResume(){
        super.onResume();
        emotion_check();
    }
    public  void emotion_check(){
        int goal = Integer.parseInt(txt1.getText().toString());
        int month = a;
        //int month = Integer.parseInt(txt2.getText().toString());
        if(goal == 0) {
            emotion_img.setImageResource(R.drawable.basic);
            emotion_txt.setText("목표 한도를 정해주세요!");
            emotion.setText("???");
        }else if(goal-month >= 300000){
            emotion_img.setImageResource(R.drawable.good);
            emotion_txt.setText("효율적인 소비생활을\n유지하고 계시는군요!");
            emotion.setText("GOOD");
        }else if(goal-month < 300000 && goal-month > 0){
            emotion_img.setImageResource(R.drawable.soso);
            emotion_txt.setText("조금 절약하실 필요가\n있으신 것 같아요.");
            emotion.setText("SOSO");
        }else{
            emotion_img.setImageResource(R.drawable.bad);
            emotion_txt.setText("한도를 초과했습니다.");
            emotion.setText("BAD");
        }
        //add_count = Integer.parseInt(getMonth());
    }
    // 설정값을 저장하는 함수
    private void save() {
        SharedPreferences.Editor editor = appData.edit();
        editor.putBoolean("SAVE_LOGIN_DATA", true);
        editor.putString("ID", txt1.getText().toString().trim());
        editor.apply();
    }

    // 설정값을 불러오는 함수
    private void load() {
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = appData.getString("ID", "");
    }
}
