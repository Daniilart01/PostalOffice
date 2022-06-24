package com.nure.postalOffice.Service;

import com.nure.postalOffice.DBObjects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UsersServiceDAO extends JdbcDaoSupport  {

    private static final String ALL_SQL = "SELECT * FROM USERS";
    private static final String BASE_SQL = "SELECT * FROM USERS WHERE username = ?";

    @Autowired
    public UsersServiceDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Bean
    public UsersServiceDAO getUserServiceDao() {
        return this;
    }

    static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            return new User(username, password, role);
        }
    }

    public List<User> getAllUsers() {
        return Objects.requireNonNull(this.getJdbcTemplate()).query(ALL_SQL, new Object[]{}, new UserRowMapper());
    }

    public User getUserWithUsername(String username) {
        List<User> user = Objects.requireNonNull(this.getJdbcTemplate()).query(BASE_SQL, new Object[]{username}, new UserRowMapper());
        if (user.size() != 0) {
            return user.get(0);
        } else {
            return null;
        }
    }

    public void addToDatabase(User user) {
        String query = "INSERT INTO USERS(USERNAME, PASSWORD) VALUES(?,?)";
        this.getJdbcTemplate().update(query, user.getUsername(), user.getPassword());
        this.getJdbcTemplate().execute("COMMIT");
    }

    public void removeFromDatabase(User user) {
        String query = "DELETE FROM USERS WHERE USERNAME = '" + user.getUsername() + "'";
        this.getJdbcTemplate().update(query);
        this.getJdbcTemplate().execute("COMMIT");
    }
}
