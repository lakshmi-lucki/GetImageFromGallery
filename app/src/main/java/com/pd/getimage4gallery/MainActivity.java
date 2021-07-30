package com.pd.getimage4gallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private ImageView imageView;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn = (Button) findViewById( R.id.btn );
        imageView = (ImageView) findViewById( R.id.imgview );

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType( "image/*" );
                intent.setAction( Intent.ACTION_GET_CONTENT );
                startActivityForResult( Intent.createChooser( intent, "SELECT PICTURE" ), PICK_IMAGE_REQUEST );

            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            Log.i( "URI", uri.toString() );
            String pathaddress = data.getData().getPath();  //img name
            try {
                // custom content resolver------
                Bitmap bitmap = MediaStore.Images.Media.getBitmap( getContentResolver(), data.getData() );
                imageView.setImageBitmap( bitmap );
                Log.i( "PATH :", "PATH is:" + pathaddress );


                // though error still execiute with some exceptions/condtions--so catch
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

