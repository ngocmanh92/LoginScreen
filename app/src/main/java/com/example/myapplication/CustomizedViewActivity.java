package com.example.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.AbsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class CustomizedViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_view);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new StandingboardAdapter(getData()));
    }

    private static class Team{
        String name;
        int position;
        int point;
    }

    private static List<Team> getData(){
        List<Team> teams = new ArrayList<Team>();

        for (int i = 0; i < 32; i++) {
            Team team = new Team();
            team.point = new Random().nextInt(30);
            team.name = "Team " + (i + 1);
            teams.add(team);
        }

        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team team, Team t1) {
                return t1.point - team.point;
            }
        });

        return teams;
    }

    private static class StandingboardAdapter extends AbsAdapter<Team>{

        public StandingboardAdapter(List<Team> data) {
            super(data);
        }

        @Override
        public void onBindData(int position, View view, ViewGroup viewGroup, AbsHolder holder) {
            Team item = getItem(position);
            Holder dataHolder = (Holder) holder;
            dataHolder.name.setText(item.name);
            dataHolder.point.setText(""+item.point);
            dataHolder.position.setText("#"+position);
        }

        @Override
        public AbsHolder onCreateHolder(View itemView) {
            return new Holder(itemView);
        }

        @Override
        public int getItemLayout() {
            return R.layout.adapter_leaderboard;
        }

        private static class Holder extends AbsHolder{
            TextView name;
            TextView position;
            TextView point;

            public Holder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.textview_name);
                point = (TextView) itemView.findViewById(R.id.textview_point);
                position = (TextView) itemView.findViewById(R.id.textview_position);
            }
        }
    }

    private static class LeaderboardAdapter extends BaseAdapter{

        List<Team> teams;

        public LeaderboardAdapter(List<Team> teams) {
            this.teams = teams;
        }

        @Override
        public int getCount() {
            return teams.size();
        }

        @Override
        public Team getItem(int i) {
            return teams.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Holder holder = null;
            if(view == null){
                view = View.inflate(viewGroup.getContext(),R.layout.adapter_leaderboard, null);
                holder = new Holder(view);
                view.setTag(holder);
            }else{
                holder = (Holder) view.getTag();
            }
            Team item = getItem(i);
            holder.position.setText(""+ item.position);
            holder.name.setText(item.name);
            holder.point.setText(""+ item.point);
            return view;
        }

        private static class Holder{
            View itemView;
            TextView name;
            TextView position;
            TextView point;

            public Holder(View itemView) {
                this.itemView = itemView;
                name = (TextView) itemView.findViewById(R.id.textview_name);
                point = (TextView) itemView.findViewById(R.id.textview_point);
                position = (TextView) itemView.findViewById(R.id.textview_position);
            }
        }
    }

}
