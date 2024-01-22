package com.sparta.scheduler.repository;

import com.sparta.scheduler.dto.PasswordResponseDto;
import com.sparta.scheduler.entity.Password;
import com.sparta.scheduler.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class PasswordRepository {

    private final JdbcTemplate jdbcTemplate;


    public PasswordRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createPassword(Password password) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO PASSWORD (schedule_id, password) VALUES (?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setLong(1, password.getSchedule_id());
                    preparedStatement.setString(2, password.getPassword());
                    return preparedStatement;
                },
                keyHolder);
    }

    public Password getPassword(long id) {
        String sql = "SELECT * FROM PASSWORD WHERE schedule_id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
               Password password = new Password();
               password.setSchedule_id(resultSet.getLong("schedule_id"));
               password.setPassword(resultSet.getString("password"));
               return password;
            } else {
                return null;
            }
        }, id);
    }
}
