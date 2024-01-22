package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.RequestDto;
import com.sparta.scheduler.dto.ResponseDto;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ScheduleService {

    private final JdbcTemplate jdbcTemplate;
    private final ScheduleRepository repository;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.repository = new ScheduleRepository(jdbcTemplate);
    }

    public ResponseDto createSchedule(RequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);

        Schedule saveSchedule = repository.save(schedule);

        ResponseDto responseDto = new ResponseDto(saveSchedule);

        return responseDto;
    }

    public List<ResponseDto> getSchedule() {
        return null;
    }

    public Long updateSchedule(Long id, RequestDto requestDto) {
        return null;
    }

    public Long deleteSchedule(Long id) {
        return null;
    }
}
