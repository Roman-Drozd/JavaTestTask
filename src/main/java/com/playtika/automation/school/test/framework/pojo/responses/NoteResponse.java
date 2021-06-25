package com.playtika.automation.school.test.framework.pojo.responses;

import lombok.Value;

@Value
public class NoteResponse {

    Integer id;
    String content;
    String createdAt;
    String modifiedAt;
    Integer version;
}
