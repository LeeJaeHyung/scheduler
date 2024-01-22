package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.PasswordRequestDto;
import com.sparta.scheduler.dto.PasswordResponseDto;
import com.sparta.scheduler.entity.Password;
import com.sparta.scheduler.repository.PasswordRepository;
import com.sparta.scheduler.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class PasswordService {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordRepository repository;
    public PasswordService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.repository = new PasswordRepository(jdbcTemplate);
    }

    public void createPassword(PasswordRequestDto passwordRequestDto, long scheduleId) {
        Password password = new Password(passwordRequestDto, scheduleId);
        repository.createPassword(password);
    }

    public PasswordResponseDto getPassword(long id){
       return new PasswordResponseDto(repository.getPassword(id));
    }



}
