package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class miniPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static int index=0;
	private CowplexUI parentPanel;
	
	private float toplamSüt;
	private float ortalamaSüt;
	private float sürüBeslenmeOrtalaması;
	private float ortalamaHasta;
	private float toplamHasta;
	private String[] imagePaths = {
            "src/imageIcons/milkBottle.png",
            "src/imageIcons/pill.png",
            "src/imageIcons/grass.png",
            "src/imageIcons/cow.png",
            "src/imageIcons/babyCow.png",
            "src/imageIcons/virus.png",
            "src/imageIcons/med.png",
            "src/imageIcons/farm.png",
            "src/imageIcons/idCard.png",
            "src/imageIcons/note.png"
        };
	ImageIcon[] icons = new ImageIcon[imagePaths.length];
	public miniPanel(CowplexUI parentPanel) {
		this.parentPanel = parentPanel;
		setBackground(new Color(0, 0, 0, 0));
		for (int i = 0; i < imagePaths.length; i++) {
            icons[i] = CowplexUI.createScaledImageIcon(imagePaths[i], 32, 32);
        }
	}
	
	public void updateContentCiftlik() {
		removeAll(); 

        switch (index) {
        	//genel
        	
            case 0:
            	getTodaysValues();
            	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            	add(Box.createVerticalStrut(50));
            	
            	JPanel genelajandaPanel1Container = new JPanel();
            	genelajandaPanel1Container.setBackground(new Color (0,0,0,0));
            	genelajandaPanel1Container.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            	genelajandaPanel1Container.setBorder(new EmptyBorder(0, 80, 0, 80));

            	CurvedPanel genelajandaPanel1 = new CurvedPanel(40, 40, Color.WHITE);
            	genelajandaPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

            	String genelButtonText1 = "<html>Toplam/Ortalama Süt<br />" + String.valueOf(toplamSüt) + "/" + String.valueOf(ortalamaSüt) + "</html>";
            	CurvedJButton genelButon1 = new CurvedJButton(genelButtonText1, icons[0], 40, 40);
            	genelButon1.setPreferredSize(new Dimension(250, 120));
            	genelButon1.addActionListener(new ActionListener() {
            	    @Override
            	    public void actionPerformed(ActionEvent e) {
            	    	miniPanel.setIndex(4);
            	    	removeAll();
            	    	miniPanel.this.updateContentSutYem();
            	    	revalidate();
            	    	repaint();
            	    	miniPanel.this.getParent().revalidate();
            	    	miniPanel.this.getParent().repaint();
            	    	
            	    }
            	});
            	
            	genelajandaPanel1.add(genelButon1);

            	String genelButtonText2 = "<html>Toplam/Ortalama Hasta<br />" + String.valueOf(toplamHasta)+"/"+String.valueOf(ortalamaHasta) + "</html>";
            	CurvedJButton genelButon2 = new CurvedJButton(genelButtonText2, icons[1], 40, 40);
            	genelButon2.setPreferredSize(new Dimension(250, 120));
            	genelajandaPanel1.add(genelButon2);
            	genelButon2.addActionListener(new ActionListener() {
            	    @Override
            	    public void actionPerformed(ActionEvent e) {
            	    	miniPanel.setIndex(3);
            	    	removeAll();
            	    	miniPanel.this.updateContentCiftlik();
            	    	revalidate();
            	    	repaint();
            	    	miniPanel.this.getParent().revalidate();
            	    	miniPanel.this.getParent().repaint();
            	    	
            	    }
            	});

            	String genelButtonText3 = "<html>Sürü Beslenme Ortalaması<br />" + String.valueOf(sürüBeslenmeOrtalaması) + "</html>";
            	CurvedJButton genelButon3 = new CurvedJButton(genelButtonText3, icons[2], 40, 40);
            	genelButon3.setPreferredSize(new Dimension(250, 120));
            	genelajandaPanel1.add(genelButon3);
            	genelButon3.addActionListener(new ActionListener() {
            	    @Override
            	    public void actionPerformed(ActionEvent e) {
            	    	miniPanel.setIndex(5);
            	    	removeAll();
            	    	miniPanel.this.updateContentSutYem();
            	    	revalidate();
            	    	repaint();
            	    	miniPanel.this.getParent().revalidate();
            	    	miniPanel.this.getParent().repaint();
            	    	
            	    }
            	});

            	genelajandaPanel1Container.add(genelajandaPanel1);
            	add(genelajandaPanel1Container);

            	
            	add(Box.createVerticalStrut(300));

            	
                break;
                
            //grafikler  1. grafik gebe inek/  
            case 1:
            	setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30)); 
            	setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
            	
                TreeMap<String, Integer> breedCountMap = new TreeMap<>();

                for (Animal animal : this.parentPanel.selectedFarm.getAnimals()) {
                    String breed = animal.getBreed();
                    breedCountMap.put(breed, breedCountMap.getOrDefault(breed, 0) + 1);
                }
                
            	CurvedPanel graphajandaPanel1 = new CurvedPanel(50, 50, Color.WHITE);
            	graphajandaPanel1.setPreferredSize(new Dimension(600, 600));
            	add(graphajandaPanel1);
            	
                DefaultPieDataset dataset = new DefaultPieDataset( );
                
                for (Entry<String, Integer> entry : breedCountMap.entrySet()) {
                	dataset.setValue( entry.getKey(),entry.getValue());
                } 
                
                 JFreeChart piechart = ChartFactory.createPieChart("Cins",dataset, false,true,false);//explain
                
                 PiePlot piePlot =(PiePlot) piechart.getPlot();
                
                 piePlot.setBackgroundPaint(Color.white);
                  
                 ChartPanel barChartPanel = new ChartPanel(piechart);
                 graphajandaPanel1.removeAll();
                 graphajandaPanel1.add(barChartPanel, BorderLayout.CENTER);
                 graphajandaPanel1.validate();

            	CurvedPanel graphajandaPanel2 = new CurvedPanel(50, 50, Color.WHITE);
            	graphajandaPanel2.setPreferredSize(new Dimension(600, 600));
            	add(graphajandaPanel2);
            	
            	
            	int disiCount=0;
            	int gebeCount=0;

                for (Animal animal : this.parentPanel.selectedFarm.getAnimals()) {
                    if(animal.isCinsiyet()) {
                    	disiCount++;
                    	if(animal.isGebe()) {
                    		gebeCount++;
                    	}
                    }
                }
            	
                DefaultPieDataset dataset2 = new DefaultPieDataset( );
                
                
                dataset2.setValue( "Gebe",gebeCount);
                dataset2.setValue("Gebe Değil", disiCount-gebeCount);
               
                
                 JFreeChart piechart2 = ChartFactory.createPieChart("Gebelik Durumu",dataset2, false,true,false);//explain
                
                 PiePlot piePlot2 =(PiePlot) piechart2.getPlot();
                
                 piePlot2.setBackgroundPaint(Color.white);
                  
                 ChartPanel barChartajandaPanel2 = new ChartPanel(piechart2);
                 graphajandaPanel2.removeAll();
                 graphajandaPanel2.add(barChartajandaPanel2, BorderLayout.CENTER);
            	
                break;
                
            case 2:
                setLayout(new BorderLayout());
                setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                CurvedPanel ajandaPanel = new CurvedPanel(60, 60, new Color(0, 0, 0, 0));
                ajandaPanel.setPreferredSize(new Dimension(1200, 700));
                ajandaPanel.setLayout(new BoxLayout(ajandaPanel, BoxLayout.X_AXIS));
                add(ajandaPanel, BorderLayout.NORTH);

                // Panel 1: Display notes as buttons
                CurvedPanel ajandaPanel1 = new CurvedPanel(50, 50, Color.WHITE);
                ajandaPanel1.setLayout(new BoxLayout(ajandaPanel1, BoxLayout.Y_AXIS));
                ajandaPanel1.setPreferredSize(new Dimension(400, 600));
                JScrollPane scrollPane1 = new JScrollPane(ajandaPanel1);
                scrollPane1.setPreferredSize(new Dimension(400, 600));
                scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                ajandaPanel.add(scrollPane1);

                ajandaPanel.add(Box.createRigidArea(new Dimension(20, 0)));

                CurvedPanel ajandaPanel2 = new CurvedPanel(50, 50, Color.WHITE);
                ajandaPanel2.setLayout(new BoxLayout(ajandaPanel2, BoxLayout.Y_AXIS));
                ajandaPanel2.setPreferredSize(new Dimension(400, 600));
                JScrollPane scrollPane2 = new JScrollPane(ajandaPanel2);
                scrollPane2.setPreferredSize(new Dimension(400, 600));
                scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                ajandaPanel.add(scrollPane2);

                ajandaPanel.add(Box.createRigidArea(new Dimension(20, 0)));

                // Panel 3: Add new notes
                CurvedPanel ajandaPanel3 = new CurvedPanel(50, 50, Color.WHITE);
                ajandaPanel3.setLayout(new BoxLayout(ajandaPanel3, BoxLayout.Y_AXIS));
                ajandaPanel3.setPreferredSize(new Dimension(400, 600));
                JScrollPane scrollPane3 = new JScrollPane(ajandaPanel3);
                scrollPane3.setPreferredSize(new Dimension(400, 600));
                scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                ajandaPanel.add(scrollPane3);

                JButton[] selectedNoteButton = new JButton[1];

                if (!parentPanel.selectedFarm.getNotes().isEmpty()) {
                    int count = 0;
                    for (String note : parentPanel.selectedFarm.getNotes()) {
                        count++;
                        JButton noteButton = new CurvedJButton("Note " + count, icons[9], 5, 5);
                        noteButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                        noteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                        noteButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ajandaPanel2.removeAll();
                                JLabel noteLabel = new JLabel(note);
                                noteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                                ajandaPanel2.add(noteLabel);
                                ajandaPanel2.revalidate();
                                ajandaPanel2.repaint();
                                selectedNoteButton[0] = noteButton;
                            }
                        });
                        ajandaPanel1.add(noteButton);
                    }
                }

                JTextField newNoteField = new JTextField(20);
                JButton addNoteButton = new CurvedJButton("Add Note", icons[9], 5, 5);
                addNoteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String newNote = newNoteField.getText();
                        if (!newNote.isEmpty()) {
                            parentPanel.selectedFarm.addNote(newNote);
                            ajandaPanel1.removeAll();
                            ajandaPanel2.removeAll();
                            ajandaPanel3.removeAll();
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter a note.");
                        }
                    }
                });

                ajandaPanel3.add(new JLabel("Enter new note:"));
                ajandaPanel3.add(newNoteField);
                ajandaPanel3.add(addNoteButton);

                JButton deleteNoteButton = new CurvedJButton("Delete Note", icons[9], 5, 5);
                deleteNoteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                deleteNoteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (selectedNoteButton[0] != null) {
                            for (int i = 0; i < ajandaPanel1.getComponentCount(); i++) {
                                if (ajandaPanel1.getComponent(i) == selectedNoteButton[0]) {
                                    parentPanel.selectedFarm.getNotes().remove(i);
                                    ajandaPanel1.remove(i);
                                    ajandaPanel2.removeAll();
                                    ajandaPanel2.revalidate();
                                    ajandaPanel2.repaint();
                                    ajandaPanel1.revalidate();
                                    ajandaPanel1.repaint();
                                    break;
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select a note to delete.");
                        }
                    }
                });

                ajandaPanel.add(Box.createRigidArea(new Dimension(20, 0))); 
                JPanel deleteNoteButtonPanel = new JPanel();
                deleteNoteButtonPanel.setBackground(new Color(0,0,0,0));
                deleteNoteButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                deleteNoteButtonPanel.add(deleteNoteButton);
                add(deleteNoteButtonPanel, BorderLayout.SOUTH);

                break;

            	// hastalık
            case 3:
                setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
                setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                CurvedPanel hastaIneklerPanel = new CurvedPanel(50, 50, Color.WHITE);
                hastaIneklerPanel.setLayout(new BoxLayout(hastaIneklerPanel, BoxLayout.Y_AXIS));
                JScrollPane hastaIneklerScrollPane = new JScrollPane(hastaIneklerPanel);
                hastaIneklerScrollPane.setPreferredSize(new Dimension(400, 600));
                hastaIneklerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                hastaIneklerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                add(hastaIneklerScrollPane);

                CurvedPanel hastalıklarPanel = new CurvedPanel(50, 50, Color.WHITE);
                hastalıklarPanel.setLayout(new BoxLayout(hastalıklarPanel, BoxLayout.Y_AXIS));
                JScrollPane hastalıklarPanelScrollPane = new JScrollPane(hastalıklarPanel);
                hastalıklarPanelScrollPane.setPreferredSize(new Dimension(400, 600));
                hastalıklarPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                hastalıklarPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                add(hastalıklarPanelScrollPane);

                for (Animal animal : parentPanel.selectedFarm.getAnimals()) {
                    JButton animalButton = new CurvedJButton(animal.getName(), icons[3], 5, 5);
                    animalButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                    animalButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                    animalButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            hastalıklarPanel.removeAll();
                            for (String disease : animal.getDiseases()) {
                                JLabel diseaseLabel = new JLabel(disease, SwingConstants.CENTER);
                                diseaseLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                                diseaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                                hastalıklarPanel.add(diseaseLabel);
                            }
                            hastalıklarPanel.revalidate();
                            hastalıklarPanel.repaint();
                        }
                    });
                    hastaIneklerPanel.add(animalButton);
                }

                break;

                
            default:
            	
            	
                break;
        }
        revalidate();
        repaint();
    
	}
	
	public void updateContentSutYem() {
		removeAll();
		Date thirtyDaysAgo = new Date(System.currentTimeMillis() - (30 * 24 * 60 * 60 * 1000L)); 
    	Color lineChartColor = new Color(204,0,51);
    	
    	setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30)); 
    	setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    	
    	//panel for graph
    	CurvedPanel grafik = new CurvedPanel(60,60,Color.WHITE);
    	grafik = new CurvedPanel(60,60,Color.WHITE);
    	grafik.setPreferredSize(new Dimension(1200,700));
    	add(grafik);
    	grafik.setLayout(new BorderLayout());
    	
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	JFreeChart linechart;
    	CategoryPlot lineCategoryPlot;
    	LineAndShapeRenderer lineRenderer;
    	ChartPanel lineChartPanel;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        
		//süt
		switch(index) {
    case 4:
    	
        List<MilkingSession> milkForLast30Days = new ArrayList<>();
        for (Animal animal : parentPanel.selectedFarm.getAnimals()) {
            List<MilkingSession> feed = animal.getMilkingSessions().stream()
                .filter(session -> session.getDate().after(thirtyDaysAgo))
                .collect(Collectors.toList());
            milkForLast30Days.addAll(feed);
        }
        
        TreeMap<String, Float> cumulativeMilkData = new TreeMap<>();
        
        for (MilkingSession session : milkForLast30Days) {
            String dateKey = dateFormat.format(session.getDate());
            float quantity = session.getQuantity();

            cumulativeMilkData.put(dateKey, cumulativeMilkData.getOrDefault(dateKey, 0f) + quantity);
        }
        
        dataset = new DefaultCategoryDataset();
        
        for (Map.Entry<String, Float> entry : cumulativeMilkData.entrySet()) {
        	dataset.setValue(entry.getValue(), "amount", entry.getKey().substring(entry.getKey().length()-2));
        }

        linechart = ChartFactory.createLineChart("Sağılan Süt Miktarı (son 30 gün)", "Günlük", "Miktar(lt)",
        		dataset, PlotOrientation.VERTICAL, false, true, false);

        lineCategoryPlot = linechart.getCategoryPlot();
        lineCategoryPlot.setBackgroundPaint(Color.white);

        lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        lineRenderer.setSeriesPaint(0, lineChartColor);

        lineChartPanel = new ChartPanel(linechart);
        grafik.removeAll();
        grafik.add(lineChartPanel, BorderLayout.CENTER);
        grafik.validate();
        break;
    	
    //yem
    case 5:

        List<FeedLog> feedForLast30Days = new ArrayList<>();
        for (Animal animal : parentPanel.selectedFarm.getAnimals()) {
            List<FeedLog> feed = animal.getFeedLogs().stream()
                .filter(session -> session.getDate().after(thirtyDaysAgo))
                .collect(Collectors.toList());
            feedForLast30Days.addAll(feed);
        }
        
        TreeMap<String, Float> cumulativeFeedData = new TreeMap<>();
        
        for (FeedLog session : feedForLast30Days) {
            String dateKey = dateFormat.format(session.getDate());
            float quantity = session.getQuantity();

            cumulativeFeedData.put(dateKey, cumulativeFeedData.getOrDefault(dateKey, 0f) + quantity);
        }
        
        dataset = new DefaultCategoryDataset();
        
        for (Map.Entry<String, Float> entry : cumulativeFeedData.entrySet()) {
        	dataset.setValue(entry.getValue(), "amount", entry.getKey().substring(entry.getKey().length()-2));
        }

        linechart = ChartFactory.createLineChart("Verilen Yem Miktarı (son 30 gün)", "Günlük", "Miktar(kg)",
        		dataset, PlotOrientation.VERTICAL, false, true, false);

        lineCategoryPlot = linechart.getCategoryPlot();
        lineCategoryPlot.setBackgroundPaint(Color.white);

        lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        lineRenderer.setSeriesPaint(0, lineChartColor);

        lineChartPanel = new ChartPanel(linechart);
        grafik.removeAll();
        grafik.add(lineChartPanel, BorderLayout.CENTER);
        grafik.validate();
        break;

    	
    //yem/süt
    case 6:
        List<FeedLog> combinedFeedForLast30Days = new ArrayList<>();
        List<MilkingSession> combinedMilkForLast30Days = new ArrayList<>();
        for (Animal animal : parentPanel.selectedFarm.getAnimals()) {
            List<FeedLog> feed = animal.getFeedLogs().stream()
                .filter(session -> session.getDate().after(thirtyDaysAgo))
                .collect(Collectors.toList());
            combinedFeedForLast30Days.addAll(feed);

            List<MilkingSession> milk = animal.getMilkingSessions().stream()
                .filter(session -> session.getDate().after(thirtyDaysAgo))
                .collect(Collectors.toList());
            combinedMilkForLast30Days.addAll(milk);
        }
        
        TreeMap<String, Float> cumulativeFeedDataForCombined = new TreeMap<>();
        TreeMap<String, Float> cumulativeMilkDataForCombined = new TreeMap<>();
        
        for (FeedLog session : combinedFeedForLast30Days) {
            String dateKey = dateFormat.format(session.getDate());
            float quantity = session.getQuantity();

            cumulativeFeedDataForCombined.put(dateKey, cumulativeFeedDataForCombined.getOrDefault(dateKey, 0f) + quantity);
        }
        
        for (MilkingSession session : combinedMilkForLast30Days) {
            String dateKey = dateFormat.format(session.getDate());
            float quantity = session.getQuantity();

            cumulativeMilkDataForCombined.put(dateKey, cumulativeMilkDataForCombined.getOrDefault(dateKey, 0f) + quantity);
        }
        
        DefaultCategoryDataset combinedDataset = new DefaultCategoryDataset();
        
        for (String dateKey : cumulativeFeedDataForCombined.keySet()) {
            if (cumulativeMilkDataForCombined.containsKey(dateKey) && cumulativeFeedDataForCombined.get(dateKey) != 0) {
                float ratio = cumulativeMilkDataForCombined.get(dateKey) / cumulativeFeedDataForCombined.get(dateKey);
                combinedDataset.setValue(ratio, "Milk/Feed Ratio", dateKey.substring(dateKey.length()-2));
            }
        }

        JFreeChart combinedLinechart = ChartFactory.createLineChart("Süt/Yem Oranı (son 30 gün)", "Günlük", "Oran",
            combinedDataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot combinedLineCategoryPlot = combinedLinechart.getCategoryPlot();
        combinedLineCategoryPlot.setBackgroundPaint(Color.white);

        LineAndShapeRenderer combinedLineRenderer = (LineAndShapeRenderer) combinedLineCategoryPlot.getRenderer();
        combinedLineRenderer.setSeriesPaint(0, lineChartColor);

        ChartPanel combinedLineChartPanel = new ChartPanel(combinedLinechart);
        grafik.removeAll();
        grafik.add(combinedLineChartPanel, BorderLayout.CENTER);
        grafik.validate();
        break;
}
        revalidate();
        repaint();
	
	}
	
	
	public static void setIndex(int selectedIndex) {
		index=selectedIndex;
	}
	
	public void updateContentFarmManagement() {
	    removeAll();
	    final Farm[] selectedFarm = new Farm[1];
	    final User[] selectedUser = new User[1];

	    setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
	    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	    CurvedPanel farmListPanel = new CurvedPanel(50, 50, Color.WHITE);
	    farmListPanel.setLayout(new BoxLayout(farmListPanel, BoxLayout.Y_AXIS));
	    JScrollPane farmListScrollPane = new JScrollPane(farmListPanel);
	    farmListScrollPane.setPreferredSize(new Dimension(400, 600));
	    farmListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    farmListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    add(farmListScrollPane);

	    CurvedPanel userListPanel = new CurvedPanel(50, 50, Color.WHITE);
	    userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));
	    JScrollPane userListScrollPane = new JScrollPane(userListPanel);
	    userListScrollPane.setPreferredSize(new Dimension(400, 600));
	    userListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    userListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    add(userListScrollPane);

	    CurvedPanel detailPanel = new CurvedPanel(50, 50, Color.WHITE);
	    detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
	    JScrollPane detailScrollPane = new JScrollPane(detailPanel);
	    detailScrollPane.setPreferredSize(new Dimension(400, 600));
	    detailScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    detailScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    add(detailScrollPane);

	    for (Farm farm : CowplexUI.farms) {
	        JButton farmButton = new CurvedJButton(farm.getId() + " - " + farm.getName(), icons[7], 5, 5);
	        farmButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	        farmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	        farmButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                selectedFarm[0] = farm;
	                detailPanel.removeAll();
	                JLabel farmIdLabel = new JLabel("ID: " + farm.getId());
	                JLabel farmNameLabel = new JLabel("İsim: " + farm.getName());
	                JLabel farmLocationLabel = new JLabel("Konum: " + farm.getLocation());
	                JLabel farmAnimalCountLabel = new JLabel("Hayvan Sayısı: " + farm.getAnimals().size());
	                farmIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	                farmNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	                farmLocationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	                farmAnimalCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	                detailPanel.add(farmIdLabel);
	                detailPanel.add(farmNameLabel);
	                detailPanel.add(farmLocationLabel);
	                detailPanel.add(farmAnimalCountLabel);
	                detailPanel.revalidate();
	                detailPanel.repaint();
	            }
	        });
	        farmListPanel.add(farmButton);
	    }

	    for (User user : CowplexUI.users) {
	        JButton userButton = new CurvedJButton(user.getName(), icons[8], 5, 5);
	        userButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	        userButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	        userButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                selectedUser[0] = user;
	                detailPanel.removeAll();
	                JLabel userIdLabel = new JLabel("ID: " + user.getId());
	                JLabel userNameLabel = new JLabel("İsim: " + user.getName());
	                JLabel userRoleLabel = new JLabel("Rol: " + user.getUserType());
	                JLabel userEmailLabel = new JLabel("Email: " + user.getEmail());
	                JLabel userContactInfoLabel = new JLabel("İletişim Bilgisi: " + user.getContactInfo());
	                userIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	                userNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	                userRoleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	                userEmailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	                userContactInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	                detailPanel.add(userIdLabel);
	                detailPanel.add(userNameLabel);
	                detailPanel.add(userRoleLabel);
	                detailPanel.add(userEmailLabel);
	                detailPanel.add(userContactInfoLabel);
	                detailPanel.revalidate();
	                detailPanel.repaint();
	            }
	        });
	        userListPanel.add(userButton);
	    }

	    JPanel managementPanel = new JPanel();
	    managementPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
	    managementPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    managementPanel.setBackground(new Color(0, 0, 0, 0));

	    JButton addFarmButton = new CurvedJButton("Çiftlik Ekle", icons[7], 20, 20);
	    addFarmButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            detailPanel.removeAll();
	            JTextField farmNameField = new JTextField(20);
	            JTextField farmLocationField = new JTextField(20);
	            JButton saveButton = new JButton("Kaydet");
	            
	            saveButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    String farmName = farmNameField.getText();
	                    String farmLocation = farmLocationField.getText();
	                    if (!farmName.isEmpty() && !farmLocation.isEmpty()) {
	                        Farm newFarm = new Farm(CowplexUI.farms.size()+1, farmName, farmLocation);
	                        CowplexUI.farms.add(newFarm);
	                        JOptionPane.showMessageDialog(null, "Çiftlik eklendi!");
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz.");
	                    }
	                }
	            });
	            
	            detailPanel.add(new JLabel("Çiftlik İsmi:"));
	            detailPanel.add(farmNameField);
	            detailPanel.add(new JLabel("Çiftlik Konumu:"));
	            detailPanel.add(farmLocationField);
	            detailPanel.add(saveButton);
	            detailPanel.revalidate();
	            detailPanel.repaint();
	        }
	    });

	    JButton removeFarmButton = new CurvedJButton("Çiftlik Sil", icons[7], 20, 20);
	    removeFarmButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (selectedFarm[0] != null) {
	                int confirm = JOptionPane.showConfirmDialog(null, "Seçili çiftliği silmek istediğinize emin misiniz?", "Çiftlik Sil", JOptionPane.YES_NO_OPTION);
	                if (confirm == JOptionPane.YES_OPTION) {
	                    CowplexUI.farms.remove(selectedFarm[0]);
	                    updateContentFarmManagement();
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Lütfen bir çiftlik seçin.");
	            }
	        }
	    });

	    JButton addUserButton = new CurvedJButton("Kullanıcı Ekle", icons[8], 20, 20);
	    addUserButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            detailPanel.removeAll();
	            JTextField userNameField = new JTextField(20);
	            JTextField userEmailField = new JTextField(20);
	            JTextField userPasswordField = new JTextField(20);
	            JTextField userContactInfoField = new JTextField(20);
	            JTextField userRoleField = new JTextField(20);
	            JButton saveButton = new JButton("Kaydet");
	            
	            saveButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    String userName = userNameField.getText();
	                    String userEmail = userEmailField.getText();
	                    String userPassword = userPasswordField.getText();
	                    String userContactInfo = userContactInfoField.getText();
	                    String userRole = userRoleField.getText();
	                    if (!userName.isEmpty() && !userEmail.isEmpty() && !userPassword.isEmpty() && !userContactInfo.isEmpty() && !userRole.isEmpty()) {
	                        User newUser = new User(CowplexUI.users.size()+1, userName, userEmail, userPassword.toCharArray(), userContactInfo, userRole);
	                        CowplexUI.users.add(newUser);
	                        JOptionPane.showMessageDialog(null, "Kullanıcı eklendi!");
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz.");
	                    }
	                }
	            });
	            
	            detailPanel.add(new JLabel("Kullanıcı İsmi:"));
	            detailPanel.add(userNameField);
	            detailPanel.add(new JLabel("Email:"));
	            detailPanel.add(userEmailField);
	            detailPanel.add(new JLabel("Şifre:"));
	            detailPanel.add(userPasswordField);
	            detailPanel.add(new JLabel("İletişim Bilgisi:"));
	            detailPanel.add(userContactInfoField);
	            detailPanel.add(new JLabel("Rol(vet, manager, worker):"));
	            detailPanel.add(userRoleField);
	            detailPanel.add(saveButton);
	            detailPanel.revalidate();
	            detailPanel.repaint();
	        }
	    });

	    JButton removeUserButton = new CurvedJButton("Kullanıcı Sil", icons[8], 20, 20);
	    removeUserButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (selectedUser[0] != null) {
	                int confirm = JOptionPane.showConfirmDialog(null, "Seçili kullanıcıyı silmek istediğinize emin misiniz?", "Kullanıcı Sil", JOptionPane.YES_NO_OPTION);
	                if (confirm == JOptionPane.YES_OPTION) {
	                    CowplexUI.users.remove(selectedUser[0]);
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Lütfen bir kullanıcı seçin.");
	            }
	        }
	    });

	    managementPanel.add(addFarmButton);
	    managementPanel.add(removeFarmButton);
	    managementPanel.add(addUserButton);
	    managementPanel.add(removeUserButton);

	    add(managementPanel);

	    revalidate();
	    repaint();
	}
	
	public void updateContentVet() {
		removeAll();
		final Animal[] selectedAnimal = new Animal[1];
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		CurvedPanel vetHastaIneklerPanel = new CurvedPanel(50, 50, Color.WHITE);
		vetHastaIneklerPanel.setLayout(new BoxLayout(vetHastaIneklerPanel, BoxLayout.Y_AXIS));
		JScrollPane vetHastaIneklerScrollPane = new JScrollPane(vetHastaIneklerPanel);
		vetHastaIneklerScrollPane.setPreferredSize(new Dimension(400, 600));
		vetHastaIneklerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		vetHastaIneklerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(vetHastaIneklerScrollPane);

		CurvedPanel vetHastalıklarPanel = new CurvedPanel(50, 50, Color.WHITE);
		vetHastalıklarPanel.setLayout(new BoxLayout(vetHastalıklarPanel, BoxLayout.Y_AXIS));
		JScrollPane vetHastalıklarPanelScrollPane = new JScrollPane(vetHastalıklarPanel);
		vetHastalıklarPanelScrollPane.setPreferredSize(new Dimension(400, 600));
		vetHastalıklarPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		vetHastalıklarPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(vetHastalıklarPanelScrollPane);

		CurvedPanel vetDetayPanel = new CurvedPanel(50, 50, Color.WHITE);
		vetDetayPanel.setLayout(new BoxLayout(vetDetayPanel, BoxLayout.Y_AXIS));
		JScrollPane vetDetayPanelScrollPane = new JScrollPane(vetDetayPanel);
		vetDetayPanelScrollPane.setPreferredSize(new Dimension(400, 600));
		vetDetayPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		vetDetayPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(vetDetayPanelScrollPane);


		for (Animal animal : parentPanel.selectedFarm.getAnimals()) {
			JButton animalButton = new CurvedJButton(animal.getName(), icons[3], 5, 5);
			animalButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
			animalButton.setAlignmentX(Component.CENTER_ALIGNMENT);

			animalButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedAnimal[0] = animal;

					vetHastalıklarPanel.removeAll();
    
					if (animal.getDiseases().isEmpty()) {
						JLabel noDiseasesLabel = new JLabel("Bu hayvanın herhangi bir aktif hastalığı yok.");
						noDiseasesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
						vetHastalıklarPanel.add(noDiseasesLabel);
					} else {
						for (String disease : animal.getDiseases()) {
							JLabel diseaseLabel = new JLabel(disease);
							diseaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
							vetHastalıklarPanel.add(diseaseLabel);
						}
					}

					vetHastalıklarPanel.revalidate();
					vetHastalıklarPanel.repaint();
					
					vetDetayPanel.removeAll();
    
					if (animal.getHealthRecords().isEmpty()) {
						JLabel noHealthRecordsLabel = new JLabel("Bu hayvan için girilmiş herhangi bir sağlık kaydı yok.");
						noHealthRecordsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
						vetDetayPanel.add(noHealthRecordsLabel);
					} else {
						for (HealthRecord record : animal.getHealthRecords()) {
							JLabel recordLabel = new JLabel("<html>" + record.toString().replaceAll("\n", "<br>") + "</html>");
							recordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
							vetDetayPanel.add(recordLabel);
						}	
					}

					vetDetayPanel.revalidate();
					vetDetayPanel.repaint();
				}
			});
			vetHastaIneklerPanel.add(animalButton);
		}

		JPanel addingPanel = new JPanel();
		addingPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		addingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		addingPanel.setBackground(new Color(0, 0, 0, 0));

		JButton addDiseaseButton = new CurvedJButton("Hastalık Ekle", icons[5], 20, 20);
		addDiseaseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedAnimal[0] == null) {
					JOptionPane.showMessageDialog(null, "Önce hayvan seçin.");
					return;
				}

				JTextField diseaseField = new JTextField(20);

				JPanel diseasePanel = new JPanel(new BorderLayout());
				diseasePanel.setOpaque(false);
				diseasePanel.add(new JLabel("Disease:"), BorderLayout.WEST);
				diseasePanel.add(diseaseField, BorderLayout.CENTER);

				JButton addButton = new CurvedJButton("Ekle", (Icon) null, 20, 20);
				addButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (selectedAnimal[0] != null) {
							String disease = diseaseField.getText();
							selectedAnimal[0].addDisease(disease);

							JOptionPane.showMessageDialog(null, "Hastalık eklendi.");

						}
					}
				});

				JPanel diseaseAndButtonPanel = new JPanel(new BorderLayout());
				diseaseAndButtonPanel.setOpaque(false);
				diseaseAndButtonPanel.add(diseasePanel, BorderLayout.CENTER);
				diseaseAndButtonPanel.add(addButton, BorderLayout.SOUTH);

				vetDetayPanel.removeAll();
				vetDetayPanel.add(diseaseAndButtonPanel);
				vetDetayPanel.revalidate();
				vetDetayPanel.repaint();
			}
		});

		JButton removeDiseaseButton = new CurvedJButton("Hastalık Kaldır",icons[6],20,20);
		removeDiseaseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedAnimal[0] == null) {
					JOptionPane.showMessageDialog(null, "Önce hayvan seçin.");
					return;
				}

				String[] diseases = selectedAnimal[0].getDiseases().toArray(new String[0]);
				String selectedDisease = (String) JOptionPane.showInputDialog(null, "Kaldırılacak hastalığı seçin:", "Hastalığı Kaldır", JOptionPane.PLAIN_MESSAGE, null, diseases, diseases[0]);

				if (selectedDisease != null) {
					selectedAnimal[0].removeDisease(selectedDisease);
					JOptionPane.showMessageDialog(null, "Hastalık kaldırıldı");
				}
			}
		});

		JButton addHealthRecordButton = new CurvedJButton("Sağlık Kaydı Ekle", icons[1], 20, 20);

		addingPanel.add(addDiseaseButton);
		addingPanel.add(removeDiseaseButton);
		addingPanel.add(addHealthRecordButton);

		add(addingPanel, BorderLayout.SOUTH);

		addHealthRecordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedAnimal[0] == null) {
					JOptionPane.showMessageDialog(null, "Önce hayvan seçin.");
					return;
				}

				JTextField dateField = new JTextField(20);
				JTextField healthStatusField = new JTextField(20);
				JTextField notesField = new JTextField(20);
				JTextField usedMedicinesField = new JTextField(20);
				JTextField doseField = new JTextField(20);

				JPanel healthRecordPanel = new JPanel(new GridLayout(5, 2));
				healthRecordPanel.setOpaque(false);
				healthRecordPanel.add(new JLabel("Tarih (yyyy-MM-dd HH:mm:ss):"));
				healthRecordPanel.add(dateField);
				healthRecordPanel.add(new JLabel("Sağlık durumu:"));
				healthRecordPanel.add(healthStatusField);
				healthRecordPanel.add(new JLabel("Not:"));
				healthRecordPanel.add(notesField);
				healthRecordPanel.add(new JLabel("İlaçlar (virgülle ayrılmış):"));
				healthRecordPanel.add(usedMedicinesField);
				healthRecordPanel.add(new JLabel("Doz (virgülle ayrılmış):"));
				healthRecordPanel.add(doseField);

				JButton addButton = new CurvedJButton("Ekle", (Icon) null, 20, 20);
				addButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (selectedAnimal[0] != null) {
							try {
								String date = dateField.getText();
								String healthStatus = healthStatusField.getText();
								String notes = notesField.getText();
								
								List<String> usedMedicines = new ArrayList<>();
								for (String med : usedMedicinesField.getText().split(",")) {
									usedMedicines.add(med.trim());
								}

								List<String> dose = new ArrayList<>();
								for (String doseEntry : doseField.getText().split(",")) {
									dose.add(doseEntry.trim());
								}

								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								dateFormat.setLenient(false);
								Date parsedDate = dateFormat.parse(date);

								if (healthStatus.isEmpty() || notes.isEmpty() || usedMedicines.isEmpty() || dose.isEmpty()) {
									throw new IllegalArgumentException("Lütfen tüm alanları doldurunuz.");
								}
								
								HealthRecord newRecord = new HealthRecord(parsedDate, healthStatus, notes, usedMedicines, dose);
								selectedAnimal[0].addHealthRecord(newRecord);

								JOptionPane.showMessageDialog(null, "Sağlık kaydı başarı ile eklendi!");
							} catch (ParseException | IllegalArgumentException ex) {
								JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});


				JPanel healthRecordAndButtonPanel = new JPanel(new BorderLayout());
				healthRecordAndButtonPanel.setOpaque(false);
				healthRecordAndButtonPanel.add(healthRecordPanel, BorderLayout.CENTER);
				healthRecordAndButtonPanel.add(addButton, BorderLayout.SOUTH);

				vetDetayPanel.removeAll();
				vetDetayPanel.add(healthRecordAndButtonPanel);
				vetDetayPanel.revalidate();
				vetDetayPanel.repaint();
			}
		});
		revalidate();
		repaint();
		}
	
	public void updateContentAnimalManagment() {
	    removeAll();
	    final Animal[] selectedAnimal = new Animal[1];

	    setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
	    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	    CurvedPanel inekBilgileri = new CurvedPanel(50, 50, Color.WHITE);
	    inekBilgileri.setLayout(new BoxLayout(inekBilgileri, BoxLayout.Y_AXIS));
	    JScrollPane inekBilgileriScrollPane = new JScrollPane(inekBilgileri);
	    inekBilgileriScrollPane.setPreferredSize(new Dimension(400, 600));
	    inekBilgileriScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    inekBilgileriScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    add(inekBilgileriScrollPane);

	    CurvedPanel bilgiDetay = new CurvedPanel(50, 50, Color.WHITE);
	    bilgiDetay.setLayout(new BoxLayout(bilgiDetay, BoxLayout.Y_AXIS));
	    JScrollPane bilgiDetayScrollPane = new JScrollPane(bilgiDetay);
	    bilgiDetayScrollPane.setPreferredSize(new Dimension(400, 600));
	    bilgiDetayScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    bilgiDetayScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    add(bilgiDetayScrollPane);

	    CurvedPanel bilgiDetayDetay = new CurvedPanel(50, 50, Color.WHITE);
	    bilgiDetayDetay.setLayout(new BorderLayout());
	    JScrollPane bilgiDeetayDetayScrollPane = new JScrollPane(bilgiDetayDetay);
	    bilgiDeetayDetayScrollPane.setPreferredSize(new Dimension(400, 600));
	    bilgiDeetayDetayScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    bilgiDeetayDetayScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    add(bilgiDeetayDetayScrollPane);

	    JTextArea bilgiDetayTextArea = new JTextArea();
	    bilgiDetayDetay.add(bilgiDetayTextArea, BorderLayout.CENTER);

	    for (Animal animal : parentPanel.selectedFarm.getAnimals()) {
	        JButton animalButton = new CurvedJButton(animal.getName(), icons[3], 5, 5);
	        animalButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	        animalButton.setAlignmentX(Component.CENTER_ALIGNMENT);

	        animalButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	                selectedAnimal[0] = animal;
	                bilgiDetay.removeAll();
	                bilgiDetayDetay.removeAll();

	                String gender = animal.isCinsiyet() ? "Dişi" : "Erkek";

	                JLabel animalInfo = new JLabel("<html>ID: " + animal.getId() + "<br>İsim: " + animal.getName() +
	                        "<br>Tür: " + animal.getSpecies() + "<br>Cins: " + animal.getBreed() + 
	                        "<br>Doğum Tarihi: " + animal.getBirthDate() + "<br>Cinsiyet: " + gender +
	                        "<br>Gebe Durumu: " + (animal.isGebe() ? "Evet" : "Hayır") + 
	                        "<br>Düve Durumu: " + (animal.isDuve() ? "Evet" : "Hayır") + "</html>");
	                animalInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
	                bilgiDetay.add(animalInfo);

	                JButton feedLogsButton = new CurvedJButton("Yem Kaydı", icons[2], 5, 5);
	                feedLogsButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	                feedLogsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	                bilgiDetay.add(feedLogsButton);

	                JButton milkingSessionsButton = new CurvedJButton("Sağım Kaydı", icons[0], 5, 5);
	                milkingSessionsButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	                milkingSessionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	                bilgiDetay.add(milkingSessionsButton);

	                feedLogsButton.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        bilgiDetayDetay.removeAll();
	                        bilgiDetayTextArea.setText("");
	                        bilgiDetayDetay.add(bilgiDetayTextArea, BorderLayout.CENTER);
	                        for (FeedLog feedLog : animal.getFeedLogs()) {
	                            bilgiDetayTextArea.append(feedLog.toString() + "\n");
	                        }
	                        bilgiDetayDetay.revalidate();
	                        bilgiDetayDetay.repaint();
	                    }
	                });

	                milkingSessionsButton.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        bilgiDetayDetay.removeAll();
	                        bilgiDetayTextArea.setText("");
	                        bilgiDetayDetay.add(bilgiDetayTextArea, BorderLayout.CENTER);
	                        for (MilkingSession session : animal.getMilkingSessions()) {
	                            bilgiDetayTextArea.append(session.toString() + "\n");
	                        }
	                        bilgiDetayDetay.revalidate();
	                        bilgiDetayDetay.repaint();
	                    }
	                });

	                bilgiDetay.revalidate();
	                bilgiDetay.repaint();
	            }
	        });
	        inekBilgileri.add(animalButton);
	    }

	    JPanel addingPanelLog = new JPanel();
	    addingPanelLog.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
	    addingPanelLog.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    addingPanelLog.setBackground(new Color(0, 0, 0, 0));

	    JButton addMilk = new CurvedJButton("Sağım Kaydı Ekle", icons[0], 20, 20);
	    addMilk.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            bilgiDetayDetay.removeAll();
	            bilgiDetayTextArea.setText("");

	            JTextField dateField = new JTextField(20);
	            JTextField quantityField = new JTextField(20);
	            JTextField durationField = new JTextField(20);

	            JPanel milkPanel = new JPanel(new GridLayout(3, 2));
	            milkPanel.setOpaque(false);
	            milkPanel.add(new JLabel("Gün (yyyy-MM-dd HH:mm:ss):"));
	            milkPanel.add(dateField);
	            milkPanel.add(new JLabel("Miktar (Litre):"));
	            milkPanel.add(quantityField);
	            milkPanel.add(new JLabel("Süre (Saniye):"));
	            milkPanel.add(durationField);

	            JButton addButton = new CurvedJButton("Ekle", (Icon) null, 5, 5);
	            addButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    if (selectedAnimal[0] == null) {
	                        JOptionPane.showMessageDialog(null, "Lütfen önce hayvan seçin.");
	                    } else {
	                        Date dateParsed;
	                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	                        try {
	                        	
	                            String date = dateField.getText();
	                            Float quantity = Float.parseFloat(quantityField.getText());
	                            int duration = Integer.parseInt(durationField.getText());

	                            dateParsed = dateFormat.parse(date);
	                            MilkingSession newSession = new MilkingSession(dateParsed, quantity, duration);

	                            selectedAnimal[0].addMilkingSession(newSession);

	                            JOptionPane.showMessageDialog(null, "Sağım Kaydı başarıyla eklendi!");
	                        } catch (ParseException | NumberFormatException e1) {
	                            JOptionPane.showMessageDialog(null, "Hata: " + e1.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
	                        }
	                    }
	                }
	            });

	            JPanel milkAndButtonPanel = new JPanel(new BorderLayout());
	            milkAndButtonPanel.setOpaque(false);
	            milkAndButtonPanel.add(milkPanel, BorderLayout.CENTER);
	            milkAndButtonPanel.add(addButton, BorderLayout.SOUTH);

	            bilgiDetayDetay.add(milkAndButtonPanel, BorderLayout.CENTER);

	            bilgiDetayDetay.revalidate();
	            bilgiDetayDetay.repaint();
	        }
	    });
	    
	    JButton setGebeStatusButton = new CurvedJButton("Gebe Durumu Ayarla", icons[4], 20, 20);
	    setGebeStatusButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (selectedAnimal[0] == null) {
	                JOptionPane.showMessageDialog(null, "Lütfen önce bir hayvan seçin.");
	            } else {
	            	
	                if (!selectedAnimal[0].isCinsiyet()) {
	                    JOptionPane.showMessageDialog(null, "Sadece dişi hayvanların gebe durumu ayarlanabilir.");
	                } else {
	                	if(selectedAnimal[0].isGebe()) {
	                		selectedAnimal[0].setDuve(false);
	                	}
	                    selectedAnimal[0].setGebe(!selectedAnimal[0].isGebe());
	                    String message = selectedAnimal[0].isGebe() ? "Hayvanın gebe durumu başarıyla ayarlandı." : "Hayvanın gebe durumu kaldırıldı.";
	                    JOptionPane.showMessageDialog(null, message);
	                    
	                }
	            }
	        }
	    });


	    
	    JButton addFeed = new CurvedJButton("Yemleme Kaydı Ekle", icons[2], 20, 20);
	    addFeed.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            bilgiDetayDetay.removeAll();
	            bilgiDetayTextArea.setText("");

	            JTextField dateField = new JTextField(20);
	            JTextField feedTypeField = new JTextField(20);
	            JTextField amountField = new JTextField(20);

	            JPanel feedPanel = new JPanel(new GridLayout(3, 2));
	            feedPanel.setOpaque(false);
	            feedPanel.add(new JLabel("Gün (yyyy-MM-dd HH:mm:ss):"));
	            feedPanel.add(dateField);
	            feedPanel.add(new JLabel("Yem Tipi:"));
	            feedPanel.add(feedTypeField);
	            feedPanel.add(new JLabel("Miktar (Kilo):"));
	            feedPanel.add(amountField);

	            JButton addButton = new CurvedJButton("Ekle", (Icon) null, 5, 5);
	            addButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    if (selectedAnimal[0] == null) {
	                        JOptionPane.showMessageDialog(null, "Lütfen önce hayvan seçin.");
	                    } else {
	                        try {
	                            String date = dateField.getText();
	                            String feedType = feedTypeField.getText();
	                            String amountText = amountField.getText();

	                            float amount = Float.parseFloat(amountText);
	                            if (amount <= 0) {
	                                throw new NumberFormatException("Miktar pozitif sayı olmalı.");
	                            }

	                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                            Date dateParsed = dateFormat.parse(date);

	                            FeedLog newFeedLog = new FeedLog(dateParsed, feedType, amount);
	                            selectedAnimal[0].addFeedLog(newFeedLog);

	                            JOptionPane.showMessageDialog(null, "Yemleme Kaydı başarıyla eklendi!");
	                        } catch (NumberFormatException | ParseException ex) {
	                            JOptionPane.showMessageDialog(null, "Hata: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
	                        }
	                    }
	                }
	            });

	            JPanel feedAndButtonPanel = new JPanel(new BorderLayout());
	            feedAndButtonPanel.setOpaque(false);
	            feedAndButtonPanel.add(feedPanel, BorderLayout.CENTER);
	            feedAndButtonPanel.add(addButton, BorderLayout.SOUTH);

	            bilgiDetayDetay.add(feedAndButtonPanel, BorderLayout.CENTER);

	            bilgiDetayDetay.revalidate();
	            bilgiDetayDetay.repaint();
	        }
	    });
	    
	    JButton addAnimalButton = new CurvedJButton("Yeni Hayvan Ekle", icons[3], 20, 20);
	    addAnimalButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            bilgiDetayDetay.removeAll();
	            bilgiDetayTextArea.setText("");

	            JTextField idField = new JTextField(20);
	            JTextField nameField = new JTextField(20);
	            JTextField speciesField = new JTextField(20);
	            JTextField breedField = new JTextField(20);
	            JTextField birthDateField = new JTextField(20);
	            JTextField genderField = new JTextField(20);

	            JPanel animalPanel = new JPanel(new GridLayout(6, 2));
	            animalPanel.setOpaque(false);
	            animalPanel.add(new JLabel("ID:"));
	            animalPanel.add(idField);
	            animalPanel.add(new JLabel("İsim:"));
	            animalPanel.add(nameField);
	            animalPanel.add(new JLabel("Tür:"));
	            animalPanel.add(speciesField);
	            animalPanel.add(new JLabel("Cins:"));
	            animalPanel.add(breedField);
	            animalPanel.add(new JLabel("Doğum Tarihi (yyyy-MM-dd HH:mm:ss):"));
	            animalPanel.add(birthDateField);
	            animalPanel.add(new JLabel("Cinsiyet (Dişi/Erkek):"));
	            animalPanel.add(genderField);

	            JButton addButton = new CurvedJButton("Ekle", (Icon) null, 5, 5);
	            addButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    try {
	                        
	                        int id = Integer.parseInt(idField.getText());
	                        String name = nameField.getText();
	                        String species = speciesField.getText();
	                        String breed = breedField.getText();
	                        String birthDateString = birthDateField.getText();
	                        boolean gender = genderField.getText().equalsIgnoreCase("Dişi"); // Convert to boolean

	                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                        Date birthDate = dateFormat.parse(birthDateString);

	                        Animal newAnimal = new Animal(id, name, species, breed, birthDate, gender);

	                        parentPanel.selectedFarm.addAnimal(newAnimal);

	                        JOptionPane.showMessageDialog(null, "Yeni hayvan başarıyla eklendi!");
	                    } catch (NumberFormatException | ParseException ex) {
	                        JOptionPane.showMessageDialog(null, "Hata: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	            });

	            JPanel animalAndButtonPanel = new JPanel(new BorderLayout());
	            animalAndButtonPanel.setOpaque(false);
	            animalAndButtonPanel.add(animalPanel, BorderLayout.CENTER);
	            animalAndButtonPanel.add(addButton, BorderLayout.SOUTH);

	            bilgiDetayDetay.add(animalAndButtonPanel, BorderLayout.CENTER);

	            bilgiDetayDetay.revalidate();
	            bilgiDetayDetay.repaint();
	        }
	    });
	    
	    JButton removeAnimalButton = new CurvedJButton("Hayvan Kaldır", (Icon) icons[3] , 20, 20);
	    removeAnimalButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (selectedAnimal[0] == null) {
	                JOptionPane.showMessageDialog(null, "Lütfen önce bir hayvan seçin.");
	            } else {

	                int confirmation = JOptionPane.showConfirmDialog(null, selectedAnimal[0].getName() + " adlı hayvanı kaldırmak istediğinize emin misiniz?", "Hayvanı Kaldır", JOptionPane.YES_NO_OPTION);
	                if (confirmation == JOptionPane.YES_OPTION) {

	                	parentPanel.selectedFarm.removeAnimal(selectedAnimal[0]);
	                    JOptionPane.showMessageDialog(null, selectedAnimal[0].getName() + " adlı hayvan başarıyla kaldırıldı.");

	                    selectedAnimal[0] = null;
	                }
	            }
	        }
	    });
	    
	    addingPanelLog.add(removeAnimalButton);
	    addingPanelLog.add(addAnimalButton);
	    addingPanelLog.add(addMilk);
	    addingPanelLog.add(addFeed);
	    addingPanelLog.add(setGebeStatusButton);
	    
	    add(addingPanelLog, BorderLayout.SOUTH);

	    revalidate();
	    repaint();
	}

	private void getTodaysValues() {
		Date currentDate = new Date();
		int animalCount=0;
		toplamSüt=0;
		sürüBeslenmeOrtalaması=0;
		toplamHasta=0;
		for (Animal animal : this.parentPanel.selectedFarm.getAnimals()) {
			animalCount++;
			if(!animal.getDiseases().isEmpty()) {
				toplamHasta++;
			}
			for (MilkingSession session : animal.getMilkingSessions()) {
	            if (isSameDay(session.getDate(),currentDate)) {
	                toplamSüt+=session.getQuantity();
	            }
	        }
			for (FeedLog session : animal.getFeedLogs()) {
	            if (isSameDay(session.getDate(),currentDate)) {
	                sürüBeslenmeOrtalaması+=session.getQuantity();
	            }
	        }
            }
		ortalamaSüt=toplamSüt/animalCount;
		sürüBeslenmeOrtalaması/=animalCount;
		ortalamaHasta=toplamHasta/animalCount;
	}
	 
	public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
               cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }
}
