# Pen LLD

A low-level design implementation modeling generic Pen mechanics in Java. This project strongly adheres to Object-Oriented Programming (OOP) concepts and SOLID principles, establishing a clean schema for various ink-handling strategies and physical pen state manipulation.

## Features
- Independent `Ink` metadata components (Enums: `Color` / `InkType`).
- Core physical component integration via composition (`Nib`, `Refill`).
- Abstract framework forcing implementations to validate `start()` (cap open) and `close()` (cap locked) states before writing.
- Dynamic polymorphic refill behaviors between:
  - `FountainPen`: Additive fluid behavior (`refillInk`).
  - `RefillablePen`: Substitutive cartridge behavior (`changeRefill`).

## UML Class Diagram

```mermaid
classDiagram
    class Pen {
        <<abstract>>
        - boolean isOpen
        - String brand
        + start()
        + close()
        + isOpen() boolean
        + getBrand() String
        + write(String content)*
    }

    class RefillablePen {
        - Refill refill
        + write(String content)
        + changeRefill(Refill newRefill)
        + getRefill() Refill
    }

    class FountainPen {
        - Ink ink
        - Nib nib
        - double currentLevel
        - double maxCapacity
        + write(String content)
        + refillInk(double amount)
        + getCurrentLevel() double
        + getMaxCapacity() double
    }

    class Refill {
        - Ink ink
        - Nib nib
        - double currentLevel
        - double maxCapacity
        + consumeInk(double amount) boolean
        + getInkLevel() double
        + getMaxCapacity() double
        + getInk() Ink
        + getNib() Nib
    }

    class Ink {
        - Color color
        - InkType type
        + getColor() Color
        + getType() InkType
    }

    class Nib {
        - double radius
        + getRadius() double
    }

    class Color {
        <<enumeration>>
        RED
        BLACK
        BLUE
        GREEN
    }

    class InkType {
        <<enumeration>>
        GEL
        BALLPOINT
        FOUNTAIN
        MARKER
    }

    Pen <|-- RefillablePen : extends
    Pen <|-- FountainPen : extends
    
    RefillablePen o-- Refill : aggregates
    FountainPen o-- Ink : has
    FountainPen o-- Nib : has

    Refill o-- Ink : contains
    Refill o-- Nib : contains
    
    Ink --> Color : uses
    Ink --> InkType : uses
```

## Core Design Principles
- **Template Method Pattern**: The abstract `Pen` class implements concrete pre-operation checks for `start()` and `close()`, but handles `#write` abstractly, allowing child implementations to dictate ink behaviors safely.
- **Composition over Inheritance**: Features like `Refill`, `Ink`, and `Nib` act as standalone reusable models allowing `RefillablePen` and `FountainPen` to freely compose them instead of building massive inheritance trees.