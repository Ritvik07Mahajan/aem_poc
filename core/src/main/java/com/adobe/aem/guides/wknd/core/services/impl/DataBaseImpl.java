package  com.adobe.aem.guides.wknd.core.services.impl;


import com.adobe.aem.guides.wknd.core.services.DataBaseService;
import com.day.commons.datasource.poolservice.DataSourcePool;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;

@Component(service = DataBaseService.class, immediate = true)
public class DataBaseImpl implements DataBaseService {

    private static Logger log = LoggerFactory.getLogger(DataBaseImpl.class);

    @Reference
    private DataSourcePool dataSourcePool;

    @Override
    public Connection makeConnection() {

        try {
            DataSource dataSource = (DataSource) dataSourcePool.getDataSource("SaveAndContinue");
            return dataSource.getConnection();
        } catch (Exception e) {
            log.error("Error retrieving data from accounts: {}", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void getData() throws SQLException {

        Connection con=makeConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM accounts");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String balance = rs.getString("balance");
            String account_number= rs.getString("account_number");

            log.error("Account: ID={}, Name={}, Email={},Account={}", id, name, balance,account_number);
        }
        con.close();
    }

    @Override
    public void insertData() throws SQLException {

        Connection con=makeConnection();
        Statement stmt = con.createStatement();
        // To inset a value into the Table
          String query=String.format("INSERT INTO accounts (name, account_number, balance) VALUES('%s', %s, %s)","yogi", 107, 1001);

          int rowsEffected= stmt.executeUpdate(query);

          if(rowsEffected > 0){
              log.info("Data Inserted");
          }
          else{
              log.info("Data not Inserted");
          }
          con.close();
    }


    @Override
    public void deleteData() throws SQLException {

        Connection con=makeConnection();
        Statement stmt = con.createStatement();
        String query= "DELETE FROM accounts WHERE id=5";

          int rowsEffected= stmt.executeUpdate(query);

          if(rowsEffected > 0){
              log.info("Data Deleted");
          }
          else{
              log.info("Data not Deleted");
          }
          con.close();
    }

    @Override
    public void updateData() throws SQLException {
        Connection con=makeConnection();
        Statement stmt = con.createStatement();

        String query=String.format("UPDATE accounts SET account_number =%s where id=%d",111,12);

          int rowsEffected= stmt.executeUpdate(query);

          if(rowsEffected > 0){
              System.out.println("Data Updated");
          }
          else{
              System.out.println("Data not Updated");
          }
    }
}
