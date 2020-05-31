package se.g21.backend.studentsystem.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;


import se.g21.backend.studentsystem.entities.*;
import se.g21.backend.studentsystem.repository.*;

import se.g21.backend.employeesystem.entities.*;
import se.g21.backend.employeesystem.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://172.17.0.201:8080")
@RestController
public class StudentController {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private NametitleRepository nametitleRepository;


    StudentController(StudentRepository studentRepository,
                     EmployeeRepository employeeRepository,
                     NametitleRepository nametitleRepository,
                     GenderRepository genderRepository,
                     ProvinceRepository provinceRepository) {

        this.studentRepository = studentRepository;
        this.employeeRepository = employeeRepository;
        this.nametitleRepository = nametitleRepository;
        this.genderRepository = genderRepository;
        this.provinceRepository = provinceRepository;

    }

    @GetMapping("/student")
    public Collection<Student> Students() {
        return studentRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/student/{fullname}/")
    public Collection<Student> Students(@PathVariable("fullname") String fullname) {
        fullname = fullname + "%";
        return studentRepository.findByFullname(fullname);
    }

    @PostMapping("/student/{nametitle_id}/{gender_id}/{fullname}/{province_id}/{address}/{username}/{password}/{old}/{tel}/{email}/{employee_id}")
    public Student newStudent(Student newStudent,
    @PathVariable long nametitle_id,
    @PathVariable long gender_id,
    @PathVariable String fullname,
    @PathVariable long province_id,
    @PathVariable String address,
    @PathVariable String username,
    @PathVariable String password,
    @PathVariable long old,
    @PathVariable String tel,
    @PathVariable String email,
    @PathVariable long employee_id) {


    Nametitle  nametitle = nametitleRepository.findById(nametitle_id);
    newStudent.setNametitle(nametitle);

    Gender     gender    = genderRepository.findById(gender_id);
    newStudent.setGender(gender);

    newStudent.setFullname(fullname);

    Province   province  = provinceRepository.findById(province_id);
    newStudent.setProvince(province);

    Employee   employee  = employeeRepository.findById(employee_id);
    newStudent.setEmployee(employee);

    newStudent.setOld((int)old);

    newStudent.setTel(tel);

    newStudent.setEmail(email);
    
    newStudent.setAddress(address);

    newStudent.setUsername(username);
    
    newStudent.setPassword(password);

    return studentRepository.save(newStudent);

    }
}
