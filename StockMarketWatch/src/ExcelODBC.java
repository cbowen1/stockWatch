import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 
public class ExcelODBC {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	static String [] columnNames;
	static Object [][]dataValues;
	private int rowCount;
	private int numberOfColumns;
	
	Stock [] stocks;
	
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

	private void resizeData(int rows){
		Object [][] temp = dataValues;
		dataValues = new Object[rows][numberOfColumns];
		for(int i = 0;i<rows;i++){
			for(int j = 0;j<numberOfColumns;j++){
				dataValues[i][j] = temp[i][j];
			}
		}	
	}
	public static String[] getColumnNames() {
		return columnNames;
	}
	public void query(String query) {
		boolean col=true;
		int colNum = 0;
		rowCount = 0;
        try {
        	String date;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            numberOfColumns = rsmd.getColumnCount();
            columnNames = new String[numberOfColumns];
            dataValues = new String[CONSTANTS.NUMBEROFSTOCKS][numberOfColumns];
            while (rs.next()) {
            	if(col){
            		for(int j = 1;j<numberOfColumns+1;j++){
            			columnNames[j-1] = rsmd.getColumnName(j);
            		}
            		col=false;
            	}
                for (int i = 1; i <= numberOfColumns; i++) {
                	String columnValue = rs.getString(i);
                	/*
                	 * When fetching the date/time from the excel spreadsheet
                	 * there is an arbitrary string appended to the end. This will
                	 * parse the data and remove the string, normalizing the data.
                	 */
                	if(i == 6 || i == 7){
                		date = columnValue.substring(1);
                		date=date.replace("\"_x000D_", "");
                		columnValue = date;
                	}
                	dataValues[colNum][i-1] = columnValue;
                	
                }
                colNum++;
                rowCount++;
                col = false;
               
            }
            st.close(); 
        } catch (Exception ex) {   
        }
        resizeData(rowCount);
        convertToStock();       
	}
	public static Object[][] getValues() {
		return dataValues;
	}
	public void convertToStock(){
		stocks = new Stock[rowCount];
		for(int i=0;i<rowCount;i++){
			stocks[i] = new Stock(dataValues[i][0],dataValues[i][1],dataValues[i][2],dataValues[i][3],
					dataValues[i][4],dataValues[i][5],dataValues[i][6],dataValues[i][7],
					dataValues[i][8],dataValues[i][9]);
		}
	}
	public Stock[] getStockArray(){
		return stocks;
	}
}