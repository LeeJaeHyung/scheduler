package com.sparta.scheduler.entity;

import com.sparta.scheduler.dto.PasswordRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Password {
    private long schedule_id;
    private String password;

    public Password(PasswordRequestDto passwordRequestDto, long schedule_id) {
        this.password = passwordRequestDto.getPassword();
        this.schedule_id = schedule_id;
    }
}
