package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private long id;
    private String title;
    private String contents;
    private String head;
    private Date date;


    public ResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.head = schedule.getHead();
        this.date = schedule.getDate();
    }


}
