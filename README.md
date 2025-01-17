# GitHub User Finder

GitHub User Finder is an Android application to search for GitHub users. The application is built using a modular architecture consisting of several modules: `app`, `domain`, `data`, `local`, `remote`, and `shared`.

## Key Features
- Search for GitHub users by username.
- Modular architecture for better code organization and scalability.
- Local mode support

## Project Architecture

### Modules
- **app**: Contains the main Android code, such as Activities and Fragments.
- **domain**: Contains business logic and use cases.
- **data**: Manages data from local and remote sources.
- **local**: Provides local storage.
- **remote**: Manages communication with external APIs (GitHub API).
- **shared**: Provides utilities, constants, or components used across multiple modules.

## System Requirements
- Android Studio LadyBug or later.
- JDK 11 or later.
- Minimum SDK version 24 (Android 5.0 Lollipop).

## How to Build the Project

### 1. Clone the Repository
Clone this repository to your local machine using the following command:
```bash
git clone https://github.com/masesas/android-github-user-search
cd android-github-user-search
```

### 2. Build Using Gradle Command Line

#### Sync and Build the Project
Run the following commands to build the project:
```bash
# Navigate to the project root directory
cd android-github-user-search

# Sync dependencies
./gradlew dependencies

# Build the project
./gradlew assembleDebug
```

#### Run the Application on an Emulator or Connected Device
Make sure you have an Android device connected or an emulator running. Then, execute:
```bash
./gradlew installDebug
```

#### Clean the Project
If you encounter any issues, you can clean the project using:
```bash
./gradlew clean
```

## Directory Structure
```
.
├── app/         # Main Android code (UI and entry point of the app)
├── domain/      # Business logic and use cases
├── data/        # Repositories and data management
├── local/       # Local data sources
├── remote/      # Remote data sources (GitHub API)
├── shared/      # Reusable components and utilities
```

## Technologies Used
- **Kotlin** as the primary programming language.
- **Jetpack Libraries** such as LiveData, ViewModel, Room, Navigation UI, Navigation UI Sage Argument.
- **OKHttp** for HTTP Request.
- **Retrofit** for API communication.
- **Moshi** for JSON Parsing.
- **Chucker** for API Debugging.
- **Koin** for dependency injection.
- **Coroutines** for asynchronous processing.
- **Worker** for worker such as cached to database or syncronized data.
- **Version Catalog** for managed dependencies.