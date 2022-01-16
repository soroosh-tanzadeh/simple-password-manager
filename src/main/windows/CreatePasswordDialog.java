package main.windows;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreatePasswordDialog extends JDialog {

    private JPanel mainPanel;

    private JTextField titleField;
    private JLabel titleLabel;

    public CreatePasswordDialog(JFrame owner) {
        super(owner);
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
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/main/images/icon.png")));
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.mainPanel = new JPanel(new GridLayout(0, 2, 12, 12));
        this.mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.mainPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(this.mainPanel, BorderLayout.CENTER);
        this.setupComponents();
    }


    public void setupComponents(){

    }

    /**
     * Add Component To frame content pane
     *
     * @param component
     */
    protected void addComponent(JComponent component) {
        this.mainPanel.add(component);
    }
}
