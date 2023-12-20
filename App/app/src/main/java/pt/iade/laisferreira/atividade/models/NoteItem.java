package pt.iade.laisferreira.atividade.models;

import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import pt.iade.laisferreira.atividade.utilities.DateJsonAdapter;
import pt.iade.laisferreira.atividade.utilities.WebRequest;

public class NoteItem implements Serializable {
    private int id;
    private String title;
    private String content;
    @JsonAdapter(DateJsonAdapter.class)
    private LocalDate creationDate;
    @JsonAdapter(DateJsonAdapter.class)
    private LocalDate modifiedDate;

    public NoteItem(){
        this(0, "", "", LocalDate.now(), LocalDate.now());
    }

    public NoteItem(int id, String title, String content, LocalDate creationDate, LocalDate modifiedDate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
    }

    public static void List(ListResponse response){
        //  Fetch the WebServer to the note list
        ArrayList<NoteItem> notes = new ArrayList<NoteItem>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/notes"));
                    String resp = request.performGetRequest();

                    JsonArray array = new Gson().fromJson(resp, JsonArray.class);

                    for (JsonElement e : array){
                        notes.add(new Gson().fromJson(e, NoteItem.class));
                    }

                    response.response(notes);

                }catch (Exception e){
                    Log.e("NoteItem.List", e.toString());
                }
            }
        });
        thread.start();
    }

    /**
     * Gets the object from the web server by its ID in the database.
     *
     * @param id ID of the item in the database.
     * @param response Object with data from our web server.
     */


    public static void GetById(int id, GetByIdResponse response){
        // Fetch the item from the web server using its id and populate the object.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/notes/" + id));
                        String resp = request.performGetRequest();

                        NoteItem item = new Gson().fromJson(resp, NoteItem.class);

                        response.response(item);

                }   catch (Exception e) {
                    Log.e("TodoItem", e.toString());
                }
            }
        });
        thread.start();

    }


    public void save(DefaultResponse response){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    if(id == 0){
                        //  The INSERT of a new item to the WebServer
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/notes"));
                        String resp = request.performPostRequest(NoteItem.this);

                        NoteItem respItem = new Gson().fromJson(resp, NoteItem.class);
                        id = respItem.getId();
                        response.response();
                    }else{
                        //  The UPDATE of an existing item to the WebServer
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/notes/" + id));
                        request.performPostRequest(NoteItem.this);
                        response.response();
                    }

                }   catch (Exception e) {
                    Log.e("TodoItem.save", e.toString());
                }
            }
        });
        thread.start();

    }

    public void delete(DefaultResponse response) {
        //  The DELETE method
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/notes/"+id));
                    request.performDeleteRequest();
                    response.response();

                } catch (Exception e){
                    Log.e("NoteItem.delete", e.toString());
                }
            }
        });
        thread.start();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = NoteItem.this.creationDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString(){
        return "Title: " + title;
    }
    public interface ListResponse {
        public void response(ArrayList<NoteItem> items);
    }
    public interface GetByIdResponse {
        public void response(NoteItem item);
    }
    public interface DefaultResponse{
        public void response();
    }

}
