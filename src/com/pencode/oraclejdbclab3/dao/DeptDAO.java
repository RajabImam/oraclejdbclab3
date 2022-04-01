/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pencode.oraclejdbclab3.dao;

import com.pencode.oraclejdbclab3.model.Dept;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAJAB IMAM
 */
public class DeptDAO extends DAO<Dept>{

    public DeptDAO(Connection connect) {
        super(connect);
    }

    @Override
    public Dept find(int id) {
        try {
            String sql = "SELECT deptno, dname, loc FROM DEPT WHERE deptno = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Dept dept = new Dept();
                dept.setDeptno(rs.getInt(1));
                dept.setDname(rs.getString(2));
                dept.setLoc(rs.getString(3));
                
               return dept;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeptDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean create(Dept object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Dept object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Dept object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
