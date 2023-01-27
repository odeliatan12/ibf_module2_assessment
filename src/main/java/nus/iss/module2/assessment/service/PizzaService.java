package nus.iss.module2.assessment.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import nus.iss.module2.assessment.model.OrderDetails;

@Service
public class PizzaService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void saveOrderDetails(final OrderDetails orderDetails) {
        System.out.println("mds >>>>>>>>>>  ");
        redisTemplate.opsForValue().set(orderDetails.getId(), orderDetails.toJson().toString());
    }

    public String findById(final String msid) throws IOException {
        String mdsStr = (String) redisTemplate.opsForValue().get(msid);
        
        return mdsStr;
    }
    
}
