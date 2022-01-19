package main.windows.auth;

import main.service.ServiceManager;
import main.service.dataAccess.database.FileDatabaseService;
import main.windows.BaseWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupWindow extends BaseWindow implements ActionListener {
    private JLabel usernameLabel;
    private JTextField usernameField;

    private JLabel phoneLabel;
    private JTextField phoneField;

    private JLabel nameLabel;
    private JTextField nameField;

    private JLabel emailLabel;
    private JTextField emailField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton signupButton;

    public SignupWindow() {
        super(new GridLayout(0, 2, 12, 12));
        setTitle("مدیریت رمزعبور - ایجاد کاربر");

        this.initComponents();
        this.setSize(420, 500);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public SignupWindow(boolean exitOnClose) {
        super(new GridLayout(0, 2, 12, 12));
        setTitle("مدیریت رمزعبور - ایجاد کاربر");

        this.initComponents();
        this.setSize(420, 500);
        if (exitOnClose) {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        setLocationRelativeTo(null);
    }

    /**
     * Setup Components
     */
    private void initComponents() {
        // User name
        this.usernameLabel = new JLabel("نام کاربری");
        this.usernameLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.usernameField = new JTextField();
        this.addComponent(this.usernameLabel);
        this.addComponent(this.usernameField);

        // Password
        this.passwordField = new JPasswordField();
        this.passwordLabel = new JLabel("رمزعبور");
        this.passwordLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.addComponent(this.passwordLabel);
        this.addComponent(this.passwordField);

        // Name
        this.nameLabel = new JLabel("نام");
        this.nameLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.nameField = new JTextField();
        this.nameField.setHorizontalAlignment(SwingConstants.RIGHT);
        this.addComponent(this.nameLabel);
        this.addComponent(this.nameField);


        // Email
        this.emailLabel = new JLabel("ایمیل");
        this.emailLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.emailField = new JTextField();
        this.addComponent(this.emailLabel);
        this.addComponent(this.emailField);

        // Phone
        this.phoneLabel = new JLabel("شماره تماس");
        this.phoneLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.phoneField = new JTextField();
        this.addComponent(this.phoneLabel);
        this.addComponent(this.phoneField);


        // Signup Button
        this.signupButton = new JButton("ثبت‌نام");
        this.signupButton.addActionListener(this);
        this.signupButton.setSize(200, 32);
        this.addComponent(new JPanel());
        this.addComponent(this.signupButton);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FileDatabaseService databaseService = ServiceManager.getService(FileDatabaseService.class);
            if (this.usernameField.getText().isEmpty() || new String(this.passwordField.getPassword()).isEmpty() || this.nameField.getText().isEmpty() || this.phoneField.getText().isEmpty() || this.emailField.getText().isEmpty()) {
                JOptionPane.showInternalMessageDialog(null, "تمامی فیلد‌ها اجباری است");
                return;
            }

            boolean userCreateResult = databaseService.createUser(this.usernameField.getText(),
                    new String(this.passwordField.getPassword()),
                    this.nameField.getText(),
                    this.phoneField.getText(),
                    this.emailField.getText());
            if (userCreateResult) {
                setDefaultCloseOperation(HIDE_ON_CLOSE);
                this.setVisible(false);
                LoginWindow frame = new LoginWindow();
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "خطا در ایجاد کاربر جدید، لطفا مجدد تلاش کنید");
                throw new Exception("Create User Failed");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
