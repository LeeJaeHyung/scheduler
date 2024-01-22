package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordResponseDto {

    private long schedule_id;
    private String password;


    public PasswordResponseDto(Password password) {
        this.schedule_id = password.getSchedule_id();
        this.password = password.getPassword();
    }
}
