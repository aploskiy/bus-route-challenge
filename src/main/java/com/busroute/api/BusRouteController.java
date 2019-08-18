package com.busroute.api;

import com.busroute.api.dto.DirectResponse;
import com.busroute.service.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BusRouteController {

  @Autowired
  private BusRouteService busRouteService;

  @GetMapping(value = "/direct", params = {"dep_sid", "arr_sid"})
  public DirectResponse direct(@RequestParam("dep_sid") Integer depSid, @RequestParam("arr_sid") Integer arrSid) {
    return busRouteService.direct(depSid, arrSid);
  }

}
