package com.example.jonathan.testfileio;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = MainActivity.class.getSimpleName();

  private static final String FILE1_NAME = "file1.txt";
  private static final String FILE2_NAME = "file2.txt";
  private static final String CONTENT_TO_WRITE = "12345";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.d(TAG, "onCreate");

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    try {
      testFileIO();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Log.v(TAG, "onCreate: end");
  }

  public void testFileIO() throws IOException {
    Log.d(TAG, "testFileIO");

    File parentDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    if (parentDir.exists()) {
      // Do nothing
    } else {
      parentDir.mkdirs();
    }

    File file1 = new File(parentDir, FILE1_NAME);
    if (file1.exists()) {
      file1.delete();
    }

    File file2 = new File(parentDir, FILE2_NAME);
    if (file2.exists()) {
      file2.delete();
    }

    try {
      FileOutputStream fos1 = new FileOutputStream(file1, false);

      try {
        String contentToWrite = CONTENT_TO_WRITE;
        byte[] contentBytes = contentToWrite.getBytes();
        fos1.write(contentBytes);
        fos1.flush();
        fos1.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

      FileInputStream fis1 = new FileInputStream(file1);

      int content = -1;
      while ((content = fis1.read()) != -1) {
        // convert to char and display it
        Log.v(TAG, "testFileIO: content=[" + (char) content + "]");
      }

      FileOutputStream fos2 = new FileOutputStream(file2, false);

      try {
        String contentRead = String.valueOf((char)content);
        byte[] contentReadBytes = contentRead.getBytes();

        fos2.write(contentReadBytes);
        fos2.flush();
        fos2.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    Log.v(TAG, "testFileIO: end");
  }

}
