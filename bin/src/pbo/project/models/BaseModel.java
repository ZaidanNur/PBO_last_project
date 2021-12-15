package pbo.project.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseModel {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    protected Connection connection;
    
    public BaseModel() throws SQLException {
        dbUrl = "jdbc:postgresql://localhost/e_library";
        dbUser = "postgres";
        dbPassword = "123";
        connection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
}
}
