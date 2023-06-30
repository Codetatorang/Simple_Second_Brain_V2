package ibf2022.tfip.simplesecondbrain.Server.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.tfip.simplesecondbrain.Server.models.User;

@Repository
public class UserRepository1 {
    @Autowired
    private JdbcTemplate template;

    private final String GETUSERBYUSERNAMEANDPASS_SQL = "select * from accounts where user_name = ? and user_password = sha2(?,256)";
    private final String GETUSERBYUSERNAME_SQL = "select * from accounts where user_name = ?";
    private final String GETUSERBYPASSWORD_SQL = "select * from accounts where user_password = sha2(?,256)";
    private final String GETUSERBYEMAIL_SQL = "select * from accounts where email = ?";
    private final String INSERT_SQL = "insert into accounts (user_name, user_password, acc_name, email) values(?,sha2(?,256),?, ?)";
    private final String DELETE_SQL = "delete from accounts where user_name = ?";

    public Optional<User> getUserByUserNameandPassword(String userName, String userPassword) {
        try {
            User result = template.queryForObject(GETUSERBYUSERNAMEANDPASS_SQL,
                    BeanPropertyRowMapper.newInstance(User.class), userName, userPassword);
            return Optional.of(result);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public Optional<User> getUserByUserName(String userName) {
        try {
            User result = template.queryForObject(GETUSERBYUSERNAME_SQL, BeanPropertyRowMapper.newInstance(User.class),
                    userName);
            return Optional.of(result);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }

    }

    public Optional<User> getUserByPassword(String userPassword) {
        try {
            User result = template.queryForObject(GETUSERBYPASSWORD_SQL, BeanPropertyRowMapper.newInstance(User.class),
                    userPassword);
            return Optional.of(result);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }

    }

    public Optional<User> getUserByEmail(String email) {
        try {
            User result = template.queryForObject(GETUSERBYEMAIL_SQL, BeanPropertyRowMapper.newInstance(User.class),
                    email);
            return Optional.of(result);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }

    }

    public int saveUser(String userName, String userPassword, String accName, String email) {
        return template.update(INSERT_SQL, userName,
                userPassword, accName, email);
    }

    public int deleteUser(String userName) {
        return template.update(DELETE_SQL, userName);
    }
}

