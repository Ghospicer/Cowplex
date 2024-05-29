package ui;
import javax.swing.*;
import java.awt.*;

public class CustomListCellRenderer extends DefaultListCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);
    private static final Font DEFAULT_FONT = UIManager.getFont("List.font").deriveFont(20f);

    private ImageIcon[] icons;

    public CustomListCellRenderer(ImageIcon[] icons) {
        this.icons = icons;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        renderer.setBackground(BACKGROUND_COLOR);
        Font font = DEFAULT_FONT;
        renderer.setFont(font);

        // iconlar
        if (index >= 0 && index < icons.length) {
            renderer.setIcon(icons[index]);
            renderer.setHorizontalTextPosition(JLabel.RIGHT);
        }

        return renderer;
    }
}
