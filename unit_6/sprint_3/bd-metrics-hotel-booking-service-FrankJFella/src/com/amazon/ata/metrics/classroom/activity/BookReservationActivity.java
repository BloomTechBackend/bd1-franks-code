package com.amazon.ata.metrics.classroom.activity;

import com.amazon.ata.metrics.classroom.dao.ReservationDao;
import com.amazon.ata.metrics.classroom.dao.models.Reservation;
import com.amazon.ata.metrics.classroom.metrics.MetricsPublisher;
import com.amazon.ata.metrics.classroom.metrics.MetricsConstants;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

import javax.inject.Inject;

/**
 * Handles requests to book a reservation.
 */
public class BookReservationActivity {

    private ReservationDao   reservationDao;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a BookReservationActivity
     * @param reservationDao Dao used to create reservations.
     */
    @Inject
    public BookReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao   = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Creates a reservation with the provided details.
     * Increment the BookedReservationCount metric
     *
     * @param reservation Reservation to create.
     * @return
     */
    public Reservation handleRequest(Reservation reservation) {

        // Create a Reservation in the data store
        Reservation response = reservationDao.bookReservation(reservation);

        // Increment the BookedReservationCount metric
        // Update the ReservationRevenueMetric metric

        //                                classname.enumname
        metricsPublisher.addMetric(MetricsConstants.BOOKED_RESERVATION_COUNT,1,StandardUnit.Count);

        //  Retrieve the total cost of the reservation from response and convert from BigDecimal to double
        metricsPublisher.addMetric(MetricsConstants.BOOKING_REVENUE,response.getTotalCost().doubleValue(),StandardUnit.None);

        return response;
    }
}
