# Parking Manager

Parking Manager is a web application that allows managing gate passes for vehicles to enter a parking. From experiences in a previous job, where managing parking spaces and issuing parking permits was time-consuming, this application was created to improve the process of managing parking spaces at a construction site. Unfortunately, it was never implemented in that company as I ended my collaboration earlier.

The application allows users to create an account, choose the company they belong to, and add cars. For each car, the user can request only one gate pass. The administrators manage the application, and they have access to an administrative panel where they can add companies and accept or reject gate pass requests.

Parking Manager can be used as a parking management application. Application its built-in REST API, it's also possible to extend it with a mobile application that would allow managing multiple parking lots at once.

# Technologies:

    Java 21
    Spring Boot 3.0.4
    Vaadin 24
    JPA
    Spring Security
    PostgreSQL
    Lombok
    JUnit
    Mockito
    Maven
    Hibernate

# Getting Started

## Prerequisites
- Java 21 or later
- Maven 3.6 or later

## Running the Application
1. Clone the repository
2. Run `mvn clean install` to build the application
3. Run `mvn spring-boot:run` to start the application
4. Open your browser and navigate to `http://localhost:8080`

## Default Users
- **Admin**: `admin` / `test` (password)
- **User**: `user` / `test` (password)

## H2 Database Console
For local development, you can access the H2 database console at:
`http://localhost:8080/h2-console`

## Production Deployment

### 🚀 Quick Deploy to Server
We've included automated deployment to `parking.zaitis.dev` using GitHub Actions.

#### Files included:
- `.github/workflows/deploy.yml` - Automated deployment workflow
- `DEPLOYMENT.md` - Complete step-by-step deployment guide
- `deployment/setup-server.sh` - Server setup automation script
- `deployment/gatepass.service` - Systemd service template
- `deployment/nginx.conf` - Nginx configuration template

#### Quick Setup:
1. **Server Setup**: Run the automated setup script on your server:
   ```bash
   curl -O https://raw.githubusercontent.com/Zaitis/GatePass/master/deployment/setup-server.sh
   sudo bash setup-server.sh
   ```

2. **GitHub Secrets**: Add these secrets to your GitHub repository:
   - `HETZNER_HOST` - Your server IP
   - `HETZNER_USER` - `deploy`
   - `HETZNER_PASSWORD` - `deploy123`
   - `GH_TOKEN` - Your GitHub personal access token
   - `DATABASE_URL` - `jdbc:postgresql://localhost:5432/gatepass`
   - `DATABASE_USER` - `gatepass_user`
   - `DATABASE_PASSWORD` - Your PostgreSQL password

3. **Deploy**: Push to master branch to trigger automatic deployment:
   ```bash
   git push origin master
   ```

4. **Access**: Your application will be available at `http://parking.zaitis.dev`

#### For detailed instructions, see [DEPLOYMENT.md](./DEPLOYMENT.md)

### Manual Production Setup
To deploy to production with PostgreSQL manually:
1. Set the following environment variables:
   - `DATABASE_URL`
   - `DATABASE_USER`
   - `DATABASE_PASSWORD`
2. Run with production profile: `mvn spring-boot:run -Dspring-boot.run.profiles=production`

---

## 🏗️ Architecture

### Database Schema
- **Employee** ↔ **Company** (Many-to-One)
- **Employee** ↔ **Car** (One-to-Many)  
- **Car** ↔ **GatePass** (One-to-One)

### Security
- Spring Security with BCrypt password encoding
- Role-based access control (USER/ADMIN)
- Session-based authentication

### API Endpoints
- REST API available for all entities
- Vaadin UI for web interface
- Extensible for mobile applications

---

## 🎯 Features

### For Users:
- ✅ Account creation and management
- ✅ Company selection
- ✅ Car registration
- ✅ Gate pass requests
- ✅ Dashboard with car overview

### For Administrators:
- ✅ Company management
- ✅ Employee management
- ✅ Gate pass approval/rejection
- ✅ System overview

---

## 📊 Development

### Local Development
- Uses H2 in-memory database
- Hot reload with Spring Boot DevTools
- Vaadin development mode enabled

### Production
- PostgreSQL database
- Liquibase database migrations
- Production-optimized builds
- Systemd service management
- Nginx reverse proxy

---

## 🔧 Configuration

### Profiles
- `dev` - Local development (H2 database)
- `production` - Production deployment (PostgreSQL)

### Database Migration
Uses Liquibase for database versioning and migrations.

---

## 🚀 Deployment Status

[![Deploy GatePass to Hetzner](https://github.com/Zaitis/GatePass/actions/workflows/deploy.yml/badge.svg)](https://github.com/Zaitis/GatePass/actions/workflows/deploy.yml)

**Live Application**: http://parking.zaitis.dev