package com.example.myapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by sondt on 07/08/2015.
 */
public class SdcardActivity extends SharedPreferenceActivity {

    private static final String FILE_PATH = "/sdcard/data/mydata.txt";

    @Override
    protected void saveData() {
        File file = new File(FILE_PATH);
        FileWriter writer = null;
        try {
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }

            writer = new FileWriter(file);
            writer.write(editText.getText().toString());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer == null) return;

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        String value = "";
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader streamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader reader = new BufferedReader(streamReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
                value += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        textViewValue.setText(value);

    }
}
