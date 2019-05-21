package com.example.zhaomingyang.music;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhaomingyang.music.bean.Cat;
import com.example.zhaomingyang.music.bean.music_bean;
import com.example.zhaomingyang.music.network.ICatService;
import com.example.zhaomingyang.music.search.*;
import com.example.zhaomingyang.music.utils.music_utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity{

    ImageView linkImage;
    ImageView lifeImage ;
    ImageView knowImage ;
    TextView linkText ;
    TextView lifeText ;
    TextView knowText ;
    MediaPlayer mediaPlayer;
    private static final String TAG = VideoPage.class.getSimpleName();
//    public Button mBtn;
//    public RecyclerView mRv;
//    private List<Cat> mCats = new ArrayList<>();
    //
    private ListView mListView;
    private List<music_bean> list;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////音乐初始化
        mediaPlayer = new MediaPlayer();

        //页面跳转
        linkImage = (ImageView)findViewById(R.id.image_music);
        lifeImage = (ImageView)findViewById(R.id.image_video);
        knowImage = (ImageView)findViewById(R.id.image_private_information);
        linkText = (TextView)findViewById(R.id.text_music);
        lifeText = (TextView)findViewById(R.id.text_video);
        knowText = (TextView)findViewById(R.id.text_private_information);
        linkImage.setImageResource(R.mipmap.music1);
        lifeImage.setImageResource(R.mipmap.image2);
        knowImage.setImageResource(R.mipmap.private2);
        linkText.setTextColor(getResources().getColor(R.color.blue));
        lifeText.setTextColor(getResources().getColor(R.color.colorText));
        knowText.setTextColor(getResources().getColor(R.color.colorText));
        lifeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VideoPage.class);
                startActivity(intent);
            }
        });
        knowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Private_information.class);
                startActivity(intent);
            }
        });
        initView();
        //        给ListView添加点击事件，实现点击哪首音乐就进行播放
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String p = list.get(i).path;//获得歌曲的地址
                play(p);
            }
        });
        //listen the bottomNavigationView1
//        LinearLayout linkll = (LinearLayout)findViewById(R.id.linkManTouchId);
//        linkll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                linkImage.setImageResource(R.mipmap.music1);
//                lifeImage.setImageResource(R.mipmap.image2);
//                knowImage.setImageResource(R.mipmap.private2);
//                linkText.setTextColor(getResources().getColor(R.color.blue));
//                lifeText.setTextColor(getResources().getColor(R.color.colorText));
//                knowText.setTextColor(getResources().getColor(R.color.colorText));
//                Mainlayout mainlayout1=new Mainlayout();
//                FragmentManager fm=getFragmentManager();
//                FragmentTransaction tx=fm.beginTransaction();
//                tx.replace(R.id.fragment_content,mainlayout1,"music");
//                tx.commit();
// //               initView();
//            }
//        });
        //listen the bottomNavigationView2
//        LinearLayout lifell = (LinearLayout)findViewById(R.id.lifeTouchId);
//        lifell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,VideoPage.class);
//                startActivity(intent);
//            }
//        });
        //listen the bottomNavigationView3
//        LinearLayout knowll = (LinearLayout)findViewById(R.id.knowledgeTouchId);
//        knowll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                linkImage.setImageResource(R.mipmap.music2);
//                lifeImage.setImageResource(R.mipmap.image2);
//                knowImage.setImageResource(R.mipmap.private1);
//                linkText.setTextColor(getResources().getColor(R.color.colorText));
//                lifeText.setTextColor(getResources().getColor(R.color.colorText));
//                knowText.setTextColor(getResources().getColor(R.color.blue));
//                //fragment
//                Private_information private_information=new Private_information();
//                FragmentManager fm=getFragmentManager();
//                FragmentTransaction tx=fm.beginTransaction();
//                tx.replace(R.id.fragment_content,private_information,"private_information");
//                tx.commit();
//            }
//        });


        //权限申请
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                //没有权限则申请权限
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else {
                //有权限直接执行,docode()不用做处理
                ;
                }
        }else {
            //小于6.0，不用申请权限，直接执行
            ;
        }
    }

    //menu加载
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //获取MenuInflater
        MenuInflater menuInflater = getMenuInflater();
        //加载Menu资源
        menuInflater.inflate(R.menu.menu_right,menu);
        return true;
    }
    //listening the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.video:break;
            case R.id.music:break;
            case R.id.setting:break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    //  权限申请回调函数
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //执行代码,这里是已经申请权限成功了,可以不用做处理
                    ;
                }else{
                    Toast.makeText(MainActivity.this,"请允许访问存储空间",Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                break;
        }
    }
    /**     * 初始化view     */
    private void initView() {
        Log.d("MainActivity","initview");
        mListView = (ListView) findViewById(R.id.main_listview);
        list = new ArrayList<>();        //把扫描到的音乐赋值给list
         list = music_utils.getMusicData(this);
         adapter = new MyAdapter(this,list);
         mListView.setAdapter(adapter);
         }
    public void play(String path) {

        try {
            //        重置音频文件，防止多次点击会报错
            mediaPlayer.reset();
//        调用方法传进播放地址
            mediaPlayer.setDataSource(path);
//            异步准备资源，防止卡顿
            mediaPlayer.prepareAsync();
//            调用音频的监听方法，音频准备完毕后响应该方法进行音乐播放
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
