# 🐍 Java Quest – Snake Edition

A classic Snake game built with **Java 21 and Java Swing**.

This project demonstrates object-oriented programming, game logic implementation, graphical user interface development, and automated unit testing.

## 🎮 Features

- Classic Snake gameplay
- Keyboard-controlled movement
- Food generation
- Score tracking
- Snake growth after eating food
- Wall collision detection
- Self-collision detection
- Start screen
- Pause and resume functionality
- Game-over screen
- Restart functionality

## 📸 Screenshots

### Start Screen

![Start Screen](screenshots/start-screen.png)

### Gameplay

![Gameplay](screenshots/gameplay.png)

### Game Over

![Game Over](screenshots/game-over.png)

## 🕹️ Controls

| Key | Action |
|---|---|
| `↑` | Move up |
| `↓` | Move down |
| `←` | Move left |
| `→` | Move right |
| `SPACE` | Start the game |
| `P` | Pause / resume |
| `R` | Restart after game over |

## 🛠️ Technologies

| Technology | Purpose |
|---|---|
| Java 21 | Main programming language |
| Java Swing | Graphical user interface |
| Maven | Build and dependency management |
| JUnit 5 | Unit testing |
| Git | Version control |
| GitHub | Source code hosting |

## 📂 Project Structure

```text
java-quest-snake/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/codecool/snake/
│   │           ├── Main.java
│   │           │
│   │           ├── controller/
│   │           │   └── GameController.java
│   │           │
│   │           ├── model/
│   │           │   ├── Position.java
│   │           │   ├── Direction.java
│   │           │   ├── Snake.java
│   │           │   ├── Food.java
│   │           │   ├── Game.java
│   │           │   └── GameState.java
│   │           │
│   │           └── view/
│   │               ├── GamePanel.java
│   │               └── GameWindow.java
│   │
│   └── test/
│       └── java/
│           └── com/codecool/snake/model/
│               ├── PositionTest.java
│               ├── DirectionTest.java
│               ├── SnakeTest.java
│               ├── FoodTest.java
│               └── GameTest.java
│
├── screenshots/
│   ├── start-screen.png
│   ├── gameplay.png
│   └── game-over.png
│
├── .gitignore
├── pom.xml
└── README.md
```

## ⚙️ Requirements

Before running the project, make sure you have:

- JDK 21 or newer
- Apache Maven
- Git

## 🚀 Getting Started

### Prerequisites

Make sure the following tools are installed:

- Java JDK
- Apache Maven

Verify your installations:

```bash
java -version
mvn -version
```

### Run the Application

1. Clone the repository:

```bash
git clone https://github.com/Tomey-SNR/java-quest-snake.git
```

2. Navigate to the project directory:

```bash
cd java-quest-snake
```

3. Compile and run the application:

```bash
mvn compile exec:java
```

**Important:** Run `mvn compile exec:java` the first time you launch the application on a fresh checkout. The `compile` goal generates the compiled classes required by `exec:java`.

After modifying the source code, use the same command to compile the latest changes and run the application again.

### Troubleshooting

If `mvn exec:java` fails with a class-not-found error, compile the project first:

```bash
mvn compile
mvn exec:java
```

Alternatively, execute both goals in one command:

```bash
mvn compile exec:java
```
## 🧪 Running Tests

The project uses JUnit 5 for automated unit testing.

Run all tests:

```bash
mvn clean test
```

### Test Coverage

The automated tests cover:

- Position initialization
- Direction movement offsets
- Snake movement and growth
- Direction changes and reversal prevention
- Food position validation
- Game initialization
- Score calculation
- Collision detection
- Game state management
- Game reset functionality

## 🏗️ Architecture

The application follows a simple separation of responsibilities.

### Model

Contains the game logic and data:

- `Position` – represents coordinates on the game board.
- `Direction` – defines movement directions.
- `Snake` – manages snake movement, body and growth.
- `Food` – represents food on the board.
- `Game` – manages game logic, scoring and collisions.
- `GameState` – represents the current game state.

### View

Responsible for displaying the game:

- `GamePanel` – renders the snake, food, score and game screens.
- `GameWindow` – creates the application window.

### Controller

Responsible for handling user input and controlling gameplay:

- `GameController` – handles keyboard input, the game loop, pause, start and restart functionality.

## 👨‍💻 Author

**Tomey SNR**

GitHub: [Tomey-SNR](https://github.com/Tomey-SNR)