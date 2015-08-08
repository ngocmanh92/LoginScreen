package com.example.myapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.myapplication.db.MySqliteHelper;
import com.example.myapplication.dialog.UpdateDialog;

import java.util.Random;

public class DatabaseActivity extends ActionBarActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, UpdateDialog.Callback,
        AdapterView.OnItemLongClickListener

{

    SQLiteDatabase db;
    UpdateDialog updateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        MySqliteHelper helper = new MySqliteHelper(this);
        db = helper.getWritableDatabase();
        findViewById(R.id.button_add_product).setOnClickListener(this);
        findViewById(R.id.button_refresh).setOnClickListener(this);
        //Khoi tao dialog
        updateDialog = new UpdateDialog(this);
        updateDialog.setCallback(this);
        initListView();

    }

    //Callback after click on update button of update dialog
    @Override
    public void onUpdate(int id, String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
//        db.update("Product",values,"_id = "+id, null);
        db.update("Product",
                values,
                "_id = ?", new String[]{"" + id});
        refresh();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        cursor.moveToPosition(position);
        int productId = cursor.getInt(cursor.getColumnIndex("_id"));
        showConfirmDeleteDialog(productId);
        return false;
    }

    private void showConfirmDeleteDialog(final int id) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Do you want to delete this item?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        deleteItem(id);
                    }
                }).create();
        dialog.show();
    }

    private void deleteItem(int id){
        int effected = db.delete("Product","_id = " + id,null);
        Toast.makeText(this,"Deleted: " + effected + "with id="+id, Toast.LENGTH_LONG).show();
        refresh();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        cursor.moveToPosition(position);
        int culumnNameIndex = cursor.getColumnIndex("name");
        String name = cursor.getString(culumnNameIndex);

        int columnIdIndex = cursor.getColumnIndex("_id");
        int productId = cursor.getInt(columnIdIndex);

        updateDialog.show(productId, name);
    }

    public void insertProduct(String name) {
        ContentValues values = new ContentValues();
        values.put("id", new Random().nextInt(100000));
        values.put("name", name);
        long row = db.insert("Product", null, values);
        if (row >= 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_add_product) {
            insertProduct("Name " + System.currentTimeMillis());
        } else if (view.getId() == R.id.button_refresh) {
            refresh();
        }
    }

    public void refresh() {
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        initListView();
    }

    Cursor cursor;

    public void initListView() {
        ListView listView = (ListView) findViewById(R.id.listview);
        cursor = db.query("Product",
                new String[]{"_id", "name"},
                null,
                null,
                null,
                null,
                null
        );

        CursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{"name"},
                new int[]{android.R.id.text1}

        );

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }


}
