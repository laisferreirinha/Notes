package pt.iade.laisferreira.notes.webserver.models;

import java.time.LocalDateTime;

public class NoteItem {
    private static int next_id=1;
    private int id;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime modifiedDate;

    public NoteItem(){

    }

    public NoteItem(String title, String content,
                    LocalDateTime creationDate, LocalDateTime modifiedDate){
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
