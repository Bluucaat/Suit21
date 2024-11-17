# Suit 21

Suit 21 is a multiplayer card game for two to six players. The objective is to achieve a hand with a score of exactly 21.  Players take turns swapping cards to optimize their hands, aiming to reach 21 before the deck runs out. Play against other players or a computer for a fun, strategic game experience!

## Table of Contents
- [Introduction](#introduction)
- [Game Rules](#game-rules)
- [Features](#features)

---

## Introduction

Suit 21 is based on a classic card game where players attempt to score exactly 21 points in a single suit. This code follows the MVC Design Pattern.

- **Setup**: 
    - Each player is dealt five cards from a shuffled standard deck.
- **Objective**:
    - Obtain a hand of cards that totals exactly 21 points.
- **Card Values**:
    - Number cards (2-10) are worth their face values.
    - Picture cards (Jack, Queen, King) are worth 10 points.
    - Aces can count as either 1 or 11 points, with the most beneficial value chosen automatically.
- **Rounds**:
    - In each round, players can swap one card from their hand with a new card from the deck.
    - If a player achieves a hand value of 21, they can win the game if no other players also achieve 21 in that round.
- **Game End**:
    - If a player scores 21, other players finish the round, giving everyone an equal chance to reach 21.
    - If multiple players achieve 21, the game ends in a draw for that round.
    - If the deck runs out before any player reaches 21, the game ends in a draw.

## Features

- **Multiplayer Support**: Play with up to six players.
- **Computer Opponent**: Add a computer player by entering "Computer" as a player name (Not very smart).
- **Random Card Draw**: The deck shuffles at the start, and each player's hand updates based on their actions.
- **Dynamic Ace Value**: The Ace can automatically adjust its value to either 1 or 11, whichever makes the player's hand closest to 21 without exceeding it.

