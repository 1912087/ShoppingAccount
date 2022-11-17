package com.example.shoppingAccount.Popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.shoppingAccount.Fragments.SecondFragment;
import com.example.shoppingAccount.R;
// implements View.OnClickListener
public class Popup_goal extends DialogFragment{


    OnMyDialogResult mDialogResult;
    private Fragment fragment;

    View view;
    public Popup_goal() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.popup_goal, container, false);

        Button ok_btn = view.findViewById(R.id.bt_okay_goal);
        final Button cancle_btn = view.findViewById(R.id.bt_cancle_goal);
        final EditText goal = view.findViewById(R.id.goal_account);
        final String goal_name = goal.getText().toString().trim();
        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment != null) {
                    if (goal.getText().toString().trim().length() != 0 ) {
                        Toast.makeText(getContext(), "확인", Toast.LENGTH_SHORT).show();
                        //Bundle args = new Bundle();
                        //args.putString("MONTH", goal_name);
                        SecondFragment frg = new SecondFragment();
                        String result = goal.getText().toString().trim();
                        //mDialogResult.finish(result);
                        //frg.setArguments(args); // 데이터 전달
                        mDialogResult.finish(result);
                        dismissDialog();
                    }else{
                        Toast.makeText(getContext(), "금액을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        cancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 아래 코드는 버튼 이벤트 안에 넣어야겠죠?
                if (fragment != null) {
                    dismissDialog();
                }
            }
        });

        return view;
    }
   /* @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_okay_goal:
                if (fragment != null) {
                    if( mDialogResult != null ){
                        if (goal.getText().toString().trim().length() != 0 ) {
                            Toast.makeText(getContext(), "확인", Toast.LENGTH_SHORT).show();
                            String result = goal.getText().toString().trim();
                            mDialogResult.finish(result);
                            dismissDialog();
                        }else{
                            Toast.makeText(getContext(), "금액을 입력해주세요", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.bt_cancle_goal:
                if(fragment != null){
                    dismissDialog();
                }
        }
    }*/

    public void setDialogResult(OnMyDialogResult dialogResult){
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult{
        void finish(String result);
    }

    private void dismissDialog() {
        this.dismiss();    }
}