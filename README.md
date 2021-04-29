# SpotifyAPIautomation

API automation of spotify APIS using rest assured

Technologies used:
1.Rest Assured
2.Maven
3.Allure for reporting
4.Mongo DB shared cluster(since its eligible under free tier)for storing client id and client secrets
5.Jenkins pipeline to run maven tests and  allure reporting(see jenkins file for the config)
6.Parameterized job to select different env in pipeline job using choices
 
 Running tests
 
 mvn clean test -DBASE_URI=<Your base URI>
 
 Base URI=https://api.spotify.com
