package com.example.contextmenuex10;

import static java.lang.Math.pow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class resultScreen extends AppCompatActivity {

    ListView lv1;
    TextView tv1;
    boolean type;
    ArrayList<Long> series;
    int q, frstNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        lv1 = findViewById(R.id.lv1);
        tv1 = findViewById(R.id.tv1);

        Intent gi = getIntent();
        type = gi.getBooleanExtra("seriesType", false);
        frstNum = gi.getIntExtra("firstNum", 1);
        q = gi.getIntExtra("difference", 1);

        generateSeriesLV();

        registerForContextMenu(lv1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Series Operations");
        menu.add("Get index");
        menu.add("Get sum until index");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if (item.getTitle().equals("Get index")) {

            tv1.setText("Index: " + (position + 1));
        }
        else if (item.getTitle().equals("Get sum until index"))
        {

            long sum = calculateSum(position, series);
            tv1.setText("Sum until index " + (position + 1) + ": " + sum);
        }

        return super.onContextItemSelected(item);
    }

    public void generateSeriesLV() {
        series = new ArrayList<>(); // Use ArrayList<Long>
        if (type) // Geometric series
        {
            long currentNum = frstNum;
            for (int i = 0; i < 20; i++)
            {
                series.add(currentNum);
                currentNum *= q;
            }
        }
        else // Arithmetic series
        {
            long currentNum = frstNum;
            for (int i = 0; i < 20; i++)
            {
                series.add(currentNum);
                currentNum += q;
            }
        }
        ArrayAdapter<Long> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, series); // Use ArrayAdapter<Long>
        lv1.setAdapter(adapter);
    }

    public long calculateSum(int index, ArrayList<Long> series) {
        long sum = 0;
        if (type)
        {
            sum = (long) (series.get(0)*(pow(q, index+1) - 1)/(q-1));
        }
        else
        {
            for (int i = 0; i <= index; i++)
            {
                sum += series.get(i);
            }
        }
        return sum;
    }

    public void Back(View view) {
        finish();
    }
}
