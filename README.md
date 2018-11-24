# SYSC3110 Zombies Are Vegan
A Turn Based Plants v Zombies Clone

This is Turn Based Plants v Zombies Clone made for SYSC 3110 (Software Development Project) Fall 2018 Carleton University.

**Team Members (Group 2): Derek Shao, David Wang, Michael Patsula, Tanisha Garg**

This game represents iteration #1 of the SYSC3110 Plants Vs Zombie Project created by Michael Patsula, Derek Shao , David Wang and Tanisha Garg. In this iteration, the game will retrieve and display
information through the console. Moreover, there is currently only one level and three units (regular zombie, sunflower and peashooter units).

In the following iteration, the game will drop the text-based representation in preference for a GUI based implementation. Some planned additions for the future are more varied plant and zombie types, a undo/redo and save/load feature and an extension on the current LevelLoader code.

## Milestone 1 (10-17-2018):
- Text Based Game
- Single Level
- Two Plants and One Zombie Type

## Milestone 2 (11-16-2018):
- (Buggy) GUI based Game
- Still single level, but that level is cloned many times to demonstrate multi-level functionality
- Two Plants and One Zombie Type still
- A Dig Up function

## Milestone 3 (11-23-2018):
- Fully Functional GUI Based Game
- Many Plants and Zombies
- Undo/Redo Function
- (Background) The Engine, Input, and UI classes have been refactored in regards to how they interact to better adhere to the MVC model

## Future Tasks
- The UI should be better at displaying information about the zombies and plants. A description that appears on hover or click would be useful
- Allow serialization of levels into xml/json so that levels can be designed without touching code
- Introduce save/load game functionality

## Known Issues
- Loading can be slow
- expect ~250 MBs of memory usage.

## Milestone 3 Division of Labour
Michael Patsula - Unit Types, Mower Implementation
Derek Shao - Unit Types, Unit Tests, Diagrams
David Wang - Unit Types, Engine Refactor, Undo/Redo Function, Documentation
Tanisha Garg - Unit Types
