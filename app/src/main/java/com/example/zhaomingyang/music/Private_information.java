package com.example.zhaomingyang.music;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhaomingyang.music.bean.Cat;
import com.example.zhaomingyang.music.network.ICatService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Private_information extends AppCompatActivity {
    ImageView linkImage;
    ImageView lifeImage;
    ImageView knowImage;
    TextView linkText;
    TextView lifeText;
    TextView knowText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_information);

        linkImage = (ImageView)findViewById(R.id.image_music);
        lifeImage = (ImageView)findViewById(R.id.image_video);
        knowImage = (ImageView)findViewById(R.id.image_private_information);
        linkText = (TextView)findViewById(R.id.text_music);
        lifeText = (TextView)findViewById(R.id.text_video);
        knowText = (TextView)findViewById(R.id.text_private_information);
        linkImage.setImageResource(R.mipmap.music2);
        lifeImage.setImageResource(R.mipmap.image2);
        knowImage.setImageResource(R.mipmap.private1);
        linkText.setTextColor(getResources().getColor(R.color.colorText));
        lifeText.setTextColor(getResources().getColor(R.color.colorText));
        knowText.setTextColor(getResources().getColor(R.color.blue));
        linkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Private_information.this,MainActivity.class);
                startActivity(intent);
            }
        });
        lifeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Private_information.this,VideoPage.class);
                startActivity(intent);
            }
        });
    }

    //menu加载
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //获取MenuInflater
        MenuInflater menuInflater = getMenuInflater();
        //加载Menu资源
        menuInflater.inflate(R.menu.menu_right, menu);
        return true;
    }

    //listening the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.video:
                break;
            case R.id.music:
                break;
            case R.id.setting:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
//}
