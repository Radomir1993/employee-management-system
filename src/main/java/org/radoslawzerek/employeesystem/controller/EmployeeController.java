package org.radoslawzerek.employeesystem.controller;

import org.radoslawzerek.employeesystem.entity.Employee;
import org.radoslawzerek.employeesystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String home(Model model) {
        return findPaginated(0, model);
    }

    @GetMapping("/addemp")
    public String addEmpForm() {
        return "add_emp";
    }

    @PostMapping("/register")
    public String empRegister(@ModelAttribute Employee employee, HttpSession session) {
        service.addEmp(employee);
        session.setAttribute("msg", "Emplyoee Added Sucessfully..");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Employee e = service.getEmpById(id);
        model.addAttribute("emp", e);
        return "edit";
    }

    @PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee employee, HttpSession session) {
        service.addEmp(employee);
        session.setAttribute("msg", "Emp Data Update Sucessfully..");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable Long id, HttpSession session) {

        service.deleteEmp(id);
        session.setAttribute("msg", "Emp Data Delete Sucessfully..");
        return "redirect:/";
    }

    @GetMapping("/page/{pagenum}")
    public String findPaginated(@PathVariable int pagenum, Model model) {

        Page<Employee> employeeList = service.getEmpByPaginate(pagenum, 2);
        model.addAttribute("emp", employeeList);
        model.addAttribute("currentPage", pagenum);
        model.addAttribute("totalPages", employeeList.getTotalPages());
        model.addAttribute("totalItem", employeeList.getTotalElements());
        return "index";
    }
}
