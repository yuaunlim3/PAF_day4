package PAF.day4.Models;

import java.sql.Date;
import java.time.LocalDate;

public class Orders {
    private int order_id;
    private LocalDate order_date;
    private String customer_name;
    private String ship_address;
    private String notes;
    private double tax;

    
    public Orders() {}
    public int getOrder_id() {return order_id;}
    public void setOrder_id(int order_id) { this.order_id = order_id;}

    public LocalDate getOrder_date() { return order_date;}
    public void setOrder_date(LocalDate order_date) { this.order_date = order_date; }

    public String getCustomer_name() { return customer_name;}
    public void setCustomer_name(String customer_name) {this.customer_name = customer_name;}

    public String getShip_address() {return ship_address; }
    public void setShip_address(String ship_address) { this.ship_address = ship_address;}

    public String getNotes() {return notes;}
    public void setNotes(String notes) {this.notes = notes;}

    public double getTax() {return tax;}
    public void setTax(double tax) { this.tax = tax;}
    @Override
    public String toString() {
        return "Orders [order_id=" + order_id + ", order_date=" + order_date + ", =" + customer_name
                + ", ship_address=" + ship_address + ", notes=" + notes + ", tax=" + tax + "]";
    }

    

    
}
