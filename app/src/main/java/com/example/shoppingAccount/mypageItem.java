package com.example.shoppingAccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class mypageItem extends LinearLayout {
    ImageView noticeImg1;
    TextView noticeText;

    public mypageItem(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context){
        LayoutInflater myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myInflater.inflate(R.layout.page3_item, this , true);

        noticeImg1 = (ImageView) findViewById(R.id.notice);
        noticeText = (TextView) findViewById(R.id.mypage_text);
    }

    public void iconItem(Integer item) {
        noticeImg1.setImageResource(item);
    }

    public void mypageItem(String item){ noticeText.setText(item); }

}
