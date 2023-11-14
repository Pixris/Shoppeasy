package com.f23.shoppeasy.model.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author drago
 */
@Repository
public class UserRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    List<User> findAll() {

        String query = "select id, firstname, lastname,email from user";
        return template.query(query,
                (result, rowNum)
                -> new User(result.getLong("id"),
                        result.getString("firstname"), result.getString("lastname"), result.getString(
                        "email")));
    }

    public User getUserById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "id", id);
        String query = "select * from user where id=:id ";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(User.class));
    }

    public void deleteUserById(long id) {

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "id", id);
        String query = "delete from user where id=:id";
        template.update(query, namedParameters);
    }
}
