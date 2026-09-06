# 🚇 MetroParcel

MetroParcel is a Java Full Stack web application designed to manage and track parcel deliveries using a metro-based transportation concept.

The system provides separate portals for customers and administrators, allowing parcels to be booked, monitored, and managed throughout the delivery process.

## ✨ Features

### 👤 Customer Portal

* Book a parcel delivery
* Enter customer and parcel details
* Select source and destination stations
* Track parcel delivery status
* View parcel information

### 🔐 Admin Portal

* Secure admin login
* View all booked parcels
* Search parcels by ID, customer name, or station
* Monitor delivery statistics
* Update parcel delivery status

### 📦 Parcel Tracking

A parcel can move through different delivery stages:

`BOOKED → PICKED_UP → AT_SOURCE_STATION → IN_TRANSIT → AT_DESTINATION_STATION → OUT_FOR_DELIVERY → DELIVERED`

## 📊 Admin Dashboard

The admin dashboard displays:

* Total Parcels
* Booked Parcels
* Parcels In Transit
* Out for Delivery
* Delivered Parcels
* Cancelled Parcels

## 🛠️ Technologies Used

### Backend

* Java
* Spring Boot
* Spring Data JPA
* REST APIs
* Maven

### Frontend

* HTML
* CSS
* JavaScript

### Database

* H2 Database

### Tools

* Eclipse IDE
* Git
* GitHub

## 🏗️ Project Structure

```text
MetroParcel
│
├── src/main/java
│   └── com.metroparcel
│       ├── controller
│       ├── model
│       ├── repository
│       ├── service
│       └── MetroParcelApplication.java
│
├── src/main/resources
│   ├── static
│   └── application.properties
│
└── pom.xml
```

## 🚀 How to Run the Project

1. Clone the repository:

```bash
git clone https://github.com/sushmithad1130-prog/MetroParcel.git
```

2. Open the project in Eclipse or another Java IDE.

3. Make sure Java and Maven are configured.

4. Run:

```text
MetroParcelApplication.java
```

5. Open your browser and access:

```text
http://localhost:8080
```

## 🎯 Project Objective

The objective of MetroParcel is to demonstrate a parcel delivery management system based on the concept of utilizing metro transportation for parcel movement between locations.

The project demonstrates the implementation of:

* Full Stack Development
* RESTful APIs
* CRUD Operations
* Database Integration
* Client-Server Communication
* Parcel Status Management

## 👩‍💻 Developed By

**Sushmitha**

Java Full Stack Developer
