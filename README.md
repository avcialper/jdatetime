# JDateTime

[![](https://jitpack.io/v/avcialper/jdatetime.svg)](https://jitpack.io/#avcialper/jdatetime)

![header](./assets/jdatetime.svg)

JDateTime is a lightweight Android library for seamless date and time operations, offering compatibility with both modern and legacy API levels. It simplifies formatting, calculations, and conversions while supporting leap year checks, day/month names, and more.

This library uses both Android's ``LocalDateTime`` and ``Calendar`` APIs. The LocalDateTime API is supported on devices running Oreo (API 26) or higher. Logical handling is in place to detect the device's API level and use the appropriate API accordingly. Some methods work with both APIs, while others have alternative implementations for different versions. If you experience any crashes, please refer to the method descriptions for clarification.

# Installation

First, add the following dependency to settings.gradle.kts file

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Later, add this implementation build.gradle.kts (Module:app)

```gradle
dependencies {
    ..
    implementation("com.github.avcialper:jdatetime:${latest_version}")
}
```

TOML usage

```gradle
dependencies {
    ..
    implementation(libs.jdatetime)
}
```

```toml
[versions]
jdatetime = "latest_version"

[libraries]
jdatetime = { module = "com.github.avcialper:jdatetime", version.ref = "jdatetime" }
```

# Properties
| Name | Description | Return |
| --- | --- | --- |
| instance | Gets the instance of JDateTime. | `JDateTime` |
| isDeviceEqualOrHigherO | Determines if the device's API level is Android 8 (API 26) or higher | `Boolean` |
| date | Current date as a string in the format `dd.MM.yyyy`. | `String` |
| time | Current time as a string in the format `HH:mm:ss`. | `String` |
| year | Current year | `Int` |
| month | Current month (0 for January to 11 for December)| `Int` |
| dayOfMonth | Current day of the month, starting from `1` | `Int` |
| hour | Current hour of the day | `Int` |
| minute | Current minute | `Int` |
| second | Current seocond | `Int` |
| epochDay | Number of days since `January 1, 1970`. | `Long` |
| timeInSecond | Time of day in a second, from `0` to `24 * 60 * 60 - 1`. | `Int` |
| dayOfWeek | Current day of the week (0 for Monday to 6 for Sunday) | `Int` |
| dayOfYear | Current day of the year, starting from `1` | `Int` |
| dayName | Current day name as `JDay` enum | `JDay` |
| weekOfMonth | Week of the month | `Int` |
| weekOfYear | Week of the year | `Int` |
| monthName | Month name as `JMonth` enum | `JMonth` |
| isLeapYear | True if the current year is a leap year | `Boolean` |
| jDate | Current date as a `JDate` object. | `JDate` |
| jTime | Current time as a `JTime` object. | `JTime` |

# Functions
| Name | Description | Params | Return |
| --- | --- | --- | --- |
| getAllDaysOfMonth | Returns all days of the month as a list of `JDayOfMonth` | `year` - desired year <br> `month` - month of the year (0 for January to 11 for December) | `List<JDayOfMonth>` |
| `Equal or higher than API 26` <br> format | Formats the current date using a `DateTimeFormatter` | `formatter` - DateTimeFormatter instance | `String` |
| `Lower than API 26` <br> format  | Formats the current date using a `SimpleDateFormatter` | `formatter` - SimpleDateFormatter instance | `String` |
| `Equal or higher than API 26` <br> formatDate | Formats a given date using a `DateTimeFormatter` | `formatter` - DateTimeFormatter instance <br> `date` - LocalDate instance | `String` |
| `Lower than API 26` <br> formatDate | Formats a given date using a `SimpleDateFormatter` | `formatter` - SimpleDateFormatter instance <br> `date` - Calendar instance | `String` |
| `Equal or higher than API 26` <br> formatTime | Formats the current time using a `DateTimeFormatter` | `formatter` - DateTimeFormatter instance | `String`|
| `Lower than API 26` <br> formatTime | Formats the current time using a `SimpleDateFormatter` | `formatter` - SimpleDateFormatter instance | `String`|
| `Equal or higher than API 26` <br> findDateDifference | Calculates the difference between two dates using `LocalDate` | `fromDate` - start date <br> `toDate` - end date | `JDateDifference` |
| `Lower than API 26` <br> findDateDifference | Calculates the difference between two dates using `Calendar` | `fromDate` - start date <br> `toDate` - end date | `JDateDifference` |
| `Equal or higher than API 26` <br> findDayDifference | Calculates the day difference between two dates using `LocalDate`. | `fromDate` - start date <br> `toDate` - end date | `Int` |
| `Lower than API 26` <br> findDayDifference | Calculates the day difference between two dates using `Calendar` | `fromDate` - start date <br> `toDate` - end date | `Int` |
| lengthOfMonth | Returns the length of a given month | `year` - year of the month <br> `month` - month (0 for January to 11 for December) | `Int` |
| findEpochDay | Calculates the epoch day of a given date | `year` - year of the date <br> `month` - month (0 for January to 11 for December) <br> `dayOfMonth` - day of the month (1 - 31) | `Long` |
| findDayOfWeek | Determines the day of the week for a given date | `year` - year of the date <br> `month` - month (0 for January to 11 for December) <br> `dayOfMonth` - day of the month (1 - 31) | `Int` |
| findDayOfYear | Determines the day of the year for a given date | `year` - year of the date <br> `month` - month (0 for January to 11 for December) <br> `dayOfMonth` - day of the month (1 - 31) | `Int` |
| findDayName | Determines the day name of a given date as `JDay` | `year` - year of the date <br> `month` - month (0 for January to 11 for December) <br> `dayOfMonth` - day of the month (1 - 31) | `JDay` |
| findTimeInMinute | Converts a given time to total minutes | `hour` - hour component of the time <br> `minute` - minute component of the time | `Int` |
| isLeapYear | Determines if a given year is a leap year | `year` - year to checked | `Boolean` |