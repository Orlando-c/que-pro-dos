---
tags: [javascript]
courses: {csa: {week: 3} }
---
<style>
    @import url('https://fonts.googleapis.com/css?family=Jost');

      .button1 {
        background-color: #2e6930;
        font-family: 'Jost';
        font-size: 36px;
        width: 200px;
        height: 80px;
        color: #fefefe;
        border: 3px solid #ffffff;
        border-radius: 8px;
        transition: background-color 0.3s ease, color 0.3s ease;
      }

      .button2 {
        background-color: #265828;
        font-family: 'Jost';
        font-size: 22px;
        width: 340px;
        color: #fefefe;
        border: 3px solid #ffffff;
        border-radius: 8px;
        transition: background-color 0.3s ease, color 0.3s ease;
      }

      .button3 {
        background-color: #265828;
        font-family: 'Jost';
        font-size: 24px;
        width: 30px;
        color: #fefefe;
        border: 3px solid #ffffff;
        border-radius: 8px;
        transition: background-color 0.2s ease, color 0.2s ease;
      }

      .button1:hover {
          background-color: #4caf50;
          color: #fefefe;
      }

      .button2:hover {
          background-color: #4caf50;
          color: #fefefe;
      }

      .button3:hover {
          background-color: #4caf50;
          color: #fefefe;
      }

      .container {
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          height: 100vh; /* This makes the container take the full viewport height */
      }

      .businesses {
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          height: 100vh; /* This makes the container take the full viewport height */
      }

      body {
        font-family: 'Jost';
        font-weight: bold;
        color: #265828;
      }
          
      h1 {
        font-family: 'Jost';
        font-weight: normal;
        font-size: 3rem;
        margin-top: 0;
        color: #265828;
      }
    

</style>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adventure Capitalist Clicker Game</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Adventure Capitalist Clicker</h1>
        <div class="money-container">
            <p>Money: $<span id="money">0</span></p>
            <p>Time Left: <span id="timer">180</span> seconds</p>
            <p id="money-per-second">Money per second: $0.00</p>
            <button class="button1" button id="click-button">~ Click ~</button>
            <button class="button1" button id="reset-button">~ Reset ~</button>
        </div>
        <div class="businesses">
            <button class="button2" button id="business1">Lemonade Stand ($25) ~ 0</button>
            <button class="button2" button id="business2">Tech Repair ($100) ~ 0</button>
            <button class="button2" button id="business3">Corner Store ($250) ~ 0</button>
            <button class="button2" button id="business4">Wholesale Store ($1000) ~ 0</button>
        </div>
                    <!-- Business search input -->
    <label for="search-input">Search for businesses:</label>
    <input type="text" id="search-input">
    <button id="search-button">Search</button>
    <!-- Display search results -->
    <div id="results">
        <h2>Search Results:</h2>
        <table id="business-table">
            <thead>
                <tr>
                    <th>Company Name</th>
                    <th>Cost</th>
                    <th>Action</th>
                </tr>
            </thead>
        <tbody id="business-list"></tbody>
    </table>
    </div>
    <!-- Display the player's businesses -->
    <div id="player-businesses">
        <h2>Your Businesses:</h2>
        <ul id="player-business-list"></ul>
    </div>
        <div>
        <img id="image" src="{{ site.baseurl }}/images/images.jpg"/>
        </div>
        <img src="{{ site.baseurl }}/images/200w.gif"/>
    </div>
    <script src="script.js"></script>
</body>
</html>
<script>
// Helper function to set a cookie with a given name and value
let congrats = "Make as much money as you can";
let money = 0;
let playerBusinesses = [];
let business1Count = 0;
let business2Count = 0;
let business3Count = 0;
let business4Count = 0;
let startTime = null;
let endTime = null;
let isGamePaused = false;
let highestScore = parseInt(getCookie("highestScore")) || 0;
const moneyDisplay = document.getElementById("money");
const clickButton = document.getElementById("click-button");
const resetButton = document.getElementById("reset-button");
const business1Button = document.getElementById("business1");
const business2Button = document.getElementById("business2");
const business3Button = document.getElementById("business3");
const business4Button = document.getElementById("business4");
const timerDisplay = document.getElementById("timer");
const scoreDisplay = document.getElementById("score");
const businessDisplay = document.getElementById("player-business-list");
const searchInput = document.getElementById("search-input");
const searchButton = document.getElementById("search-button");
const businessList = document.getElementById("business-list");
const businesses = [
        { name: "ABC Corporation", networth: 5000 },
        { name: "XYZ Industries", networth: 3000 },
        { name: "Alpha Inc.", networth: 1000 },
        { name: "Beta Corp.", networth: 8000 },
        { name: "Gamma Corp.", networth: 7000 },
        { name: "Delta Enterprises", networth: 6000 },
        { name: "Omega Ltd.", networth: 2000 },
        { name: "Sigma Co.", networth: 4000 },
        { name: "Zeta Holdings", networth: 9000 },
        { name: "Epsilon Ventures", networth: 2500 },
        { name: "Microsoft", networth: 10000},
        { name: "Apple", networth: 150000},
        { name: "Google", networth: 900000},
        { name: "Bob", networth: 99999999},
    ];
