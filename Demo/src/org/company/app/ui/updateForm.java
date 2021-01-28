package org.company.app.ui;

import org.company.app.util.BaseForm;
import org.company.app.util.BaseSubForm;

import javax.swing.*;

public class updateForm extends BaseSubForm {
    private JPanel mainPanel;
    private JTextField loginField;
    private JTextField passwordFiled;
    private JTextField ageField;
    private JTextField jobField;
    private JButton backButton;
    private JButton saveButton;
    private JTextField idField;

    public updateForm(BaseForm mainForm) {
        super(mainForm);
    }


    @Override
    public int getFormWidth() {
        return 500;
    }

    @Override
    public int getFormHeight() {
        return 500;
    }
}
