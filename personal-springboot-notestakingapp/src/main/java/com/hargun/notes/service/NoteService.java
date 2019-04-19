package com.hargun.notes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hargun.notes.model.Note;

import com.hargun.notes.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	public List<Note> findAllNote(){
		return noteRepository.findAll();
	}
	
	public Note saveNote(Note note) {
		return noteRepository.save(note);
	}
	
	public Optional<Note> findNoteById(Long id) {
		return noteRepository.findById(id);
	}
	
	public Note updateNote(Note newNoteValue,Note existingNote,Long id) throws Exception {
				
		existingNote.setContent(newNoteValue.getContent());
		existingNote.setTitle(newNoteValue.getTitle());
		
		Note updatedNote = noteRepository.save(existingNote);
		return updatedNote;
	}
	
	public void deleteNote(Long id) {
		noteRepository.deleteById(id);
	}
}
