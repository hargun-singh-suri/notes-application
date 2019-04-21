package com.hargun.notes.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargun.notes.exception.NoteNotFoundException;
import com.hargun.notes.model.Note;
import com.hargun.notes.service.NoteService;

@RestController
@Controller
@RequestMapping("/api/notes")
public class NoteController {
	
	@Autowired
	NoteService noteService;
	
	@GetMapping
	public ResponseEntity<List<Note>> findAllNote(){
		return ResponseEntity.ok(noteService.findAllNote());	
	}
	
	@PostMapping
	public ResponseEntity<Note> saveNote(@Valid @RequestBody Note note) {
		return ResponseEntity.ok(noteService.saveNote(note));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Note> findNoteById(@PathVariable Long id){
		
		Optional<Note> noteById = noteService.findNoteById(id);
		noteService.findNoteById(id).orElseThrow(() -> new NoteNotFoundException("Note Not Found for id "+id));
		return ResponseEntity.ok(noteById.get());
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Note> updateNote(@PathVariable Long id, @Valid @RequestBody Note newNoteValue) throws Exception{
	  Note existingNote = noteService.findNoteById(id).orElseThrow(() -> new NoteNotFoundException("Note Not Found for id "+id));
	  return ResponseEntity.ok(noteService.updateNote(newNoteValue, existingNote));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		noteService.findNoteById(id).orElseThrow(() -> new NoteNotFoundException("Note Not Found for id "+id));
		noteService.deleteNote(id);
		return ResponseEntity.ok("Data successfully deleted");
	}
	
}
