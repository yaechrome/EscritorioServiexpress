package bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection getConexion() {
        Connection connection = null;
        try {
            String driverClassName = "oracle.jdbc.driver.OracleDriver";
            String driverUrl = "jdbc:oracle:thin:@mydbinstance.cal8mkx5xh77.us-east-2.rds.amazonaws.com/ORCL";
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(
                    driverUrl, "TALLER_SERVIEXPRESS", "TALLER_SERVIEXPRESS");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
