package org.company.app;


import org.company.app.database.manager.DateEntityManager;
import org.company.app.database.manager.UserEntityManager;
import org.company.app.ui.TableForm;
import org.company.app.util.BaseForm;
import org.company.app.util.MySqlDatabase;

import javax.swing.*;
import java.sql.Connection;

public class Application {
    private static Application instance;
    private final MySqlDatabase database = new MySqlDatabase("127.0.0.1","predemo","root", "1234");
    private final UserEntityManager userEntityManager = new UserEntityManager(database);

    private Application(){
        instance = this;

        initDatabase();
        initUI();

        new TableForm();

    }

    public UserEntityManager getUserEntityManager() {
        return userEntityManager;
    }

    public static void main(String[] args) {
        new Application();
    }

    public static Application getInstance(){
        return instance;
    }

    private void initDatabase()
    {
        try(Connection c = database.getConnection())
        {

        }catch (Exception e)
        {
            System.out.println("Ошибка подключения к БД");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    private void initUI()
    {
        BaseForm.setBaseApplicationTitle("Nice");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
