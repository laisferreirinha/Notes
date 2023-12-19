package pt.iade.laisferreira.atividade.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class NoteItem implements Serializable {
    private int id;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime modifiedDate;

    public NoteItem(){
        this(0, "", "", LocalDateTime.now(), LocalDateTime.now());
    }

    public NoteItem(int id, String title, String content, LocalDateTime creationDate, LocalDateTime modifiedDate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
    }

    public static ArrayList<NoteItem> List(){
        //  TODO: Fetch the WebServer to the note list

        //  Simulates the WebServer Fetch
        ArrayList<NoteItem> notes = new ArrayList<NoteItem>();
        notes.add(new NoteItem(1, "Math home-work", "Delivery date: 19-12-2023",
                LocalDateTime.now(), LocalDateTime.now()));

        notes.add(new NoteItem(2, "Download the Joaquim's song.", "Released at 4 A.M",
                LocalDateTime.now(), LocalDateTime.now()));

        notes.add(new NoteItem(3, "Android note applicaion to deliver", "Delivery date: 20-12-2023",
                LocalDateTime.now(), LocalDateTime.now()));

        notes.add(new NoteItem(4, "Some new note", "Yep.",
                LocalDateTime.now(), LocalDateTime.now()));

        return notes;
    }

    public static NoteItem GetById(int id){
        return new NoteItem();
    }


    public void save(){
        if(id == 0){
            //  TODO: implement the INSERT of a new item to the WebServer
            id = new Random().nextInt(1000)+1;
        }else{
            //  TODO: implement the UPDATE of an existing item to the WebServer
        }
    }

    public void delete() {
        //  TODO: implete the DELETE method
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = NoteItem.this.creationDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString(){
        return "Title: " + title;
    }
}
