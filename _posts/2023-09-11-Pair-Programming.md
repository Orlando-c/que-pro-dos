---
comments: True
layout: post
title: Adventure Capitalist Pair Program Planning
description: A classical ripoff of Adventure Capitalist.
type: hacks
courses: { csa: {'week': 3}}
categories: ['C4.1']
---

# Draft and ideas

### Idea 
- A cookie clicker game to keep the user entertained. Record the player's score once they are finished

### First Draft 
- A game where the objective is to get as much money as you can. You are able to buy businesses to grow your economy. We included cookies and math functions to remember the users's name as well as count up the money.

### Addon 1 
- We added styling and a bigger business to make the game more fast paced and fluid. we also made the game more cometitive by adding a time limit, requiring players to react fast. We also added a cookie to save the player's score. Attempted to add reset button.

### Addon 2
- We added a database as per requirements which allows you to search up a company and purchase it, bringing in even more cash. We also implemented a money-per-second counter to allow the user to keep track of how much they earn. Used the player's score to add a message determining whether you beat your previous score or lost.


# ChatGPT College Board Review and Comments

### HTML and CSS Styling:
- Your use of HTML and CSS for structuring and styling the web page is well done. Properly structured HTML and CSS are important foundational skills in web development.

### JavaScript Functions:
- You've defined various JavaScript functions to handle different aspects of the game logic. This demonstrates your understanding of organizing code into functions, a fundamental concept in programming.

### Event Listeners:
- You've utilized event listeners to respond to user interactions like button clicks. Event handling is a key concept, and you've implemented it effectively.

### Cookies:
- You've used cookies to store and retrieve information (e.g., the username and highest score). This shows knowledge of handling browser cookies, which can be useful in web applications.

### DOM Manipulation:
- You manipulate the Document Object Model (DOM) to update and display information dynamically. This is a fundamental skill for web development and is necessary for creating interactive web pages.

### Game Logic:
- Your game includes features like clicking to earn money, purchasing businesses, and calculating income per second. These elements demonstrate the application of basic game logic.

### Conditional Statements:
- You use conditional statements (e.g., if statements) to check conditions and make decisions. This is a core programming concept and is used effectively in your code.

### Loops:
- You've used loops, such as setInterval, to create periodic updates in the game (e.g., income from businesses). Loops are important for managing game dynamics.

### Data Structures:
- Your use of arrays (playerBusinesses) to store and manage player-owned businesses showcases knowledge of data structures, an essential concept in programming.

### Time Handling:
- You manage time within the game by tracking the start and end times. Handling time is a common requirement in game development and other applications.

### User Interface (UI):
- Your UI design, including buttons and labels, is clear and user-friendly. Good UI design is crucial for user engagement.

### Comments:
- You've added comments at various points in your code to explain its functionality. This is a good practice for code readability and documentation.

### Error Handling:
- You handle cases where the user may not have enough money to purchase a business or where the game timer runs out. Error handling is an important aspect of software development.

### Scalability:
- Your code allows for scalability by adding more businesses to the game dynamically. This demonstrates an understanding of code design for extensibility.

### Overall Structure:
- Your code is well-organized, with clear separation of concerns between HTML, CSS, and JavaScript. This is good practice for maintainable code.

### Optimizations:
- You've implemented optimizations, such as calculating money per second and handling passive income from businesses. This adds depth to the gameplay. 


# Issues and things to improve on

### Money Per Second and Reset button not working
- One of the issues we've been having is getting the reset button to work with fully reseting all aspects such as bought businesses. Our let statements havent been working in the function and esentially ignoring them.

- Money Per Second was originally working but was broken when code was combined with new code. My new solution rather than using complicated math to calcuate it is to add up all of the businesses you have and multiply it through there rather than going a more complicated route.