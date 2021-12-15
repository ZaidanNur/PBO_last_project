package pbo.project.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginModel extends BaseModel {
    private int admin_status = 0;
    private String username_user ="";
    private int user_id = 0;
    private final String get_user = "select * from users";
    private final String add_user = "insert into users (username,password,adminship) values (?,?,0)";
    public LoginModel() throws SQLException {
        super();
    }

    public void tambahAkun (String username,String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(this.add_user);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        preparedStatement.executeUpdate();



    }

    public boolean login(String username, String password) throws SQLException {
        boolean login = false;
        PreparedStatement preparedStatement = connection.prepareStatement(this.get_user);
        ResultSet rs = preparedStatement.executeQuery();



        while(rs.next()){
            String cekUsername = rs.getString("username");
            String cekPassword = rs.getString("password");
            if(Objects.equals(cekUsername, username)){
                if(Objects.equals(password, cekPassword)){
                    login = true;
                    this.admin_status = rs.getInt("adminship");
                    this.username_user = rs.getString("username");
                    this.user_id = rs.getInt("id_users");
                }
            }
        }
        return login;
    }

    public int getRole() {
        return this.admin_status;
    }

    public String getUsername_user() {
        return username_user;
    }

    public int getUser_id() {
        return user_id;
    }
}
