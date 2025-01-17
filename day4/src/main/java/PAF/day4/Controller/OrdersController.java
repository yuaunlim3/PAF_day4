package PAF.day4.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PAF.day4.Models.Orders;
import PAF.day4.Models.OrdersDetails;
import PAF.day4.Models.exception.ResourceNotFoundException;
import PAF.day4.Service.OrdersService;

@Controller
@RequestMapping({ "/", "index.html" })
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping()
    public String start(Model model) {
        List<Orders> orderList = ordersService.getAll();
        model.addAttribute("orderList",orderList);
        return "add";
    }

    @PostMapping("/orderList")
    public String insertOrders(@RequestBody MultiValueMap<String,String> form, Model model){
        try{
            Orders order = new Orders();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate order_date = LocalDate.parse(form.getFirst("order_date"),formatter);

            String customer_name = form.getFirst("customer_name");
            String ship_address = form.getFirst("ship_address");
            String notes = form.getFirst("note");
            if(notes==null || notes.isEmpty()){
                notes = "NA";
            }
            double taxes = Double.parseDouble(form.getFirst("tax"));

            order.setOrder_date(order_date);
            order.setNotes(notes);
            order.setCustomer_name(customer_name);
            order.setShip_address(ship_address);
            order.setTax(taxes);

            Boolean checker = ordersService.insertOrder(order);

            if(checker){
                return "success";
            }

            else{
                throw new ResourceNotFoundException();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            return "add";
        }
    }

    @PostMapping("/details")
    public String insertDetails(@RequestBody MultiValueMap<String,String> form, Model model, @RequestParam("id") int id){
        OrdersDetails ordersDetails = new OrdersDetails();
        String product = form.getFirst("product");
        Double unit_price = Double.parseDouble(form.getFirst("unit_price"));
        Double discount = Double.parseDouble(form.getFirst("discount"));
        int quantity = Integer.parseInt(form.getFirst("quantity"));

        ordersDetails.setDiscount(discount);
        ordersDetails.setProduct(product);
        ordersDetails.setQuantity(quantity);
        ordersDetails.setUnit_price(unit_price);

        boolean checker = ordersService.insertDetails(ordersDetails,id);
        if(checker){
            return "success";
        }

        else{
            throw new ResourceNotFoundException();
        }
    }

    @GetMapping("/order/{order_id}")
    public String getOrderDetails(@PathVariable String order_id,Model model){
        List<OrdersDetails> ordersDetails = ordersService.getDetails(Integer.parseInt(order_id));

        model.addAttribute("detailList", ordersDetails);
        model.addAttribute("id",order_id);
        return "addDetails";
    }

}
