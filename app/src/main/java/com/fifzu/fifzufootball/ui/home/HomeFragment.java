package com.fifzu.fifzufootball.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.fifzu.fifzufootball.MainActivity;
import com.fifzu.fifzufootball.R;

import java.util.List;

public class HomeFragment extends Fragment {

    FragmentActivity listener;

    private HomeViewModel homeViewModel;
    private List<String> spielplanList;
    WebView webView1;
    int page;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
      //  final TextView textView = root.findViewById(R.id.text_home);

        spielplanList = ((MainActivity)getActivity()).getSpielplanList();

    //    String[] spielplaene = {((MainActivity)getActivity()).spielplan1.replace(" ","+"),((MainActivity)getActivity()).spielplan2.replace(" ","+"),((MainActivity)getActivity()).spielplan3.replace(" ","+"),((MainActivity)getActivity()).spielplan4.replace(" ","+"),((MainActivity)getActivity()).spielplan5.replace(" ","+")};
        page=0;
        webView1 = (WebView) root.findViewById(R.id.web_view1);
        TextView textview_page = root.findViewById(R.id.textview_page);

        if (savedInstanceState != null) {
            webView1.restoreState(savedInstanceState);
        } else {
            webView1.getSettings().setJavaScriptEnabled(true);
            webView1.getSettings().setUseWideViewPort(true);
            webView1.getSettings().setLoadWithOverviewMode(true);
            webView1.getSettings().setSupportZoom(true);
            webView1.getSettings().setSupportMultipleWindows(true);
            webView1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
         //   webView1.setBackgroundColor(Color.WHITE);

            webView1.setWebChromeClient(new WebChromeClient() {

            });
        }

        webView1.setWebViewClient(new MyWebViewClient());
        webView1.loadUrl("https://www.google.com/search?q=spielplan+"+ getSpielplanReplaced(0));

        final Button left = root.findViewById(R.id.button_left);
        left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                page -= 1;
                if(page==-1){
                    page = spielplanList.size() - 1;
                }
                webView1.loadUrl("https://www.google.com/search?q=spielplan+"+getSpielplanReplaced(page));
                textview_page.setText((page + 1) + " von "+ spielplanList.size());
            }
        });

        final Button right =  root.findViewById(R.id.button_right);
        right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                page += 1;
                if(page==spielplanList.size()){
                    page=0;
                }
                webView1.loadUrl("https://www.google.com/search?q=spielplan+"+getSpielplanReplaced(page));
                textview_page.setText((page + 1) + " von "+ spielplanList.size());
            }
        });

        return root;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    private String getSpielplanReplaced(int index){
        String spielplanReplaced = spielplanList.get(index).replace(" ","+");
        return spielplanReplaced;
    }

}