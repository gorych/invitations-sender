package by.gsu.forms;

import javax.swing.*;

public abstract class AbstractForm {

    public abstract JPanel getMainPanel();

    public abstract String getTitle();

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract int getDefaultCloseOperation();

    public abstract void onCloseAction();

}