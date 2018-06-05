package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import Framework.BSROException;


/**
 * Description : Functional Test Script
 * 
 * @author css89714
 */
public class ProductDBConnection {
	/**
	 * Script Name : <b>ProductDBConnection</b> Generated : <b>Jun 24, 2013
	 * 4:05:09 PM</b> Description : Functional Test Script Original Host : WinNT
	 * Version 6.1 Build 7601 (S)
	 * 
	 * @since 2013/06/24
	 * @author css89714
	 */
	public static Connection getConnection(String argUserName,
			String argPassword) throws BSROException {

		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String connectionURL = "jdbc:oracle:thin:@" + ""
					+ DBConstants.productDBHostName + ":" + ""
					+ DBConstants.productDBConnectionPort + ":" + ""
					+ DBConstants.productDBInstanceName + "";

			connection = DriverManager.getConnection(connectionURL,
					argUserName, argPassword);

		} catch (Exception ex) {

			ex.printStackTrace(); // To change body of catch statement use File
									// | Settings | File Templates.

			throw new BSROException(ex);

		}

		return connection;

	}
}
