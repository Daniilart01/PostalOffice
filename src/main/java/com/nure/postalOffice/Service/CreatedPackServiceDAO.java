package com.nure.postalOffice.Service;

import com.nure.postalOffice.DBObjects.CreatedPack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CreatedPackServiceDAO extends JdbcDaoSupport {

    private static final String BASE_SQL = "SELECT p.ID, sender, receiver, a.ID as dep1_id, a.city as dep1_city, a.country dep1_country, a.PHONE_NUMBER as dep1_phone_number," +
            "b.ID as dep2_id, b.city as dep2_city,b.country as dep2_country, b.PHONE_NUMBER as dep2_phone_number , p.date_creating" +
            " FROM CREATEDPACK p INNER JOIN DEPARTMENT a on DEPARTMENT_ID_SENDER = a.ID INNER JOIN DEPARTMENT b ON DEPARTMENT_ID_RECEIVER = b.ID";

    @Autowired
    public CreatedPackServiceDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    @Bean
    public CreatedPackServiceDAO getCreatedPackServiceDao() {
        return this;
    }

    static class CreatedPackRowMapper implements RowMapper<CreatedPack> {

        @Override
        public CreatedPack mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String sender = resultSet.getString("sender");
            String receiver = resultSet.getString("receiver");
            int senderDepartmentId = resultSet.getInt("dep1_id");
            String senderDepartmentCity = resultSet.getString("dep1_city");
            String senderDepartmentCountry = resultSet.getString("dep1_country");
            String senderDepartmentNumber = resultSet.getString("dep1_phone_number");
            int receiverDepartmentId = resultSet.getInt("dep2_id");
            String receiverDepartmentCity = resultSet.getString("dep2_city");
            String receiverDepartmentCountry = resultSet.getString("dep2_country");
            String receiverDepartmentNumber = resultSet.getString("dep2_phone_number");
            Date dateCreating = resultSet.getDate("date_creating");
            return new CreatedPack(id, sender, receiver, senderDepartmentId, senderDepartmentCity, senderDepartmentCountry, senderDepartmentNumber, receiverDepartmentId, receiverDepartmentCity, receiverDepartmentCountry, receiverDepartmentNumber, dateCreating);
        }
    }

    public List<CreatedPack> getAllPackages() {
        return Objects.requireNonNull(this.getJdbcTemplate()).query(BASE_SQL, new Object[]{}, new CreatedPackRowMapper());
    }

    public List<CreatedPack> getPackagesByParams(String sql, Object[] args) {
        return this.getJdbcTemplate().query(BASE_SQL + " " + sql, args, new CreatedPackRowMapper());
    }

    public void deleteFromDatabase(long id) {
        String query = "DELETE FROM CREATEDPACK WHERE ID = " + id;
        this.getJdbcTemplate().update(query);
        this.getJdbcTemplate().execute("COMMIT");
    }

    public void addToDatabase(long id, String sender, String receiver, int senderDepartmentID, int receiverDepartmentId, Date dateCreating) {
        String query = "INSERT INTO CREATEDPACK VALUES(?,?,?,?,?,?)";
        try {
            this.getJdbcTemplate().update(query, id, sender, receiver, senderDepartmentID, receiverDepartmentId, dateCreating);
            this.getJdbcTemplate().execute("COMMIT");
        } catch (Exception exception) {
            System.err.println("Error writing to DB!");
            throw exception;
        }
    }

}