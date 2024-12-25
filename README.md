---

# VGDB - Fullstack Game Database Project

**VGDB** is a personal fullstack project designed to help me better understand software development. This project includes both a **backend** and a **frontend** that work together to provide a platform for managing game data and reviews.

![VGDB Home](https://github.com/user-attachments/assets/8271c646-ddf2-4a91-8957-a817cbdffd05)
![VGDB Game](https://github.com/user-attachments/assets/30468d1f-5a93-45d3-9679-c02cde5d57a2)
![VGDB Review](https://github.com/user-attachments/assets/63e175ab-f875-49ad-a4b3-a66fa600f002)



## Project Structure

- **Backend**: Java, Maven, Spring Boot, MongoDB
- **Frontend**: ReactJS, JavaScript, Tailwind CSS

## Features

- **Game Information**: Display detailed information about various games fetched from a RESTful API.
- **User Reviews**: Allow users to submit reviews for games, which are then stored in a MongoDB database.
- **Responsive UI**: Built with React and styled using Tailwind CSS, ensuring a responsive and modern design.

## Technologies Used

### Backend
- **Java**: The primary language for backend development.
- **Maven**: For project management and build automation.
- **Spring Boot**: A framework to create RESTful APIs and handle backend logic.
- **MongoDB**: NoSQL database used to store game data and reviews.

### Frontend
- **ReactJS**: JavaScript library used for building the user interface.
- **Tailwind CSS**: A utility-first CSS framework used for styling the frontend.

## Setup and Installation

### Prerequisites

Before you begin, ensure you have the following installed:
- [Java 11 or higher](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- [MongoDB](https://www.mongodb.com/try/download/community) (or use a cloud service like MongoDB Atlas)
- [Node.js](https://nodejs.org/) (for the frontend)
- [npm or yarn](https://www.npmjs.com/) (for managing frontend dependencies)

### Backend Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/SerenePrince/VGDB.git
   cd VGDB/Backend
   ```

2. Install dependencies and build the backend:
   ```bash
   mvn clean install
   ```

3. Run the backend using Maven:
   ```bash
   mvn spring-boot:run
   ```

4. Ensure MongoDB is running (either locally or use a cloud-based instance like MongoDB Atlas). Update your `application.properties` file with your MongoDB connection details.

### Frontend Setup

1. Navigate to the **Frontend** folder:
   ```bash
   cd VGDB/Frontend
   ```

2. Install frontend dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm start
   ```

4. Open your browser and go to `http://localhost:3000` to see the frontend in action.

### API Endpoints

The backend exposes the following API endpoints:

- **GET /api/games**: Retrieve a list of all games.
- **GET /api/games/{id}**: Retrieve details for a specific game by ID.
- **POST /api/reviews**: Submit a review for a game.
- **GET /api/reviews/{gameId}**: Get reviews for a specific game.

### Frontend Features

- Fetch game data from the backend API and display it in a user-friendly interface.
- Users can submit reviews for individual games, which will be stored in the MongoDB database.
- The UI is responsive and optimized for desktop and mobile devices.

## Directory Structure

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

## Contributing

This is a personal project, but if you would like to contribute or offer suggestions, feel free to open an issue or submit a pull request!
