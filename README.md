# SpotifyAPIautomation

API automation of spotify APIS using rest assured

Technologies used:
1.Rest Assured
2.Maven
3.Allure for reporting
4.Mongo DB shared cluster(since its eligible under free tier)for storing client id and client secrets
 
 
 Running tests
 
 mvn clean test -DBASE_URI=<Your base URI>
 
 Base URI=https://api.spotify.com