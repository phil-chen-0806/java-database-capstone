## MySQL Database Design

### Table: users
- id: INT, Primary Key, Auto Increment
- username: Varchar(40), NOT NULL
- email: Varchar(40), NOT NULL
- phone: Varchar(15), NOT NULL
- password: Varchar(40), NOT NULL
- status: INT (1 = Active, 0 = Deleted)

### Table: patients
- patient_id: INT, Primary Key, Foreign Key → users(id)
- name: Varchar(40), NOT NULL
- details: Varchar(120), NOT NULL

### Table: doctors
- doctor_id: INT, Primary Key, Foreign Key → users(id)
- name: Varchar(40), NOT NULL
- specialization: Varchar(40), NOT NULL
- contact information: Varchar(80), NOT NULL

### Table: doctors_unavailability**
- doctor_id: INT, Foreign Key → doctors(id)
- unavailable_time_begin: DATETIME, Not Null
- unavailable_time_end: DATETIME, Not Null

### admin
- user_id: INT, Primary Key, Foreign Key → users(id)
- name: Varchar(40), NOT NULL
- 
### Table: appointments
- id: INT, Primary Key, Auto Increment
- doctor_id: INT, Foreign Key → doctors(id)
- patient_id: INT, Foreign Key → patients(id)
- appointment_time: DATETIME, Not Null
- status: INT (0 = Scheduled, 1 = Completed, 2 = Cancelled)

### other questions
What happens if a patient is deleted? Should appointments also be deleted?
v2) No, appointments and patient should not be physically deleted. Deleted can be a status for the records in appointments table and patient table.
v1) Yes for future appintments, and no for other status.

Should a doctor be allowed to have overlapping appointments?
No.

Should each doctor have their own available time slots?
Yes.

Should a patient's past appointment history be retained forever?
Yes.

Is a prescription tied to a specific appointment or can it exist independently?
A prescription should tied to a specific appointment.



## MongoDB Collection Design


### Collection: prescriptions
```json
{
"_id": "ObjectId('64abc123456')",
"patients_id",
"patientName": "John Smith",
"appointmentId": 51,
"medication": "Paracetamol",
"dosage": "500mg",
"doctorNotes": "Take 1 tablet every 6 hours.",
"refillCount": 2,
"pharmacy": 
	{
		"name": "Walgreens SF",
		"location": "Market Street"
	}
}
json```

### Collection: message
```json
{
"_id": "ObjectId('64abc123456')",
"patient_id",
"doctor_id",
"time",
"message": "Thanks for your prescriptions",
}


### other questions
Should MongoDB documents include the full patient object or just an ID?
Just an ID.

What would a chat message document look like?
See my Collection: message

What happens if the schema needs to evolve ‒ will your design support that?
Yes.
