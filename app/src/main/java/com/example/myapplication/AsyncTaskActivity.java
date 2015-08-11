package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.model.BloggerPost;
import com.example.myapplication.model.BloggerPostList;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AsyncTaskActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        findViewById(R.id.button_load).setOnClickListener(this);
    }

    private static final String URL = "https://www.googleapis.com/blogger/v3/blogs/3213900/posts?key=AIzaSyBEnRhFNK_Q6ZFdSdFEKQgIKvjx4lJ3LIE";

    @Override
    public void onClick(View view) {
        MyAsyncTask task = new MyAsyncTask(this);
        task.execute(URL,URL,URL,"text1","text2");
    }

    private static class MyAsyncTask extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog;
        AsyncTaskActivity context;

        public MyAsyncTask(AsyncTaskActivity context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            for (int i = 0; i < strings.length; i++) {
                String param = strings[i];
                Log.i("MyAsyncTask.doInBackground", "-----> param " + i + ": " + param);
            }
            HttpClient client = new DefaultHttpClient();
            String url = strings[0];
            HttpGet httpGet = new HttpGet(url);

            String data = "";
            try {
                HttpResponse response = client.execute(httpGet);
                InputStream stream = response.getEntity().getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line = null;
                while ((line = reader.readLine()) != null){
                    data += line;
                }

                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }


        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String data) {
            progressDialog.dismiss();
            TextView textView = (TextView) context.findViewById(R.id.textview);
            textView.setText(data);

            Gson gson = new Gson();
            BloggerPostList bloggerPostList = gson.fromJson(data, BloggerPostList.class);
            Log.i("MyAsyncTask.onPostExecute","-----> " + bloggerPostList);
            for (int i = 0; i < bloggerPostList.items.size(); i++) {
                BloggerPost post = bloggerPostList.items.get(i);
                Log.i("MyAsyncTask.onPostExecute","-----> post: " + post.title);
            }
        }






    }
}
