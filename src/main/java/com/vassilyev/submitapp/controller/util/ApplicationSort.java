package com.vassilyev.submitapp.controller.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum ApplicationSort {

    CREATE_ASC(Sort.by(Sort.Direction.ASC, "creationTime")),
    CREATE_DESC(Sort.by(Sort.Direction.DESC, "creationTime"));

    private final Sort sortValue;

}
