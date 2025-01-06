package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;
import javafx.scene.control.TableView;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO{
    //SAVE
    public void saveItem(String code, String description, BigDecimal unitPrice, int qtyOnHand) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDbConnection().getConnection();
                PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
                pstm.setString(1, code);
                pstm.setString(2, description);
                pstm.setBigDecimal(3, unitPrice);
                pstm.setInt(4, qtyOnHand);
                pstm.executeUpdate();
    }

    //LOAD TABLE
    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException{
        /*Get all items*/
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        while (rst.next()) {
            ItemDTO itemDTO = new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand"));

            itemDTOS.add(itemDTO);
        }

        return itemDTOS;
    }

    //DELETE
    public void deleteItems(String code) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
        pstm.executeUpdate();
    }

    //UPDATE
    public void updateItem(String description, BigDecimal unitPrice, int qtyOnHand, String code, TableView<ItemTM> tblItems) throws ClassNotFoundException, SQLException{
        /*Update Item*/
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, description);
        pstm.setBigDecimal(2, unitPrice);
        pstm.setInt(3, qtyOnHand);
        pstm.setString(4, code);
        pstm.executeUpdate();

        ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
        selectedItem.setDescription(description);
        selectedItem.setQtyOnHand(qtyOnHand);
        selectedItem.setUnitPrice(unitPrice);
    }

    //HERE CHECK IS EXIST
    public boolean isExistItem(String code) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();
    }

    //GENERATE ITEM ID
    public String genarateItem() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    //GET ITEM DETAILS
    public ItemDTO itemDetails(String newItemCode) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");
        ResultSet rst = pstm.executeQuery();
        if(rst.next()){
            return new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand"));
        }

        return null;
    }

    //HERE GET ALL ITEM IDS
    public ArrayList<String> getAllItemID() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");

        ArrayList<String> ids = new ArrayList<>();
        while(rst.next()){
            ids.add(rst.getString("code"));
        }

        return ids;
    }
}
