package com.okrawczy.restaurantsfinder.utils;

import com.okrawczy.restaurantsfinder.domain.Reservation;
import com.okrawczy.restaurantsfinder.domain.ReservationStatus;
import com.okrawczy.restaurantsfinder.repository.ReservationRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Olaf on 2017-11-20.
 */

@Component
public class StatusManagerTask {

    private static final Logger log = Logger.getLogger(StatusManagerTask.class);

    @Autowired
    private ReservationRepository reservationRepository;

    @Scheduled(fixedDelay=60000)
    public void setStatusToClosed() {
        log.info("Looking for old reservations...");
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        Date todayStart = calendar.getTime();

        calendar.setTime(today);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);

        Date todayEnd = calendar.getTime();
        Date now = new Date();

        List<Reservation> reservations = this.reservationRepository.findByReservationDateAfterAndReservationDateBefore(todayStart, todayEnd);

        for (Reservation reservation: reservations) {
            if ((reservation.getReservationDate().getTime() + ((long)Reservation.TIME_SLOT_DURATION)*3600*1000) <= now.getTime()){
                reservation.setReservationStatus(ReservationStatus.CLOSED);
                log.info("Reservation [" + reservation.getId() + "] set as CLOSED");
                this.reservationRepository.save(reservation);
            }
        }

    }

}
