# search_fight_server

This project is a maven project bootstrapped with [Spring Initializr](https://github.com/spring-io/initializr/).

## Running the project locally

After cloning the project, `mvn clean install` ...installs all the dependencies 
the project needs.

Go to the `application.properties` file and replace the placeholder values for 
the google engine context, google api key, and bing api key with literal values. 

```
search_url.engine_context.google=${GOOGLE_CONTEXT}
search_url.api_key.google=${GOOGLE_API_KEY}
search_url.api_key.bing=${BING_API_KEY}
```

The api endpoint for conducting the search fight and getting it's result is:

`GET http://localhost:8080/api/search_terms/{searchTermOne}/{searchTermTwo}`

A successful search fight call will respond with a Scorecard data transfer object
in the body of a status 201 created response entity. An unsuccessful search fight 
call will respond with a string message saying why it did not succeed in the body 
of a status 200 response entity.

An example of a scorecard response json object is:

```json
{
  "searchTermOneGoogleHits": 17000000000,
  "searchTermTwoGoogleHits": 2700,
  "searchTermOneBingHits": 2699,
  "searchTermTwoBingHits": 17000000000,
  "googleWinner": "search term one",
  "bingWinner": "search term two",
  "overallWinner": "search term two"
}
```
