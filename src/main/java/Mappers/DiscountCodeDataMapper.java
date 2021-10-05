package Mappers;

import Classes.DiscountCode.DiscountCode;

import java.sql.*;
import java.util.Optional;

/*
 * Should we have some discount codes already stored when the program starts and then
 * compare it with the one that the user inserts?
 */

public class DiscountCodeDataMapper implements DataMapper{
    Connection conn;
    public DiscountCodeDataMapper(Connection conn, boolean dropTable){
        this.conn = conn;
        Statement stmt;
        try{
            stmt = conn.createStatement();

            if(dropTable)
                stmt.executeUpdate("DROP TABLE IF EXISTS discountCodes");

            stmt.executeUpdate("CREATE TABLE discountCodes ("
                    + "discountCodeId INT NOT NULL AUTO_INCREMENT, "
                    + "isUsed TINYINT, "
                    + "PRIMARY KEY (discountCodeId))");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @Override
    public Optional<DiscountCode> find(int id) {
        DiscountCode d = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT isUsed FROM discountCodes WHERE discountCodeId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                d = new DiscountCode(id, rs.getBoolean(0));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(d);
    }

    @Override
    public void update(Object object) {
        try{
            DiscountCode discountCodeToBeUpdated = (DiscountCode) object;
            PreparedStatement pstmt = conn.prepareStatement("UPDATE discountCodes SET isUsed = ? WHERE discountCodeId = ?;");
            pstmt.setBoolean(1, discountCodeToBeUpdated.isUsed());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(Object object) {
        try{
            DiscountCode discountCodeToBeInserted = (DiscountCode) object;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO discountCodes (isUsed) VALUES (?);");
            pstmt.setBoolean(1, discountCodeToBeInserted.isUsed());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Object object) {
        try{
            DiscountCode discountCodeToBeDeleted = (DiscountCode) object;
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM discountCodes WHERE discountCodeId = ?;");
            pstmt.setInt(1, discountCodeToBeDeleted.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}