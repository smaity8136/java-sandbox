package com.seedollar.java.spring.webflux.backend.service.transformer;

import org.springframework.stereotype.Component;

import com.seedollar.java.spring.webflux.backend.common.util.Transformer1;
import com.seedollar.java.spring.webflux.backend.domain.Workshop;
import com.seedollar.java.spring.webflux.backend.domain.dto.WorkshopResponse;

@Component
public class WorkshopTransformer implements Transformer1<WorkshopResponse, Workshop> {

  @Override
  public Workshop apply(WorkshopResponse workshopResponse) {
    return Workshop.builder().name(workshopResponse.getName()).address(workshopResponse.getAddress()).contactNumber(workshopResponse.getContactNumber()).build();
  }
}
