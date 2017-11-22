package com.mengka.springboot.controller;

import com.mengka.springboot.dao.domain.BookDO;
import com.mengka.springboot.dao.persistence.BookDOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

/**
 * @author mengka
 * @date 2017/06/04.
 */
@Slf4j
@Controller
@RequestMapping(value = "/v1")
public class MengkaController {

    @Autowired
    private BookDOMapper bookDOMapper;

    @RequestMapping("/rate")
    public String product(Map<String, Object> model, Long id) {
        log.info("CommonController rate id = {}", id);
        model.put("list", null);

        //add
        BookDO bookDO = new BookDO();
        bookDO.setName("数学");
        bookDO.setPrice(100);
        bookDO.setTenantId("2001");
        bookDOMapper.insert(bookDO);
        log.info("CommonController add a new book! id = {}", bookDO.getId());

        return "product_rate";
    }
}
