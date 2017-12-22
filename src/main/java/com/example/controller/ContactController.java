package com.example.controller;

import com.example.entity.Contact;
import com.example.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by tianfeng on 2017/4/16.
 */
@Controller
@RequestMapping(value = "/contacts")
@Api("联系人管理的api")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "或得主页", httpMethod = "GET")
    public String home(Map<String, Object> model) {
        System.out.println("the root is accessed...");
        model.put("contacts", contactService.findAll());
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "保存联系人", httpMethod = "GET")
    public String submit(Contact contact) {
        System.out.println("the param is " + contact.toString());
        System.out.println("the root is accessed...");
        return "redirect:/";
    }

    @RequestMapping("/cache")
    public ResponseEntity<String> cache(HttpServletRequest request, //为了方便测试，此处传入文档最后修改时间
                                        @RequestParam("millis") long lastModifiedMillis,
                                        //浏览器验证文档内容是否修改时传入的Last-Modified
                                        @RequestHeader(value = "If-Modified-Since", required = false) Date ifModifiedSince) {
        //当前系统时间
        long now = System.currentTimeMillis();
        //文档可以在浏览器端/proxy上缓存多久
        long maxAge = 20;

        //判断内容是否修改了，此处使用等值判断
        if (ifModifiedSince != null && ifModifiedSince.getTime() == lastModifiedMillis) {
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        }

        DateFormat gmtDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        String body = "<a href=''>点击访问当前链接</a>";
        MultiValueMap<String, String> headers = new HttpHeaders();
        //文档修改时间
        headers.add("Last-Modified", gmtDateFormat.format(new Date(lastModifiedMillis)));
        //当前系统时间
        headers.add("Date", gmtDateFormat.format(new Date(now)));
        //过期时间 http 1.0支持
        headers.add("Expires", gmtDateFormat.format(new Date(now + maxAge)));
        //文档生存时间 http 1.1支持
        headers.add("Cache-Control", "max-age=" + maxAge);
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }
}
