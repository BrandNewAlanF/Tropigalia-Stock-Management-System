# Tropigália — Stock Management System

Tropigália is a desktop stock management system developed in Java Swing as an academic case study based on real inventory management operations.

The project uses Tropigália, one of the leading wholesale distribution companies in Mozambique, as the business reference due to the scale of its operations, the diversity of its products, and the viability of its inventory management processes.

The objective of the system is to simulate the daily activities involved in stock management, supplier relationships, product replenishment, sales operations, and business monitoring.

## Business Context

Wholesale distribution companies handle large quantities of products with different categories, suppliers, expiration dates, and stock levels.

This project models several real business processes, including:

* Product registration and categorization.
* Stock replenishment through suppliers.
* Sales and stock reduction.
* Supplier management.
* Customer management.
* Low-stock monitoring.
* Product expiration monitoring.
* Operational history tracking.

## Business Rules

The system implements several business rules to ensure operational consistency:

* Products cannot be sold below their purchase price.
* Products with stock levels below the minimum threshold generate replenishment alerts.
* Expired products are identified as hazardous waste and flagged by the system.
* Stock quantities are automatically updated after purchases and sales.
* All important operations are recorded using a stack data structure, allowing operation history tracking.
* Reports provide information about inventory status, suppliers, customers, and alerts.

## Features

* Dashboard with business indicators.
* Inventory management.
* Product registration.
* Supplier management.
* Customer management.
* Stock entry operations.
* Sales registration.
* Alert system.
* Reporting system.
* Operation history using Stack (TAD Pilha).
* File-based persistence.
* Custom Java Swing graphical interface.

## Technologies

* Java
* Java Swing
* Object-Oriented Programming
* Collections Framework
* File Handling
* Data Structures (Stack)
* Custom GUI Components

## Software Architecture

The project is currently being refactored into a modular architecture, separating:

* Business rules
* Validation
* File management
* Inventory operations
* User interface components
* Reports
* Visual styling
* Operation history

This process aims to improve maintainability, scalability, and code organization.

## Motivation

Tropigália was selected as the business case because of its relevance within the Mozambican wholesale distribution sector and the complexity of its inventory operations, making it an excellent environment for studying stock management systems and business rules.

https://github.com/user-attachments/assets/5e7eb85d-ecf7-49a6-8e76-018ba4a7a5fb
