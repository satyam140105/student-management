package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String showForm(Model model) {

        model.addAttribute("student", new Student());

        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );

        return "students";
    }

    @PostMapping("/save")
    public String saveStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "students",
                    studentService.getAllStudents()
            );

            return "students";
        }

        studentService.saveStudent(student);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/";
    }
}