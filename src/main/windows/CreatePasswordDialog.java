package main.windows;

import main.service.ServiceManager;
import main.service.dataAccess.UserDataAccessService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;

public class CreatePasswordDialog extends JDialog implements ActionListener {

    private final JPanel mainPanel;
    ArrayList<ActionListener> actionListeners = new ArrayList<>();
    private JTextField titleField;
    private JLabel titleLabel;

    private JTextField usernameField;
    private JLabel usernameLabel;

    private JPasswordField passwordField;
    private JLabel passwordLabel;

    private JTextField websiteField;
    private JLabel websiteLabel;

    private JTextArea descriptionField;
    private JLabel descriptionLabel;

    public CreatePasswordDialog(JFrame owner) {
        super(owner, true);
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
        setMinimumSize(new Dimension(400, 350));
        setResizable(false);
        setTitle("رمزعبور جدید");
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/main/images/icon.png")));
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.mainPanel = new JPanel(new GridLayout(0, 2, 12, 12));
        this.mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.mainPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(this.mainPanel, BorderLayout.CENTER);
        this.setupComponents();
    }


    public void setupComponents() {
        this.titleField = new JTextField();
        this.titleLabel = new JLabel("عنوان");
        this.titleField.setSize(0, 10);
        this.titleLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.addComponent(this.titleLabel, (JComponent) new JPanel().add(this.titleField));

        this.usernameField = new JTextField();
        this.usernameLabel = new JLabel("نام کاربری");
        this.usernameLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.addComponent(this.usernameLabel, this.usernameField);

        this.passwordField = new JPasswordField();
        this.passwordLabel = new JLabel("رمزعبور");
        this.passwordLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.addComponent(this.passwordLabel, this.passwordField);

        this.websiteField = new JTextField();
        this.websiteLabel = new JLabel("وب‌سایت");
        this.websiteLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.addComponent(this.websiteLabel, this.websiteField);

        this.descriptionField = new JTextArea();
        this.descriptionField.setRows(4);
        this.descriptionLabel = new JLabel("توضیحات");
        this.descriptionLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.addComponent(this.descriptionLabel, this.descriptionField);

        JButton okButton = new JButton("ثبت");
        okButton.addActionListener(this);
        this.addComponent(new JPanel(), okButton);

    }

    public void addActionListener(ActionListener listener) {
        this.actionListeners.add(listener);
    }

    /**
     * Add Component To frame content pane
     *
     * @param components list of components to add
     */
    protected void addComponent(JComponent... components) {
        for (JComponent component : components) {
            this.mainPanel.add(component);
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.titleField.getText().isEmpty() || this.websiteField.getText().isEmpty() || this.usernameField.getText().isEmpty() || new String(this.passwordField.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "تمامی فیلد‌ها بجز توضیحات اجباری است");
            return;
        }

        // Save Password
        try {
            UserDataAccessService dataAccessService = ServiceManager.getService(UserDataAccessService.class);
            dataAccessService.addPassword(this.titleField.getText(),
                    this.usernameField.getText(),
                    new String(this.passwordField.getPassword()),
                    this.websiteField.getText(),
                    this.descriptionField.getText());
            this.actionListeners.forEach(actionListener -> actionListener.actionPerformed(new ActionEvent(this, UUID.randomUUID().hashCode(), "password-add")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
