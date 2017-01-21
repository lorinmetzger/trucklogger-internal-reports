package com.trucklogger.internal.reports;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class FileMarker
{

  public boolean isMarkerOlderThan(String marker, long timestamp)
  {
    File file = new File(marker);
    return (file.exists() && (file.lastModified() < timestamp));
  }
 
  public void createMarker(String marker) throws Exception
  {
    FileUtils.touch(new File(marker));
  }
 
} 
