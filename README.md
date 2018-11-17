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

## Future Tasks
- Increase variety of plants and zombies
- Implement a Lawn Mower Function
- Reduce Input Lag (if Possible)
- Allow serialization of levels into xml/json so that levels can be designed without touching code
- Introduce save/load game serialization

## Known Issues
- INPUT LAG: Input takes some time to parse. Please be patient when clicking.
- Loading can be slow
- expect ~250 MBs of memory usage.
- Sometimes it will spawn 0 zombies
- Zombie positions do not update correctly on the UI. it will show its previous position as well as the new one.

## Milestone 2 Division of Labour
Michael Patsula - GameUI, GameController, Card, Documentation
Derek Shao - GameUI, GridUI, ZombiePanel, Images, GameController, bugfixes, Diagrams
David Wang - MenuUI, MenuInteractions, JImagePanel, ZombiePanel, GameController, Game Engine Fixes
Tanisha Garg - Unit Tests, QA
