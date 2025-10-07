# Portfolio webpage backend application

Simple backend application for receiving messages from a contact form on a portfolio webpage

## Description

This application handles contact form submissions from a portfolio webpage frontend. It validates incoming form data and returns appropriate error messages for invalid fields. Valid messages are stored in a PostgreSQL database, and an email notification is sent to the site owner through Brevo Email API.

### API Endpoints
| Method | Endpoint | Action |
| --- | --- | --- |
| POST | /submit-message | To submit a message to the database and notify site owner |

Requests are sent with the following JSON-structure: 

**Request Body:**
```json
{
  "name": "Name",
  "email": "name@example.com",
  "message": "Your message here"
}
```

### Responses

* 200 OK - Message was submitted succesfully
* 400 Bad request - Invalid field data (with error details)
* 500 Internal server error - Server error

### Installation Guide

* Clone this repository
* Install all dependencies with Maven
* Set up a database
* Register on https://developers.brevo.com/ and generate your own api key
* Create an .env file in your project root folder and add following variables:

# Database Configuration
DB_URL=your_db_url
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password

# Brevo Email API
BREVO_API_KEY=your_brevo_api_key_here
NOTIFICATION_ADDRESS=your_email_here

# CORS Configuration
CORS_ALLOWED_ORIGINS=http://localhost:3000

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
