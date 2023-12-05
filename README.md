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

Ghost Delivery is an on-campus food delivery platform for UNIST members. Our product suggests a solution for students and other university members who do not want to go to restaurants on campus or convenience stores by themselves. Because, when studying, students are considerably more inclined to prefer meals delivered immediately to their location rather than leaving their study space. The reasons for that is that sometimes it is inconvenient for people to waste time for walking to restaurant or cafeteria and stand in line waiting for the food.

When it comes to having a lunch or a dinner, UNIST members mainly have 3 options: eating at university cafeteria, going to restaurant at campus or ordering food outside from the campus. Our product will supplement those options, by giving people an opportunity to order food from their liked restaurant directly to their location. 

To use our product, a person should firstly register to our platform. Then, when a user orders a food from one of the restaurants or convenience stores, couriers who are near to that location get a notification and can accept that order. Also, any specific details can be discussed in a built in messenger which connects a buyer and a courier. And regarding couriers, anyone who registered to the platform can work as a courier. Adding on campus delivery system is a win win situation: students can use time efficiently by ordering a food, and restaurants will have another stream of revenue.

## Features
**Authorization:** The first feature of our product is authorization. It can secure a delivery product and protect client data. Asking users to create a different username and password provides them additionally with their special ID number which helps individuals to acquire access to their delivery service. This functionality is particularly important for goods that deal with private information like payment or personal information. Users can access the majority of the services on our website with the aid of this feature.

**Rating system:** We and our customers can both benefit from a rating system. With this system, customers can provide feedback on their delivery experience and rate their courier (and vice versa), allowing for the user's rating to be considered during their next delivery request. Additionally, customers will be able to view the ratings of other users, and those with higher ratings will be seen as more trustworthy. This will help ensure a high-quality delivery experience for all parties involved. 

**Map/coordinates:** Using maps in delivery items is a great feature that gives clients tracking. Our product can provide consumers with an honest and trustworthy view of the delivery status by including maps in the product. Also, users can input their location by just choosing the location from the map instead of typing it. 

**Messenger:** For communication between delivery cpuriers and clients, messaging is a powerful resource. This product allows user communication and provides updates on the delivery status and expected delivery times by integrating a messaging option in a delivery product. By improving customer happiness, this feature may ultimately boost client loyalty and retention.

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

## Conclusion
This documentation provides an overview of the features available in our web application. Please refer to the specific feature sections for detailed instructions and usage guidelines.

If you have any further questions or need assistance, feel free to contact our support team.

Thank you for using our application!
