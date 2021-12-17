package pbo.project.view;

import pbo.project.models.LoginModel;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    LoginModel login = new LoginModel();
    Scanner sc = new Scanner(System.in);

    public Login() throws SQLException {
    }

    public boolean UserLogin() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("===================");
        System.out.println("|   E-LIBRARY     |");
        System.out.println("===================");

        System.out.print("Username : ");
        String username = sc.nextLine();
        System.out.print("Password : ");
        String password = sc.nextLine();
        return login.login(username,password);
    }

    public int getRole(){
        return login.getRole();
    }
    public String getUsername(){
        return login.getUsername_user();
    }
    public int getUserId(){
        return login.getUser_id();
    }

}
