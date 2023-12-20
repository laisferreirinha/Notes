package pt.iade.laisferreira.notes.webserver.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NoteItem {
    private static int next_id=1;
    private int id;
    private String title;
    private String content;
    private LocalDate creationDate;
    private LocalDate modifiedDate;

    public NoteItem(){

    }

    public NoteItem(String title, String content,
                    LocalDate creationDate, LocalDate modifiedDate){
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
        this.id = next_id;
        next_id++;
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
        this.creationDate = creationDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
