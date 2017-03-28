package plujezi.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import plujezi.example.springboot.controller.bean.BusinessOrder;
import plujezi.example.springboot.service.BusinessOrderService;
import sun.util.resources.cldr.es.CalendarData_es_PY;

@Slf4j
@Controller
public class PaymentController {

    private BusinessOrderService businessOrderService;

    @Autowired
    public PaymentController(BusinessOrderService businessOrderService){
        this.businessOrderService = businessOrderService;
    }

    @RequestMapping(value = "/order")
    @ResponseBody
    public String order(@RequestBody BusinessOrder businessOrder){
        return JSON.toJSONString(businessOrderService.create(businessOrder));
    }
}
