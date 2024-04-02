# Smart Card Chip Authentication app implements “SDA & DDA protocols”

What is "Smart Card Chip Authentication app"?

CryptoPuce is an educational program used to simulate the operation of smart card cryptographic protocols, namely SDA and DDA. The project also contains a simulation of the exploitation of a flaw in the SDA protocol.
 
  How to use it ?

   1. Open the project with eclipse (or other).
   
   2. SDA protocol (offline Static Data Authentication):
    
    2.1 "step 1": We will fill the information on the original card (the step is mandatory at the beginning) 
    - You must fill in the information in the "Bank" (the "Card Number" field is filled automatically by the bank) 
    - After pressing the bank icon 
    - You would see the all the queries between the bank, the certification center and the card with all the transfer happening
    - Finally you will have a valid card ready to use
     
    2.2 "step 2": Verifying the authenticity of the letter in the "card" field choose "Original" 
    - Click on the bank card 
    - Click on the terminal and enter the PIN code 
    - Click on ok and the transaction will be validated
     
    2.3 "step 3": it consists of creating a fake card with out the knowledge of the pin 
    - Click on the card and follow the events 
    - To test, you must return to "step 2" and choose "Fake" in "Card" 
    - For final testing, enter a wrong pin and see what happen
     
   3. DDA protocol (Dynamic Data Authentication):
    
    3.1 "Step 1" is identical to SDA "step1" (same steps).
    3.2 "Step 2" is identical to SDA "step2" (same steps).



System use:
OS="Ubuntu 16.04" JAVA_VERSION="1.8.0_131"
