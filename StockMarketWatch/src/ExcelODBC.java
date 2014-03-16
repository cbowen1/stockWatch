import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 
public class ExcelODBC {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	static String [] columnNames;
	static Object [][]dataValues;
	
	public ExcelODBC(String fileName) {
		String newName;
		newName = "jdbc:odbc:"+fileName;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection(newName);
		} catch (Exception e) {
			System.err.print("Exception: ");
            System.err.println(e.getMessage());
		}
	}
	public void closeConnection(){
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Connection unable to close");
			e.printStackTrace();
		}
	}
	public JTable runQuery(String query){ 
		boolean col=true;
		int colNum = 0;
		
		JTable table = baseClass.getDisplayTable();
		JScrollPane scrollPane = baseClass.getTableScrollPane();
		
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            columnNames = new String[numberOfColumns];
            //Make datavalues a static private with getter so everything else can access
            String [][]dataValues;
            //Create a constant file, program 17 to be the num of stocks watching
            dataValues = new String[CONSTANTS.NUMBEROFSTOCKS][numberOfColumns];
            
            while (rs.next()) {
            	if(col){
            		for(int j = 1;j<numberOfColumns+1;j++){
            			columnNames[j-1] = rsmd.getColumnName(j);
            		}
            	}
            	
                for (int i = 1; i <= numberOfColumns; i++) {
                	String columnValue = rs.getString(i);
                	//System.out.println(columnValue);
                	dataValues[colNum][i-1] = columnValue;
                    
                }
                colNum++;
                
                col = false;
            }
            table = new JTable( dataValues, columnNames );
        	scrollPane = new JScrollPane( table );
        	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 
            st.close(); 
        } catch (Exception ex) {   
        }
        return table;
    }
	public static String[] getColumnNames() {
		return columnNames;
	}
	public void query(String query) {
		boolean col=true;
		int colNum = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            columnNames = new String[numberOfColumns];
            dataValues = new String[CONSTANTS.NUMBEROFSTOCKS][numberOfColumns];
            while (rs.next()) {
            	if(col){
            		for(int j = 1;j<numberOfColumns+1;j++){
            			columnNames[j-1] = rsmd.getColumnName(j);
            		}
            	}
            	
                for (int i = 1; i <= numberOfColumns; i++) {
                	String columnValue = rs.getString(i);
                	dataValues[colNum][i-1] = columnValue;
                }
                colNum++;
                
                col = false;
            }
            st.close(); 
        } catch (Exception ex) {   
        }		
	}
	public static Object[][] getValues() {
		return dataValues;
	}

}