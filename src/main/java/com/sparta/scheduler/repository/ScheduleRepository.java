package com.sparta.scheduler.repository;

import com.sparta.scheduler.dto.RequestDto;
import com.sparta.scheduler.dto.ResponseDto;
import com.sparta.scheduler.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO schedule (title, contents, head, date) VALUES (?, ?, ?, NOW())";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getTitle());
                    preparedStatement.setString(2, schedule.getContents());
                    preparedStatement.setString(3, schedule.getHead());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);


        return schedule;
    }

    public List<ResponseDto> findAll() {
        String sql = "SELECT * FROM SCHEDULE";

        return jdbcTemplate.query(sql, new RowMapper<ResponseDto>() {
            @Override
            public ResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String contents = rs.getString("contents");
                String head = rs.getString("head");
                Date date = rs.getDate("date");
                return new ResponseDto(id, title, contents, head, date);
            }
        });
    }

    public List<ResponseDto> sortList(List<ResponseDto> responseDto){
        return responseDto;
    }

    public Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM SCHEDULE WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(id);
                schedule.setTitle(resultSet.getString("title"));
                schedule.setContents(resultSet.getString("contents"));
                schedule.setHead(resultSet.getString("head"));
                schedule.setDate(resultSet.getDate("date"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }

    public int delete(Long id) {
        String sql = "DELETE FROM SCHEDULE WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int update(Long id, Schedule schedule) {
        String sql = "UPDATE SCHEDULE SET title = ?, contents = ?, head = ?, date = NOW() WHERE id = ?";
        return jdbcTemplate.update(sql, schedule.getTitle(), schedule.getContents(), schedule.getHead() ,id);
    }
}
