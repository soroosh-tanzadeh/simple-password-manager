package main.windows;

import main.windows.auth.SignupWindow;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends BaseWindow {
    public MainWindow() {
        super(new GridLayout(0, 2, 12, 24));
        setSize(400, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setupMenu();
    }

    /**
     * Setup MenuBar
     */
    public void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu usersMenu = new JMenu("کاربران");
        JMenuItem createUserItem = new JMenuItem("ایجاد کاربر");
        createUserItem.addActionListener(e -> {
            SignupWindow frame = new SignupWindow(false);
            frame.setVisible(true);
        });
        usersMenu.add(createUserItem);
        menuBar.add(usersMenu);
        setJMenuBar(menuBar);
    }

}
