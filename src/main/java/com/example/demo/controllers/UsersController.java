package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepo;

    @PostMapping("/users/add")
    public String addUser(@RequestParam("name") String name,
            @RequestParam("weight") String weight,
            @RequestParam("height") String height,
            @RequestParam("hairColor") String hairColor,
            @RequestParam("gpa") String gpa, HttpServletResponse response) {

        System.out.println("Entering addUser method");
        User myUser = new User();
        myUser.setName(name);
        myUser.setWeight(weight);
        myUser.setHeight(height);
        myUser.setHairColor(hairColor);
        myUser.setGpa(gpa);
        userRepo.save(myUser);
        response.setStatus(201);
        return "users/addedUser";
    }

    @PostMapping("/users/update")
    public String updateStudentByName(@RequestParam("name") String nameUpdate,
            @RequestParam("weight") String weightUpdate,
            @RequestParam("height") String heightUpdate,
            @RequestParam("hairColor") String hairColorUpdate,
            @RequestParam("gpa") String gpaUpdate, HttpServletResponse responseForUpdate) {
        Optional<User> existingStudent = userRepo.findUserByName(nameUpdate);
        if (existingStudent.isPresent()) {
            User updatedStudent = existingStudent.get();
            updatedStudent.setName(nameUpdate);
            updatedStudent.setWeight(weightUpdate);
            updatedStudent.setHeight(heightUpdate);
            updatedStudent.setHairColor(hairColorUpdate);
            updatedStudent.setGpa(gpaUpdate);
            userRepo.save(updatedStudent);
            responseForUpdate.setStatus(201);
            return "users/viewAfterUpdate";
        } else {
            return "users/studentNotFound";
        }
    }
    @GetMapping("/printAll")
    public String getAllUsers(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("students", users);
        return "users/view";
    }
    @PostMapping("/users/delete")
    public String deleteStudentByName(@RequestParam("name") String name) {
        Optional<User> student = userRepo.findUserByName(name);
        if (student.isPresent()) {
            userRepo.delete(student.get());
            return "users/viewAfterDeletion";
        } else {
            return "users/studentNotFound";
        }
    }

    public class StudentDTO {
        private String name;
        private double gpa;

        public StudentDTO(String name, double gpa) {
            this.name = name;
            this.gpa = gpa;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getGpa() {
            return gpa;
        }

        public void setGpa(double gpa) {
            this.gpa = gpa;
        }
    }

    @GetMapping("/submit")
    public String handleSubmission(Model model) {
        List<StudentDTO> studentDTOs = userRepo.findAll().stream()
                .map(user -> {
                    double gpa = 0.0;
                    try {
                        gpa = Double.parseDouble(user.getGpa());
                    } catch (NumberFormatException e) {
                    }
                    return new StudentDTO(user.getName(), gpa);
                })
                .collect(Collectors.toList());

        model.addAttribute("students", studentDTOs);
        return "rectangle";
    }
}
