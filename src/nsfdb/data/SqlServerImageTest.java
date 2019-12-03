package nsfdb.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SqlServerImageTest {
	// Declare the JDBC objects.
	Connection connection = null;
	Statement statement = null;

	public static void main(String[] args) {
		new SqlServerImageTest().run();
	}
	
	private SqlServerImageTest() {
		setup();
	}

	private void setup() {
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://;servername=cssql\\sqldata;"
				+ "user=NsfDbDev;password=nDsBf1;" +
			"databaseName=NSFResearch;";

		// Declare the JDBC objects.
		// Establish the connection.
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				
			connection = DriverManager.getConnection(connectionUrl);

			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void run() {
		// TODO Auto-generated method stub
		
		//simpleTest();
		
		//insertIntoDB();
		
		retrieveFromDb();
	}

	private void insertIntoDB() {
		// TODO Auto-generated method stub
		PreparedStatement statement = null;
		FileInputStream imageInputStream = null;
		try {
            // get statement object  
		    statement = connection.prepareStatement("insert into ImageTest" +
			"(ImgID,ImgTitle,ImageBlob) values(4,'Image #4',?)");

		    // create an input stream pointing to the image file to store
			imageInputStream = new FileInputStream(new 
			File("D:\\IST220\\Assignments\\Sample\\ChnDB\\Chinese DB ER Concepts.png"));
                    
			// inform the statement that first parameter in the query is of binary type
		    statement.setBinaryStream(1, imageInputStream);
                    // execute query
		    statement.execute();
		} catch (SQLException sqe) {
			sqe.printStackTrace();;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // close resources 
			if(imageInputStream!=null){
			     try {
					imageInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	//if (statement != null) try { statement.close(); } catch(Exception e) {}
	    	//if (connection != null) try { connection.close(); } catch(Exception e) {}
		}

	}

	private void retrieveFromDb() {
		ResultSet rs = null;
		
    	try {
    
    		// Create and execute an SQL statement that returns some data.
    		String SQL = "SELECT * FROM ImageTest";
    		rs = statement.executeQuery(SQL);
                ResultSetMetaData meta = rs.getMetaData();
                int columns = meta.getColumnCount();
                System.out.println(columns);
                for (int i=1; i<=columns; i++)
                    System.out.print(meta.getColumnName(i) + ", ");
                    
                System.out.println();
    
    		// Iterate through the data in the result set and display it.
                String curRow = "";
    		while (rs.next()) {
                    for (int i=1; i<=columns; i++)
                        System.out.print(rs.getString(i) + 
                                ((i==columns)?"":", "));
    			//System.out.println(rs.getString(2) + " " + rs.getString(1));
    			//System.out.println(rs.wasNull());
                    System.out.println();
    		}
    		
    		//String displaySQL = "select ImageBlob from ImageTest where ImgID = 1";
    		String displaySQL = "select ImageBlob from ImageTest where ImgID = 4";
    		rs = statement.executeQuery(displaySQL);
            while (rs.next()) {
                BufferedImage im = ImageIO.read(rs.getBinaryStream(1));
                ImageIcon ss = new ImageIcon(im);
                //String chnZi = "不";
                String chnZi = "Design";
        		JOptionPane.showConfirmDialog(null, chnZi, "Image Test", 
        				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, 
        				ss);
            }
    		
    	}
    
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null) try { rs.close(); } catch(Exception e) {}
	    	//if (statement != null) try { statement.close(); } catch(Exception e) {}
	    	//if (connection != null) try { connection.close(); } catch(Exception e) {}
		}
	}

	private static void simpleTest() {
		// TODO Auto-generated method stub
		ImageIcon scriptImg = new ImageIcon(
				"C:\\courses\\IST220\\img\\767Dss.png");
		String chnZi = "白";
		// Generates a dialog box to show 
		// - chnZi as "message", and 
		// - its image as "icon"
		JOptionPane.showConfirmDialog(null, chnZi, "Chinese The Right Way", 
				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, 
				scriptImg);
	}

}
