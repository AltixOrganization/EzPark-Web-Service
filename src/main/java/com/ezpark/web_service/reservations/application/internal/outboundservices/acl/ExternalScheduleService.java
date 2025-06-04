package com.ezpark.web_service.reservations.application.internal.outboundservices.acl;

import com.ezpark.web_service.parkings.interfaces.acl.ScheduleContextFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ExternalScheduleService {
    private final ScheduleContextFacade scheduleContextFacade;

    public ExternalScheduleService(ScheduleContextFacade scheduleContextFacade) {
        this.scheduleContextFacade = scheduleContextFacade;
    }

    public boolean isScheduleAvailable(Long scheduleId) {
        return scheduleContextFacade.isScheduleAvailable(scheduleId);
    }

    public boolean markScheduleAsUnavailable(Long scheduleId) {
        return scheduleContextFacade.markScheduleAsUnavailable(scheduleId);
    }
}
