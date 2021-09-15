package com.cookandroid.sdcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("심이진");
        Button read, mkdir, rmdir, list;
        final EditText edt;
        read=findViewById(R.id.read);
        edt=findViewById(R.id.edt);
        mkdir=findViewById(R.id.Mkdir);
        rmdir=findViewById(R.id.rmdir);
        list=findViewById(R.id.list);
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File mydir = new File(strSDpath+"/mydir");
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream inputStream = new FileInputStream("/storage/emulated/0/sd_test.txt");
                    byte[]txt = new byte[inputStream.available()];
                    inputStream.read(txt);
                    edt.setText(new String(txt));
                    inputStream.close();
                }catch (IOException e){}
            }
        });

        mkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydir.mkdir();
            }
        });

        rmdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydir.delete();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File[]sysFiles = (new File(strSDpath).listFiles());
                edt.setText("");
                String strFname = "파일/폴더 개수 : "+sysFiles.length;
                for(int i=0; i<sysFiles.length; i++){
                    if(sysFiles[i].isDirectory()==true)
                        strFname=strFname+"\n<폴더>"+sysFiles[i].toString();
                    if(sysFiles[i].isFile()==true)
                        strFname=strFname+"\n<파일>"+sysFiles[i].toString();
                    edt.setText(strFname);
                }
            }
        });
    }
}
