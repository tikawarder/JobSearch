### Alap követelmények
- Git
- Java 11 
- Maven 3.6

### A JobSearch alkalmazás használata
git clone 
használjunk Maven-t az alkalmazás build-eléséhez:
- mvn clean install
Inditsuk el a gyökérkönyvtárból:
- java -jar target/JobSearch-0.0.1-SNAPSHOT.jar

## Webes felületek:
- Az alkalmazás a localHost:8080 cimen érhető el.
- Az inmemory adatbázis az alábbi helyen található: http://localhost:8080/h2-console
  - felhasználónév: sa
  - jelszó: password
- A szükséges endpointokat a swagger-ui segitségével lehet tesztelni.

### Továbbfejlesztési lehetőségek:
- integration testing kidolgozása
- deployálni javasolt egy felhő tárhelyre.
- beállitani a Ci/Cd alkalmazást, pl. Jenkins, GitHub workflows
- a Frontend részt le kell fejleszteni és összekapcsolni a Rest API-val.
- UI teszteket lefuttatni
- manual tesztelést elvégezni a felhasználóval
- 