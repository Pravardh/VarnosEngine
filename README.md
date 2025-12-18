# Varnos Engine

A simple 2D game engine built with Java Swing with player movement, collision detection, and interactive objects.

## Prerequisites

- **Java Development Kit (JDK)** 8 or higher
- A terminal/command prompt

## Project Structure

Ensure your project has the following structure:

```
project-root/
├── Game.java
├── Player.java
├── PlayerMovementComponent.java
├── Window.java
├── Handler.java
├── GameObject.java
├── GameObjectComponent.java
├── CollisionComponent.java
├── InputReader.java
├── CoinItem.java
├── MovingPlatform.java
├── Block.java
├── ID.java
├── Transform.java
└── Math/
    └── Vector2.java
```

## Compilation

### Option 1: Compile All Files at Once

Navigate to your project directory and run:

```bash
javac *.java Math/*.java
```

### Option 2: Compile with Explicit File Listing

If you prefer to compile specific files:

```bash
javac Math/Vector2.java ID.java Transform.java GameObject.java GameObjectComponent.java \
      CollisionComponent.java InputReader.java Handler.java PlayerMovementComponent.java \
      Player.java CoinItem.java MovingPlatform.java Block.java Window.java Game.java
```

## Running

After successful compilation, run the game with:

```bash
java Game
```

## Controls

- **Move Right:** `D` or `→` (Right Arrow)
- **Move Left:** `A` or `←` (Left Arrow)
- **Jump:** `W` or `SPACE`

## Gameplay Features

- **Player Character:** A 32×64 pixel character that can move and jump
- **Collectible Coins:** Pick up coins to increase your score
- **Moving Platform:** A platform that moves horizontally
- **Ground Collision:** Physics-based ground detection
- **Gravity System:** Realistic falling mechanics

