# Application Documentation

## Table of Contents
- [Introduction](#introduction)
- [Access](#access)
- [Features](#features)
  - [Login](#login)
  - [Order/Delivery](#order-delivery)
  - [User Rating](#user-rating)

## Introduction
Welcome to the documentation for our application. This document provides an overview of the features and functionality of our web application.

## Access
The web app can be accessed from https://localhost:8080 url, not from https://localhost:8080/my-service

## Features

### Login
When a user accesses the web app, they will be redirected to the login page. If they don't have an account, they can create one by clicking the sign-up button located at the bottom right corner.

After logging in, the client will receive a token, which is required for all API calls. Without a valid token, the API endpoints cannot be accessed.

### Order/Delivery
On the order page, users can choose a restaurant from which they want to order. They will then see the menu and can select items and quantities. After selecting the items, they can scroll down to the bottom of the page and provide the delivery address, delivery fee, and any additional comments. By clicking the order button, their order will be placed.

Users can navigate back to the order page and click on the "My Orders" tab to view their orders. The status of each order will be displayed as follows:
- IN_PROGRESS: If someone has taken the order for delivery
- PLACED: If no one has taken the order yet
- DELIVERED: If someone has delivered the order

#### Delivery
If a user wants to deliver an order, they can go to the delivery page. In the "Available Orders" tab, they will see a list of orders. By clicking the "Take Order" button for a specific order, they can claim the delivery task. After refreshing the page, the order will move to the "Taken Orders" tab, where they can view its status.

### User Rating
After an order is complete, users have the ability to rate the deliverer. If a user's rating is lower than 3, they are not allowed to place or take orders.
Also, you can see your rating in the home page.

## Conclusion
This documentation provides an overview of the features available in our web application. Please refer to the specific feature sections for detailed instructions and usage guidelines.

If you have any further questions or need assistance, feel free to contact our support team.

Thank you for using our application!
