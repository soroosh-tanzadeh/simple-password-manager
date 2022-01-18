package main.components;

import main.records.secure.PasswordStore;

import javax.swing.*;
import java.awt.*;

public class PasswordStoreRenderer extends JPanel implements ListCellRenderer<PasswordStore> {

    private final JLabel label;
    public PasswordStoreRenderer(JFrame frame) {
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.LEFT);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(label);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends PasswordStore> list, PasswordStore value, int index, boolean isSelected, boolean cellHasFocus) {
        label.setText(value.getTitle());
        label.setForeground(Color.BLACK);
        Color background;
        if (isSelected) {
            background = Color.CYAN;
        } else {
            background = Color.WHITE;
        }
        setBackground(background);
        return this;
    }
}
