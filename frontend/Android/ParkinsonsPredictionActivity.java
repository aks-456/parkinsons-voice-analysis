package com.pinnaclesoftwaresolutions.ews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ParkinsonsPredictionActivity extends AppCompatActivity {


    //Declaration of variables
    Button btnGetResults, btnStartRecording, btnStopRecording;
    String recordFile = "";
    Boolean isRecording = false;
    private MediaRecorder mediaRecorder;

    //On Create Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkinsons_prediction);

        //btnPickRecording = findViewById(R.id.btnPickRecording);
        //Get elements from activity
        btnGetResults = findViewById(R.id.btnGetResults);
        btnStartRecording = findViewById(R.id.btnStartRecording);
        btnStopRecording = findViewById(R.id.btnStopRecording);

        btnStopRecording.setEnabled(false);

        //Media recorder definition
        mediaRecorder = new MediaRecorder();


        //start recording button
        btnStartRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkPermissions())
                {
                    startRecording();
                } else {
                    Toast.makeText(ParkinsonsPredictionActivity.this, "Error: Permissions not granted", Toast.LENGTH_SHORT).show();
                }


            }

        });

        //Stop recording button
        btnStopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
                Toast.makeText(ParkinsonsPredictionActivity.this, "Stopped recording", Toast.LENGTH_SHORT).show();
            }
        });

        //Get result from http request from Flask Server

        btnGetResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Navigate to activity to get results
                Intent resultsIntent = new Intent(ParkinsonsPredictionActivity.this, ParkinsonsResultActivity.class);
                startActivity(resultsIntent);

            }
        });


    }

    //Check if user permission for recording audio has been granted
    private boolean checkPermissions() {
        //Start Recording
        if (ActivityCompat.checkSelfPermission(ParkinsonsPredictionActivity.this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(ParkinsonsPredictionActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
            return false;
        }
    }

    //Check if user permission for accesing external storage has been granted
    private boolean checkPermissionsStorage() {
        if (ActivityCompat.checkSelfPermission(ParkinsonsPredictionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(ParkinsonsPredictionActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return false;
        }
    }

    //Start Recording audio
    private void startRecording() {
            //String recordPath = ParkinsonsPredictionActivity.this.getExternalFilesDir("/").getAbsolutePath();
        String recordPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        recordFile = "parkinsonRecording.3gp";

        btnStartRecording.setEnabled(false);
        btnStopRecording.setEnabled(true);

            //String text = "Stop";
        String outputFile = recordPath + "/" + recordFile;
        isRecording = true;

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(outputFile);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(ParkinsonsPredictionActivity.this, "Started recording", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ParkinsonsPredictionActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

    }

    //Stop recording audio
    private void stopRecording() {
        mediaRecorder.stop();
        String text = "Start";
        mediaRecorder.release();
        mediaRecorder = null;

        btnStartRecording.setEnabled(true);
        btnStopRecording.setEnabled(false);
    }




}






