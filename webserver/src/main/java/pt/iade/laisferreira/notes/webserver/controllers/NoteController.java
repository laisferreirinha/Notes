package pt.iade.laisferreira.notes.webserver.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.laisferreira.notes.webserver.models.NoteItem;
import pt.iade.laisferreira.notes.webserver.models.repositories.NoteRepository;
import pt.iade.laisferreira.notes.webserver.results.Response;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/notes")
public class NoteController {
    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<NoteItem> getNotes(){
        logger.info("Sending all the notes.");
        return NoteRepository.getNotes();
    }


    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteItem getNoteById(@PathVariable("id") int id){
        logger.info("Sending the note with id="+id);
        return NoteRepository.getNoteById(id);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteItem createNote(@RequestBody NoteItem note){
        logger.info("Inserting a new note.");
        return NoteRepository.addNote(note);
    }

    @PostMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteItem updateNote(@RequestBody NoteItem note){
        logger.info("Updating an existing note.");
        return NoteRepository.updateNote(note);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteNote(@PathVariable("id") int id){
        logger.info("Deleting the note with id="+id);
        return NoteRepository.deleteNote(id);
    }

}
