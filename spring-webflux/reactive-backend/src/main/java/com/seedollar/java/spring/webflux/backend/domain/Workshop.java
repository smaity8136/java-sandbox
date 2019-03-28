package com.seedollar.java.spring.webflux.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Workshop {

  private String name;

  private String address;

  private String contactNumber;

}
