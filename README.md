# 🧠 AI-Based Contextual Vocabulary Learning System

A smart vocabulary learning backend API that provides contextual word definitions powered by Google Gemini AI. Words are explained based on specific fields (Physics, Law, Finance) and proficiency levels (Beginner, Intermediate, Expert).

## 🌐 Live API
**Base URL:** `https://vocabulary-system-production.up.railway.app`

Test it right now:
GET https://vocabulary-system-production.up.railway.app/api/vocabulary/hello

---

## 🚀 Features

- 🤖 **AI-Powered Definitions** — Real contextual definitions using Google Gemini AI
- 🔐 **JWT Authentication** — Secure register and login system
- 💾 **Smart Caching** — Same word + field never calls AI twice (saves from DB)
- 🎯 **Context Aware** — "charge" means different things in Physics vs Law vs Finance
- 📊 **Proficiency Levels** — Beginner, Intermediate, Expert explanations
- ✅ **Input Validation** — Rejects invalid words and empty inputs
- 🚨 **Error Handling** — Clean JSON error responses with proper HTTP status codes

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Spring Boot 3.5 | Backend framework |
| Spring Security + JWT | Authentication and authorization |
| Spring Data JPA + Hibernate | Database ORM |
| MySQL | Database |
| Google Gemini AI | AI-generated definitions |
| Railway | Cloud deployment |
| Maven | Dependency management |
| Lombok | Boilerplate reduction |

---

## 📡 API Endpoints

### Authentication

**Register**
POST /api/auth/register
Content-Type: application/json
{
"name": "John Doe",
"email": "john@gmail.com",
"password": "yourpassword"
}
Response:
{
"token": "eyJhbGci...",
"message": "Registration successful"
}

**Login**
POST /api/auth/login
Content-Type: application/json
{
"email": "john@gmail.com",
"password": "yourpassword"
}
Response:
{
"token": "eyJhbGci...",
"message": "Login successful"
}

---

### Vocabulary (Requires JWT Token)

**Search Word**
POST /api/vocabulary/search
Authorization: Bearer {your_token}
Content-Type: application/json
{
"word": "ephemeral",
"field": "physics",
"level": "beginner"
}
Response:
{
"word": "ephemeral",
"message": "Ephemeral in physics refers to..."
}

**Search Word (GET)**
GET /api/vocabulary/search/{word}?field=physics&level=beginner
Authorization: Bearer {your_token}

**Health Check (Public)**
GET /api/vocabulary/hello
Response: "Vocabulary System is running!"

---

## 🏗️ Architecture
Client Request
↓
VocabularyController  (Web Layer - handles HTTP)
↓
VocabularyService     (Business Logic - validates, checks DB, calls AI)
↓
WordRepository        (Database Layer - MySQL operations)
↓
GeminiService         (AI Layer - calls Google Gemini API)

---

## 🔐 How Authentication Works

Register → password encrypted with BCrypt → saved to DB
Login → password verified → JWT token generated
Every request → JWT token validated → user identified
Invalid/missing token → 401 Unauthorized


---

## 💡 Smart Caching Logic
User searches "ephemeral" in "physics":
→ Check MySQL → not found
→ Call Gemini AI → get definition
→ Save to MySQL
→ Return definition
Same search again:
→ Check MySQL → FOUND
→ Return from DB instantly
→ No AI call needed (saves quota + faster)

---

## ⚡ Error Responses

```json
{
    "error": "Word cannot be empty",
    "status": 400,
    "timestamp": "2026-05-26T12:00:00"
}
```

| Status | Meaning |
|---|---|
| 400 | Bad Request — invalid input |
| 401 | Unauthorized — missing/invalid token |
| 404 | Not Found — resource doesn't exist |
| 500 | Internal Server Error |

---

## 🏃 Run Locally

**Prerequisites:**
- Java 17+
- MySQL
- Maven

**Steps:**

```bash
# Clone the repository
git clone https://github.com/Hariharan-004/vocabulary-system.git

# Navigate to project
cd vocabulary-system/vocabulary-system

# Create MySQL database
mysql -u root -p
CREATE DATABASE vocabulary_db;

# Create application-local.properties in src/main/resources/
spring.datasource.url=jdbc:mysql://localhost:3306/vocabulary_db
spring.datasource.username=root
spring.datasource.password=yourpassword
gemini.api.key=your_gemini_api_key
jwt.secret.key=your_secret_key

# Run the application
mvn spring-boot:run
```

---

## 📁 Project Structure
vocabulary-system/
├── src/main/java/com/vocabulary/vocabulary_system/
│   ├── VocabularySystemApplication.java  (Entry point)
│   ├── VocabularyController.java         (HTTP endpoints)
│   ├── VocabularyService.java            (Business logic)
│   ├── GeminiService.java                (AI integration)
│   ├── WordRepository.java               (Database operations)
│   ├── Word.java                         (Entity)
│   ├── AuthController.java               (Auth endpoints)
│   ├── AuthService.java                  (Auth logic)
│   ├── JwtService.java                   (JWT operations)
│   ├── JwtAuthFilter.java                (Request filter)
│   ├── SecurityConfig.java               (Security rules)
│   ├── User.java                         (User entity)
│   ├── UserRepository.java               (User DB operations)
│   ├── GlobalExceptionHandler.java       (Error handling)
│   └── ErrorResponse.java               (Error model)
└── src/main/resources/
└── application.properties            (Configuration)

---

## 👨‍💻 Developer

**Hariharan**
ECE Student | Backend Developer
GitHub: https://github.com/Hariharan-004
Live Project: https://vocabulary-system-production.up.railway.app

---

## 📄 License
MIT License

