package pt.iade.laisferreira.notes.webserver.models.repositories;

import pt.iade.laisferreira.notes.webserver.models.NoteItem;
import pt.iade.laisferreira.notes.webserver.results.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NoteRepository {
    private static ArrayList<NoteItem> notes = new ArrayList<NoteItem>();

    public static void populate() {

    }


    public static ArrayList<NoteItem> getNotes(){
        return notes;
    }

    public static NoteItem getNoteById(int id){
        for (NoteItem n : notes){
            if (n.getId() == id){
                return n;
            }
        }
        return null;
    }

    public static NoteItem addNote(NoteItem note){
        NoteItem newNote = new NoteItem(note.getTitle(), note.getContent(),
                note.getCreationDate(), note.getModifiedDate());
        notes.add(newNote);
        return newNote;
    }

    public static NoteItem updateNote(NoteItem note){
        for (NoteItem n : notes){
            if (n.getId() == note.getId()){
                n.setTitle(note.getTitle());
                n.setContent(note.getContent());
                n.setCreationDate(note.getCreationDate());
                n.setModifiedDate(note.getModifiedDate());

                return n;
            }
        }
        return null;
    }

    public static Response deleteNote(int id){
        if (notes.removeIf(n->n.getId() == id)){
            return new Response(id+" was deleted.", null);
        }else{
            return new Response(id+" was not found.", null);
        }
    }
}
