/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblorderdetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.tblmeal.TblMealDAO;
import sample.tblmeal.TblMealDTO;
import sample.utils.DBUtilities;

/**
 *
 * @author Administrator
 */


public class TblOrderDetailDAO implements Serializable {

    List<OrderDetail> orderList;
    List<TblOrderDetailDTO> listOrderDetail;

    public List<TblOrderDetailDTO> getListOrderDetail() {
        return listOrderDetail;
    }

    public List<OrderDetail> getOrderList() {
        return orderList;
    }

    public int changeStatus(String orderId, int no, String newStatus, String cookId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.makeConnection();
            String sql;
            Time time = null;
            if (newStatus.equals("ready")) {
                time = new Time(System.currentTimeMillis());
            }
            sql = "UPDATE tblOrderDetail SET status = ?, cookID = ?, readyTime = ? WHERE orderID = ? AND no = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, newStatus);
            stm.setString(2, cookId);
            stm.setTime(3, time);
            stm.setString(4, orderId);
            stm.setInt(5, no);
            return stm.executeUpdate();

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean insertOrderDetail(TblOrderDetailDTO dto, String orderID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "Insert into "
                        + "tblOrderDetail(orderID, no, mealID, quantity, takenTime, readyTime, cookID, status) "
                        + "Values(?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setInt(2, dto.getNo());
                stm.setString(3, dto.getMeal().getId());
                stm.setInt(4, dto.getQuantity());
                stm.setTime(5, dto.getTakenTime());

                if (dto.getReadyTime() == null) {
                    stm.setNull(6, java.sql.Types.TIME);
                } else {
                    stm.setTime(6, dto.getReadyTime());
                }
                stm.setString(7, dto.getCookID());
                stm.setString(8, dto.getStatus());

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void loadOrderDetailByOrderID(String orderID)
            throws SQLException, NamingException {
        // load toàn bộ order detail của order này
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "Select * From tblOrderDetail Where orderID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderId = rs.getString("orderID");
                    int no = rs.getInt("no");

                    String mealID = rs.getString("mealID");
                    TblMealDAO dao = new TblMealDAO();
                    TblMealDTO meal = dao.loadMeal(mealID);

                    int quantity = rs.getInt("quantity");
                    Time takenTime = rs.getTime("takenTime");
                    Time readyTime = rs.getTime("readyTime");
                    String status = rs.getString("status");
                    String cookID = rs.getString("cookID");
                    TblOrderDetailDTO dto
                            = new TblOrderDetailDTO(no, meal, quantity, takenTime, readyTime, status, cookID);
                    if (listOrderDetail == null) {
                        listOrderDetail = new ArrayList<>();
                    }
                    listOrderDetail.add(dto);
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean updateOrderDetailQuantity(TblOrderDetailDTO dto, int quantity, String orderID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "Update tblOrderDetail "
                        + "Set quantity = ?"
                        + "Where orderID = ? and no = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, orderID);
                stm.setInt(3, dto.getNo());

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void getList() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            String sql = "SELECT o.orderID AS OrderID, o.no, m.name AS MealName, "
                    + "m.unit AS MealUnit,  o.quantity, c.name AS Category, o.takenTime, o.status "
                    + "FROM tblOrderDetail o, "
                    + "(SELECT * "
                    + "FROM tblMeal) m, "
                    + "(SELECT * "
                    + "FROM tblCategory) c "
                    + "WHERE o.cookID = m.id AND c.id = m.cateID AND (o.status LIKE 'ordered' OR o.status LIKE 'cooking')";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (orderList == null) {
                orderList = new ArrayList<OrderDetail>();
            }
            while (rs.next()) {
                String orderId = rs.getString("OrderID");
                int no = rs.getInt("no");
                String mealName = rs.getString("MealName");
                String mealUnit = rs.getString("MealUnit");
                BigDecimal quantity = rs.getBigDecimal("quantity");
                String cate = rs.getString("Category");
                Time takenTime = rs.getTime("takenTime");
                String status = rs.getString("status");
                OrderDetail order = new OrderDetail(orderId, no, mealName, mealUnit, quantity, cate, takenTime, status);
                orderList.add(order);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }
}
