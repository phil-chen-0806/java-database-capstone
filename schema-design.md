## MySQL Database Design

### users
- id: INT, Primary Key, Auto Increment
- username: Varchar(40), NOT NULL 
- password: Varchar(40), NOT NULL
- email: Varchar(40), NOT NULL 

### patients
- user_id: INT, Foreign Key → users(id)

### doctors
- user_id: INT, Foreign Key → users(id)
- name: Varchar(40), NOT NULL
- specialization: Varchar(40), NOT NULL

### admin
- user_id: INT, Foreign Key → users(id)
- name: Varchar(40), NOT NULL
- 
### Table: appointments
- id: INT, Primary Key, Auto Increment
- doctor_id: INT, Foreign Key → doctors(id)
- patient_id: INT, Foreign Key → patients(id)
- appointment_time: DATETIME, Not Null
- status: INT (0 = Scheduled, 1 = Completed, 2 = Cancelled)
 

## MongoDB Collection Design
