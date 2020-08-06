package com.ns.feign;

import com.ns.entity.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "menu")
@Component
public interface MenuFeign {
    @GetMapping("/menu/findAll/{index}/{limit}")
    ResultData findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);
}
