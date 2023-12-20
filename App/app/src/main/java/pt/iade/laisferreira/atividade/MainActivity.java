package pt.iade.laisferreira.atividade;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import pt.iade.laisferreira.atividade.adapters.NoteItemRowAdapter;
import pt.iade.laisferreira.atividade.models.NoteItem;

public class MainActivity extends AppCompatActivity {
    private static final int NOTE_ACTIVITY_REQUEST_ID = 1;
    protected NoteItemRowAdapter itemRowAdapter;
    protected RecyclerView itemsListView;

    protected ArrayList<NoteItem> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupComponents();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_new_item) {
            //Actionbar "Add" button.
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);
            intent.putExtra("position", -1);
            intent.putExtra("item", new NoteItem());

            startActivityForResult(intent, NOTE_ACTIVITY_REQUEST_ID);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NOTE_ACTIVITY_REQUEST_ID){
            if(resultCode == AppCompatActivity.RESULT_OK){

                boolean toDelete = data.getBooleanExtra("delete", false);
                int position = data.getIntExtra("position", -1);
                NoteItem updatedItem = (NoteItem) data.getSerializableExtra("updatedItem");

                if (!toDelete){
                    if(position == -1){
                        itemsList.add(updatedItem);
                        itemRowAdapter.notifyItemInserted(itemsList.size()-1);
                    }
                    else{
                        itemsList.set(position, updatedItem);
                        itemRowAdapter.notifyItemChanged(position);

                    }
                } else{
                    if(position != -1){
                        Log.e("delete_action", "Yep");
                        itemsList.remove(position);
                        itemRowAdapter.notifyItemRemoved(position);
                    }
                }

            }

        }

    }


    private void setupComponents () {
        //Setup the Actionbar.
        setSupportActionBar(findViewById(R.id.toolbar));

        NoteItem.List(new NoteItem.ListResponse() {
            @Override
            public void response(ArrayList<NoteItem> items) {
                itemsList=items;

                itemRowAdapter = new NoteItemRowAdapter(MainActivity.this, itemsList);
                itemRowAdapter.setOnClickListener(new NoteItemRowAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("item", itemsList.get(position));

                        startActivityForResult(intent, NOTE_ACTIVITY_REQUEST_ID);
                    }
                });

                //  Setup the items Recycler View
                itemsListView = (RecyclerView) findViewById(R.id.notes_list);
                itemsListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                itemsListView.setAdapter(itemRowAdapter);
            }
        });


    }


}