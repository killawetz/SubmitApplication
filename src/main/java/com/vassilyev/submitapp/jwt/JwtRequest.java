package com.vassilyev.submitapp.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {

    private String login;

    private String password;

}
