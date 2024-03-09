package com.vassilyev.submitapp.DTO;

import com.vassilyev.submitapp.model.Status;
import com.vassilyev.submitapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponseDTO {

    private String title;
    private String text;
    private User user;
    private Timestamp creationTime;
    private Timestamp lastEditTime;
    private Status status;

}

