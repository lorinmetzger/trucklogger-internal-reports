package com.trucklogger.internal.reports;

import com.trucklogger.transportation.assets.Driver;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface DriverRepository extends Repository<Driver, Long> {


   List<Driver> findByCreatedDateGreaterThanAndCreatedDateLessThan(Long start, Long end);
}
