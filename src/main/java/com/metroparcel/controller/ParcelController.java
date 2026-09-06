package com.metroparcel.controller;

import com.metroparcel.model.Parcel;
import com.metroparcel.service.ParcelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parcels")
@CrossOrigin
public class ParcelController {

    private final ParcelService service;

    public ParcelController(ParcelService service) {
        this.service = service;
    }


    // Get all parcels
    @GetMapping
    public List<Parcel> getAll() {

        return service.getAll();

    }


    // Get parcel by ID
    @GetMapping("/{id}")
    public Parcel getById(
            @PathVariable Long id) {

        return service.getById(id);

    }


    // Create a new parcel booking
    @PostMapping
    public ResponseEntity<Parcel> create(
            @Valid @RequestBody Parcel parcel) {

        return ResponseEntity.ok(
                service.create(parcel)
        );

    }


    // Update parcel status
    @PatchMapping("/{id}/status")
    public Parcel updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        return service.updateStatus(
                id,
                body.get("status")
        );

    }


    // Cancel parcel
    @PatchMapping("/{id}/cancel")
    public Parcel cancelParcel(
            @PathVariable Long id) {

        return service.cancelParcel(id);

    }


    // Delete parcel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();

    }

}