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

public class VideoPage extends AppCompatActivity{
    public Button mBtn;
    public RecyclerView mRv;
    private List<Cat> mCats = new ArrayList<>();
    ImageView linkImage;
    ImageView lifeImage ;
    ImageView knowImage ;
    TextView linkText ;
    TextView lifeText ;
    TextView knowText ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //底部导航栏//
        linkImage = (ImageView)findViewById(R.id.image_music);
        lifeImage = (ImageView)findViewById(R.id.image_video);
        knowImage = (ImageView)findViewById(R.id.image_private_information);
        linkText = (TextView)findViewById(R.id.text_music);
        lifeText = (TextView)findViewById(R.id.text_video);
        knowText = (TextView)findViewById(R.id.text_private_information);
        linkImage.setImageResource(R.mipmap.music2);
        lifeImage.setImageResource(R.mipmap.image1);
        knowImage.setImageResource(R.mipmap.private2);
        linkText.setTextColor(getResources().getColor(R.color.colorText));
        lifeText.setTextColor(getResources().getColor(R.color.blue));
        knowText.setTextColor(getResources().getColor(R.color.colorText));
        linkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
        knowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoPage.this,Private_information.class);
                startActivity(intent);
            }
        });
        ///初始化
        Retrofit catRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        catRetrofit.create(ICatService.class).randomcat(5).enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                restoreBtn();
                loadPics(response.body());
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                restoreBtn();
            }
        });
        ///
        //刷新图片//
        mBtn = findViewById(R.id.btn);
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(new RecyclerView.Adapter() {
            @NonNull @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                ImageView imageView = new ImageView(viewGroup.getContext());
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setAdjustViewBounds(true);
                return new VideoPage.MyViewHolder(imageView);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                ImageView iv = (ImageView) viewHolder.itemView;

                String url = mCats.get(i).getUrl();
                Glide.with(iv.getContext()).load(url).into(iv);
            }

            @Override public int getItemCount() {
                return mCats.size();
            }
        });
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
            case R.id.video:
                Retrofit catRetrofit = new Retrofit.Builder()
                        .baseUrl("https://api.thecatapi.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                catRetrofit.create(ICatService.class).randomcat(5).enqueue(new Callback<List<Cat>>() {
                    @Override
                    public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                        restoreBtn();
                        loadPics(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Cat>> call, Throwable t) {
                        restoreBtn();
                    }
                });
                break;
            case R.id.music:break;
            case R.id.setting:break;
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

    public void requestData(View view) {
        mBtn.setText(R.string.image);
        mBtn.setEnabled(false);

        Retrofit catRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        catRetrofit.create(ICatService.class).randomcat(5).enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                restoreBtn();
                loadPics(response.body());
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                restoreBtn();
            }
        });

    }

    private void loadPics(List<Cat> cats) {
        mCats = cats;
        mRv.getAdapter().notifyDataSetChanged();
    }

    private void restoreBtn() {
        mBtn.setText(R.string.video_title);
        mBtn.setEnabled(true);
    }
}
//public class VideoPage extends Fragment {
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view=inflater.inflate(R.layout.activity_video,container,false);
//        return view;
//    }
//
//}
