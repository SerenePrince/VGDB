---

# 🎮 **VGDB - Fullstack Game Database**  

![VGDB Home](https://github.com/user-attachments/assets/8271c646-ddf2-4a91-8957-a817cbdffd05)  
![VGDB Game](https://github.com/user-attachments/assets/30468d1f-5a93-45d3-9679-c02cde5d57a2)  
![VGDB Review](https://github.com/user-attachments/assets/63e175ab-f875-49ad-a4b3-a66fa600f002)

Welcome to **VGDB**, a full-stack game database project designed to help manage game data and reviews. This application allows users to search for games, view detailed information, and submit reviews, all powered by a **React.js** frontend and a **Java** backend.

---

## ✨ **Project Features**  

- **Game Information**: View detailed information about various games fetched from a RESTful API.  
- **User Reviews**: Submit and view reviews for games, stored in a **MongoDB** database.  
- **Responsive UI**: Built with **React.js** and styled using **Tailwind CSS**, ensuring smooth usability on both desktop and mobile devices.  

---

## 🚀 **Technologies Used**

### **Backend**  
- **Java**: The main language for backend development.  
- **Maven**: For project management and build automation.  
- **Spring Boot**: A framework for building RESTful APIs and handling backend logic.  
- **MongoDB**: A NoSQL database used to store game data and reviews.

### **Frontend**  
- **ReactJS**: JavaScript library for building dynamic and interactive user interfaces.  
- **Tailwind CSS**: A utility-first CSS framework used for styling and designing the frontend.

---

## ⚙️ **Setup and Installation**

### **Prerequisites**  
Before you begin, make sure you have the following installed:
- [Java 11 or higher](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- [MongoDB](https://www.mongodb.com/try/download/community) (or use **MongoDB Atlas** for cloud storage)
- [Node.js](https://nodejs.org/)  
- [npm or yarn](https://www.npmjs.com/) for managing frontend dependencies

### **Backend Setup**

1. Clone the repository:
   ```bash
   git clone https://github.com/SerenePrince/VGDB.git
   cd VGDB/Backend
   ```

2. Install dependencies and build the backend:
   ```bash
   mvn clean install
   ```

3. Run the backend with Maven:
   ```bash
   mvn spring-boot:run
   ```

4. Ensure MongoDB is running locally or use a cloud-based service like **MongoDB Atlas**. Update your `application.properties` file with your MongoDB connection details.

### **Frontend Setup**

1. Navigate to the **Frontend** folder:
   ```bash
   cd VGDB/Frontend
   ```

2. Install the frontend dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm start
   ```

4. Open your browser and visit `http://localhost:3000` to interact with the frontend.

### **API Endpoints**

The backend exposes the following API endpoints:
- **GET /api/games**: Retrieve a list of all games.
- **GET /api/games/{id}**: Retrieve details for a specific game by its ID.
- **POST /api/reviews**: Submit a review for a game.
- **GET /api/reviews/{gameId}**: Retrieve reviews for a specific game.

---

## 🗂️ **Project Structure**

```
VGDB/
│
├── Backend/vgdb             # Backend (Java, Maven, Spring Boot)
│   ├── src/                 # Backend source code
│   ├── pom.xml              # Maven configuration file
│   └── application.properties # MongoDB and Spring Boot configurations
│
├── Frontend/                # Frontend (ReactJS, Tailwind CSS)
│   ├── src/                 # React source code
│   ├── package.json         # Node.js project configuration
│   └── tailwind.config.js   # Tailwind CSS configuration
│
└── .gitignore               # Git ignore configuration
```

---

## 🤝 **Contributing**  

This is a personal project, but contributions and suggestions are always welcome!  
Feel free to open an issue or submit a pull request if you'd like to contribute.

---
