
# Hostel Management System

A comprehensive web application for managing hostels, rooms, and tenants with real-time occupancy tracking and automated status management.

## Features

- **Hostel Management**: Add and view hostels with owner details and floor information
- **Room Management**: Create rooms with different types (Single, Double Shared, Triple Shared) and track occupancy
- **Tenant Management**: Manage tenant information, room assignments, and rent payment status
- **Real-time Status Tracking**: Automatic calculation of room and hostel occupancy status
- **Capacity Validation**: Prevents overbooking with business rule enforcement
- **Responsive UI**: Modern glassmorphism design with smooth animations

## Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA** for database operations
- **MySQL** as the primary database
- **Lombok** for reducing boilerplate code

### Frontend
- **HTML5/CSS3** with modern design patterns
- **Vanilla JavaScript** with ES6+ features
- **Fetch API** for HTTP requests
- **LocalStorage** for client-side authentication

## Architecture

The application follows a layered architecture pattern:

```
├── Controller Layer    (REST endpoints)
├── Service Layer      (Business logic)
├── Repository Layer   (Data access)
└── Entity Layer       (Database models)
```
## Architecture Diagram 
<img width="1927" height="3840" alt="Image" src="https://github.com/user-attachments/assets/b15488e3-6c2d-4bed-b178-15c283a5c028" />


## Database Schema

- **HostelEntity**: Stores hostel information and calculated status
- **Roomentity**: Manages room types, capacity, and current occupancy
- **Tenantsentity**: Handles tenant details and room assignments

### Key Relationships
- One Hostel → Many Rooms (1:N)
- One Room → Many Tenants (1:N)

## API Endpoints

### Hostels
- `POST /api/hostel/add` - Add new hostel
- `GET /api/hostel/all` - Get all hostels with status

### Rooms
- `POST /api/rooms/addroom` - Add new room to hostel
- `PATCH /api/rooms/updateroom/{id}` - Update room details
- `GET /api/rooms/getroomstatus/{id}` - Get room occupancy status
- `GET /api/rooms/all` - Get all rooms

### Tenants
- `POST /api/tenants/addtenant` - Add new tenant
- `PATCH /api/tenants/updatetenant/{id}` - Update tenant information
- `GET /api/tenants/tenantbyid/{id}` - Get tenant details
- `GET /api/tenants/all` - Get all tenants
- `DELETE /api/tenants/{id}` - Remove tenant

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Modern web browser

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/hostel-management-system.git
   cd hostel-management-system
   ```

2. **Configure Database**
   ```properties
   # application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hostel_management
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**
   - Backend API: `http://localhost:8080`
   - Frontend: Open `login.html` in your browser

### Sample Data
The application starts with empty database tables. Use the frontend interface to:
1. Register/Login
2. Add hostels
3. Create rooms for hostels
4. Add tenants to rooms

## Key Features Implementation

### Automatic Occupancy Tracking
- Room occupancy updates automatically when tenants are added/removed
- Hostel status calculated based on all room occupancy states
- Real-time validation prevents exceeding room capacity

### Business Logic
- **Room Types**: Enum-based capacity calculation (Single=1, Double=2, Triple=3)
- **Status Management**: Dynamic status updates (Vacant/Partially Occupied/Fully Occupied)
- **Data Integrity**: Transactional operations ensure consistency

### Error Handling
- Comprehensive validation at both frontend and backend
- Meaningful error messages for business rule violations
- HTTP status codes following REST standards

## Project Structure

```
src/
├── main/java/com/pgmanagement/hostels/
│   ├── controller/     # REST endpoints
│   ├── service/        # Business logic
│   ├── repository/     # Data access layer
│   └── entity/         # JPA entities
├── main/resources/
│   └── application.properties
└── frontend/
    ├── index.html      # Main application
    ├── login.html      # Authentication
    └── register.html   # User registration
```

## Future Enhancements

- [ ] Spring Security integration with JWT authentication
- [ ] Role-based access control (Admin/Manager/Staff)
- [ ] Email notifications for rent reminders
- [ ] Reporting dashboard with analytics
- [ ] Payment tracking and invoice generation
- [ ] Mobile-responsive improvements
- [ ] Unit and integration tests
- [ ] Docker containerization

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

Your Name - your.email@example.com

Project Link: [https://github.com/yourusername/hostel-management-system](https://github.com/yourusername/hostel-management-system)
# Hostel-Management-System-Using-Springboot-
Created a Springboot  Application 


## Login Page 

<img width="1900" height="874" alt="Image" src="https://github.com/user-attachments/assets/a5a6c8de-579b-43b0-875f-ecaa4a4b371f" />

## Adding New Tenant
<img width="1220" height="842" alt="Image" src="https://github.com/user-attachments/assets/63e7b558-118b-4e7b-bf0e-a4bb0e5067a6" />

## Add Hostel 
<img width="1218" height="858" alt="Image" src="https://github.com/user-attachments/assets/da1da790-3446-416a-b056-ad1798cb9569" />
##

<img width="1320" height="860" alt="Image" src="https://github.com/user-attachments/assets/f78ceb5b-9890-473e-b57f-70519e04ffa8" />

## Add New Room 
<img width="1222" height="869" alt="Image" src="https://github.com/user-attachments/assets/a6370450-3c7e-413f-9385-e429a1cce925" />
