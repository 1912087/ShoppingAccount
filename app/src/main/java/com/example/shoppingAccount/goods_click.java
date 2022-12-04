package com.example.shoppingAccount;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingAccount.Fragments.FirstFragment;
import com.example.shoppingAccount.Popup.PopupActivity;
import com.example.shoppingAccount.dao.DBHelper;
import com.example.shoppingAccount.dto.First_Item;
import com.example.shoppingAccount.orderList.order_planAdapter;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

// implements FirstFragment.onGridSetListener
public class goods_click extends AppCompatActivity{
    private String name_goods, account_goods;
    private Integer add_count, count_goods, total_account;
    private ImageView back_btn, goods_img;
    private TextView goods_name, goods_account;
    private TextView goods_amount;
    private Button add_btn, minus_btn, order_btn;
    Bitmap image, newImage;
    DBHelper mydb;
    private order_planAdapter mAdapter;
    int pid;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_click);
        back_btn = findViewById(R.id.goods_main_back_btn);
        goods_img = findViewById(R.id.goods_title_img);
        goods_amount = findViewById(R.id.goods_main_picker);
        goods_name = findViewById(R.id.goods_main_name);
        goods_account = findViewById(R.id.goods_main_account);
        add_btn = findViewById(R.id.add_btn);
        minus_btn = findViewById(R.id.minus_btn);
        order_btn = findViewById(R.id.order_click);


        Intent secondIntent = getIntent();
        pid = Integer.parseInt(secondIntent.getStringExtra("pid"));
        First_Item dto = First_Adapter.productDao.productInfo(pid);

        /*byte[] byteArray = secondIntent.getByteArrayExtra("goods_image") ;
        image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        name_goods = secondIntent.getStringExtra("goods_name");
        account_goods = secondIntent.getStringExtra("goods_account");*/

        add_count = Integer.parseInt(goods_amount.getText().toString());


        //goods_img.setImageBitmap(image);
        goods_img.setImageResource(dto.getImage_list());
        //goods_name.setText(name_goods);
        goods_name.setText(dto.getName_list());

        //count_goods = Integer.parseInt(account_goods);
        count_goods = Integer.parseInt(dto.getAccount_list());
        add_minus(add_count, count_goods);

        back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_count += 1;
                if(add_count > 20){
                    add_count = 20;
                }
                goods_amount.setText(add_count.toString());
                add_minus(add_count, count_goods);
            }
        });
        minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_count -= 1;
                if(add_count < 1){
                    add_count = 1;
                }
                goods_amount.setText(add_count.toString());
                add_minus(add_count, count_goods);
            }
        });
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(goods_click.this, PopupActivity.class);
                /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = ((BitmapDrawable)goods_img.getDrawable()).getBitmap();
                Bitmap resize = Bitmap.createScaledBitmap(bitmap, 470, 470, true);
                resize.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                intent.putExtra("goods_image_pop", byteArray);
                intent.putExtra("goods_name_pop", goods_name.getText().toString());
                intent.putExtra("goods_account_pop", total_account.toString());*/
                intent.putExtra("goods_amount_pop", add_count.toString());
                intent.putExtra("pid", pid);
                startActivityForResult(intent, 1);
            }
        });
    }
    public void add_minus(int a, int b){
        total_account = a * b;
        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formattedStringPrice = myFormatter.format(total_account);
        goods_account.setText(formattedStringPrice);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            //데이터 처리
            if (resultCode == RESULT_OK) {

                mydb = new DBHelper(this);
                Intent intent1 = getIntent();
                byte[] newImage = data.getByteArrayExtra("image") ;
                String newName = data.getStringExtra("name");
                String newAmount = data.getStringExtra("amount");
                String newAccount = data.getStringExtra("account");
                String newDate = data.getStringExtra("date");;
                //String newDate = "2021-02-03";
                //입력한 데이터를 DB에 저장하자.
                boolean isInserted = mydb.insertData(newImage, newName, newAmount, newAccount, newDate);
                //mydb.insertData(newImage, newName, newAmount, newAccount);
                if (isInserted == true) {
                    Toast.makeText(getBaseContext(), "주문 완료", Toast.LENGTH_SHORT).show();

                    //Fragment_one.adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getBaseContext(), "주문 실패", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
