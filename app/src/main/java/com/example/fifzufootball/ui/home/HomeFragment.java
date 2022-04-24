package com.example.fifzufootball.ui.home;

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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fifzufootball.MainActivity;
import com.example.fifzufootball.R;

public class HomeFragment extends Fragment {

    FragmentActivity listener;

    private HomeViewModel homeViewModel;
    WebView webView1;
    WebView webView2;
    WebView webView3;
    int page;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
      //  final TextView textView = root.findViewById(R.id.text_home);

        String[] spielplaene = {((MainActivity)getActivity()).spielplan1.replace(" ","+"),((MainActivity)getActivity()).spielplan2.replace(" ","+"),((MainActivity)getActivity()).spielplan3.replace(" ","+"),((MainActivity)getActivity()).spielplan4.replace(" ","+")};
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
        webView1.loadUrl("https://www.google.com/search?q=spielplan+"+spielplaene[0]);


        final Button left = root.findViewById(R.id.button_left);
        left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                page -= 1;
                if(page==-1){
                    page=3;
                }
                webView1.loadUrl("https://www.google.com/search?q=spielplan+"+spielplaene[page]);
                textview_page.setText((page + 1) + " von 4");
            }
        });

        final Button right =  root.findViewById(R.id.button_right);
        right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                page += 1;
                if(page==4){
                    page=0;
                }
                webView1.loadUrl("https://www.google.com/search?q=spielplan+"+spielplaene[page]);
                textview_page.setText((page + 1) + "von 4");
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

}