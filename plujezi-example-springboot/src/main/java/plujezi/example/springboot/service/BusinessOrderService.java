package plujezi.example.springboot.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import plujezi.example.springboot.controller.bean.BusinessOrder;
import plujezi.example.springboot.controller.defination.BusinessType;

import java.util.Map;

@Slf4j
@Service
public class BusinessOrderService {

    public Map<String, String> create(BusinessOrder businessOrder){
        log.info(JSON.toJSONString(businessOrder));
        Map<String, String> response = Maps.newHashMap();
        response.put("success", "true");
        response.put("respCode", "000000");
        response.put("respMsg", "succeed");
        return response;
    }

}
