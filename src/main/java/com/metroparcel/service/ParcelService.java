package com.metroparcel.service;

import com.metroparcel.model.Parcel;
import com.metroparcel.repository.ParcelRepository;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ParcelService {

    private final ParcelRepository repository;

    public ParcelService(ParcelRepository repository) {
        this.repository = repository;
    }

    // Get all parcels
    public List<Parcel> getAll() {

        updateParcelStatuses();

        return repository.findAll();
    }

    // Get parcel by ID
    public Parcel getById(Long id) {

        updateParcelStatuses();

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Parcel not found with ID: " + id
                        )
                );
    }

    // Create new parcel booking
    public Parcel create(Parcel parcel) {

        LocalDateTime bookingTime = LocalDateTime.now();

        parcel.setStatus("BOOKED");

        parcel.setBookingTime(bookingTime);

        parcel.setCurrentLocation(
                "Booking confirmed. Waiting for delivery partner."
        );

        LocalDateTime estimatedTime =
                bookingTime.plusMinutes(15);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd MMM yyyy, hh:mm a"
                );

        parcel.setEstimatedDelivery(
                estimatedTime.format(formatter)
        );

        return repository.save(parcel);
    }

    // Update parcel status manually
    public Parcel updateStatus(Long id, String status) {

        Parcel parcel = getById(id);

        parcel.setStatus(status);

        updateCurrentLocation(parcel, status);

        return repository.save(parcel);
    }

    // Cancel parcel only before pickup
    public Parcel cancelParcel(Long id) {

        Parcel parcel = getById(id);

        if (!parcel.getStatus().equals("BOOKED")) {

            throw new RuntimeException(
                    "Parcel cannot be cancelled after pickup."
            );
        }

        parcel.setStatus("CANCELLED");

        parcel.setCurrentLocation(
                "Booking cancelled by customer."
        );

        return repository.save(parcel);
    }

    // Automatic parcel journey
    private void updateParcelStatuses() {

        List<Parcel> parcels =
                repository.findAll();

        LocalDateTime now =
                LocalDateTime.now();

        for (Parcel parcel : parcels) {

            /*
             * Fix old parcels that don't have
             * booking time or tracking information
             */
            if (parcel.getBookingTime() == null) {

                parcel.setBookingTime(now);

                parcel.setStatus("BOOKED");

                parcel.setCurrentLocation(
                        "Booking confirmed. Waiting for delivery partner."
                );

                LocalDateTime estimatedTime =
                        now.plusMinutes(15);

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern(
                                "dd MMM yyyy, hh:mm a"
                        );

                parcel.setEstimatedDelivery(
                        estimatedTime.format(formatter)
                );

                repository.save(parcel);

                continue;
            }

            // Don't update cancelled or delivered parcels
            if (parcel.getStatus().equals("CANCELLED")
                    || parcel.getStatus().equals("DELIVERED")) {

                continue;
            }

            long minutesPassed =
                    Duration.between(
                            parcel.getBookingTime(),
                            now
                    ).toMinutes();

            String newStatus;

            if (minutesPassed < 2) {

                newStatus = "BOOKED";

            } else if (minutesPassed < 4) {

                newStatus = "PICKED_UP";

            } else if (minutesPassed < 6) {

                newStatus = "AT_SOURCE_STATION";

            } else if (minutesPassed < 10) {

                newStatus = "IN_TRANSIT";

            } else if (minutesPassed < 12) {

                newStatus =
                        "AT_DESTINATION_STATION";

            } else if (minutesPassed < 15) {

                newStatus =
                        "OUT_FOR_DELIVERY";

            } else {

                newStatus = "DELIVERED";
            }

            parcel.setStatus(newStatus);

            updateCurrentLocation(
                    parcel,
                    newStatus
            );

            repository.save(parcel);
        }
    }

    // Update parcel location according to status
    private void updateCurrentLocation(
            Parcel parcel,
            String status
    ) {

        switch (status) {

            case "BOOKED":

                parcel.setCurrentLocation(
                        "Booking confirmed. Waiting for delivery partner."
                );

                break;

            case "PICKED_UP":

                parcel.setCurrentLocation(
                        "Parcel has been picked up by the delivery partner."
                );

                break;

            case "AT_SOURCE_STATION":

                parcel.setCurrentLocation(
                        "Parcel has reached "
                                + parcel.getSourceStation()
                                + " Metro Station."
                );

                break;

            case "IN_TRANSIT":

                parcel.setCurrentLocation(
                        "Travelling via metro from "
                                + parcel.getSourceStation()
                                + " towards "
                                + parcel.getDestinationStation()
                                + "."
                );

                break;

            case "AT_DESTINATION_STATION":

                parcel.setCurrentLocation(
                        "Parcel has reached "
                                + parcel.getDestinationStation()
                                + " Metro Station."
                );

                break;

            case "OUT_FOR_DELIVERY":

                parcel.setCurrentLocation(
                        "Parcel is out for final delivery."
                );

                break;

            case "DELIVERED":

                parcel.setCurrentLocation(
                        "Parcel successfully delivered to the customer."
                );

                break;

            case "CANCELLED":

                parcel.setCurrentLocation(
                        "Booking cancelled by customer."
                );

                break;

            default:

                parcel.setCurrentLocation(
                        "Tracking information is being updated."
                );
        }
    }

    // Delete parcel
    public void delete(Long id) {

        repository.deleteById(id);
    }
}