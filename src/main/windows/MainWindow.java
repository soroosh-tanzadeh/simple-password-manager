package main.windows;

import main.components.PasswordStoreRenderer;
import main.records.secure.PasswordStore;
import main.service.AuthenticationService;
import main.windows.auth.SignupWindow;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends BaseWindow {

    private JList<PasswordStore> passwordStores;
    private JTextField searchField;

    public MainWindow() {
        super(new GridLayout(0, 1, 12, 24));
        setSize(400, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setupMenu();
        this.setupComponents();
    }

    private void setupComponents() {
        this.passwordStores = new JList<>();
        DefaultListModel<PasswordStore> passwordStoreListModel = new DefaultListModel<>();
        passwordStoreListModel.addAll(AuthenticationService.currentUser.getPasswordStores());
        this.passwordStores.setCellRenderer(new PasswordStoreRenderer());
        this.passwordStores.setModel(passwordStoreListModel);
        addComponent(this.passwordStores);
    }

    /**
     * Setup MenuBar
     */
    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu usersMenu = new JMenu("کاربران");
        JMenuItem createUserItem = new JMenuItem("ایجاد کاربر");
        createUserItem.addActionListener(e -> {
            SignupWindow frame = new SignupWindow(false);
            frame.setVisible(true);
        });
        usersMenu.add(createUserItem);


        JMenu passwordsMenu = new JMenu("رمزعبوز");
        JMenuItem createPasswordItem = new JMenuItem("رمزعبور جدید");
        createPasswordItem.addActionListener(e -> {

        });

        menuBar.add(usersMenu);
        setJMenuBar(menuBar);
    }

}
