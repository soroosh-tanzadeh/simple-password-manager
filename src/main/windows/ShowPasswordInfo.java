package main.windows;

import main.records.secure.PasswordStore;

import javax.swing.*;
import java.awt.*;

public class ShowPasswordInfo extends BaseDialog {

    private PasswordStore password;

    private JTextField usernameField;
    private JLabel usernameLabel;

    private JPasswordField passwordField;
    private JLabel passwordLabel;

    private JTextField websiteField;
    private JLabel websiteLabel;

    private JTextArea descriptionField;
    private JLabel descriptionLabel;

    public ShowPasswordInfo(JFrame owner, PasswordStore password) {
        super(owner, new GridLayout(0, 2, 12, 12));
        this.password = password;
        setSize(700, 250);
        setTitle(password.getTitle());
        setLocationRelativeTo(null);
        this.setupComponents();
    }

    public void setupComponents() {
        this.usernameField = new JTextField(this.password.getUsername());
        this.usernameField.setEditable(false);
        this.usernameLabel = new JLabel("نام کاربری");
        this.usernameLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.addComponent(this.usernameLabel, this.usernameField);


        this.passwordField = new JPasswordField(this.password.getPassword());
        this.passwordField.setEditable(false);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel,BoxLayout.X_AXIS));

        JToggleButton showPasswordButton = new JToggleButton("نمایش");
        JButton copyPasswordButton = new JButton("کپی");

        showPasswordButton.addActionListener(e -> {
           if(showPasswordButton.isSelected()){
               this.passwordField.setEchoChar((char)0);
               showPasswordButton.setText("عدم نمایش");
           }else{
               this.passwordField.setEchoChar('*');
               showPasswordButton.setText("نمایش");
           }
        });
        copyPasswordButton.addActionListener(e -> Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new java.awt.datatransfer.StringSelection(this.password.getPassword()), null));
        passwordPanel.add(this.passwordField);
        passwordPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        passwordPanel.add(showPasswordButton);
        passwordPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        passwordPanel.add(copyPasswordButton);

        this.passwordLabel = new JLabel("رمزعبور");
        this.passwordLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.addComponent(this.passwordLabel, passwordPanel);

        this.websiteField = new JTextField(this.password.getWebsite());
        this.websiteField.setEditable(false);
        this.websiteLabel = new JLabel("وب‌سایت");
        this.websiteLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.addComponent(this.websiteLabel, this.websiteField);

        this.descriptionField = new JTextArea(this.password.getDescription());
        this.descriptionField.setEditable(false);
        this.descriptionField.setRows(4);
        this.descriptionLabel = new JLabel("توضیحات");
        this.descriptionLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.addComponent(this.descriptionLabel, this.descriptionField);

    }

}
