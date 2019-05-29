package tdc.edu.vn.exesum;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {
    int imgTitle;
    ImageButton ibPrev,ibNext,ibStart,ibStop;
    Animation rotateAnimation;
    TextView tvStart,tvEnd;
    String tvTitle;
    EditText txtLyrics;
    Button btnEditLyrics,btnCancel;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    String lyrics;
    ConstraintLayout conEditZone;
    ArrayList<Song> arrayList;
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_main);
        setControl();
        khoitao();
        setEvent();
    }

    private void khoitao() {
        arrayList = new ArrayList<>();
        arrayList.add(new Song("Anh chẳng sao mà",R.drawable.khangviet, R.raw.b1,"A1"));
        arrayList.add(new Song("Anh nhớ nhé",R.drawable.anhnhonhe, R.raw.b2,"A2"));
        arrayList.add(new Song("Đời là thế thôi",R.drawable.phule, R.raw.b3,"D"));
        arrayList.add(new Song("Anh có tài",R.drawable.anhcotaima, R.raw.b4,"A3"));
        arrayList.add(new Song("Ta đi tìm em",R.drawable.taditimem, R.raw.b5,"T"));
    }

    private void setMediaPlayer(){
        mediaPlayer = MediaPlayer.create(this,arrayList.get(position).getFileMp3());
        tvTitle = arrayList.get(position).getTenBai();
        imgTitle = arrayList.get(position).getHinhAnh();
        lyrics = arrayList.get(position).getLyrics();
    }

    private void setEvent() {
        rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_ani);
        setMediaPlayer();
        ibStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mediaPlayer.isPlaying()){
                    fragmentTitle(imgTitle,lyrics,tvTitle);
                    mediaPlayer.start();
                    ibStart.setBackgroundResource(R.drawable.pause_style);
                }else{
                    mediaPlayer.pause();
                    ibStart.setBackgroundResource(R.drawable.play_style);
                }
                setTimeTotal();
                updateTime();
            }
        });
        ibStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                setMediaPlayer();
                setTimeTotal();
                updateTime();
                ibStart.setBackgroundResource(R.drawable.play_style);
            }
        });
        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;

                if(position > arrayList.size() - 1) {
                    position = 0;
                }

                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }

                setMediaPlayer();
                mediaPlayer.start();
                fragmentTitle(imgTitle,lyrics,tvTitle);
                ibStart.setBackgroundResource(R.drawable.pause_style);
                setTimeTotal();
                updateTime();
            }
        });
        ibPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;

                if(position < 0) {
                    position = arrayList.size() - 1;
                }

                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }

                setMediaPlayer();
                mediaPlayer.start();
                fragmentTitle(imgTitle,lyrics,tvTitle);
                ibStart.setBackgroundResource(R.drawable.pause_style);
                setTimeTotal();
                updateTime();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        btnEditLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnEditLyrics.getText().equals("Edit lyrics")){
                    conEditZone.setVisibility(View.VISIBLE);
                    btnEditLyrics.setText("Save");
                }else
                {
                    arrayList.get(position).setLyrics(txtLyrics.getText().toString());
                    setMediaPlayer();
                    mediaPlayer.start();
                    fragmentTitle(imgTitle,lyrics,tvTitle);
                    ibStart.setBackgroundResource(R.drawable.pause_style);
                    setTimeTotal();
                    updateTime();
                    txtLyrics.setText("");
                    conEditZone.setVisibility(View.GONE);
                    btnEditLyrics.setText("Edit lyrics");
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLyrics.setText("");
                conEditZone.setVisibility(View.GONE);
                btnEditLyrics.setText("Edit lyrics");
            }
        });
    }

    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                tvStart.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()) + "");
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;

                        if(position > arrayList.size() - 1) {
                            position = 0;
                        }

                        if(mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }

                        setMediaPlayer();
                        mediaPlayer.start();
                        ibStart.setBackgroundResource(R.drawable.pause_style);
                        setTimeTotal();
                        updateTime();
                    }
                });

                handler.postDelayed(this,500);
            }
        },100);
    }

    private void setTimeTotal() {
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        tvEnd.setText(dinhDangGio.format(mediaPlayer.getDuration()) + "");
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void setControl() {
        ibNext = findViewById(R.id.ic_next);
        ibPrev = findViewById(R.id.ic_prev);
        ibStart = findViewById(R.id.ic_play);
        ibStop = findViewById(R.id.ic_stop);
        seekBar = findViewById(R.id.seekBar);
        tvStart = findViewById(R.id.tvStartTime);
        tvEnd = findViewById(R.id.tvEndTime);
        btnEditLyrics = findViewById(R.id.btnEditLyrics);
        conEditZone = findViewById(R.id.conEditZone);
        btnCancel = findViewById(R.id.btnCancel);
        txtLyrics = findViewById(R.id.txtLyrics);
    }

    private void fragmentTitle(int image, String lyrics, String title) {
        Fragment myFragment;
        myFragment = new FragmentTitleImage();
        ((FragmentTitleImage) myFragment).setTitle(title);
        ((FragmentTitleImage) myFragment).setImgT(image);
        ((FragmentTitleImage) myFragment).setLyrics(lyrics);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentswicth,myFragment);
        fragmentTransaction.commit();
    }
}
