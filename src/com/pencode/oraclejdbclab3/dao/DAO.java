/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pencode.oraclejdbclab3.dao;

import java.sql.Connection;

/**
 *
 * @author RAJAB IMAM
 */
public abstract class DAO<T> {
    protected Connection connect = null;
	
	public DAO(Connection connect) {
		this.connect = connect;
	}
	
	public abstract T find(int id);
	
	public abstract boolean create(T object);
	
	public abstract boolean update(T object);
	
	public abstract boolean delete(T object);

}
