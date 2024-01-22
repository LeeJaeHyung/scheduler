package com.sparta.scheduler.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class RequestDto {

    private String title;
    private String contents;
    private String head;
    private Date date;

}
