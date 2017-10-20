package com.example.administrator.seeimage_big;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.administrator.seeimage_big.MyView.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ListAdapter adapter;
    private CircleImageView imageView;
    private TextView tv;
    public final static int SMALL_CAPTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);
        imageView=(CircleImageView) findViewById(R.id.iv);
        tv.setOnClickListener(this);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        showDialog();
    }
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.activity_my_dialog, null);//获取自定义布局
        builder.setView(layout);
        builder.setTitle("qqqqqqq");//设置标题内容
        Button see_big_image=(Button)layout.findViewById(R.id.see_big_image);
        Button camara=(Button)layout.findViewById(R.id.canara);
        final AlertDialog dlg = builder.create();
        see_big_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new BossZoomHelper(MainActivity.this,imageView,300);
                dlg.dismiss();

            }
        });
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, SMALL_CAPTURE);
                    dlg.dismiss();
                }
            }
        });

        dlg.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == SMALL_CAPTURE) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
