package main.windows;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BaseWindow extends JFrame {

    protected JPanel mainPanel;

    public BaseWindow() {
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
        this.mainPanel = new JPanel(new GridLayout(3, 1, 12, 12));
        this.mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.mainPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(this.mainPanel, BorderLayout.CENTER);
    }

    public BaseWindow(GridLayout layout) {
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
        this.mainPanel = new JPanel(layout);
        this.mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.mainPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(this.mainPanel, BorderLayout.CENTER);
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
