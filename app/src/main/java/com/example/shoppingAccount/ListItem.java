
package com.example.shoppingAccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

class ListItem extends LinearLayout {
    ImageView img1;
    TextView textView;
    TextView textView2;
    TextView textView3;

   public ListItem(Context context) {
       super(context);
       init(context);

   }

   private void init(Context context){
       LayoutInflater myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       myInflater.inflate(R.layout.list_item, this , true);

       img1 = (ImageView) findViewById(R.id.shopping_image);
       textView = (TextView) findViewById(R.id.shopping_name);
       textView2 = (TextView) findViewById(R.id.shopping_account);
       textView3 = (TextView) findViewById(R.id.shopping_search);
   }

   public void setFirstItem(String item) {
       textView.setText(item);
   }

   public void setSecondItem(String item){
       textView2.setText(item); }

   public void setfiveItem(Integer item){
        img1.setImageResource(item);
    }

    public void setSixItem(String item) {
        textView3.setText(item);
    }
}

