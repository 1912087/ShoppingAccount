package com.example.shoppingAccount.Popup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingAccount.R;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PopupActivity extends Activity {

    private TextView or_name, or_amount, or_account;
    private ImageView or_image;
    private String name_goods_pop, account_goods_pop, amount_goods_pop;
    Bitmap img_goods_pop;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup);

        //UI 객체생성
        or_image = (ImageView) findViewById(R.id.goods_order_image);
        or_name = (TextView) findViewById(R.id.goods_order_name);
        or_amount = (TextView) findViewById(R.id.goods_order_amount);
        or_account = (TextView) findViewById(R.id.goods_order_account);

        //데이터 가져오기
        Intent popIntent = getIntent();

        name_goods_pop = popIntent.getStringExtra("goods_name_pop");
        amount_goods_pop = popIntent.getStringExtra("goods_amount_pop");
        account_goods_pop = popIntent.getStringExtra("goods_account_pop");
        byte[] byteArray = popIntent.getByteArrayExtra("goods_image_pop") ;
        img_goods_pop = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);



        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formattedStringPrice = myFormatter.format(Integer.parseInt(account_goods_pop));

        or_image.setImageBitmap(img_goods_pop);
        or_name.setText(name_goods_pop);
        or_amount.setText(amount_goods_pop);
        or_account.setText(formattedStringPrice);

        //확인버튼 이벤트
        Button button_ok = (Button) findViewById(R.id.bt_ok);
        Button button_cancle = (Button) findViewById(R.id.bt_cancle);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 전달하고 액티비티 닫기
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = ((BitmapDrawable)or_image.getDrawable()).getBitmap();
                Bitmap resize = Bitmap.createScaledBitmap(bitmap, 470, 470, true);
                resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                String name = or_name.getText().toString();
                String amount = or_amount.getText().toString();
                String account = account_goods_pop;
                //String account = account_goods_pop;
                String date = getTime();

                Intent intent = new Intent();
                intent.putExtra("image", byteArray);
                intent.putExtra("name", name);
                intent.putExtra("amount", amount);
                intent.putExtra("account", account);
                intent.putExtra("date", date);
                setResult(RESULT_OK, intent);
                //Intent intent = new Intent();
                //setResult(RESULT_OK, intent);
                finish();
            }
        });
        button_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    //바깥영역 클릭 방지와 백 버튼 차단
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}