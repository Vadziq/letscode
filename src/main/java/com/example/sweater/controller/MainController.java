package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.TestClass;
import com.example.sweater.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.sweater.repos.MessageRepo;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model)
    {

        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        TestClass message = new TestClass(1,"text1", "tag1");
        Iterable<Message> messages = messageRepo.findAll();
//        System.out.println(" GetMapping " + messages);

//        System.out.println(" TestClass " + message.getId() + " " + message.getText() + " " + message.getTag());
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag, user);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
//        System.out.println(" PostMapping multi " + messages);
        //System.out.println(" MessageRepo " + messageRepo.findById(2));
//        System.out.println(" New message " + message.getId() + " " + message.getText() + " " + message.getTag());
        model.put("Messages",messages);
//        model.put("Messages",messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        Iterable<Message> messages2;
        //messageRepo.deleteAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        messages2 = messages;
        System.out.println(" Filtered " + messages2);
        model.put("messages", messages2);

        return "main";
    }

//    @PostMapping
//    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
////        Message message = new Message(text, tag);
//        TestClass testClass = new TestClass(text, tag);
////        messageRepo.save(message);
////        Iterable<Message> messages = messageRepo.findAll();
////        System.out.println(" PostMapping multi " + messages);
//        System.out.println(" PostMapping single " + testClass);
//        model.put("Messages",testClass);
//        return "main";
//    }

}

