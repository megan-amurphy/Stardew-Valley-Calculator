# Stardew Valley Profit Calculator

The **Stardew Valley Profit Calculator** is a full-stack web application designed to help players of Stardew Valley calculate the revenue and net profit for various crops and seeds. It takes into account crop quality, seed purchase location, and whether seeds are bought out-of-season to provide players with accurate financial information to optimize their in-game farming strategy.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [License](#license)
- [Screens](#screens)
- [Future Improvements](#future-improvements)

## Features

- **Crop and Seed Input**: Enter multiple crops with different qualities, quantities, and seed purchase locations.
- **Revenue and Net Profit Calculation**: Calculates the revenue and net profit for crops based on their quality and quantity.
- **Handling Out-of-Season Purchases**: Allows users to input whether the seeds were bought out of season, affecting the price.
- **User-Friendly Interface**: Features a responsive design with a clean user interface.
- **Export Results**: Users can export their results to a JSON file for future reference.
- **Grand Totals**: Displays the grand total for revenue and net profit for all crops entered.

## Technologies

This project uses the following technologies:

- **Front-End**:
  - Vue.js
  - HTML/CSS (Responsive Design)
  - JavaScript
- **Back-End**:
  - Spring Boot (Java)
  - RESTful APIs
- **Database**:
  - PostgreSQL
- **Build Tools**:
  - Maven
  - Axios (for HTTP requests)

## Usage

1. Navigate to the crop input screen.
2. Input the crop details (name, quality, quantity, seed purchase location, etc.).
3. Click on "Calculate" to see the revenue and net profit for each crop.
4. View the results on the results screen with detailed breakdowns of how the calculations were made.
5. Export the results as a JSON file for later reference.

## API Documentation

For detailed information about the public API, see API Documentation below.

**********\_\_\_**********-
| HTTP Method | Endpoint | Request Body | Description |
|-------------|-----------|--------------|-------------|
| POST | `/api/calculate` | `{ crops: [], seeds: [], cropQuantities: [], seedQuantities: [] }` | Submits crop and seed data for revenue and profit calculations. |
| GET | `/api/crops` | N/A | Fetches all available crop suggestions from the database. |

##### Example Request: POST `/api/calculate`

```json
{
  "crops": [
    {
      "cropName": "Parsnip",
      "cropQuality": "gold",
      "price": 40
    },
    {
      "cropName": "Corn",
      "cropQuality": "silver",
      "price": 70
    }
  ],
  "seeds": [
    {
      "seedName": "Parsnip Seed",
      "purchaseLocationName": "Pierre's General Store",
      "isOutOfSeason": false
    },
    {
      "seedName": "Corn Seed",
      "purchaseLocationName": "JojaMart",
      "isOutOfSeason": true
    }
  ],
  "cropQuantities": [20, 30],
  "seedQuantities": [15, 25]
}
______________________________________________

├── backend/               # Spring Boot Backend
│   ├── src/               # Source files
│   └── pom.xml            # Maven build file
├── frontend/              # Vue.js Frontend
│   ├── public/            # Static assets
│   ├── src/               # Vue source files
│   └── package.json       # Node.js dependencies
└── README.md              # Project documentation

## License
This project is licensed under the MIT License. See the LICENSE file for more details.

# Screen Designs

## Crop Selection Screen

- A screen where user enters all crop details.
- Crop Name (autocomplete)
- Quality of Selected Crop (dropdown)
- Amount of Selected Crop (number input)
- Select where seeds were purchased (dropdown)
- Submit button
- Clear/ Reset Input Button

## Result Screen

- Screen that shows the revenue and profits to the user, in itemized format.
- Displayed data inputted
- Revenue
  -Display how revenue was calculated
- Cost
  -Display how cost was calculated
- Net Profit
  -Display how net profit was calculated
- Export button
  - Allows user to save the data inputted as a file (TODO: FORMAT?)
- Start-over button

# Future Improvements

- Processed Crop Names
- Processed Crop Sell Values
- Growth rate for Crop (to implement gold per day value)
```
