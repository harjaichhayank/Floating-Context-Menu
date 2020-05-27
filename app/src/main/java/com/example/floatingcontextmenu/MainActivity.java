package com.example.floatingcontextmenu;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    String[] Top_10_movies;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        registerForContextMenu(listView);

        Top_10_movies = getResources().getStringArray(R.array.Top_10_movies);
        Collections.addAll(arrayList, Top_10_movies);

        /*for (String item:Top_10_movies){
            arrayList.add(item);
        }
        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_adapter,R.id.textView,Top_10_movies); without arrayList
        */

        //with ArrayList for clickHandling on the floating context menu
        adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.custom_adapter,R.id.textView,arrayList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        //To get the position of the item in the context menu
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //item is the context menu item --> Delete, Help, Settings

        switch (item.getItemId()){
            case R.id.Delete:
                arrayList.remove(info.position);
                //now notify the adapter about the change
                adapter.notifyDataSetChanged();
                return true;
            case R.id.Help:
                return false;
            case R.id.Settings:
                Toast.makeText(this, "Settings not available", Toast.LENGTH_SHORT).show();
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.contextual_menu,menu);
    }
}
