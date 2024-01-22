package com.sparta.scheduler.entity;

import com.sparta.scheduler.dto.RequestDto;
import lombok.Getter;

import java.util.Date;

@Getter
public class Schedule {

    private long id;
    private String title;
    private String contents;
    private String head;
    private Date date;

    public Schedule(RequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.head = requestDto.getHead();
        this.date = requestDto.getDate();
    }
}
