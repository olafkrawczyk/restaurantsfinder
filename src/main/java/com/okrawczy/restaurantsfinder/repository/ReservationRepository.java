package com.okrawczy.restaurantsfinder.repository;

import com.okrawczy.restaurantsfinder.domain.Reservation;
import com.okrawczy.restaurantsfinder.domain.ReservationStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

/**
 * Created by Olaf on 2017-10-09.
 */

@RepositoryRestResource
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

    List<Reservation> findByReservationDateAfterAndReservationDateBeforeAndRestaurant_IdAndTable_Seats(Date afterDate, Date beforeDate, Long restaurantId, int tableSeats);
    List<Reservation> findByReservationStatusAndRestaurant_Id(ReservationStatus status, Long restaurantId);
    List<Reservation> findByReservationDateAfterAndReservationDateBeforeAndReservationStatus(Date afterDate, Date beforeDate, ReservationStatus status);
    Reservation findReservationById(Long id);

    List<Reservation> findReservationsByClientId(long id);
    List<Reservation> findReservationsByRestaurant_Id(Long restaurantId);
}
