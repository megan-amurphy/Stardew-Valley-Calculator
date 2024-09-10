# Requirements

Calculates total and net profit of Stardew Valley crops that user inputs. ie. 10 silver star pumpkins revenue = XXXXXXXX G, money spent on seeds: XXXX, net profit = (revenue - cost). 


# Crop Data Included: 
- Crop Name
- Crop Sell Value (for each quality level)
- Location of Seeds Purchased
  - Seed Cost

# Screen
## Crop Selection Screen 
- A screen where user enters all crop details.
- Crop Name (autocomplete)
- Quality of Selected Crop (dropdown)
- Amount of Selected Crop  (number input)
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

Notes for PostgreSQL ER/D, move seed_price to seed_location join table, create a lower and upper bound columns for handling price range
Consider adding "item" column for islander trade location where gold is not exchanged for seeds. Trade and seedmaker are both locations in this system

