package com.trucklogger.internal.reports;

import com.trucklogger.transportation.assets.Driver;

import java.util.List;
import java.util.Date;

import org.springframework.data.repository.Repository;

public interface DriverRepository extends Repository<Driver, Long> {


   List<Driver> findByCreatedDateGreaterThanAndCreatedDateLessThan(Date start, Date end);
}
