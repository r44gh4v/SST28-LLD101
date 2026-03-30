# Snakes and Ladders LLD

A low-level design implementation of the classic Snakes and Ladders board game in Java. This project demonstrates strong Object-Oriented Programming (OOP) concepts, applying SOLID principles and common design patterns to ensure maintainability and extensibility.

## Features
- Dynamic Initialization: Configurable board size ($N \times N$), number of players, and difficulty.
- Custom Algorithms: Randomly generates $N$ Snakes and $N$ Ladders ensuring zero cycles/infinite loops.
- Multiple game modes (Easy, Hard) using the Strategy Pattern.
- Abstracted jump mechanics (Snakes and Ladders) using Polymorphism.
- Progress tracked until $X-1$ players win.
- Console-based 2D grid visualization.

## UML Class Diagram
![Class Diagram](./Snakes%20%26%20Ladders%20classDiagram%20Light.png)

## Core Design Principles
- **Strategy Pattern** for Game Modes (`EasyMode`, `DifficultMode`).
- **Polymorphism** for Board obstacles (`Jumpable` interface implemented by `Snake` and `Ladder`).
- **Dependency Injection** & **Single Responsibility Principle** enforced across components.