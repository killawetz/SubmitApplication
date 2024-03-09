package com.vassilyev.submitapp.controller.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum StatusType {

    DRAFT("draft"),
    SENT("sent"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    private final String value;

}
