package com.vassilyev.submitapp.controller.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum RequestRights {

    USER("user"),
    OPERATOR("operator"),
    ADMIN("admin");

    private final String value;
}
