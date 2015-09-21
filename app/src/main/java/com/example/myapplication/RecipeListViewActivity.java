package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.adapter.SimpleAdapter;

/**
 * Created by sondt on 21/09/2015.
 */
public class RecipeListViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_view);
        ListView listView = (ListView) findViewById(R.id.listview);
        CoursesItem coursesItem = new CoursesItem();
        coursesItem.Ingredient_name = new String[]{"Ingredient 1", "Ingredient 2"};
        coursesItem.Unit_name = new String[]{"Unit 1", "Unit 2"};
        coursesItem.amount = new float[]{10, 20};
        listView.setAdapter(new IngredientsAdapter(coursesItem));

    }

    public static class CoursesItem {

        String Recipe_name;
        String[] Ingredient_name;
        float[] amount;

        String[] Unit_name;
    }

    public static class IngredientsAdapter extends BaseAdapter {
        CoursesItem coursesItem;

        public IngredientsAdapter(CoursesItem coursesItem) {
            this.coursesItem = coursesItem;
        }

        @Override
        public int getCount() {
            return coursesItem.Ingredient_name.length;
        }

        @Override
        public Object getItem(int position) {
            return coursesItem.Ingredient_name[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            IngredientHolder holder;

            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_ingredient, null);
                holder = new IngredientHolder(view);
                view.setTag(holder);
            } else {
                holder = (IngredientHolder) view.getTag();
            }
            holder.name.setText(coursesItem.Ingredient_name[position]);
            holder.unit.setText(coursesItem.Unit_name[position]);
            holder.quantity.setText("" + coursesItem.amount[position]);
            return view;
        }
    }

    public static class IngredientHolder {
        TextView name;
        TextView quantity;
        TextView unit;
        View itemView;

        public IngredientHolder(View itemView) {
            this.itemView = itemView;
            name = (TextView) itemView.findViewById(R.id.name);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
            unit = (TextView) itemView.findViewById(R.id.unit_name);

        }
    }
}
