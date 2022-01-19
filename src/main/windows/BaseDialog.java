package main.windows;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BaseDialog extends JDialog {

    private final JPanel mainPanel;

    public BaseDialog(JFrame owner, LayoutManager layout) {
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
        this.mainPanel = new JPanel(layout);
        this.mainPanel.setBorder(new EmptyBorder(16, 16, 16, 16));
        this.mainPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.add(this.mainPanel);
    }

    protected void addComponent(JComponent... components) {
        for (JComponent component : components) {
            this.mainPanel.add(component);
        }
    }
}
