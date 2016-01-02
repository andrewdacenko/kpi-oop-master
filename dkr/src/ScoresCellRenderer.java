import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ScoresCellRenderer extends DefaultTableCellRenderer {
    public static final Dimension dimension = new Dimension(400, 100);
    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        component.setBackground(Color.BLUE);
        return component;
    }
}
