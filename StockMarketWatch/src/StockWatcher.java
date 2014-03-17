import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class StockWatcher extends JFrame {
    // Variables declaration                     
    private JButton reset;
    private JPanel buyPanel;
    private JMenu edit;
    private JMenu file;
    private JPanel holdPanel;
    private JScrollPane holdScrollPane;
    private JTable holdTable;
    private JMenuItem jMenuItem1;
    private JMenuItem exitMenuBut;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JPopupMenu.Separator jSeparator1;
    private JTable sellTable;
    private JTable buyTable;
    private JTable mainTable;
    private JScrollPane mainTablePanel;
    private JMenuBar menuBar;
    private JPanel menuPanel;
    private JButton search;
    private JTextField searchField;
    private JLabel searchText;
    private JPanel sellPanel;
    private JComboBox stratChooser;
    
    ExcelODBC stockSheet;
    String query;
    String lastSort = "";
    String ascOrDsc = "DESC";
    String searchQuery;
    int buy,hold,sell;
    
    Object [][] buyArray;
    Object [][] sellArray;
    Object [][] holdArray;
    
    public StockWatcher() {
    	stockSheet = new ExcelODBC("realTimeMarket");
    	getInitialData();
        initComponents();
    }
    @SuppressWarnings("unchecked")                          
    private void initComponents() {    	
        buyPanel = new JPanel();
        jScrollPane3 = new JScrollPane();
        buyTable = new JTable();
        holdPanel = new JPanel();
        holdScrollPane = new JScrollPane();
        holdTable = new JTable();
        sellPanel = new JPanel();
        jScrollPane2 = new JScrollPane();
        sellTable = new JTable();
        mainTablePanel = new JScrollPane();
        mainTable = new JTable();
        menuPanel = new JPanel();
        searchField = new JTextField();
        search = new JButton();
        searchText = new JLabel();
        stratChooser = new JComboBox();
        reset = new JButton();
        menuBar = new JMenuBar();
        file = new JMenu();
        jMenuItem1 = new JMenuItem();
        jSeparator1 = new JPopupMenu.Separator();
        exitMenuBut = new JMenuItem();
        edit = new JMenu();
        jMenuItem3 = new JMenuItem();
        jMenuItem4 = new JMenuItem();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Stock Watch");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(1440, 800));
        setResizable(false);

        buyPanel.setBorder(BorderFactory.createBevelBorder(0));

        buyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Symbol", "Name", "Last", "Open" , "Close" , "Volume"
            }
        ));
        jScrollPane3.setViewportView(buyTable);

        javax.swing.GroupLayout buyPanelLayout = new javax.swing.GroupLayout(buyPanel);
        buyPanel.setLayout(buyPanelLayout);
        buyPanelLayout.setHorizontalGroup(
            buyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        buyPanelLayout.setVerticalGroup(
            buyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buyPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        holdPanel.setBorder(BorderFactory.createBevelBorder(0));

        holdTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                    "Symbol", "Name", "Last", "Open" , "Close" , "Volume"
                }
        ));
        holdScrollPane.setViewportView(holdTable);

        javax.swing.GroupLayout holdPanelLayout = new javax.swing.GroupLayout(holdPanel);
        holdPanel.setLayout(holdPanelLayout);
        holdPanelLayout.setHorizontalGroup(
            holdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(holdPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(holdScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        holdPanelLayout.setVerticalGroup(
            holdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(holdPanelLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(holdScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sellPanel.setBorder(BorderFactory.createBevelBorder(0));
        
        sellTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                    "Symbol", "Name", "Last", "Open" , "Close" , "Volume"
                }
        ));
        jScrollPane2.setViewportView(sellTable);

        javax.swing.GroupLayout sellPanelLayout = new javax.swing.GroupLayout(sellPanel);
        sellPanel.setLayout(sellPanelLayout);
        sellPanelLayout.setHorizontalGroup(
            sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sellPanelLayout.setVerticalGroup(
            sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellPanelLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        repaintTable();
        mainTablePanel.setViewportView(mainTable);
        menuPanel.setBackground(new java.awt.Color(0, 153, 153));
        mainTable.getTableHeader().addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e){
        		//add ascending or descending option
        		int col = mainTable.columnAtPoint(e.getPoint());
        		String name = mainTable.getColumnName(col);
        		if(lastSort.equals(name)){
        			if(ascOrDsc.equals("ASC")){
        				ascOrDsc = "DESC";
        			}else{
        				ascOrDsc = "ASC";
        			}
        		}
        		String runQuery = query + "ORDER BY " +name+ " ";
        		//query = "SELECT * FROM [Sheet1$] ORDER BY " +name + " ";
        		runQuery += ascOrDsc;
        		stockSheet.query(runQuery);
        		mainTable.setModel(new DefaultTableModel(
        				ExcelODBC.getValues(),ExcelODBC.getColumnNames()));
        		lastSort = name;
        	}
        });
        mainTable.setEnabled(false);

        searchField.getDocument().addDocumentListener(new DocumentListener(){             
            
        	@Override
            public void insertUpdate(DocumentEvent e) {
        		searchQuery = searchField.getText();
        		if(searchQuery != " ")
        			runSearchQuery(searchQuery);
            }               
            @Override
            public void removeUpdate(DocumentEvent e) {
            	searchQuery = searchField.getText();
            	runSearchQuery(searchQuery);
            }                
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        
        search.setText("Search");
        
        search.setVisible(false);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        searchText.setText("SEARCH");

        stratChooser.setModel(new DefaultComboBoxModel(new String[] { "Please Select a Strategy Pattern","Item 1", "Item 2", "Item 3", "Item 4","Random" }));
        stratChooser.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		if(stratChooser.getSelectedItem() != "Please Select a Strategy Pattern"){
        			stratChooser(stratChooser.getSelectedIndex());
        		}	
        	}
        });
       
        reset.setText("RESET");
        reset.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		resetActionPerformed(evt);
        	}
        });
        

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(search)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 515, Short.MAX_VALUE)
                .addComponent(stratChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(searchField)
                .addComponent(search)
                .addComponent(searchText)
                .addComponent(stratChooser, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                .addComponent(reset))
        );

        file.setText("File");

        jMenuItem1.setText("Disconnect");
        jMenuItem1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		//implement action function
        	}
        });
        file.add(jMenuItem1);
        file.add(jSeparator1);

        exitMenuBut.setText("EXIT");
        exitMenuBut.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton(evt);
            }
        });
        file.add(exitMenuBut);

        menuBar.add(file);

        edit.setText("Help");

        jMenuItem3.setText("Strategy Pattern Information");
        edit.add(jMenuItem3);
        jMenuItem4.setText("About");
        edit.add(jMenuItem4);
        
        jMenuItem3.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		stratPatternHelp();
        	}
        });
        jMenuItem4.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		helpButton();
        	}
        });

        menuBar.add(edit);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(holdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sellPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                            .addComponent(mainTablePanel)
                            //EDIT THE 1425 TO modify the width of the main table
                            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1424, Short.MAX_VALUE))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(holdPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buyPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sellPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(93, 93, 93))
        );
        
        super.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                stockSheet.closeConnection();
                System.out.println("Connection Closed");
                System.exit(0);
            }
        });

        pack();
    }                    

	public void getInitialData(){
    	query = "Select * from [Sheet1$]";
    	stockSheet.query(query);
    }
    private void helpButton(){
    	JOptionPane.showMessageDialog(super.getContentPane(), CONSTANTS.ABOUT);
    }
    
    private void stratPatternHelp(){   	
    	JOptionPane.showMessageDialog(super.getContentPane(), CONSTANTS.STRATEGY_PATTERN_INFO);
    }
    
    private void exitButton(ActionEvent evt) {     
    	stockSheet.closeConnection();
        System.out.println("Connection Closed");
        System.exit(0);
    }    
    private void resetActionPerformed(ActionEvent evt){
    	searchField.setText("");
    	getInitialData();
    	repaintTable();
    	buyTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Symbol", "Name", "Last", "Open" , "Close" , "Volume"
                }));
    	holdTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Symbol", "Name", "Last", "Open" , "Close" , "Volume"
                }));
    	sellTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Symbol", "Name", "Last", "Open" , "Close" , "Volume"
                }));
    }
    private void repaintTable(){
    	mainTable.setModel(new DefaultTableModel(
				ExcelODBC.getValues(),ExcelODBC.getColumnNames()));
    }

    private void searchActionPerformed(ActionEvent evt) {                                       
        // TODO add your handling code here:
    }  
    private void runSearchQuery(String passedQuery){
    	query = "Select * from [Sheet1$] where NAME like '%" + passedQuery + "%'";
    	stockSheet.query(query);
    	repaintTable();
    }
    
    private void stratChooser(int i) {
    	buy = 0;
    	hold = 0;
    	sell = 0;
    	Stock[] stocks = stockSheet.getStockArray();
		for (Stock currStock: stocks) {
			switch(i) {
			case 1:
				currStock.whatToDo(new BuyIncSellDec());
				break;
			case 2:
				currStock.whatToDo(new BuyDecSellInc());				
				break;
			case 3:
				currStock.whatToDo(new BuyCharSellTime());
				break;
			case 4:
				currStock.whatToDo(new BuyPrimeSellEven());
				break;
			case 5:
				currStock.whatToDo(new BuyTestSellTest());
				break;
			default:
				System.err.println("Error in strategy chooser");
				break;
			}
			
			if (currStock.getBsh() == BSH.BUY) {
				buy++;
			} else if (currStock.getBsh() == BSH.SELL) {
				sell++;
			} else {
				hold++;
			}
		}
		buyHoldSell(stocks);
	}
    private void buyHoldSell(Stock[] stocks){
    	int buyTemp=0;
    	int sellTemp=0;
    	int holdTemp=0;
    	buyArray = new Object [buy][10];
    	sellArray = new Object [sell][10];
    	holdArray = new Object [hold][10];
    	Object [][] smallBuy = new Object [buy][6];
    	Object [][] smallSell = new Object [sell][6];
    	Object [][] smallHold = new Object [hold][6];
    	for (Stock currStock: stocks) {
    		if(currStock.getBsh()==BSH.BUY){
    			columnsInArray(buyArray,currStock,buyTemp);
    			smallArray(smallBuy,currStock,buyTemp);
    			buyTemp++;
    		}else if(currStock.getBsh()==BSH.SELL){
    			columnsInArray(sellArray,currStock,sellTemp);
    			smallArray(smallSell,currStock,sellTemp);
    			sellTemp++;
    		}else{
    			columnsInArray(holdArray,currStock,holdTemp);
    			smallArray(smallHold,currStock,holdTemp);
    			holdTemp++;
    		}
    	}
    	buyTable.setModel(new DefaultTableModel(
				smallBuy,
				new String [] {"Symbol", "Name", "Last", "Open" , "Close" , "Volume"}){
    		private static final long serialVersionUID = 1L;

            @Override
    		public boolean isCellEditable(int row,int column){
    			return false;
    		}
    	});
    	sellTable.setModel(new DefaultTableModel(
				smallSell,
				new String [] {"Symbol", "Name", "Last", "Open" , "Close" , "Volume"}){
    		private static final long serialVersionUID = 1L;

        	@Override
			public boolean isCellEditable(int row,int column){
				return false;
        	}
		});
    	holdTable.setModel(new DefaultTableModel(
				smallHold,
				new String [] {"Symbol", "Name", "Last", "Open" , "Close" , "Volume"}){
    		private static final long serialVersionUID = 1L;

        	@Override
			public boolean isCellEditable(int row,int column){
				return false;
        	}
        });
    	
    	if(buyTemp >= 1){
    		buyTable.addMouseListener(new MouseAdapter(){
    			public void mouseClicked(MouseEvent e){
    				if(e.getClickCount()==2){
    					JTable target = (JTable)e.getSource();
        				int row = target.getSelectedRow();
    					printMessage(buyArray,row,"buy");
    				}
    			}
    		});
    	}
    	if(sellTemp >= 1){
    		sellTable.addMouseListener(new MouseAdapter(){
    			public void mouseClicked(MouseEvent e){
    				if(e.getClickCount()==2){
    					JTable target = (JTable)e.getSource();
        				int row = target.getSelectedRow();
    					printMessage(sellArray,row,"sell");
    				}
    			}
    		});
    	}
    	if(holdTemp >= 1){
    		holdTable.addMouseListener(new MouseAdapter(){
    			public void mouseClicked(MouseEvent e){
    				if(e.getClickCount()==2){
    					JTable target = (JTable)e.getSource();
        				int row = target.getSelectedRow();
    					printMessage(holdArray,row,"hold");
    				}
    			}
    		});
    	}
    }
    private void printMessage(Object[][] array,int row,String doWhat){
    	String moreInfo;
    	moreInfo = "<html><b>Symbol</b>::" + array[row][0] + "\n";
    	moreInfo += "<html><b>Name</b>:: "+ array[row][1] + "\n";
    	moreInfo += "<html><b>Last</b>::$" + array[row][2] + "\n";
    	moreInfo += "<html><b>Open</b>::$" + array[row][3] + "\n";
    	moreInfo += "<html><b>Close</b>::$" + array[row][4] + "\n";
    	moreInfo += "<html><b>High</b>::$" + array[row][7] + "\n";
    	moreInfo += "<html><b>Low</b>::$" + array[row][8] + "\n";
    	moreInfo += "<html><b>Volume</b>::" + array[row][9] + "\n";
    	moreInfo += "\n<html><b>Last Updated</b>::\n";
    	moreInfo += "<html><b>Time</b>::" + array[row][5];
    	moreInfo += "<html><b>Date</b>::" + array[row][6];
    	
    	switch (doWhat){
		case "buy":
			moreInfo += "<html><b>BUY!</b>";
			break;
		case "sell":
			moreInfo += "<html><b>SELL!</b>";
			break;
		case "hold":
			moreInfo += "<html><b>HOLD!</b>";
			break;
	}
    	moreInfo +="</html>";
    	JOptionPane.showMessageDialog(super.getContentPane(), moreInfo);
    }
    
    private static void smallArray(Object[][] data,Stock currStock,int i){
    	data[i][0]=currStock.getSymbol();
    	data[i][1]=currStock.getName();
    	data[i][2]=currStock.getLast();
    	data[i][3]=currStock.getOpen();
    	data[i][4]=currStock.getClose();
    	data[i][5]=currStock.getVolume();
    }

    /*
     * This function allows us to insert all information into a given array
     */
    public static void columnsInArray(Object[][] data,Stock currStock,int i){
    	data[i][0]=currStock.getSymbol();    	
    	data[i][1]=currStock.getName();
    	data[i][2]=currStock.getLast();
    	data[i][3]=currStock.getOpen();
    	data[i][4]=currStock.getClose();
    	data[i][5]=currStock.getTime();
    	data[i][6]=currStock.getDate();
    	data[i][7]=currStock.getHigh();
    	data[i][8]=currStock.getLow();
    	data[i][9]=currStock.getVolume();
    }
    
    private void addListenerToTable(final JTable table,String tableName){
    	System.out.println(table.getName());
    	table.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e){
    			if(e.getClickCount()==2){
    				JTable target = (JTable)e.getSource();
    				System.out.println(target.getName());
    				int row = target.getSelectedRow();
    				String moreInfo = "Symbol:";
    				moreInfo = moreInfo+sellArray[row][0];
    				JOptionPane.showMessageDialog(table.getParent(), moreInfo);
    				System.out.println("You clicked ROW::"+row);
    			}
    		}
    	});
    }
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StockWatcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StockWatcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StockWatcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StockWatcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StockWatcher().setVisible(true);
            }
        });
    }

}
