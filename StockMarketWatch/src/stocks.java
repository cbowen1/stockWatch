import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.Document;

public class stocks extends JFrame {
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
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JPopupMenu.Separator jSeparator1;
    private JTable jTable2;
    private JTable jTable3;
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
    //allow the user to sort based of column click
    String lastSort = "";
    String ascOrDsc = "DESC";
    String searchQuery;

    public stocks() {
    	stockSheet = new ExcelODBC("realTimeMarket");
    	getInitialData();
        initComponents();
    }
    @SuppressWarnings("unchecked")                          
    private void initComponents() {    	
        buyPanel = new JPanel();
        jScrollPane3 = new JScrollPane();
        jTable3 = new JTable();
        holdPanel = new JPanel();
        holdScrollPane = new JScrollPane();
        holdTable = new JTable();
        sellPanel = new JPanel();
        jScrollPane2 = new JScrollPane();
        jTable2 = new JTable();
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

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Stock Watch");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(1440, 800));
        setResizable(false);

        buyPanel.setBorder(BorderFactory.createBevelBorder(0));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

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

        //holdPanel.setBackground(new java.awt.Color(255, 255, 0));
        holdPanel.setBorder(BorderFactory.createBevelBorder(0));

        holdTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            stockSheet.getColumnNames()
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

        //sellPanel.setBackground(new java.awt.Color(255, 255, 0));
        sellPanel.setBorder(BorderFactory.createBevelBorder(0));
        
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            stockSheet.getColumnNames()
        ));
        jScrollPane2.setViewportView(jTable2);

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

        //searchField.setText("Begin typing to search...");
        /*searchField.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e){
        		searchField.setText("");
        	}
        })*/;
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
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        searchText.setText("SEARCH");

        stratChooser.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                .addComponent(stratChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(reset))
        );

        file.setText("File");

        jMenuItem1.setText("jMenuItem1");
        file.add(jMenuItem1);
        file.add(jSeparator1);

        exitMenuBut.setText("EXIT");
        exitMenuBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton(evt);
            }
        });
        file.add(exitMenuBut);

        menuBar.add(file);

        edit.setText("Edit");

        jMenuItem3.setText("jMenuItem3");
        edit.add(jMenuItem3);

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
    
    private void exitButton(ActionEvent evt) {     
    	stockSheet.closeConnection();
        System.out.println("Connection Closed");
        System.exit(0);
    }    
    private void resetActionPerformed(ActionEvent evt){
    	searchField.setText("");
    	getInitialData();
    	repaintTable();
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
    	//System.out.println(query);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stocks().setVisible(true);
            }
        });
    }

}
