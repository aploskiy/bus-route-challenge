package com.busroute.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectResponse {

  @JsonProperty("dep_sid")
  private final Integer depSid;
  @JsonProperty("arr_sid")
  private final Integer arrSid;
  @JsonProperty("direct_bus_route")
  private final boolean directBusRoute;

  public DirectResponse(Integer depSid, Integer arrSid, boolean directBusRoute) {
    this.depSid = depSid;
    this.arrSid = arrSid;
    this.directBusRoute = directBusRoute;
  }

  public Integer getDepSid() {
    return depSid;
  }

  public Integer getArrSid() {
    return arrSid;
  }

  public boolean isDirectBusRoute() {
    return directBusRoute;
  }

}
