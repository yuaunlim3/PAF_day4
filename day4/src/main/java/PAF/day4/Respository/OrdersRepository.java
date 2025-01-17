package PAF.day4.Respository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import PAF.day4.Models.Orders;
import PAF.day4.Models.OrdersDetails;
import PAF.day4.Util.SQL;

@Repository
public class OrdersRepository {
    @Autowired
    private JdbcTemplate template;
    public boolean check(int id){
        int checker = template.queryForObject(SQL.check,Integer.class, id);
        if(checker< 1 ){
            return false;
        }

        return true;
    }
    public boolean insert(Orders order) {
        int orderChecker = template.update(SQL.insertOrder, order.getOrder_date(), order.getCustomer_name(),
                order.getShip_address(), order.getNotes(), order.getTax());

        if (orderChecker < 0) {
            return false;
        }
        return true;
    }

    public boolean insert(OrdersDetails details,int id){
        int checker = template.update(SQL.insertOrderDetails,id, details.getProduct(),details.getUnit_price(),details.getDiscount(),details.getQuantity());
        if(checker < 0){
            return false;
        }
        return true;
    }

    public List<Orders> getOrders() {
        List<Orders> orderList = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SqlRowSet rs = template.queryForRowSet(SQL.getAllOrders);
        while(rs.next()){
            Orders orders = new Orders();
            orders.setOrder_id(rs.getInt("order_id"));
            orders.setOrder_date(LocalDate.parse(rs.getString("order_date"),formatter));
            orders.setCustomer_name(rs.getString("customer_name"));
            orders.setNotes(rs.getString("notes"));
            orders.setShip_address(rs.getString("ship_address"));
            orders.setTax(rs.getDouble("tax"));

            orderList.add(orders);
        }

        return orderList;
    }

    public List<OrdersDetails> getDetails(int id){
        List<OrdersDetails> ordersDetails = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL.getAllDetails,id);

        while(rs.next()){
            OrdersDetails details = new OrdersDetails();
            details.setId(rs.getInt("id"));
            details.setOrder_id(rs.getInt("order_id"));
            details.setProduct(rs.getString("product"));
            details.setUnit_price(rs.getDouble("unit_price"));
            details.setDiscount(rs.getDouble("discount"));
            details.setQuantity(rs.getInt("quantity"));

            ordersDetails.add(details);

        }

        return ordersDetails;
    }
}
