package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;
import javafx.scene.control.TableView;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO {
    ArrayList<String> getAllItemID() throws SQLException, ClassNotFoundException;
    ItemDTO itemDetails(String newItemCode) throws SQLException, ClassNotFoundException;
    String genarateItem() throws SQLException, ClassNotFoundException;
    boolean isExistItem(String code) throws ClassNotFoundException, SQLException;
    void updateItem(String description, BigDecimal unitPrice, int qtyOnHand, String code, TableView<ItemTM> tblItems) throws ClassNotFoundException, SQLException;
    void deleteItems(String code) throws ClassNotFoundException, SQLException;
    ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException;
    void saveItem(String code, String description, BigDecimal unitPrice, int qtyOnHand) throws ClassNotFoundException, SQLException;
}
