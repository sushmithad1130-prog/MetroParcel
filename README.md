# MetroParcel

A beginner Java full-stack prototype for booking and tracking parcels transported between metro stations.

## Stack
- Java 17
- Spring Boot
- Spring Web / REST
- Spring Data JPA / Hibernate
- H2 database
- HTML, CSS, JavaScript

## Features
- Book a parcel
- View all parcels
- View a parcel by ID
- Update parcel status
- Delete a parcel
- Simple browser UI

## Run
1. Open the project in IntelliJ IDEA or Eclipse as a Maven project.
2. Make sure Java 17+ and Maven are available.
3. Run `MetroParcelApplication`.
4. Open `http://localhost:8080/`.

## Main API endpoints
GET    /api/parcels
GET    /api/parcels/{id}
POST   /api/parcels
PATCH  /api/parcels/{id}/status
DELETE /api/parcels/{id}

Important: This is a learning/personal project. Understand the code before listing it on a resume or discussing it in an interview.
