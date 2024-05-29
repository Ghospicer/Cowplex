package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginScreen extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField eposta;
    ImageIcon originalLogo = new ImageIcon("src/imageIcons/logo.png");
    private JPasswordField sifre;

    public LoginScreen() {
        setLayout(new BorderLayout());

        JPanel loginBackground = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();

                int width = getWidth();
                int height = getHeight();

                GradientPaint gradientPaint = new GradientPaint(0, 0, new Color( 114, 128, 250 ), width, 0, new Color( 114, 215, 250));

                g2d.setPaint(gradientPaint);

                g2d.fillRect(0, 0, width, height);

                g2d.dispose();
            }
        };

        loginBackground.setLayout(null);
        add(loginBackground, BorderLayout.CENTER);
        
        JPanel loginComponents = new CurvedPanel(40,40,Color.WHITE);
        loginComponents.setBounds(284, 114, 287, 245);
        loginBackground.add(loginComponents, BorderLayout.CENTER);
        loginComponents.setLayout(null);
        
        Image scaledLogoImage = originalLogo.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(scaledLogoImage);
        
        eposta = new JTextField();
        eposta.setBounds(74, 105, 145, 19);
        loginComponents.add(eposta);

        JLabel epostaLabel = new JLabel("Şifre");
        epostaLabel.setBounds(74, 134, 63, 19);
        loginComponents.add(epostaLabel);

        JLabel sifreLabel = new JLabel("E-Posta");
        sifreLabel.setBounds(74, 76, 44, 19);
        loginComponents.add(sifreLabel);

        CurvedJButton login = new CurvedJButton("Giriş Yap", null, 20, 20);
        login.setBounds(86, 198, 116, 37);
        loginComponents.add(login);
        
        JLabel logoLabel = new JLabel(scaledLogo);
        logoLabel.setBounds(0, 0, 287, 72);
        loginComponents.add(logoLabel);
        
        sifre = new JPasswordField();
        sifre.setBounds(74, 158, 145, 19);
        loginComponents.add(sifre);

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login Screen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new LoginScreen());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
