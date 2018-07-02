package com.example.jonathan.testfileio;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.d(TAG, "onCreate");

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    testFileIO("testFile1.txt", "testFile2.txt", "testFileIO");

    Log.v(TAG, "onCreate: end");
  }

  public void testFileIO(final String file1Name, final String file2Name, final String fileContent) {
    Log.d(TAG, "testFileIO: fil1Name=[" + file1Name + "], file2Name=[" + file2Name + "], fileContent=[" + fileContent + "]");

    try {
      // This line is required for AOSP:
      //JZ Environment.setUserRequired(false);

      File parentDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
      if (!parentDir.exists()) {
        boolean dirCreated = parentDir.mkdirs();
        Log.w(TAG, "testFileIO: parentDir=[" + parentDir.getPath() + "] did not exist. dirCreate=[" + dirCreated + "]");
      }

      File file1 = new File(parentDir, file1Name);
      if (file1.exists()) {
        boolean file1Deleted = file1.delete();
        Log.w(TAG, "testFileIO: file1=[" + file1.getPath() + "] existed. file1Deleted=[" + file1Deleted + "]");
      }

      File file2 = new File(parentDir, file2Name);
      if (file2.exists()) {
        boolean file2Deleted = file2.delete();
        Log.w(TAG, "testFileIO: file2=[" + file2.getPath() + "] existed. file2Deleted=[" + file2Deleted + "]");
      }

      // Create file1:

      FileOutputStream fos1 = new FileOutputStream(file1, false);

      byte[] contentBytes = fileContent.getBytes();
      fos1.write(contentBytes);
      fos1.flush();
      fos1.close();

      // Copy file1 to file2:

      FileInputStream fis1 = new FileInputStream(file1);
      FileOutputStream fos2 = new FileOutputStream(file2, false);

      int len;
      byte[] buffer = new byte[1024];

      while ((len = fis1.read(buffer)) != -1) {
        fos2.write(buffer, 0, len);
      }

      fos2.flush();
      fos2.close();
      fis1.close();

      // Check results:

      Log.v(TAG, "testFileIO: file1.path=[" + file1.getPath() + "]");
      Log.v(TAG, "testFileIO: file1.length=[" + file1.length() + "]");
      Log.v(TAG, "testFileIO: file2.path=[" + file2.getPath() + "]");
      Log.v(TAG, "testFileIO: file2.length=[" + file2.length() + "]");
    } catch (IOException e){
      e.printStackTrace();
    }

    Log.v(TAG, "testFileIO: end");
  }
}