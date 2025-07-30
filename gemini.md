# Gemini Project Notes

This file is for notes and configurations specific to how the Gemini agent should interact with this project.

## Project Overview
- **Name:** RentHouse
- **Type:** Spring Boot Application
- **Language:** Java
- **Build Tool:** Maven
- **Database:** H2 (rentHouse.mv.db, rentHouse.trace.db)
- **Frontend:** FreeMarker templates, Bootstrap, SB Admin 2 (HTML, CSS, JS)

## Key Directories
- `src/main/java`: Main Java source code
- `src/main/resources`: Application properties, static assets, FreeMarker templates
- `src/test/java`: Java test code
- `data`: H2 database files

## Build and Run Commands
- **Build:** `./mvnw clean install`
- **Run:** `java -jar target/renthouse-0.0.1-SNAPSHOT.jar` (after building) or `./mvnw spring-boot:run`
- **Run (Windows batch):** `run-RentHouseService.bat`

## Testing
- **Run Tests:** `./mvnw test`

## Important Notes for Gemini
- When modifying Java code, adhere to existing Spring Boot conventions.
- When modifying frontend assets, maintain the SB Admin 2 theme and Bootstrap conventions.
- Always verify changes by running relevant tests or build commands.
- Be mindful of the different `application-*.properties` files for various environments.