package com.busroute.service;

import com.busroute.api.dto.DirectResponse;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BusRouteService {

  private final List<Set<Integer>> routes;

  @Autowired
  public BusRouteService(ApplicationArguments arguments) {
    routes = BusRouteFileParser.parse(arguments);
  }

  public DirectResponse direct(Integer depSid, Integer arrSid) {
    return new DirectResponse(depSid, arrSid, isDirectRoute(depSid, arrSid));
  }

  private boolean isDirectRoute(Integer depSid, Integer arrSid) {
    Set<Integer> stops = Sets.newHashSet(depSid, arrSid);
    return routes.stream().anyMatch(stationIds -> stationIds.containsAll(stops));
  }

}
