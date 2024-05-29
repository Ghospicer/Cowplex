package ui;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CowplexUI extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static List<User> users = new ArrayList<>();
	User currentUser;
    Date currentDate = new Date();
	static List<Farm> farms=new ArrayList<>();
	Farm selectedFarm;
	private JTextField userNameIn;
    private JPasswordField sifreIn;
    ImageIcon originalLogo = new ImageIcon("src/imageIcons/logo.png");
    Image backgroundImage;
    Color topRightBackgroundColor = new Color(250, 250, 250);

    public CowplexUI() {
    	
    	try {
			backgroundImage= ImageIO.read(new File("src/imageIcons/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	getContentPane().setLayout(new BorderLayout());
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cowplex Application");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setIconImage( originalLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
    	this.loggedOut();

    }
    
    
    public void loggedIn() {
    	getContentPane().removeAll();
    	
    	//sağ alt
        JPanel mainContentPanel = new CiftlikPanel(this) {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Ensures that the panel is properly rendered
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, this);
                }
            }
        };
        
        Color backGroundColor = new Color(252, 252, 252);
       
        //sol üst logo
        Image scaledLogoImage = originalLogo.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(scaledLogoImage);
        JLabel logoLabel = new JLabel(scaledLogo);
        JPanel logoPanel = new JPanel();
        logoPanel.add(logoLabel);
        logoPanel.setBackground(backGroundColor);
        logoPanel.setPreferredSize(new Dimension(300, 100));


        //sol alt menu
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(backGroundColor);
        menuPanel.setPreferredSize(new Dimension(300, 100));
        menuPanel.setLayout(new GridLayout(0, 1));
        
        //menu text
        String menuList[] = {"Çiftlik", "Süt", "Veteriner", "Hayvan Yönetimi",  "Çiftlik Ayarları","Kaydet"};
        JList<String> menuSelection = new JList<>(menuList);
        menuPanel.add(menuSelection);
        Color menuDefaultColor = new Color(126, 131, 202);
        menuSelection.setForeground(menuDefaultColor);
        
        String[] imagePaths = {
                "src/imageIcons/slack.png",
                "src/imageIcons/droplet.png",
                "src/imageIcons/stetoskop.png",
                "src/imageIcons/idCard.png",
                "src/imageIcons/settings.png",
                "src/imageIcons/save.png"
                
            };
        ImageIcon[] icons = new ImageIcon[imagePaths.length];
        for (int i = 0; i < imagePaths.length; i++) {
            icons[i] = createScaledImageIcon(imagePaths[i], 32, 32);
        }
            
            menuSelection.setCellRenderer(new CustomListCellRenderer2(icons));
            
        //menude item seçince öbür paneli değiştirme
        menuSelection.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedItem = menuSelection.getSelectedIndex();
                    ((CiftlikPanel) mainContentPanel).setSelectedIndex(selectedItem);
                    ((CiftlikPanel) mainContentPanel).updateMainContent();
                    mainContentPanel.revalidate();
                    mainContentPanel.repaint();
                }
            }
        });


     // Sağ üst kullanıcı farm bilgileri
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(topRightBackgroundColor);
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setPreferredSize(new Dimension(1000, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Logged farm area
        JLabel loggedFarmArea = new JLabel(selectedFarm.getName());
        Font font = new Font("Arial", Font.BOLD, 18);
        loggedFarmArea.setFont(font);
        loggedFarmArea.setForeground(menuDefaultColor);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 1.0;
        infoPanel.add(loggedFarmArea, gbc);

        // User info
        JLabel userInfo = new JLabel(currentUser.getName());
        userInfo.setFont(font);
        userInfo.setForeground(menuDefaultColor);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        infoPanel.add(userInfo, gbc);

        // Log out
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem logoutItem = new JMenuItem("Logout");
        popupMenu.add(logoutItem);

        userInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Left click
                    popupMenu.show(userInfo, e.getX(), e.getY());
                }
            }
        });

        logoutItem.addActionListener(e -> {
            currentUser = null;
            loggedOut();
        });

        // Farm popup menu
        JPopupMenu farmPopupMenu = new JPopupMenu();
        for (Farm farm : farms) {
            JMenuItem farmMenuItem = new JMenuItem(farm.getName());
            farmPopupMenu.add(farmMenuItem);

            farmMenuItem.addActionListener(e -> {
                selectedFarm = farm;
                loggedFarmArea.setText(selectedFarm.getName());
                revalidate();
                repaint();
            });
        }

        loggedFarmArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                farmPopupMenu.show(loggedFarmArea, 0, loggedFarmArea.getHeight());
            }
        });


        //panelleri implement etme
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(logoPanel, BorderLayout.WEST);
        topPanel.add(infoPanel, BorderLayout.CENTER);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(menuPanel, BorderLayout.WEST);
        bottomPanel.add(mainContentPanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    public void loggedOut() {
    	
    
    getContentPane().removeAll();
    
    // Background panel
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
            GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(114, 128, 250), width, 0, new Color(114, 215, 250));
            g2d.setPaint(gradientPaint);
            g2d.fillRect(0, 0, width, height);
            g2d.dispose();
        }
    };
    loginBackground.setLayout(new GridBagLayout());
    add(loginBackground, BorderLayout.CENTER);

    // Login input panel
    JPanel loginComponents = new CurvedPanel(40, 40, Color.WHITE);
    loginComponents.setPreferredSize(new Dimension(290, 300));
    loginComponents.setLayout(null);

    // Add login input fields
    Image scaledLogoImage = originalLogo.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
    ImageIcon scaledLogo = new ImageIcon(scaledLogoImage);

    userNameIn = new JTextField();
    userNameIn.setBounds(74, 105, 145, 19);
    loginComponents.add(userNameIn);

    sifreIn = new JPasswordField();
    sifreIn.setBounds(74, 158, 145, 19);
    loginComponents.add(sifreIn);

    JLabel userNameLabel = new JLabel("Kullanıcı Adı");
    userNameLabel.setBounds(74, 76, 100, 19);
    loginComponents.add(userNameLabel);

    JLabel sifreLabel = new JLabel("Şifre");
    sifreLabel.setBounds(74, 134, 63, 19);
    loginComponents.add(sifreLabel);

    CurvedJButton login = new CurvedJButton("Giriş Yap", null, 20, 20);
    login.setBounds(86, 198, 116, 37);
    loginComponents.add(login);

    JLabel logoLabel = new JLabel(scaledLogo);
    logoLabel.setBounds(0, 0, 287, 72);
    loginComponents.add(logoLabel);

    // Mouse listener for the login button
    login.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            for (User user : users) {
                if (user.loginInfoChecker(userNameIn.getText(), sifreIn.getPassword())) {
                    currentUser = user;
                    Arrays.fill(sifreIn.getPassword(), ' ');
                    loggedIn();
                    return;
                }
            }
            JOptionPane.showMessageDialog(CowplexUI.this, "Hatalı e-posta veya şifre.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Add loginComponents to loginBackground
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    loginBackground.add(loginComponents, gbc);

    revalidate();
    repaint();
}

    public static ImageIcon createScaledImageIcon(String path, int width, int height) {
        try {
            File imgFile = new File(path);
            URL imgURL = imgFile.toURI().toURL();
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImg);
            } else {
                System.err.println("Couldn't find file: " + imgFile.getAbsolutePath());
                return null;
            }
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + path);
            return null;
        }
    }
    
    public static void main(String[] args) {
    	
    	CowplexUI frame = new CowplexUI();
         
    	fileReader.readEverythingFromFile("datas");
        frame.selectedFarm=CowplexUI.farms.get(0);
    	SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
            
        });
            
        
    }
    }
class CustomListCellRenderer2 extends DefaultListCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ImageIcon[] icons;

    public CustomListCellRenderer2(ImageIcon[] icons) {
        this.icons = icons;
    }

    @Override
    public Component getListCellRendererComponent(
        JList<?> list, Object value, int index,
        boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
            list, value, index, isSelected, cellHasFocus);

        if (index >= 0 && index < icons.length && icons[index] != null) {
            label.setIcon(icons[index]);
        }

        return label;
    }
}
