package com.trucklogger.internal.reports;

import com.trucklogger.transportation.assets.data.log.LogEvent;

import java.util.List;
import java.util.Date;

import org.springframework.data.repository.Repository;

public interface LogEventRepository extends Repository<LogEvent, Long> {


   List<LogEvent> findByTimeGreaterThan(Long time);
   
   List<LogEvent> findByTimeGreaterThanAndTimeLessThan(Long t1, Long t2);
   
   List<LogEvent> findByAssetUuidAndTimeGreaterThan(String assetUuid, Long time);

}
