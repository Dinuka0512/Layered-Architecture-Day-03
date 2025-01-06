package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.*;

public class OrderDAOImpl {

    //HERE GET NEW ORDER ID
    public static String getNewOrderId() throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    //CHECK IS ORDER EXISTS
    public static boolean isOrderExsist(String orderId, Connection connection) throws ClassNotFoundException, SQLException{
        connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);

        if(stm.executeQuery().next()){
            return true;
        }

        return false;
    }
}
