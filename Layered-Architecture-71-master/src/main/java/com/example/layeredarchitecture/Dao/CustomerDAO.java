package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    String getCustDetails(String newValue) throws SQLException, ClassNotFoundException;
    boolean isExsistCustomer(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomersIds() throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> getAllcustDetails() throws SQLException, ClassNotFoundException;
}
