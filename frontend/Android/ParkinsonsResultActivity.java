package com.pinnaclesoftwaresolutions.ews;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class ParkinsonsResultActivity extends AppCompatActivity {

    TextView tvFetchResults;
    Retrofit retrofit;

    private Button connect;
    private static final MediaType MEDIA_TYPE = MediaType.parse("audio/*");
    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkinsons_result);

        tvFetchResults = findViewById(R.id.tvFetchResultsParkinsons);


        String BASE_URL = "http://127.0.0.1:5000/";

        retrofit = NetworkClient.getRetrofit(BASE_URL);


        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        filePath += "/parkinsonRecording.3gp";

        File fileParse = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("audio/*"), filePath);

        MultipartBody.Part part = MultipartBody.Part.createFormData("file", fileParse.getName(), requestBody);

        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("file"), "file");

        UploadAPI uploadAPI = retrofit.create(UploadAPI.class);
        Call call = uploadAPI.uploadFile(part, fileRequestBody);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                tvFetchResults.setText("success");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });




    }





    private String getMimeType(String path) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(path);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);

    }



}




