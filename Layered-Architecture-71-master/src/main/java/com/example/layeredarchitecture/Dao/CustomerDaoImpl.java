package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDAO{

    //GET CUSTOMER DETAILS
    @Override
    public String getCustDetails(String newValue) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, newValue + "");
        ResultSet rst = pstm.executeQuery();
        if(rst.next()){
            CustomerDTO customerDTO = new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));
            return rst.getString("name");
        }
        return null;
    }

    //CHECK IS CUSTOMER EXISTS
    @Override
    public boolean isExsistCustomer(String id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }

    //GET ALL CUSTOMER IDS
    @Override
    public ArrayList<String> getAllCustomersIds() throws SQLException, ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString("id"));
        }
        return ids;
    }

    //GET ALL CUSTOMERS
    @Override
    public ArrayList<CustomerDTO> getAllcustDetails() throws SQLException, ClassNotFoundException {
        ResultSet res = SQLUtil.execute("SELECT * FROM Customer");

        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        while(res.next()){
            CustomerDTO dto = new CustomerDTO(
                    res.getString("id"),
                    res.getString("name"),
                    res.getString("address")
            );

            customerDTOS.add(dto);
        }

        return customerDTOS;
    }

}
