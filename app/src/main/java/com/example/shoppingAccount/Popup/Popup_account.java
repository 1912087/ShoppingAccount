package com.example.shoppingAccount.Popup;/*package com.example.shoppingAccount.Popup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.shoppingAccount.Fragments.SecondFragment;
import com.example.shoppingAccount.R;

import java.io.ByteArrayOutputStream;

public class Popup_account extends DialogFragment{

    private NumberPicker num;
    private Fragment fragment;
    private Button btn_ok, btn_cancle;
    OnMyDialogResult mDialogResult;
    private SecondFragment second;
    public static Popup_account getInstance() {
        Popup_account e = new Popup_account();
        return e;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_account, container, false);

        num = view.findViewById(R.id.number_month);
        num.setMinValue(1);
        num.setMaxValue(12);
        Bundle args = getArguments();
        String value = args.getString("key");
        setCancelable(false);

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        btn_ok = view.findViewById(R.id.bt_okay);
        btn_cancle = view.findViewById(R.id.bt_canclee);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 아래 코드는 버튼 이벤트 안에 넣어야겠죠?
                if (fragment != null) {
                    int a = num.getValue();
                        if( mDialogResult != null ){
                            a = num.getValue();
                            String b = Integer.toString(a);
                            //mDialogResult.finish(Integer.toString(a));
                            mDialogResult.finish(b);
                        }
                    Popup_account dialogFragment = (Popup_account) fragment;
                    dialogFragment.dismiss();
                }
            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 아래 코드는 버튼 이벤트 안에 넣어야겠죠?
                    if (fragment != null) {
                        DialogFragment dialogFragment = (DialogFragment) fragment;
                        dialogFragment.dismiss();
                    }
                }
        });
        return view;
    }
    public void setDialogResult(OnMyDialogResult dialogResult){
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult{
        void finish(String result);
    }
}
*/