package com.ezpark.web_service.parkings.interfaces.acl;

public interface ScheduleContextFacade {
    boolean isScheduleAvailable(Long scheduleId);
    boolean markScheduleAsUnavailable(Long scheduleId);
}
