package com.trucklogger.internal.reports;

import it.ozimov.springboot.templating.mail.model.Email;
import it.ozimov.springboot.templating.mail.model.impl.EmailImpl;
import it.ozimov.springboot.templating.mail.model.impl.EmailAttachmentImpl;
import it.ozimov.springboot.templating.mail.service.EmailService;

import java.nio.charset.Charset;
import javax.mail.internet.InternetAddress;
import com.google.common.collect.Lists;
import com.trucklogger.transportation.assets.Driver;
import com.trucklogger.transportation.assets.data.log.LogEvent;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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

  @Inject
  private LogEventRepository logEventRepository;

  @Inject
  private EmailService emailService;

  @Scheduled(fixedRate = 15000)
  public void reportCurrentTime() {
    Date yesterday = TimeUtil.yesterday();
    Date start = TimeUtil.startOfDay(yesterday);
    Date end = TimeUtil.endOfDay(yesterday);
    List<Driver> drivers = driverRepository.findByCreatedDateGreaterThanAndCreatedDateLessThan(start, end);
    logger.info("Date range [" + start + ", " + end + "] found [" + drivers.size() + "].");   
    List<LogEvent> events = logEventRepository.findByTimeGreaterThan(
        (start.getTime() - (7 * 24 * 60 * 60000)));
    Set<String> active = new HashSet<String>();
    if( events != null )
    {
      for(LogEvent event : events)
      {
        active.add( event.getUuid() );
      }
    }
    logger.info("Active users in the past 7 days. [" + active.size() + "].");
try {
    final Email email = EmailImpl.builder()
      .from(new InternetAddress("lmetzger@trucklogger.com", "Lorin's Rebot"))
      .to(Lists.newArrayList(new InternetAddress("lorinmetzger@gmail.com", "Lorin Metzger")))
      .subject("Daily Report")
      .attachments(new ArrayList<EmailAttachmentImpl>())
      .body("")//Empty body
      .encoding(Charset.forName("UTF-8")).build();
    //Defining the model object for the given Freemarker template
    final Map<String, Object> modelObject = new HashMap<>();
    modelObject.put("name", "Lorin");
    modelObject.put("date", "Jan 19th");
    modelObject.put("registrations", String.format("%d", drivers.size()));
    modelObject.put("dailyusers", "120");
    modelObject.put("weeklyusers", String.format("%d", active.size()));
    emailService.send(email, "daily_report.ftl", modelObject);
} catch(Exception exp) {
  logger.error("", exp);
}
  }
}
