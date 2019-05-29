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

import tdc.edu.vn.exesum.FragmentTitleImage;
import tdc.edu.vn.exesum.R;

public class FragmentLyrics extends Fragment {
    View view;
    ImageButton ibBack;
    int imgT;
    TextView tvLyrics;
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
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lyrics,container,false);
        tvLyrics = view.findViewById(R.id.tvLyrics);
        tvLyrics.setText(this.lyrics);
        ibBack = view.findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment myFragment;
                myFragment = new FragmentTitleImage();
                ((FragmentTitleImage) myFragment).setTitle(title);
                ((FragmentTitleImage) myFragment).setImgT(imgT);
                ((FragmentTitleImage) myFragment).setLyrics(lyrics);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentswicth,myFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
