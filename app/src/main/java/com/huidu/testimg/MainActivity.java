package com.huidu.testimg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huidu.testimg.proxy.loadimg.LoadImg;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String[] imgs = {"http://img1.imgtn.bdimg.com/it/u=1848544421,3231659694&fm=11&gp=0.jpg", "http://img3.imgtn.bdimg.com/it/u=3643041233,96092050&fm=11&gp=0.jpg", "http://pic24.photophoto.cn/20120814/0005018348123206_b.jpg", "http://img2.imgtn.bdimg.com/it/u=219237983,4291885357&fm=27&gp=0.jpg", "http://img4.imgtn.bdimg.com/it/u=3329268744,4236934808&fm=27&gp=0.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycle);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new RAdapter());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Bitmap bitmap = getImageBitmap(imgs[0]);
//                Log.i("M-TAG", bitmap.getWidth() + " " + bitmap.getHeight());
//            }
//        }).start();
//        int[] i = getImageWidthHeight(imgs[0]);
//        Log.i("M", "test: " + i[0] + " " + i[1]);
        //manager.scrollToPositionWithOffset(0, 0);

    }

    public static int[] getImageWidthHeight(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        /**
         *options.outHeight为原始图片的高
         */
        return new int[]{options.outWidth, options.outHeight};
    }

    class RAdapter extends RecyclerView.Adapter<BaseHolder> {

        @Override
        public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                return new Holder0(View.inflate(MainActivity.this, R.layout.item, null));

            }
            return new Holder1(View.inflate(MainActivity.this, R.layout.item_another, null));

        }

        @Override
        public void onBindViewHolder(BaseHolder holder, int position) {
            holder.bindData(position);
        }

        @Override
        public int getItemCount() {
            return imgs.length + 10;
        }

        @Override
        public int getItemViewType(int position) {
            if (position < imgs.length)
                return 0;
            return 1;
        }
    }

    class BaseHolder extends RecyclerView.ViewHolder {

        public BaseHolder(View itemView) {
            super(itemView);
        }

        public void bindData(int p) {
        }
    }

    class Holder0 extends BaseHolder {
        public int layout = R.layout.item;
        ImageView imageView;

        public Holder0(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item);
        }

        public void bindData(int p) {
            LoadImg.getInstance().load(MainActivity.this, imgs[p], imageView);
        }
    }

    class Holder1 extends BaseHolder {

        public Holder1(View itemView) {
            super(itemView);
        }
    }

//    public Bitmap getImageBitmap(String url) {
//        URL imgUrl = null;
//        Bitmap bitmap = null;
//        try {
//            imgUrl = new URL(url);
//            HttpURLConnection conn = (HttpURLConnection) imgUrl
//                    .openConnection();
//            conn.setDoInput(true);
//            conn.connect();
//            InputStream is = conn.getInputStream();
//            bitmap = BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//    }
}