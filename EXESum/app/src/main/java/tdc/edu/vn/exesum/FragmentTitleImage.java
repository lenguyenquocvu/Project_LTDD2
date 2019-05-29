package tdc.edu.vn.exesum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import tdc.edu.vn.exesum.R;

public class FragmentTitleImage extends Fragment {
    View view;
    ImageButton ibBack;
    TextView tvTitle;
    ImageView imgTitle;
    int imgT;
    String lyrics,title;

    public void setImgT(int imgT) {
        this.imgT = imgT;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setLyrics(String lyrics)
    {
        this.lyrics = lyrics;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_titleimage,container,false);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        imgTitle = view.findViewById(R.id.imgTitle);
        imgTitle.setImageResource(this.imgT);
        ibBack = view.findViewById(R.id.ibBack1);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment myFragment;
                myFragment = new FragmentLyrics();
                ((FragmentLyrics) myFragment).setTitle(title);
                ((FragmentLyrics) myFragment).setImgT(imgT);
                ((FragmentLyrics) myFragment).setLyrics(lyrics);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentswicth,myFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
