package com.playtika.automation.school.test.framework.client;

import com.playtika.automation.school.test.framework.pojo.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.UpdateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.responses.NoteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "service-client",
        url = "${test.service.host}",
        path = "v1/notes",
        configuration = FeignConfiguration.class
)
public interface NotesFeignClient {

    @GetMapping(consumes = "application/json")
    List<NoteResponse> getAllUserNotes(@RequestHeader("Authorization") String accessToken);

    @PostMapping(consumes = "application/json")
    NoteResponse addNote(@RequestHeader("Authorization") String accessToken,
                 @RequestBody CreateNoteRequest request);

    @GetMapping(path = "{noteId}", consumes = "application/json")
    NoteResponse getNote(@RequestHeader("Authorization") String accessToken,
                 @PathVariable("noteId") Integer noteId);

    @PutMapping(path = "{noteId}", consumes = "application/json")
    NoteResponse updateNote(@RequestHeader("Authorization") String accessToken,
                    @PathVariable("noteId") Integer noteId,
                    @RequestBody UpdateNoteRequest request);

    @DeleteMapping(path = "{noteId}")
    void deleteNote(@RequestHeader("Authorization") String accessToken,
                    @PathVariable("noteId") Integer noteId);
}

