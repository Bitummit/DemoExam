package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.UserEntity;
import org.company.app.database.manager.UserEntityManager;
import org.company.app.util.BaseSubForm;

import javax.swing.*;
import java.sql.SQLException;

public class AddUserForm extends BaseSubForm<TableForm> {
    UserEntityManager userEntityManager = Application.getInstance().getUserEntityManager();

    private JPanel mainPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JTextField jobField;
    private JTextField ageField;
    private JButton backButton;
    private JButton addButton;

    public AddUserForm(TableForm mainForm)
    {
        super(mainForm);
        setContentPane(mainPanel);

        initButtons();

        setVisible(true);
    }

    private void initButtons()
    {
        backButton.addActionListener(e -> {
            closeSubForm();
        });



        addButton.addActionListener(e -> {

            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            int age = Integer.parseInt(ageField.getText());
            String job = jobField.getText();

            UserEntity user = new UserEntity(login, password, age, job);
            try {


                userEntityManager.add(user);
                mainForm.getModel().getValues().add(user);
                mainForm.getModel().fireTableDataChanged();
                closeSubForm();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    @Override
    public int getFormWidth() {
        return 500;
    }

    @Override
    public int getFormHeight() {
        return 250;
    }
}
