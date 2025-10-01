# Portfolio webpage backend application

Simple backend application for receiving messages from a contact form on a portfolio webpage

## Description

This application handles contact form submissions from a portfolio webpage frontend. It validates incoming form data and returns appropriate error messages for invalid fields. Valid messages are stored in a PostgreSQL database, and an email notification is sent to the site owner.

## Tech stack

### Language

* Java 17

### Framework

* Spring Boot 3.5.5

### Database

* PostgreSQL -  database for production
* H2 - database for testing

### External APIs

* Brevo Email API
