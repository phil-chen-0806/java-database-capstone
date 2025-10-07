package com.project.back_end.services;

//import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back_end.DTO.Login;
import com.project.back_end.models.Admin;
import com.project.back_end.models.Appointment;
import com.project.back_end.models.Doctor;
import com.project.back_end.models.Patient;
import com.project.back_end.repo.AdminRepository;
import com.project.back_end.repo.DoctorRepository;
import com.project.back_end.repo.PatientRepository;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

//@Service
@org.springframework.stereotype.Service
public class Service {
    private final TokenService tokenService;
    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;

// 1. **@Service Annotation**
// The @Service annotation marks this class as a service component in Spring. This allows Spring to automatically detect it through component scanning
// and manage its lifecycle, enabling it to be injected into controllers or other services using @Autowired or constructor injection.

// 2. **Constructor Injection for Dependencies**
// The constructor injects all required dependencies (TokenService, Repositories, and other Services). This approach promotes loose coupling, improves testability,
// and ensures that all required dependencies are provided at object creation time.
    public Service(
        TokenService tokenService, 
        AdminRepository adminRepository, 
        DoctorService doctorService,
        DoctorRepository doctorRepository, 
        PatientRepository patientRepository, 
        PatientService patientService) 
        {
            this.tokenService = tokenService;
            this.adminRepository = adminRepository;
            this.doctorService = doctorService;
            this.doctorRepository = doctorRepository;
            this.patientRepository = patientRepository;
            this.patientService = patientService;
        }


// 3. **validateToken Method**
// This method checks if the provided JWT token is valid for a specific user. It uses the TokenService to perform the validation.
// If the token is invalid or expired, it returns a 401 Unauthorized response with an appropriate error message. This ensures security by preventing
// unauthorized access to protected resources.

    public ResponseEntity<Map<String, String>> validateToken(String token, String user){
        Map<String, String> response = new HashMap<>();
        
        if(tokenService.validateToken(token, user) == false)
        {   
            //not validateToken false 
            response.put("error", "Invalid or expired token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        else{
            response.put("valid", "valid token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            //这里还是 UNAUTHORIZED？ 感觉不太对
        }
    }

// 4. **validateAdmin Method**
// This method validates the login credentials for an admin user.
// - It first searches the admin repository using the provided username.
// - If an admin is found, it checks if the password matches.
// - If the password is correct, it generates and returns a JWT token (using the admin’s username) with a 200 OK status.
// - If the password is incorrect, it returns a 401 Unauthorized status with an error message.
// - If no admin is found, it also returns a 401 Unauthorized.
// - If any unexpected error occurs during the process, a 500 Internal Server Error response is returned.
// This method ensures that only valid admin users can access secured parts of the system.

    public ResponseEntity<Map<String, String>> validateAdmin(Admin receivedAdmin) {
        Map<String, String> map = new HashMap<>();
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

// 5. **filterDoctor Method**
// This method provides filtering functionality for doctors based on name, specialty, and available time slots.
// - It supports various combinations of the three filters.
// - If none of the filters are provided, it returns all available doctors.
// This flexible filtering mechanism allows the frontend or consumers of the API to search and narrow down doctors based on user criteria.

    public Map<String, Object> filterDoctor(String name, String specility, String time) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("Service.filterDoctor is called");
        if (!specility.equals("null") && !time.equals("null")) {
            map = doctorService.filterDoctorByTimeAndSpecility(specility, time);
        }
        else {
            map.put("doctors", doctorService.getDoctors());
        }
        return map;
    }

// 6. **validateAppointment Method**
// This method validates if the requested appointment time for a doctor is available.
// - It first checks if the doctor exists in the repository.
// - Then, it retrieves the list of available time slots for the doctor on the specified date.
// - It compares the requested appointment time with the start times of these slots.
// - If a match is found, it returns 1 (valid appointment time).
// - If no matching time slot is found, it returns 0 (invalid).
// - If the doctor doesn’t exist, it returns -1.
// This logic prevents overlapping or invalid appointment bookings.

    public int validateAppointment(Appointment appointment) {
        return 0;
    }

// 7. **validatePatient Method**
// This method checks whether a patient with the same email or phone number already exists in the system.
// - If a match is found, it returns false (indicating the patient is not valid for new registration).
// - If no match is found, it returns true.
// This helps enforce uniqueness constraints on patient records and prevent duplicate entries.

    public boolean validatePatient(Patient patient) {
        return true;
    }

// 8. **validatePatientLogin Method**
// This method handles login validation for patient users.
// - It looks up the patient by email.
// - If found, it checks whether the provided password matches the stored one.
// - On successful validation, it generates a JWT token and returns it with a 200 OK status.
// - If the password is incorrect or the patient doesn't exist, it returns a 401 Unauthorized with a relevant error.
// - If an exception occurs, it returns a 500 Internal Server Error.
// This method ensures only legitimate patients can log in and access their data securely.

    public ResponseEntity<Map<String, String>> validatePatientLogin(Login login) {
        Map<String, String> map = new HashMap<>();
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

// 9. **filterPatient Method**
// This method filters a patient's appointment history based on condition and doctor name.
// - It extracts the email from the JWT token to identify the patient.
// - Depending on which filters (condition, doctor name) are provided, it delegates the filtering logic to PatientService.
// - If no filters are provided, it retrieves all appointments for the patient.
// This flexible method supports patient-specific querying and enhances user experience on the client side.
    public ResponseEntity<Map<String, Object>> filterPatient(String condition, String name, String token) {  
        Map<String, Object> map = new HashMap<>();
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
    


}
