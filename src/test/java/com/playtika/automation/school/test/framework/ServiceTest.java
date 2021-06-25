package com.playtika.automation.school.test.framework;

import com.playtika.automation.school.test.framework.action.AuthActions;
import com.playtika.automation.school.test.framework.action.NotesActions;
import com.playtika.automation.school.test.framework.action.RegistrationActions;
import com.playtika.automation.school.test.framework.configuration.AuthConfiguration;
import com.playtika.automation.school.test.framework.configuration.NotesConfiguration;
import com.playtika.automation.school.test.framework.configuration.RegistrationConfiguration;
import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;
import feign.FeignException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@EnableAutoConfiguration
@SpringBootTest(classes = {RegistrationConfiguration.class, AuthConfiguration.class, NotesConfiguration.class})
public class ServiceTest {

    @Autowired
    private RegistrationActions registrationActions;

    @Autowired
    private AuthActions authActions;

    @Autowired
    private NotesActions notesActions;

    private final String updatedContent = "Updated Content";
    private Integer secondNoteId;
    private String mail;
    private String password;
    private AuthResponse authResponse;

    @BeforeEach
    void setUp() {
        mail = registrationActions.generateRandomMail();
        password = registrationActions.generateRandomPassword();
        registrationActions.getRegistrationData(mail, password);
        authResponse = authActions.authUser(mail, password);
    }

    @Test
    void serviceTest() {
        var firstNote = notesActions.addNote(authResponse);
        var notesResponse = notesActions.getNotes(authResponse);
        Integer firstNoteId = firstNote.getId();
        assertThat(notesResponse.size()).isEqualTo(1);

        var secondNote = notesActions.addNote(authResponse);
        notesResponse = notesActions.getNotes(authResponse);
        secondNoteId = secondNote.getId();
        assertThat(notesResponse.size()).isEqualTo(2);

        var firstNoteResponse = notesActions.getNote(authResponse, firstNoteId);
        assertThat(firstNote).isEqualTo(firstNoteResponse);

        notesActions.updateNote(authResponse,firstNoteId, updatedContent);
        notesResponse = notesActions.getNotes(authResponse);
        var updatedNoteResponse = notesResponse.stream().filter(note -> note.getId().equals(firstNoteId)).findFirst().orElseThrow(()-> new RuntimeException("Note was not found"));
        assertThat(updatedNoteResponse.getId().equals(firstNoteResponse.getId()));
        assertThat(updatedNoteResponse.getVersion() > firstNoteResponse.getVersion());
        assertThat(updatedNoteResponse.getContent().equals(updatedContent));
        assertThat(updatedNoteResponse.getCreatedAt().equals(firstNoteResponse.getCreatedAt()));
        assertThat(updatedNoteResponse.getModifiedAt()).isNotEqualTo(firstNoteResponse.getModifiedAt());

        notesActions.deleteNote(authResponse, firstNoteId);
        notesResponse = notesActions.getNotes(authResponse);
        assertThat(notesResponse.size()).isEqualTo(1);
        assertThat(notesResponse.get(0).getContent()).isNotEqualTo(updatedContent);

        assertThatThrownBy(() -> notesActions.getNote(authResponse, firstNoteId))
                .isInstanceOf(FeignException.class)
                .hasMessageContaining("Note with id [" + firstNoteId.toString() + "] wasn't found");
    }

    @AfterEach
    void tearDown() {
        notesActions.deleteNote(authResponse, secondNoteId);
    }
}
