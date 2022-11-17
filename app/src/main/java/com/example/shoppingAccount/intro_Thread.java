package com.example.shoppingAccount;

import android.os.Handler;
import android.os.Message;

public class intro_Thread extends Thread {

    private Handler handler;

    public intro_Thread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {

        Message msg = new Message();

        try {
            Thread.sleep(3000);
            msg.what = 1;
            handler.sendEmptyMessage(msg.what);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}