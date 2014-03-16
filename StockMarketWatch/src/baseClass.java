import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class baseClass extends JFrame implements ActionListener{
	ExcelODBC stockSheet;
	
	JButton queryCheck;
	JButton sortByCurr;
	static JTable table;
	static JScrollPane tableScrollPane;

	String query;
	
    public baseClass() {
    	//stockSheet = new ExcelODBC("updateStocks");
    	stockSheet = new ExcelODBC("realTimeMarket");
        initComponents();
        getInitialData();
    }
                      
    private void initComponents() {
    	queryCheck = new JButton();
    	sortByCurr = new JButton();
    	table = null;
    	tableScrollPane = null;
    	
    	super.setLayout(new FlowLayout());
    	super.setPreferredSize(new Dimension(1080,800));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);

    	queryCheck.setText("DISPLAY");
    	sortByCurr.setText("SORT BY CURRENT");
    	sortByCurr.addActionListener(this);
    	queryCheck.addActionListener(this);
    
    	add(queryCheck);
    	add(sortByCurr);
    	
    	super.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                stockSheet.closeConnection();
                System.out.println("Connection Closed");
                System.exit(0);
            }
        });
    	
    	
        pack();
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
            java.util.logging.Logger.getLogger(baseClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(baseClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(baseClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(baseClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new baseClass().setVisible(true);
            }
        });
    }

    public void getInitialData(){
    	query = "Select * from [Sheet1$]";
    	//tableScrollPane = stockSheet.runQuery(query);
    	super.add(tableScrollPane);
    	super.pack();
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "DISPLAY":
			System.out.println("disp");
			query = "Select * from [Sheet1$]";
			super.remove(tableScrollPane);
			//tableScrollPane = stockSheet.runQuery(query);
			super.add(tableScrollPane);
			break;
		case "SORT BY CURRENT":
			System.out.println("curr");
			query = "Select * from [Sheet1$] ORDER BY \"LAST\" DESC";
			super.remove(tableScrollPane);
			//tableScrollPane = stockSheet.runQuery(query);
			
			super.add(tableScrollPane);
			break;
		}
			super.pack();
		
		
	}
	public static JTable getDisplayTable(){
		return table;
	}
	public static JScrollPane getTableScrollPane(){
		return tableScrollPane;
	}

}
