package com.nure.postalOffice.Service;

import com.nure.postalOffice.DBObjects.Department;
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
public class DepartmentServiceDAO extends JdbcDaoSupport {
    @Autowired
    public DepartmentServiceDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Bean
    public DepartmentServiceDAO getDepartmentServiceDAO() {
        return this;
    }

    static class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String city = resultSet.getString("city");
            String country = resultSet.getString("country");
            String number = resultSet.getString("phone_number");
            return new Department(id, city, country, number);
        }
    }

    public List<Department> getAllDepartments() {
        String sql = "SELECT * FROM DEPARTMENT";
        return Objects.requireNonNull(this.getJdbcTemplate()).query(sql, new Object[]{}, new DepartmentRowMapper());
    }
}
