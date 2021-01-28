package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.UserEntity;
import org.company.app.database.manager.UserEntityManager;
import org.company.app.util.BaseForm;
import org.company.app.util.CustomTableModel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class TableForm extends BaseForm {

    UserEntityManager userEntityManager = Application.getInstance().getUserEntityManager();

    private CustomTableModel<UserEntity> model;

    private JPanel mainPanel;
    private JTable table;
    private JButton addButton;

    public TableForm()
    {
        setContentPane(mainPanel);

        initTable();
        initButtons();
        setVisible(true);
    }

    private void initTable()
    {
        table.getTableHeader().setReorderingAllowed(false);



        try {
            model = (new CustomTableModel<UserEntity>(UserEntity.class, userEntityManager.getAll()));
            table.setModel(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        table.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {


                if(e.getKeyCode() == KeyEvent.VK_DELETE && table.getSelectedRow() != -1){
                    try {
                        userEntityManager.delete(model.getValues().get(table.getSelectedRow()));
                        model.getValues().remove(table.getSelectedRow());
                        model.fireTableDataChanged();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if(row != -1 && e.getClickCount() == 2)
                {

                }
            }
        });
    }

    private void initButtons()
    {
        addButton.addActionListener(e -> {
            new AddUserForm(this);
        });
    }

    @Override
    public int getFormWidth() {
        return 700;
    }

    @Override
    public int getFormHeight() {
        return 500;
    }

    public CustomTableModel<UserEntity> getModel() {
        return model;
    }
}
