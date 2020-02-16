package com.example.HotelSPP.repository.implementation;

import com.example.HotelSPP.entity.User;
import com.example.HotelSPP.entity.UserRole;
import com.example.HotelSPP.exceptions.ResourceNotFoundException;
import com.example.HotelSPP.repository.interfaces.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Repository
@PropertySource("classpath:/config/user.properties")
public class UserRepositoryImpl implements UserRepository {
    @Value("${user.id}")
    private String paramUserId;
    @Value("${user.phone}")
    private String paramUserPhone;
    @Value("${user.surname}")
    private String paramUserSurname;
    @Value("${user.name_var}")
    private String paramUserName;
    @Value("${user.email}")
    private String paramUserEmail;
    @Value("${user.password}")
    private String paramUserPassword;
    @Value("${user.role}")
    private String paramUserRole;

    @Value("${sql.insert.user}")
    private String ADD_USER;

    @Value("${sql.select.userById}")
    private String GET_USER_BY_ID;

    @Value("${sql.select.userByEmail}")
    private String GET_USER_BY_EMAIL;
    @Value("${sql.select.userByPhone}")
    private String GET_USER_BY_PHONE;

    @Value("${const.searchStr}")
    private String paramUserSearchStr;

    @Autowired
    private JdbcTemplate template;
    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;

    @Override
    public User addUser(User user) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(paramUserEmail, user.getEmail());
        paramMap.put(paramUserSurname, user.getSurname());
        paramMap.put(paramUserName, user.getName());
        paramMap.put(paramUserPhone, user.getPhone());
        paramMap.put(paramUserPassword, user.getPassword());
        paramMap.put(paramUserRole, user.getRole().getId());
        try {
            namedTemplate.update(ADD_USER, paramMap);
        } catch (DataAccessException e) {
            //throw RepositoryUtils.toDatabaseException(e, "Sorry, service can't create user now !");
        }

        Optional<User> createdUser = findUserByPhone(user.getPhone());
        return createdUser.orElseThrow(
                () -> new ResourceNotFoundException("User", "phone", user.getPhone()));
    }

    @Override
    public Optional<User> findUserById(Long id) {
        User res;
        try {
            res = namedTemplate.queryForObject(GET_USER_BY_ID, new MapSqlParameterSource(
                    paramUserId, id), new UserMapper());
        }catch(EmptyResultDataAccessException e){
            log.warn(String.format("Couldn't find user by id: %s", id));
            res = null;
        }
        return Optional.ofNullable(res);
    }

    @Override
    public Optional<User> findUserByEmail(String searchStr) {
        User res;
        try {
            res = namedTemplate.queryForObject(GET_USER_BY_EMAIL, new MapSqlParameterSource(
                    paramUserSearchStr, searchStr), new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            log.warn(String.format("Couldn't find user by email: %s", searchStr));
            res = null;
        } catch (DataAccessException e) {
            throw e;
            //throw RepositoryUtils.toDatabaseException(e, "Couldn't perform search for user by email !");
        }
        return Optional.ofNullable(res);
    }

    @Override
    public Optional<User> findUserByPhone(String phone) {
        User res;
        try {
            log.debug("kek");
            res = namedTemplate.queryForObject(GET_USER_BY_PHONE, new MapSqlParameterSource(
                    paramUserPhone, phone), new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            log.warn(String.format("Couldn't find user by phone: %s", phone));
            res = null;
        } catch (DataAccessException e) {
            log.error("Error: ", e);
            throw e;
            //throw RepositoryUtils.toDatabaseException(e, "Couldn't perform search for user by phone !");
        }
        return Optional.ofNullable(res);
    }


    private class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int arg1) throws SQLException {
            log.info("User variable: {}", paramUserName);
            return User.builder()
                    .id(rs.getInt(paramUserId))
                    .phone(rs.getString(paramUserPhone))
                    .surname(rs.getString(paramUserSurname))
                    .name(rs.getString(paramUserName))
                    .email(rs.getString(paramUserEmail))
                    .password(rs.getString(paramUserPassword))
                    .role(UserRole.getRoleById(rs.getInt(paramUserRole)))
                    .build();
        }
    }
}
