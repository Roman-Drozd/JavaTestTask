package com.playtika.automation.school.test.framework.configuration;

import com.playtika.automation.school.test.framework.action.NotesActions;
import com.playtika.automation.school.test.framework.client.NotesFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = NotesFeignClient.class)
public class NotesConfiguration {

    @Bean
    public NotesActions notesActions(NotesFeignClient notesFeignClient) {
        return new NotesActions(notesFeignClient);
    }

}
