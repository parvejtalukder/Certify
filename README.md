# Certify - Digital Certificate Management System

Certify is a comprehensive web-based certificate management platform designed to streamline the issuance, verification, and management of digital certificates. Built with modern enterprise technologies, this system provides a secure, scalable solution for organizations to manage certificates efficiently while maintaining audit trails and user access controls.

## Project Vision

In an increasingly digital world, certificate management has become critical for organizations across education, training, and professional sectors. Certify addresses the need for a centralized, secure platform that enables authorized administrators to issue certificates, verify authenticity, and maintain comprehensive audit records—all through an intuitive web interface.

## Key Features

### Security & Access Control
- **Role-Based Access Control (RBAC)**: Three-tier authentication system with Super Admin, Admin, and User (Public) roles
- **Secure Authentication**: Session-based login with username and password validation

### Certificate Management
- **Certificate Issuance**: Streamlined process for creating and registering new certificates
- **Digital Verification**: Quick certificate verification using unique certificate IDs
- **Certificate Details**: Rich metadata storage including issuer information, recipient details, and issue dates
- **PDF Support**: Integration with Apache PDFBox for PDF certificate generation and processing

### User Management
- **Multi-Level Administration**: Super Admin can create new admin accounts; Admins can manage certificates
- **User Profiles**: Complete user information management with email and authentication credentials
- **Role-Based Filtering**: Administrators view only their issued certificates; Super Admins see all certificates

### Data Management
- **Pagination Support**: Efficient browsing of large certificate collections with 5 certificates per page
- **MongoDB Integration**: NoSQL database for flexible and scalable certificate storage

## Technology Stack
### Backend
- **Framework**: Spring Boot 3.2.5
- **Language**: Java 17
- **Build Tool**: Maven
- **Database**: MongoDB with Spring Data MongoDB
- **PDF Processing**: Apache PDFBox 3.0.2
- **Template Engine**: Thymeleaf
- **Validation**: Jakarta Bean Validation

### Frontend
- **Template Framework**: Thymeleaf (Server-side rendering)
- **CSS Framework**: Tailwind CSS for responsive, modern UI
- **Session Management**: Jakarta Servlet HttpSession

### Development Tools
- **Version Control**: Git with GitHub
- **Build**: Spring Boot Maven Plugin with Maven Wrapper (mvnw)
- **Testing**: JUnit 5 with Spring Boot Test