function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  let expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
function getCookie(cname) {
  let name = cname + "=";
  let ca = document.cookie.split(';');
  for(let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
function checkCookie() {
  let user = getCookie("username");
  if (user != "") {
    alert("Welcome again " + user);
  } else {
    user = prompt("Please enter your name:", "");
    if (user != "" && user != null) {
      setCookie("username", user, 365);
    }
  }
}
checkCookie();
clickButton.addEventListener("click", () => {
    if (!isGamePaused) {
        money += 1;
        updateMoneyDisplay();
        startTimer();
    }
});
business1Button.addEventListener("click", () => {
    if (money >= 25 && !isGamePaused) {
        money -= 25;
        business1Count += 1;
        updateMoneyDisplay();
    }
});
business2Button.addEventListener("click", () => {
    if (money >= 100 && !isGamePaused) {
        money -= 100;
        business2Count += 1;
        updateMoneyDisplay();
    }
});
business3Button.addEventListener("click", () => {
    if (money >= 250 && !isGamePaused) {
        money -= 250;
        business3Count += 1;
        updateMoneyDisplay();
    }
});
business4Button.addEventListener("click", () => {
    if (money >= 1000 && !isGamePaused) {
        money -= 1000;
        business4Count += 1;
        updateMoneyDisplay();
    }
});
function updateMoneyDisplay() {
    moneyDisplay.textContent = money;
    business1Button.textContent = `Lemonade Stand ($25) ~ ${business1Count}`;
    business2Button.textContent = `Tech Repair ($100) ~ ${business2Count}`;
    business3Button.textContent = `Corner Store ($250) ~ ${business3Count}`;
    business4Button.textContent = `Wholesale Store ($1000) ~ ${business4Count}`;
}
function startTimer() {
    if (!startTime) {
        startTime = Date.now();
        endTime = startTime + 181000; // 3 minutes
        setInterval(updateTimer, 1000);
    }
}
function updateTimer() {
    if (startTime && !isGamePaused) {
        const currentTime = Date.now();
        const remainingTimeInSeconds = Math.max(0, Math.floor((endTime - currentTime) / 1000));
        timerDisplay.textContent = remainingTimeInSeconds;
        if (remainingTimeInSeconds <= 0) {
            gameOver();
        }
    }
}
function gameOver() {
    isGamePaused = true;
    const elapsedTimeInSeconds = Math.floor((endTime - startTime) / 1000);
    timerDisplay.textContent = "Time is up man! You have zero ";
    setCookie("playerScore", money, 365);
    if (money < highestScore) {
        alert("You didn't make it... Your balance was $" + money + "... but your high score is $" + highestScore);
    }
    if (money > highestScore) {
        highestScore = money;
        setCookie("highestScore", highestScore, 365);
        alert("Congratulations! Your bank was shocked as they looked at your account and saw $" + highestScore );
    }
}
setInterval(() => {
    if (!isGamePaused) {
        money += business1Count * .5 + business2Count * 2.5 + business3Count * 7.5 + business4Count * 37.5;
        updateMoneyDisplay();
    }
}, 250);
resetButton.addEventListener("click", () => {
    money = 0;
    startTime = null;
    endTime = null;
    let business1Count = 0;
    let business2Count = 0;
    let business3Count = 0;
    let business4Count = 0;
    isGamePaused = false;
    updateMoneyDisplay();
    timerDisplay.textContent = "180";
});
const moneyPerSecondDisplay = document.getElementById("money-per-second");
setInterval(updateMoneyPerSecondDisplay, 250);
function updateMoneyPerSecondDisplay() {
    if (!isGamePaused) {
        const currentTime = Date.now();
        const elapsedTimeInSeconds = (currentTime - startTime) / 1000;
        const moneyPerSecond = (money / elapsedTimeInSeconds).toFixed(2);
        moneyPerSecondDisplay.textContent = `Money per second: $${moneyPerSecond}`;
    }
};
searchButton.addEventListener("click", function () {
    const searchTerm = searchInput.value.toLowerCase();
    // Clear previous results
    businessList.innerHTML = "";
    // Filter and sort businesses based on the search term
    const filteredBusinesses = businesses.filter(business => business.name.toLowerCase().includes(searchTerm));
    const sortedBusinesses = filteredBusinesses.sort((a, b) => b.networth - a.networth);
    // Display the top 10 businesses
    const top10Businesses = sortedBusinesses.slice(0, 10);
    top10Businesses.forEach(business => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${business.name}</td>
            <td>$${business.networth}</td>
            <td><button class="purchase-button" data-business-name="${business.name}" data-cost="${business.networth}">Purchase</button></td>
        `;
        businessList.appendChild(row);
    });
    // Add event listeners to the "Purchase" buttons
    const purchaseButtons = document.querySelectorAll(".purchase-button");
    purchaseButtons.forEach(button => {
        button.addEventListener("click", function () {
            const businessName = this.getAttribute("data-business-name");
            const cost = parseInt(this.getAttribute("data-cost"));
            purchaseBusiness(businessName, cost);
        });
    });
});
function updatePlayerBusinessesList() {
    const playerBusinessList = document.getElementById("player-business-list");
    playerBusinessList.innerHTML = "";
    playerBusinesses.forEach((business, index) => {
        const listItem = document.createElement("li");
        listItem.textContent = Business ${index + 1}: ${business.name} (Revenue: $${business.revenue.toFixed(2)} per second);
        playerBusinessList.appendChild(listItem);
    });
}
// Function to purchase a business
// Call the updatePlayerBusinessesList function whenever a business is purchased
function purchaseBusiness(businessName, cost) {
    if (money >= cost && !isGamePaused) {
        money -= cost;
        const revenue = cost / 10; // Each business generates 1/10 of its cost per second
        playerBusinesses.push({ name: businessName, revenue });
        updateMoneyDisplay();
        updatePlayerBusinessesList(); // Update the list when a business is purchased
    } else {
        alert("Not enough money to buy this business.");
    }
}
</script>