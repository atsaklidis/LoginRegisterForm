/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.sumit.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Tsak
 */
public class CustomerDAOImpl implements CustomerDAO {
    static Connection con;
    static PreparedStatement ps;
    
    @Override
    public int insertCustomer(Customer c){
        int status=0;
        try{
           con= MyConnectionProvider.getCon();
           
           String query = "insert into customer values(?,?,?,?,?)";
           ps= con.prepareStatement(query);
           ps.setString(1, c.getUsername());
           ps.setString(2, c.getFirstName());
           ps.setString(3, c.getLastName());
           ps.setString(4, c.getEmail());
           ps.setString(5, c.getPassword());
           status= ps.executeUpdate();
           con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return status;
    }
    
    @Override
    public Customer getCustomer(String username){
        Customer c=new Customer();
        try{
            con = MyConnectionProvider.getCon();
            
            ps= con.prepareStatement("SELECT * FROM dbloginregister.customer where username=?");
            ps.setString(1, username);
        
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                c.setUsername(rs.getString(1));
                c.setFirstName(rs.getString(2));
                c.setLastName(rs.getString(3));
                c.setEmail(rs.getString(4));
                c.setPassword(rs.getString(5));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return c;
    }
}
