package org.company.app.util;

import javax.swing.*;
import java.awt.*;

public abstract class BaseForm extends JFrame {
    private static String baseApplicationTitle = "Application";
    public  BaseForm(){

        setTitle(baseApplicationTitle);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(getFormWidth(), getFormHeight()));
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width/2 - getFormWidth()/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2 - getFormHeight()/2
        );

    }

    public abstract int getFormWidth();

    public abstract int getFormHeight();

    public static String getBaseApplicationTitle() {
        return baseApplicationTitle;
    }

    public static void setBaseApplicationTitle(String baseApplicationTitle) {
        BaseForm.baseApplicationTitle = baseApplicationTitle;
    }
}
