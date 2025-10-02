Section 1: Architecture summary
In my Spring Boot application (java-database-capstone project), for the user interface layer, I use Thymeleaf templates for the Admin and Doctor dashboards.
In the controller layer, Thymeleaf controllers are used to handle request from browers and REST controllers is responsible for requests from API consumers.
In the service layer, controllers implement logic and validations, coordinate data flow across multiple entities, and separate logic with data access. All controllers route requests through a common service layer, which in turn delegates to the appropriate repositories. MySQL uses JPA entities while MongoDB uses document models.
In the data access layer, two databases—MySQL (for patient, doctor, appointment, and admin data) and MongoDB (for prescriptions) are used. 


Section 2: Numbered flow of data and control

Step1: User accesses Admin Dashboard or Appointment pages.

Step2: The action is routed to the appropriate Thymeleaf or REST controller.

Step3: The controller calls the service layer(apply business rules and validations)

Step4: service layer communicates with repository layer for data access operations(MySQL uses Spring Data JPA and MongoDB uses Spring Data MongoDB)

Step5: Repositories interface interact with underlying database engines.

Step6: Model binding (In MySQL, the data is converted into JPA entities. In MongoDB, the data is loaded into document objects)

Step7: Application models (In MVC flows, models are passed from the controller to Thymeleaf templates. In REST flows, the models are serialized into JSON)

