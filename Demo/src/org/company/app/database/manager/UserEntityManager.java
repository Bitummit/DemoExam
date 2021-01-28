package org.company.app.database.manager;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.company.app.database.entity.UserEntity;
import org.company.app.util.MySqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserEntityManager {

    private MySqlDatabase database;


    public UserEntityManager(MySqlDatabase database) {
        this.database = database;
    }

    public void add(UserEntity user) throws SQLException
    {
        try(Connection c = database.getConnection()){
            String sql = "insert into user(login,password,age,job) values (?,?,?,?)";


            PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getJob());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next()){
                user.setId(keys.getInt(1));
            }
        }
    }

    public UserEntity getById(int id) throws SQLException
    {
        try(Connection c = database.getConnection()){
            String sql = "select * from user where id=?";

            PreparedStatement ps = c.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next())
            {
                return new UserEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getInt("age"),
                        resultSet.getString("job")
                );
            }
        }
        return null;
    }

    public List<UserEntity> getAll() throws SQLException
    {
        try(Connection c = database.getConnection()){
            String sql = "select * from user";

            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<UserEntity> users = new ArrayList<>();
            while(resultSet.next())
            {
                users.add(new UserEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getInt("age"),
                        resultSet.getString("job")
                ));
            }
            return users;
        }

    }

    public void update(UserEntity user) throws SQLException
    {
        try(Connection c = database.getConnection())
        {
            String sql = "update user set login=?, password=?, age=?, job=? where id=?";

            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getJob());
            ps.setInt(5, user.getId());

            ps.executeUpdate();

        }
    }

    public void deleteById(int id) throws SQLException {
        try(Connection c = database.getConnection())
        {
            String sql = "delete from user where id=?";
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setInt(1,id);
            ps.executeUpdate();
        }
    }

    public void delete(UserEntity user) throws SQLException {
        deleteById(user.getId());
    }
}
