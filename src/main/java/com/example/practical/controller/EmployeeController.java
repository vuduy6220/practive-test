package com.example.practical.controller;

import com.example.practical.entity.Employee;
import com.example.practical.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeModel employeeModel;

    @GetMapping
    public String getListEmployee(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "") String name) {
        Page<Employee> pagination;
        if(!name.isEmpty() && name != null) {
            pagination = employeeModel.findAllEmployeeByName(name, PageRequest.of(page - 1, limit));
        } else {
            pagination = employeeModel.findAll(PageRequest.of(page - 1, limit));
        }
        model.addAttribute("pagination", pagination);
        model.addAttribute("page", page);
        model.addAttribute("limit", limit);
        return "list-employee";
    }



    @GetMapping(value = "/create")
    public String createEmployee() {
        return "form-employee";
    }

    @PostMapping(value = "/create")
    public String saveEmployee(Employee employee) {
        employeeModel.save(employee);
        return "redirect:";
    }
}
