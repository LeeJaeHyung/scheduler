package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;

import java.util.Date;

@Getter
public class ResponseDto {

    private long id;
    private String title;
    private String contents;
    private String head;
    private Date date;


    public ResponseDto(Schedule saveSchedule) {
        this.id = saveSchedule.getId();
        this.title = saveSchedule.getTitle();
        this.contents = saveSchedule.getContents();
        this.head = saveSchedule.getHead();
        this.date = saveSchedule.getDate();
    }
}
