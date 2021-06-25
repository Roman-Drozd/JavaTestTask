package com.playtika.automation.school.test.framework.pojo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateNoteRequest {

    String content;
}
