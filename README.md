# Team21
#   - สมาชิกทีม
* นายเกรียงไกร  บัวบาน B6014728
* นายพิชญตม์ อ่ำบุญ B6021511
* นายพงศกร มาประโคน B6005795
* นายณัฐวัตร นารินทร์ B6015695
* นายปิยะพงษ์ กิตติชัยวัฒนา B6010768
* นายดำรงค์ เครือศรี B6010331

# Backend RUN
```
- cd backend
- ./gradlew bootRun | gradlew bootRun
- http://localhost:9000/db
```
   * application.properties
     * server.port = 9000
     * spring.h2.console.enabled=true
     * spring.h2.console.path=/db
     * spring.datasource.url=jdbc:h2:mem:testdb
     * spring.datasource.driverClassName=org.h2.Driver
     * spring.datasource.username=sa
     * spring.datasource.password=


# Frontend RUN
```
- cd frontend
- npm install
- yarn install
- yarn serve
- http://localhost:8080/
```




