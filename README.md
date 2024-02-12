# Disclaimer
This project was created as an recruiting assignment. The intended timeline to complete it is 
2-3 hours. There are multiple things in this project that are hacky and goofy, but most of them were due to 
trying to get them somewhat in the 2-3 hour limit
. 
## Good
1. Containerization and easy setup 
2. Sensible project structure and a lot of tooling bundled with technologies chosen
3. A seeder that can easily parse a very large CSV file in the service
## Bad
1. No unit tests
2. An awkward mutable map in what would be in the database layer to save time. Never would do this in real development
3. A very simple implementation

Right now it is a dead repo, but leaving it in since I think there is a lot of good with it considering the time I spent. 


# Why I Used the Toolkit I did
## Seeder
For the seeder I used Python and the request library and iterated a csv file. That was mostly done for speed of developing. 
## Loan-Service
The big decision on the Loan Service was to use Tapir. Some things that I like about it is that it was super easy to set up. It also comes
with a project auto-generating [support](https://tapir.softwaremill.com/en/latest/). The things that I like about that technology is that it Auto Generates an Open-API specification. It also feels pretty light weight, but will 
couple the project to Cats or Zio. It's been awhile since I have worked with those libraries, but I think they are well supported and can reduce complexity when leveraged correctly. If I had time to learn something, I probably would have opted out of Tapir and used something more adopted for this project. One of my issues with Tapir is that while it feels a bit light weight, it also feels a bit infelxible with its couplings to Cat/Zio. 

# Improvements
1. Persist loan level data in the database and be able to query efficiently the metrics
    1. With the amount of fields on loan level data, I would probably lean towards some form of Document store or a combination between RDMS and a Document store. Having a 200 column table in an RDMS is a bit heavy handed
2. Would add integration and unit tests. I skipped those for this assignment. 
3. Better logging 
4. Probably would familiarize myself with Cats a bit more.
5. A lot of stuff is hardcoded and not configurable that should be.
6. The implementation would break if there was more than one service

# How to Run
Start the service via
```shell
docker build . -t loan-service
docker run -it -p 8080:8080 loan-service
```
Seed the data into the service via following these [instructions](seeder/README.md)
