package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.RequestDto;
import com.sparta.scheduler.dto.ResponseDto;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    private final JdbcTemplate jdbcTemplate;
    private final ScheduleService service;
    public SchedulerController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.service = new ScheduleService(jdbcTemplate);
    }


    @PostMapping("schedule")
    public ResponseDto createSchedule(@RequestBody RequestDto requestDto){
        return service.createSchedule(requestDto);
    }

    @GetMapping("/schedule")
    public List<ResponseDto> getSchedule() {
        return service.getSchedule();
    }

    @PutMapping("/schedule/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        return service.updateSchedule(id, requestDto);
    }

    @DeleteMapping("/schedule/{id}")
    public Long deleteSchedule(@PathVariable Long id) {
        return service.deleteSchedule(id);
    }


}
