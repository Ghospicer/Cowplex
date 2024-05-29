package ui;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CiftlikPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JMenuItem lastClickedMenuItem;
	static Color mainContentColor = new Color(0,0,0,0);
	Color menuItemBackgroundColor = new Color(108, 218, 254);
	int selectedIndex = 0;
	JPanel topPanel;
	JTextArea titleArea;
	JMenuBar menuBar;
	miniPanel contentPanel;
	CowplexUI parentPanel;
	
	public CiftlikPanel(CowplexUI parentPanel) {
		
		this.parentPanel=parentPanel;
		setLayout(new BorderLayout());
        setBackground(new Color(231, 232, 238));
		
	}
	
	public void updateMainContent() {
        removeAll(); 

        switch (selectedIndex) {
        //genel
            case 0:
            	topPanel = new JPanel();
            	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
            	topPanel.setBackground(new Color(0, 0, 0, 0));
            	add(topPanel, BorderLayout.NORTH);

            	titleArea = new JTextArea("Çiftlik Yönetim Konsolu");
            	titleArea.setEditable(false);
            	titleArea.setFont(new Font("Arial", Font.BOLD, 16));
            	titleArea.setBackground(new Color(0, 0, 0, 0));
            	titleArea.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 40));
            	topPanel.add(titleArea);

            	menuBar = new JMenuBar();
            	menuBar.setBackground(mainContentColor);
            	menuBar.setPreferredSize(new Dimension(menuBar.getPreferredSize().width, 75)); 
            	menuBar.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
            	topPanel.add(menuBar);

        		contentPanel = new miniPanel(this.parentPanel);
        		add(contentPanel);
        		
        		String[] ciftlikMenuItems = {"Genel", "Grafikler", "Notlar", "Hastalık"};

        		for (int i = 0; i < ciftlikMenuItems.length; i++) {
        		    final int index = i;
        		    JMenuItem menuItem = new JMenuItem(ciftlikMenuItems[i]);
        		    menuItem.setForeground(new Color(255, 255, 255));
        		    menuItem.setFont(new Font("Arial", Font.BOLD, 13));
        		    menuItem.setBackground(menuItemBackgroundColor);
        		    menuBar.add(menuItem);

        		    menuItem.addMouseListener(new MouseAdapter() {
        		        @Override
        		        public void mouseClicked(MouseEvent e) {
        		            if (lastClickedMenuItem != null) {
        		                lastClickedMenuItem.setForeground(Color.WHITE);
        		            }
        		            menuItem.setForeground(Color.BLACK);
        		            lastClickedMenuItem = menuItem;
        		            miniPanel.setIndex(index);
        		            contentPanel.updateContentCiftlik();
        		            revalidate();
        		            repaint();
        		        }
        		    });
        		}

                break;
                //süt yem
            case 1:
            	
            	topPanel = new JPanel();
            	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
            	topPanel.setBackground(new Color(0, 0, 0, 0));
            	add(topPanel, BorderLayout.NORTH);

            	titleArea = new JTextArea("Süt-Yem Takip Konsolu");
            	titleArea.setEditable(false);
            	titleArea.setFont(new Font("Arial", Font.BOLD, 16));
            	titleArea.setBackground(new Color(0,0,0,0));
            	titleArea.setOpaque(true);
            	titleArea.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 40));
            	topPanel.add(titleArea);
            	
            	menuBar = new JMenuBar();
            	menuBar.setBackground(mainContentColor);
            	menuBar.setPreferredSize(new Dimension(menuBar.getPreferredSize().width, 75)); 
            	menuBar.setBorder(BorderFactory.createEmptyBorder(10, 200, 10, 200));
            	topPanel.add(menuBar);
            	
            	contentPanel = new miniPanel(this.parentPanel );
        		add(contentPanel);
        		
        		String[] sutMenuItems = {"Toplam Sağılan Süt Miktarı", "Toplam Yenilen Yem Miktarı", "Yem/Süt Oranı"};

        		for (int i = 0; i < sutMenuItems.length; i++) {
        		    final int index = i+4;
        		    JMenuItem menuItem = new JMenuItem(sutMenuItems[i]);
        		    menuItem.setForeground(new Color(255, 255, 255));
        		    menuItem.setFont(new Font("Arial", Font.BOLD, 13));
        		    menuItem.setBackground(menuItemBackgroundColor);
        		    menuBar.add(menuItem);

        		    menuItem.addMouseListener(new MouseAdapter() {
        		        @Override
        		        public void mouseClicked(MouseEvent e) {
        		            if (lastClickedMenuItem != null) {
        		                lastClickedMenuItem.setForeground(Color.WHITE);
        		            }
        		            menuItem.setForeground(Color.BLACK);
        		            lastClickedMenuItem = menuItem;
        		            miniPanel.setIndex(index);
        		            contentPanel.updateContentSutYem();
        		            revalidate();
        		            repaint();
        		        }
        		    });
        		}
            	
                break;
            //vet
            case 2:
            	topPanel = new JPanel();
            	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
            	topPanel.setBackground(new Color(0, 0, 0, 0));
            	add(topPanel, BorderLayout.NORTH);

            	titleArea = new JTextArea("Veteriner Ekranı");
            	titleArea.setEditable(false);
            	titleArea.setFont(new Font("Arial", Font.BOLD, 16));
            	titleArea.setBackground(new Color(0,0,0,0));
            	titleArea.setOpaque(true);
            	titleArea.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 40));
            	topPanel.add(titleArea);
            	
            	contentPanel = new miniPanel(this.parentPanel);
        		add(contentPanel);
            	miniPanel.setIndex(7);
	            contentPanel.updateContentVet();
	            revalidate();
	            repaint();

            	
            	break;
            	//hayvan yönetimi
            case 3:
            	topPanel = new JPanel();
            	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
            	topPanel.setBackground(new Color(0, 0, 0, 0));
            	add(topPanel, BorderLayout.NORTH);

            	titleArea = new JTextArea("Hayvan Yönetimi");
            	titleArea.setEditable(false);
            	titleArea.setFont(new Font("Arial", Font.BOLD, 16));
            	titleArea.setBackground(new Color(0,0,0,0));
            	titleArea.setOpaque(true);
            	titleArea.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 40));
            	topPanel.add(titleArea);
            	
            	contentPanel = new miniPanel(this.parentPanel);
        		add(contentPanel);
	            contentPanel.updateContentAnimalManagment();
	            revalidate();
	            repaint();
	            break;
            	
            case 4:
            	topPanel = new JPanel();
            	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
            	topPanel.setBackground(new Color(0, 0, 0, 0));
            	add(topPanel, BorderLayout.NORTH);

            	titleArea = new JTextArea("Çiftlik Yönetimi");
            	titleArea.setEditable(false);
            	titleArea.setFont(new Font("Arial", Font.BOLD, 16));
            	titleArea.setBackground(new Color(0,0,0,0));
            	titleArea.setOpaque(true);
            	titleArea.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 40));
            	topPanel.add(titleArea);
            	
            	contentPanel = new miniPanel(this.parentPanel );
        		add(contentPanel);
	            contentPanel.updateContentFarmManagement();
	            revalidate();
	            repaint();
            	break;
            case 5:
            	fileReader.writeEverythingToFile("datas",CowplexUI.users,CowplexUI.farms);
            	break;
            default:


                break;
        }
 
        revalidate();
        repaint();
    }
	
	public void setSelectedIndex(int index) {
		selectedIndex=index;
	}
	
	
}
