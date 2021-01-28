package org.company.app.database.manager;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.company.app.database.entity.DateEntity;
import org.company.app.util.MySqlDatabase;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.sql.*;

public class DateEntityManager {
    private MySqlDatabase database;

    public DateEntityManager(MySqlDatabase database) {
        this.database = database;
    }

    public void add(DateEntity dateEntity) throws SQLException {
        try(Connection c = database.getConnection())
        {
            String sql = "insert into datetable(date) values (?)";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(dateEntity.getDate().getTime()));
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next())
            {
                dateEntity.setId(keys.getInt(1));

            }

        }
    }

    public DateEntity getById(int id) throws SQLException {
        try(Connection c = database.getConnection())
        {
            String sql = "select * from datetable where id=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next())
            {
                return new DateEntity(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("date")
                );
            }
        }
        return null;
    }
}
