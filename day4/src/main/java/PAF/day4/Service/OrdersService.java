package PAF.day4.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import PAF.day4.Models.Orders;
import PAF.day4.Models.OrdersDetails;
import PAF.day4.Models.exception.ResourceNotFoundException;
import PAF.day4.Respository.OrdersRepository;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    public boolean insertOrder(Orders order){
        return ordersRepository.insert(order);
    }

    public List<Orders> getAll(){
        return ordersRepository.getOrders();
    }

    
    @Transactional(rollbackFor = ResourceNotFoundException.class)
    public boolean insertDetails(OrdersDetails ordersDetails,int id){
        if(!ordersRepository.check(id)){
            throw new ResourceNotFoundException(String.format("%d not in database", id));
        }
        return ordersRepository.insert(ordersDetails,id);
    }


    @Transactional(rollbackFor = ResourceNotFoundException.class)
    public List<OrdersDetails> getDetails(int id){
        if(!ordersRepository.check(id)){
            throw new ResourceNotFoundException(String.format("%d not in database", id));
        }
        return ordersRepository.getDetails(id);
    }


}
