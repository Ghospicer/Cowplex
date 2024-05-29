package ui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MenuButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color defaultColor = new Color( 126, 131, 202);
	Color hoverColor = new Color( 115, 119, 181);
	
	public MenuButton (String text) {
		setText(text);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setForeground(defaultColor);
        setHorizontalAlignment(SwingConstants.LEFT); 
        setPreferredSize(new Dimension(300, 30));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(defaultColor);
            }
        });
	}
	
}
