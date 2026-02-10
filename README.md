# codeChallengeSPotify Backend - Spring Boot
This project is a backend Java application developed as part of a Full Stack Developer code challenge.
It is integrated with the codeChallengeSpotify React Application (https://github.com/rafaelcirq/codeChallengeSpotifyFrontend) using music tracks ISRC codes to display their information retreived from the backend or store it, using a Spotify API Token.

## Spotify API Token
A standard Spotify Access Token could not be obtained since Spotify has blocked creation of new Apps.
A temporary token can be obtained at the get-an-album documentation page (https://developer.spotify.com/documentation/web-api/reference/get-an-album) and used as an input in this project to create new tracks.

### Usage
The token is received in the endpoint as a header parameter.
The backend uses the token only to make authenticated requests to the Spotify API.
The token is not stored on the server; it is used only during the request lifecycle.

### Limitations
Temporary tokens expire quickly, so a new token must be obtained for continued usage.
Since the backend does not store the token, there is no long-term authentication.
This approach assumes that the user or developer provides a valid temporary token for every request that requires Spotify API access.
This method is not suitable for production environments, where a proper Spotify App and OAuth flow should be used to obtain permanent access tokens securely.

This approach is strictly a temporary solution and is not suitable for production environments.

## Features
- Search track metadata by ISRC code
- Get stored Album covers
- Create and store new tracks by ISRC code

## Stacks
- Java
- Spring Boot
- Maven
- Git
- PostgreSQL 16
- Postman
- Flyway

## Live Demo

The frontend has been deployed to GitHub Pages and can be accessed here:

[https://rafaelcirq.github.io/codeChallengeSpotifyFrontend/#/]

The app interacts with this backend API, so To make it fully functional, you need to have tthis backend installed and running locally on the correct port. Without the backend running, most features will not work.

Frontend repository: https://github.com/rafaelcirq/codeChallengeSpotifyFrontend

## Setup and Installtion
### Prerequisites
- Java 17
- Maven
- Postgresql 16

Dependencies are handled by Maven (pom.xml)
Media files for album covers are stored under media/covers (Spring creates this folder automatically)

### Installation
## clone the repository
``` bash
git clone https://github.com/rafaelcirq/codeChallengeSpotifyBackend.git

cd .\codeChallengeSpotifyBackend\
```
## before running the application
Make sure the database exists with proper settings and the user has privileges to create tables
Proper settings can be found in application.properties

## run
``` bash
mvn clean install

mvn spring-boot:run
```

### Endpoint example
POST http://localhost:8080/codechallenge/createTrack?isrc={isrc}
Headers:
  Authorization: Bearer {temporary_spotify_token}