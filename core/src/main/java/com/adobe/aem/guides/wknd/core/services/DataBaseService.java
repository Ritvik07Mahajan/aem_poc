package  com.adobe.aem.guides.wknd.core.services;


import com.day.commons.datasource.poolservice.DataSourceNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataBaseService {
    public Connection makeConnection() throws DataSourceNotFoundException;

    public  void getData() throws SQLException;

    public void insertData() throws SQLException;

    public void deleteData() throws SQLException;

    public void updateData() throws SQLException;
}
