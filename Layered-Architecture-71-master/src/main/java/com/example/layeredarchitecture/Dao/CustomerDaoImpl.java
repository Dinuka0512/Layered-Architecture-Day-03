package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDaoImpl {

    //GET CUSTOMER DETAILS
    public static String getCustDetails(String newValue) throws SQLException, ClassNotFoundException{
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
    public static boolean isExsistCustomer(String id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }

    //GET ALL CUSTOMER IDS
    public static ArrayList<String> getAllCustomersIds() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
            ids.add(rst.getString("id"));
        }
        return ids;
    }

    //GET ALL CUSTOMERS
    public ArrayList<CustomerDTO> getAllcustDetails() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        String sql = "SELECT * FROM Customer";
        Statement stm = connection.createStatement();
        ResultSet res = stm.executeQuery(sql);

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
