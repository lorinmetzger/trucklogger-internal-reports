package com.trucklogger.internal.reports;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyRegistrationReport {

  private static final Logger logger = LoggerFactory.getLogger(DailyRegistrationReport.class);

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  @Inject 
  private DriverRepository driverRepository;

  @Scheduled(fixedRate = 5000)
  public void reportCurrentTime() {
    Date yesterday = TimeUtil.yesterday();
    Date start = TimeUtil.startOfDay(yesterday);
    Date end = TimeUtil.endOfDay(yesterday);
    logger.info("Date range [" + start + ", " + end + "].");   
  }
}
