package Mappers;

import Classes.DiscountCode.DiscountCode;

import java.sql.*;
import java.util.Optional;

/*
 * Should we have some discount codes already stored when the program starts and then
 * compare it with the one that the user inserts?
 */

public class DiscountCodeDataMapper {
    Connection conn;
    public DiscountCodeDataMapper(Connection conn){
        this.conn = conn;

    }

    public Optional<DiscountCode> find(long discountCode) {
        DiscountCode d = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT discountCodeId, isUsed FROM discountCodes WHERE discountCode = ?");
            pstmt.setLong(1, discountCode);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                d = new DiscountCode(rs.getInt(1), discountCode, rs.getBoolean(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(d);
    }

    public void update(Object object) {
        try{
            DiscountCode discountCodeToBeUpdated = (DiscountCode) object;
            PreparedStatement pstmt = conn.prepareStatement("UPDATE discountCodes SET discountCode = ?, isUsed = ? WHERE discountCodeId = ?;");
            pstmt.setLong(1, discountCodeToBeUpdated.getDiscountCode());
            pstmt.setBoolean(2, discountCodeToBeUpdated.isUsed());
            pstmt.setInt(3, discountCodeToBeUpdated.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert(Object object) {
        try{
            DiscountCode discountCodeToBeInserted = (DiscountCode) object;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO discountCodes (discountCode, isUsed) VALUES (?, ?);");
            pstmt.setLong(1, discountCodeToBeInserted.getDiscountCode());
            pstmt.setBoolean(2, discountCodeToBeInserted.isUsed());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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