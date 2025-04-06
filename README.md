# Android Intern Assignment

This project is a job application app built as part of an Android intern assignment. It demonstrates a professional Material Design UI and incorporates several modern Android development technologies, including Jetpack Compose, Retrofit, Paging 3, Room Database, and Dagger Hilt for dependency injection.

## Features

- **Bottom Navigation:**  
  The app features a bottom navigation bar with two sections:
  - **Jobs:** Displays job listings fetched from the API.
  - **Bookmarks:** Displays the bookmarked jobs stored locally for offline viewing.

- **Infinite Scrolling Jobs List:**  
  The Jobs screen fetches data from the API endpoint `https://testapi.getlokalapp.com/common/jobs?page=1` using Retrofit and the Paging 3 library. Each job is displayed in a custom card showing title, location, salary, and phone.

- **Job Details Screen:**  
  Tapping on a job card navigates to a detailed screen that shows more information about the job. The top app bar displays "Job Details" along with a back button and a bookmark icon.  
  - The bookmark icon toggles state (with color changes) and reflects the bookmark status.
  - Direct links are provided for contacting HR via WhatsApp and by phone (using `tel:` links).

- **Bookmarking & Offline Storage:**  
  Users can bookmark jobs, and all bookmarked jobs are stored in a Room database for offline viewing.

- **Clean Architecture:**  
  The project follows clean architecture principles, ensuring separation of concerns and scalability.

- **Dependency Injection:**  
  Dagger Hilt is used for dependency injection, facilitating a modular and testable codebase.

## Libraries & Technologies Used

- **Jetpack Compose:** For building the UI.
- **Material3:** For modern Material Design components.
- **Retrofit:** For networking and API calls.
- **Paging 3:** For infinite scrolling and efficient data loading.
- **Room:** For local data persistence.
- **Dagger Hilt:** For dependency injection.
- **Kotlin Coroutines:** For asynchronous programming.

## Screenshots

*(Include screenshots of your app here if available.)*

## Video Walkthrough

A video walkthrough of the app is available [here](https://yourvideolink.com).

## Getting Started

### Prerequisites

- Android Studio Bumblebee or later
- Android SDK with minimum API level 21
- Gradle 7.0 or later

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/your-repo-name.git
