package modele;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import java.util.ArrayList;
/**
 *
 * @author pedago
 */
public class DAODiscount_Code {
    
    protected final DataSource myDataSource;
    
    public DAODiscount_Code(DataSource dataSource){
        this.myDataSource = dataSource;
    }
    
    
    public List<DiscountCode> getDiscountCodeList() throws SQLException{
        String sql = "SELECT * FROM DISCOUNT_CODE";
        List<DiscountCode> listDiscount = null;
        try(Connection connexion = myDataSource.getConnection();
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
                listDiscount = new ArrayList();
                DiscountCode dc;
                while(rs.next()){
                    dc = new DiscountCode(rs.getString("DISCOUNT_CODE"), rs.getFloat("RATE"));
                    listDiscount.add(dc);
                }
            } catch (Exception e){
                Logger.getLogger("DAO").log(Level.SEVERE, null, e);
			throw new SQLException(e.getMessage());
            }
        return listDiscount;
    }
        
    public void addNewDiscountCode(DiscountCode dc) throws SQLException{
        String sql = "INSERT INTO DISCOUNT_CODE VALUES (?, ?)";
        try(Connection connexion = myDataSource.getConnection();
            PreparedStatement pStmt = connexion.prepareStatement(sql)){
            pStmt.setString(1, dc.getCode());
            pStmt.setFloat(2, dc.getRate());
            int updt = pStmt.executeUpdate();
            if(updt==0){
                throw new SQLException();
            }
            
        } catch (Exception e){
            Logger.getLogger("DAO").log(Level.SEVERE, null, e);
            throw new SQLException(e.getMessage());
        }
    }
    
    public void deleteDiscountCode(String dc) throws SQLException{
        String sql = "DELETE FROM DISCOUNT_CODE WHERE Discount_code = ?";
        try(Connection connexion = myDataSource.getConnection();
            PreparedStatement pStmt = connexion.prepareStatement(sql)){
            pStmt.setString(1, dc);
            int updt = pStmt.executeUpdate();
            if(updt==0){
                throw new SQLException();
            }
        }
    }
}
