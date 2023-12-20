package pt.iade.laisferreira.atividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.time.LocalDate;
import java.time.LocalDateTime;

import pt.iade.laisferreira.atividade.models.NoteItem;

public class NoteActivity extends AppCompatActivity {
    protected EditText title;
    protected EditText content;

    protected int position;
    protected NoteItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        item = (NoteItem) intent.getSerializableExtra("item");

        setupComponents();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_save_item) {
            //Actionbar "Save" button.
            commitView();
            this.item.save(new NoteItem.DefaultResponse() {
                @Override
                public void response() {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("position", position);
                    returnIntent.putExtra("updatedItem", NoteActivity.this.item);
                    setResult(AppCompatActivity.RESULT_OK, returnIntent);

                    finish();
                }
            });
            return true;
        } else if(item.getItemId() == R.id.action_delete_item){
            this.item.delete(new NoteItem.DefaultResponse() {
                @Override
                public void response() {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("delete", true);
                    returnIntent.putExtra("position", position);
                    returnIntent.putExtra("updatedItem", NoteActivity.this.item);
                    setResult(AppCompatActivity.RESULT_OK, returnIntent);

                    finish();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupComponents () {
        //Setup the Actionbar.
        setSupportActionBar(findViewById(R.id.toolbar2));
        title = (EditText) findViewById(R.id.title_edit);
        content = (EditText) findViewById(R.id.content_edit);

        populateView();
    }

    private void populateView() {
        title.setText(item.getTitle());
        content.setText(item.getContent());
    }

    private void commitView() {
        item.setTitle(title.getText().toString());
        item.setContent(content.getText().toString());
        item.setCreationDate(LocalDate.now());
        item.setModifiedDate(LocalDate.now());
    }

}