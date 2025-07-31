💼 HRMS - Human Resource Management System
A comprehensive HR management system developed using Spring Boot, designed to manage employee data, attendance, leaves, payroll, and other HR operations efficiently.

🛠️ Technologies Used
Backend: Spring Boot, Spring MVC, Spring Data JPA, Spring Security

Database: MySQL 

Frontend: (Optional) React.js 

Authentication: JWT (JSON Web Token) / Spring Security

Tools: Maven, Lombok, Postman, Git.

📋 Key Features

👤 Employee Management
Add, update, delete employee details

Department, designation, role assignment

Employee profile photo upload

📆 Attendance Management

Mark attendance (IN/OUT)

View daily/monthly attendance records

Auto-calculation of working hours

📅 Leave Management

Apply for leave

Leave approval by admin/manager

Track leave status and history

💰 Payroll System

Generate salary slips

Monthly salary report

Tax & deduction calculations

🔐 Authentication & Authorization

Admin, HR, and Employee roles

Role-based access control using Spring Security

JWT-based token authentication

📊 Dashboard & Reports

Admin dashboard showing employee stats

Downloadable reports (PDF/Excel)


🔗 API Endpoints (Sample)
Method	Endpoint	Description
POST	/api/auth/login	Login for Admin/HR/Employee

POST	/api/employees	Add new employee

GET	/api/employees	List all employees

PUT	/api/employees/{id}	Update employee info

POST	/api/attendance/mark	Mark attendance

GET	/api/leaves	Get all leave applications

🧑‍💼 User Roles
Admin: Manage all data, approve HR users, full access

HR: Manage employees, leaves, payroll

Employee: View own profile, apply for leave, mark attendance

✅ Future Enhancements
Email notifications (for leave status, payroll, etc.)

Integration with biometric devices for attendance

Performance reviews and KPI tracking

Multi-language support.
