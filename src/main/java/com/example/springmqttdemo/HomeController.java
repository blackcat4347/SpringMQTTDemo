package com.example.springmqttdemo;

import com.example.springmqttdemo.model.MqttSubscribeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Arrays;

@Controller
@RequestMapping("/")
public class HomeController {
    private RestTemplate rest = new RestTemplate();
    @ModelAttribute
    private void getData(Model model){
        List<MqttSubscribeModel> mqttSubscribeModels = Arrays.asList(
                rest.getForObject("http://localhost:8080/api/mqtt/subscribe?topic=/PTIT_Test/p/temp&wait_millis=2000",
                        MqttSubscribeModel[].class));
        System.out.println(mqttSubscribeModels.size());
        String temp = "No Data";
        if (mqttSubscribeModels.size() >0) temp = mqttSubscribeModels.get(mqttSubscribeModels.size()-1).getMessage();
        model.addAttribute("temp",temp);
    }
    @GetMapping
    public String home(){
        return "index.html";
    }



}
