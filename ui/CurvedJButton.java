package ui;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.geom.RoundRectangle2D;

	public class CurvedJButton extends JButton {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int arcWidth;
	    private int arcHeight;

	    public CurvedJButton(String text, Icon icon, int arcWidth, int arcHeight) {
	        super(text, icon);
	        this.arcWidth = arcWidth;
	        this.arcHeight = arcHeight;
	        setOpaque(false);
	        setContentAreaFilled(false);
	        setFocusPainted(false);
	        setBorderPainted(false);
	        setFont(new Font("Arial", Font.BOLD, 14));
	        
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g.create();
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        int width = getWidth();
	        int height = getHeight();

	        if (getModel().isPressed()) {
	            g2d.setColor(getBackground().darker());
	        } else if (getModel().isRollover()) {
	            g2d.setColor(getBackground().brighter());
	        } else {
	            g2d.setColor(getBackground());
	        }
	        g2d.fill(new RoundRectangle2D.Double(0, 0, width, height, arcWidth, arcHeight));

	        super.paintComponent(g2d);

	        g2d.dispose();
	    }
	}

