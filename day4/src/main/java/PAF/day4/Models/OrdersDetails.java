package PAF.day4.Models;

public class OrdersDetails {
    private String product;
    private double unit_price;
    private double discount;
    private int quantity;
    private int order_id;
    private int id;

    public OrdersDetails() {   }

    public String getProduct() { return product;    }
    public void setProduct(String product) {this.product = product;   }

    public double getUnit_price() { return unit_price;  }
    public void setUnit_price(double unit_price) {   this.unit_price = unit_price;   }

    public double getDiscount() {    return discount;   }
    public void setDiscount(double discount) {     this.discount = discount;   }

    public int getQuantity() {     return quantity;   }
    public void setQuantity(int quantity) {    this.quantity = quantity;   }

    public int getOrder_id() {  return order_id;   }
    public void setOrder_id(int order_id) {this.order_id = order_id;}

    public int getId() {return id;}
    public void setId(int id) {  this.id = id; }
    
    
}
