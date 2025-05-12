package com.ezpark.web_service.parkings.interfaces.acl;

import java.time.LocalTime;

public interface ScheduleContextFacade {
    boolean doesScheduleEncloseTimeRange(String day, LocalTime startTime, LocalTime endTime);
}
