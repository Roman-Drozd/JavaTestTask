package com.playtika.automation.school.test.framework.action;

import com.playtika.automation.school.test.framework.client.NotesFeignClient;
import com.playtika.automation.school.test.framework.pojo.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.UpdateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;
import com.playtika.automation.school.test.framework.pojo.responses.NoteResponse;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class NotesActions {

    private final NotesFeignClient notesFeignClient;

    private String createAccessToken(AuthResponse responseData) {
        return responseData.getToken_type() + " " + responseData.getAccess_token();
    }

    public List<NoteResponse> getNotes(AuthResponse responseData) {
        return notesFeignClient.getAllUserNotes(createAccessToken(responseData));
    }

    public NoteResponse addNote(AuthResponse responseData) {
        CreateNoteRequest request = CreateNoteRequest.builder().content(responseData.getAccess_token()).build();
        return notesFeignClient.addNote(createAccessToken(responseData), request);
    }

    public NoteResponse getNote(AuthResponse responseData, Integer noteId) {
        return notesFeignClient.getNote(createAccessToken(responseData), noteId);
    }

    public NoteResponse updateNote(AuthResponse responseData, Integer noteId, String content) {
        UpdateNoteRequest request = UpdateNoteRequest.builder().content(content).build();
        return notesFeignClient.updateNote(createAccessToken(responseData), noteId, request);
    }

    public void deleteNote(AuthResponse responseData, Integer noteId) {
        notesFeignClient.deleteNote(createAccessToken(responseData), noteId);
    }
}
