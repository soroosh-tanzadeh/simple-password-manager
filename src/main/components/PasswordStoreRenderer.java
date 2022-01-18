package main.components;

import main.records.secure.PasswordStore;

import javax.swing.*;
import java.awt.*;

public class PasswordStoreRenderer extends JPanel implements ListCellRenderer<PasswordStore> {
    private JLabel label;

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public PasswordStoreRenderer() {
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.LEFT);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(label);
    }

    /**
     * Return a component that has been configured to display the specified
     * value. That component's <code>paint</code> method is then called to
     * "render" the cell.  If it is necessary to compute the dimensions
     * of a list because the list cells do not have a fixed size, this method
     * is called to generate a component on which <code>getPreferredSize</code>
     * can be invoked.
     *
     * @param list         The JList we're painting.
     * @param value        The value returned by list.getModel().getElementAt(index).
     * @param index        The cells index.
     * @param isSelected   True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return A component whose paint() method will render the specified value.
     * @see JList
     * @see ListSelectionModel
     * @see ListModel
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends PasswordStore> list, PasswordStore value, int index, boolean isSelected, boolean cellHasFocus) {
        label.setText(value.getTitle());
        label.setForeground(Color.BLACK);

        Color background;
        // check if this cell represents the current DnD drop location
        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            background = Color.BLUE;

            // check if this cell is selected
        } else if (isSelected) {
            background = Color.CYAN;
            // unselected, and not the DnD drop location
        } else {
            background = Color.WHITE;
        }
        ;

        setBackground(background);

        return this;
    }
}
