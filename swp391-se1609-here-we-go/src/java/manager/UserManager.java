/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import utils.DBUtils;

/**
 *
 * @author baolo
 */
public class UserManager {
    public static User getUserById(long id) throws SQLException{
        Connection cn = null;
        User us = null;
        try{
            cn = DBUtils.getConnection();
            if (cn != null){
                String sql = "select * from [User] where userId = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setLong(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs!= null && rs.next()){
                    us = new User(id, rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getInt(5), rs.getString(6));
                }
                if (rs!= null) rs.close();
                if (pst!= null) pst.close();
            }
        } catch(Exception e){
            
        } finally{
            if (cn!= null) cn.close();
        }
        return us;
    }
}