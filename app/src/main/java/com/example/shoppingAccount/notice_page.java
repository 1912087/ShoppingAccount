package com.example.shoppingAccount;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class notice_page extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.notice_activity, null);
        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.idListView);
        elv.setAdapter(new SavedTabsListAdapter());
        return v;
    }

    public class SavedTabsListAdapter extends BaseExpandableListAdapter {

        private String[] groups = { "공지사항입니다", "이용해주셔서 감사합니다"};

        private String[][] children = {
                { " 공지사항란입니다.\n 문의사항은 shopping@account.com 으로 주시기 바랍니다. \n Tel : 02-1111-1111" },
                { " 쇼핑가계를 이용해주시는 모든 여러분들께 진심으로 감사드립니다." }
        };

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return children[i].length;
        }

        @Override
        public Object getGroup(int i) {
            return groups[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            return children[i][i1];
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            ImageView imgview = new ImageView(notice_page.this.getActivity());
            TextView textView = new TextView(notice_page.this.getActivity());
            textView.setText(getGroup(i).toString());
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextSize(23);
            textView.setPadding(90,15,15,15);
            return textView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(notice_page.this.getActivity());
            textView.setText(getChild(i, i1).toString());
            textView.setPadding(30,15,15,15);
            return textView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }

    }

}