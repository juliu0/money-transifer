# Money Transfer App

A comprehensive Android application for managing financial transactions including sending money, receiving money, investing, and betting on games.

## Features

### 1. Send Money
- Transfer money to other users by entering their recipient ID
- Real-time balance verification
- Transaction history tracking

### 2. Receive Money
- Accept money from other users
- Instant balance updates
- Transaction recording

### 3. Investment
- Multiple investment types: Stocks, Bonds, and Mutual Funds
- Set expected returns and investment duration
- Track active investments
- Automatic balance deduction and returns calculation

### 4. Betting on Games
- View available games (Football, Tennis, Basketball, etc.)
- Place bets with custom predictions
- Set odds and calculate potential winnings
- Track bet status (Pending, Won, Lost)

## Project Structure

```
app/
├── src/main/
│   ├── java/com/moneytransfer/app/
│   │   ├── models/           # Data models
│   │   │   ├── User.java
│   │   │   ├── Transaction.java
│   │   │   ├── Investment.java
│   │   │   ├── Game.java
│   │   │   └── Bet.java
│   │   ├── managers/         # Business logic
│   │   │   ├── UserManager.java
│   │   │   ├── TransactionManager.java
│   │   │   ├── InvestmentManager.java
│   │   │   └── BettingManager.java
│   │   ├── MainActivity.java
│   │   ├── SendMoneyActivity.java
│   │   ├── ReceiveMoneyActivity.java
│   │   ├── InvestmentActivity.java
│   │   └── BettingActivity.java
│   ├── res/
│   │   ├── layout/           # UI layouts
│   │   ├── values/           # Strings, colors, themes
│   │   └── mipmap/           # App icons
│   └── AndroidManifest.xml
└── build.gradle
```

## Technical Details

- **Language**: Java
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 33 (Android 13)
- **Build System**: Gradle
- **UI Framework**: Android SDK with Material Design Components

## Key Classes

### Models
- **User**: User account with balance management
- **Transaction**: Records money transfers between users
- **Investment**: Tracks investment details and returns
- **Game**: Represents betting games and their status
- **Bet**: Records user bets on games

### Managers
- **UserManager**: Manages user accounts and current user session
- **TransactionManager**: Handles send/receive operations
- **InvestmentManager**: Manages investment creation and withdrawals
- **BettingManager**: Handles game betting and bet resolution

## How to Build

1. Ensure you have Android SDK installed
2. Clone the repository
3. Open in Android Studio or build with Gradle:
   ```bash
   ./gradlew build
   ```

## Usage

1. Launch the app to see your current balance
2. Choose from four main options:
   - **Send Money**: Transfer funds to another user
   - **Receive Money**: Accept funds from another user
   - **Invest**: Create an investment with expected returns
   - **Betting on Games**: Place bets on available games

## Default Demo User

The app comes with a default demo user:
- **ID**: user1
- **Name**: John Doe
- **Email**: john@example.com
- **Initial Balance**: $1000.00

## License

Boost Software License - Version 1.0
