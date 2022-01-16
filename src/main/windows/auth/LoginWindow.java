package main.windows.auth;


import main.exceptions.UserNotFoundException;
import main.records.user.UserDetail;
import main.service.AuthenticationService;
import main.service.ServiceManager;
import main.windows.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener {
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JPanel mainPanel;
    private JButton loginButton;


    /**
     * Setup Window
     */
    public LoginWindow() {
        try {
            String os = System.getProperty("os.name");
            if (os.toLowerCase().contains("windows")) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } else if (os.toLowerCase().contains("linux")) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            } else {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            System.out.println("Error setting the LAF..." + e);
        }
        setLocationRelativeTo(null);
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        // Initialize Frame
        setTitle("Password Manager Login");
        this.mainPanel = new JPanel(new GridLayout(3, 1, 12, 12));
        this.mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.mainPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        add(this.mainPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initComponents();
        this.setSize(420, 200);
        setLocationRelativeTo(null);

    }

    /**
     * Setup Components
     */
    private void initComponents() {
        // Initialize Components
        this.usernameLabel = new JLabel("نام کاربری");
        this.usernameLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.usernameField = new JTextField();
        this.passwordField = new JPasswordField();
        this.passwordLabel = new JLabel("رمزعبور");
        this.passwordField.setSize(200, 0);
        this.passwordLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        this.loginButton = new JButton("ورود");
        this.loginButton.addActionListener(this);
        this.loginButton.setSize(200, 32);
        this.addComponent(this.usernameLabel);
        this.addComponent(this.usernameField);

        this.addComponent(this.passwordLabel);
        this.addComponent(this.passwordField);

        this.addComponent(new JPanel());
        this.addComponent(this.loginButton);
    }

    /**
     * Add Component To frame content pane
     *
     * @param component - component to add
     */
    private void addComponent(JComponent component) {
        this.mainPanel.add(component);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) throws UserNotFoundException {
        try {
            AuthenticationService service = ServiceManager.getService(AuthenticationService.class);
            UserDetail loginResult;
            try{
                loginResult = service.login(this.usernameField.getText(), new String(this.passwordField.getPassword()));
            }catch(UserNotFoundException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,"کاربری با این نام کاربر وجود ندارد");
                return;
            }
            if (loginResult != null) {
                MainWindow mainWindow = new MainWindow();
                setDefaultCloseOperation(HIDE_ON_CLOSE);
                setVisible(false);
                mainWindow.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(this,"رمزعبور اشتباه است");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
