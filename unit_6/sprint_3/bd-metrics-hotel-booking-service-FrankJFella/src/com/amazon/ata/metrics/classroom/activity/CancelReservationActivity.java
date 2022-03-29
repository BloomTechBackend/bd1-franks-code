package com.amazon.ata.metrics.classroom.activity;

import com.amazon.ata.metrics.classroom.dao.ReservationDao;
import com.amazon.ata.metrics.classroom.dao.models.Reservation;
import com.amazon.ata.metrics.classroom.metrics.MetricsConstants;
import com.amazon.ata.metrics.classroom.metrics.MetricsPublisher;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

import javax.inject.Inject;

/**
 * Handles requests to cancel a reservation.
 */
public class CancelReservationActivity {

    private ReservationDao reservationDao;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a CancelReservationActivity
     * @param reservationDao Dao used to update reservations.
     */
    @Inject
    public CancelReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Cancels the given reservation.
     * Increment the CanceledReservationCount metric
     * Update the ReservationRevenue metric
     *
     * @param reservationId of the reservation to cancel.
     * @return canceled reservation
     */
    public Reservation handleRequest(final String reservationId) {

        // Remove the reservation from the data store
        Reservation response = reservationDao.cancelReservation(reservationId);

        // Increment the CanceledReservationCount metric
        metricsPublisher.addMetric(MetricsConstants.CANCEL_COUNT,1, StandardUnit.Count);

        //  Retrieve the total cost of the reservation from response and convert from BigDecimal to double
        metricsPublisher.addMetric(MetricsConstants.BOOKING_REVENUE,response.getTotalCost().doubleValue(),StandardUnit.None);

        return response;
    }
}
