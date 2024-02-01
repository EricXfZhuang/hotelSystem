To run the application:
1. Load all dependencies by Maven
2. Replace with your own mysql credential in `src/main/resources/application.properties`
2. Create a databse `hotel`.
3. Run `src/main/resources/hotel.sql`
4. Run `src/main/java/com/org/hotelSystem/HotelSystemApplication.java`

I put the design diagram in the `doc` directory.    

I have already put a test a use case in `src/test/java/com/org/hotelSystem/controller/UseCaseTest.java` which test the main flow of this project.

I spent roughly 25+ hours on this project. I made the architecture very simple and I only implemented the part described in the requirements.

The executable jar is `target/hotelSystem-0.0.1-SNAPSHOT.jar`