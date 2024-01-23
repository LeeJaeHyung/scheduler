package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.PasswordRequestDto;
import com.sparta.scheduler.dto.PasswordResponseDto;
import com.sparta.scheduler.dto.RequestDto;
import com.sparta.scheduler.dto.ResponseDto;
import com.sparta.scheduler.service.PasswordService;
import com.sparta.scheduler.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    private final JdbcTemplate jdbcTemplate;
    private final ScheduleService scheduleService;
    private final PasswordService passwordService;

    public SchedulerController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.scheduleService = new ScheduleService(jdbcTemplate);
        this.passwordService = new PasswordService(jdbcTemplate);
    }


    @PostMapping("schedule")
    public ResponseDto createSchedule(@RequestBody RequestDto requestDto){

        ResponseDto responseDto = scheduleService.createSchedule(requestDto);
        if(requestDto.getPassword()!=null){
            passwordService.createPassword(new PasswordRequestDto(requestDto.getPassword()),responseDto.getId());
        }
        return responseDto;
    }

    @GetMapping("/schedule")
    public List<ResponseDto> getSchedule() {
        return scheduleService.getSchedule();
    }

    @GetMapping("/schedule/{id}")
    public ResponseDto getScheduleById(@PathVariable Long id){
        return scheduleService.getScheduleById(id);
    }

    @PutMapping("/schedule/{id}")
    public ResponseDto updateSchedule(@PathVariable Long id, @RequestBody RequestDto requestDto) {

        PasswordResponseDto passwordResponseDto = passwordService.getPassword(id);
        if(passwordResponseDto != null&&requestDto != null&&passwordResponseDto.getPassword().equals(requestDto.getPassword())) {
            return scheduleService.updateSchedule(id, requestDto);
        }else{
            throw new IllegalArgumentException("비밀번호를 확인해 주세요!.");
        }
    }

    @DeleteMapping("/schedule/{id}")
    public Long deleteSchedule(@PathVariable Long id, @RequestBody PasswordRequestDto passwordRequestDto) {

        PasswordResponseDto passwordResponseDto = passwordService.getPassword(id);
        if(passwordResponseDto != null&&passwordResponseDto.getPassword().equals(passwordRequestDto.getPassword())) {
            return scheduleService.deleteSchedule(id);
        }else{
            throw new IllegalArgumentException("비밀번호를 확인해 주세요!.");
        }
    }


}
