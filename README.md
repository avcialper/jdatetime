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
    implementation("com.github.avcialper:jdatetime:1.0.0")
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
jdatetime = "1.0.0"

[libraries]
jdatetime = { module = "com.github.avcialper:jdatetime", version.ref = "jdatetime" }
```

# Properties
| Name | Description | Return |
| --- | --- | --- |
| instance | Gets the instance of JDateTime. | `JDateTime` |
| date | Gets the current date as a string in the format `dd.MM.yyyy`. | `String` |
| time | Gets the current time as a string in the format `HH:mm:ss`. | `String` |
| year | Gets the current year field. | `Int` |
| month | Gets the current month-of-year field. <br> `0 - Januray` <br> `11 - December`| `Int` |
| day | Gets the current day-of-month field. Start day `1` | `Int` |
| hour | Gets the current hour-of-day field. | `Int` |
| minute | Gets the current minute-of-hour field. | `Int` |
| second | Gets the current second-of-minute field. | `Int` |
| epochDay | Current date as a long value representing the number of days since `January 1, 1970`. | `Long` |
| timeInSecond | Gets the time as seconds of day, from `0` to `24 * 60 * 60 - 1`. | `Int` |
| dayOfWeek | Gets the current day-of-week field. <br> `0 - Monday` <br> `6 - Sunday` | `Int` |
| dayOfMonth | Gets the current day-of-month field. Start day `1`. | `Int` |
| dayOfYear | Gets the current day-of-year field. Start day `1`. | `Int` |
| dayName | Gets the current day name field, which is an enum `JDay`. | `JDay` |
| weekOfMonth | Gets the current week-of-month field. | `Int` |
| weekOfYear | Gets the current week-of-year field. | `Int` |
| monthName | Gets the current month name field, which is an enum `JMonth`. | `JMonth` |
| isLeapYear | Check if the current year is a leap year. | `Boolean` |

# Functions
| Name | Description | Params | Return |
| --- | --- | --- | --- |
| getAllDaysOfMonth | Gets all days of the month-of-year. | `year` - desired year. <br> `month` - the month-of-year. (0-11) | `List<JdafOfMonth>` |
| format | Formats current date using a formatter. | `formatter` - if the device is lower than **API 26** use `SimpleDateFormat` else `DateTimeFormatter` | `String` |
| formatDate | Formats the given date using a formatter. | `formatter` - if the device is lower than **API 26** use `SimpleDateFormatter` else `DateTimeFormatter` <br> `date` - if the device is lower than **API 26** use `Calendar` else `LocalDate` | `String` |
| formatTime | Formats current time using a formatter. | `formatter` - if the device is lower than **API 26** use `SimpleDateFormat` else `DateTimeFormatter` | `String`|
| findDateDifference | Calculates the difference between two dates. | If the device is lower than **API 26** use `Calendar` else `LocalDate`. <br> `fromDate` - start date <br> `toDate` - end date | `JDateDifference` |
| findDayDifference | Calculates the day difference between two dates. | If the device is lower than **API 26** use `Calendar` else `LocalDate`. <br> `fromDate` - start date <br> `toDate` - end date | `Int` |
| lengthOfMonth | Calculate the length of the given month. | `year` - wanted year (the length of some months may vary from year to year) <br> `month` - wanted month | `Int` |
| isLeapYear | Check if the given year is a leap year. | `year` - year to be checked | `Boolean` |