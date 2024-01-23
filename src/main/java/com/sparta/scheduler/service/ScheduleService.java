package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.PasswordRequestDto;
import com.sparta.scheduler.dto.RequestDto;
import com.sparta.scheduler.dto.ResponseDto;
import com.sparta.scheduler.entity.Password;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Comparator;
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
        List<ResponseDto> list =  repository.findAll();
        return list.stream()
                .sorted(Comparator.comparing(ResponseDto::getDate)).toList().reversed();
    }

    public ResponseDto updateSchedule(Long id, RequestDto requestDto){
        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = repository.findById(id);
        if (schedule != null) {
            // memo 내용 수정
            schedule.setTitle(requestDto.getTitle());
            schedule.setContents(requestDto.getContents());
            schedule.setHead(requestDto.getHead());
            int result = repository.update(id, schedule);
            if(result==1){
                return new ResponseDto(schedule);
            }else{
                throw new IllegalArgumentException("업데이트 실패");
            }
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }

    public Long deleteSchedule(Long id) {
        Schedule schedule = repository.findById(id);
        if (schedule != null) {
            // memo 삭제
            repository.delete(id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }

    public ResponseDto getScheduleById(Long id) {
        return new ResponseDto(repository.findById(id));
    }
}
