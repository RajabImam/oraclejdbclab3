/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pencode.oraclejdbclab3.dao;

import com.pencode.oraclejdbclab3.model.Emp;
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
public class EmpDAO extends DAO<Emp>{

    public EmpDAO(Connection connect) {
        super(connect);
    }

    @Override
    public Emp find(int id) {
         try {
            String sql = "SELECT empno, ename, efirst, job, sal FROM EMP WHERE empno = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Emp emp = new Emp();
                emp.setEmpNo(rs.getLong(1));
                emp.seteName(rs.getString(2));
                emp.seteFirst(rs.getString(3));
                //emp.setEmp(rs.getObject("mgr", Emp.class));
                emp.setJob(rs.getString(4));
                emp.setSal(rs.getInt(5));
                
               return emp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeptDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean create(Emp object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Emp object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Emp object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
