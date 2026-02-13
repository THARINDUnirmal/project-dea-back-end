# EventHub 🎉

EventHub is a full‑stack web application for creating, publishing, and managing events. It provides user authentication, event browsing, and a powerful admin panel to manage users and events.

The project is built using:

* **Flutter Web** for the frontend
* **Spring Boot** for the backend (REST API)
* **Firebase** for authentication and additional services

---

## ✨ Features

### 👤 User Features

* Create an account & login
* Browse published events
* View event details
* Register / participate in events (optional – if implemented)
* Secure authentication using Firebase

### 🛠 Admin Panel Features

* Manage users (view, block, delete)
* Create, update, and delete events
* Approve or reject events (if enabled)
* Dashboard for event & user statistics

### ⚙ Technical Features

* RESTful API communication between Flutter & Spring Boot
* JWT / Firebase based authentication
* Role-based access control (Admin / User)
* Responsive web UI

---

## 🏗 System Architecture

```
Flutter Web (Frontend)
        |
        | REST API (HTTP / JSON)
        v
Spring Boot Backend  --------> Database (MySQL)
        |
        v
Firebase (Authentication)
```

---

## 🧰 Tech Stack

### Frontend

* Flutter (Web)
* Dart
* HTTP package / Dio
* Firebase Auth

### Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* REST API

### Database

* MySQL

### Other Tools

* Firebase Console
* Postman (API testing)
* Git & GitHub

---

## 📁 Project Structure

```
EventHub/
│
├── frontend/            # Flutter Web project
│   ├── lib/
│   ├── web/
│   └── pubspec.yaml
│
├── backend/             # Spring Boot project
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── pom.xml
│
└── README.md
```

---

## 🚀 Getting Started

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/THARINDUnirmal/project-dea.git
cd eventhub
```

---

## ▶ Running the Backend (Spring Boot)

### Requirements

* JDK 17+
* Maven
* MySQL

### Steps

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Backend will start at:

```
http://localhost:8080
```

---

## ▶ Running the Frontend (Flutter Web)

### Requirements

* Flutter SDK
* Chrome browser

### Steps

```bash
cd frontend
flutter pub get
flutter run -d chrome
```

Frontend will run at:

```
http://localhost:xxxx
```

---

## 🔐 Firebase Setup

1. Create a Firebase project
2. Enable **Authentication → Email/Password**
3. Add a Web App in Firebase
4. Copy Firebase config to Flutter project
5. Download `google-services.json` / web config if needed

---

## 🔌 API Communication

Flutter communicates with Spring Boot using REST APIs:

Example:

```
GET    /api/events
POST   /api/events
POST   /api/auth/login
GET    /api/admin/users
```

Data format: **JSON**

---

## 🧪 Testing

* API Testing: Postman
* Frontend Testing: Flutter test

---

## 📸 Screenshots

> Add screenshots here

---

## 📌 Future Improvements

* Payment gateway integration
* Email notifications
* Event ticket QR system
* Mobile app version
* Advanced analytics dashboard

---

## 👨‍💻 Author

**Tharindu (Apex)**

Software Engineering Student – NSBM Green University

---

## 📄 License

This project is licensed under the MIT License.

---

## ⭐ Support

If you like this project, please give it a ⭐ on GitHub!

---

Happy Coding 🚀
