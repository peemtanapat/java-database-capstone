# Smart Clinic Management System

## Architecture Summary

This Spring Boot application uses both MVC and REST controllers. Thymeleaf templates are used for the Admin and Doctor dashboards, while REST APIs serve all other modules. The application interacts with two databases—MySQL (for patient, doctor, appointment, and admin data) and MongoDB (for prescriptions). All controllers route requests through a common service layer, which in turn delegates to the appropriate repositories. MySQL uses JPA entities while MongoDB uses document models. Three-tier Design: Presentation, Application, and Data layers:

### Presentation

- **Technologies**: HTML/CSS/JavaScript, Thymeleaf

### Application

- **Technologies**: Java, Spring Boot

### Data

- **Technologies**: MySQL, MongoDB

## Numbered Flow

1. **User Interaction**: User accesses AdminDashboard or Appointment pages.
2. **Routing**: The action is routed to the appropriate Thymeleaf or REST controller.
3. **Controller Layer**: The controller calls the service layer.
4. **Service Layer**: The service layer interacts with the repository layer (MySQL or MongoDB) as needed.
5. **Repository Layer**: The repository layer performs CRUD operations on the databases.
6. **Model binding**: Data is bound to model attributes for Thymeleaf views or returned as JSON for REST APIs.
7. **Service Layer**: The service layer processes the data and returns it to the controller.
8. **Controller Layer**: The controller use the completed model to renders the view or returns a JSON response.
9. **User Interaction**: The user sees the updated information on the UI.
