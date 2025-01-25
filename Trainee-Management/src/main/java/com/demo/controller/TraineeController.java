package com.demo.controller;

import com.demo.model.Trainee;
import com.demo.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/trainees") //http://localhost:5070/trainees/management
public class TraineeController {
    
    @Autowired
    private TraineeService traineeService;

    @GetMapping("/management")
    public String showTraineeManagementPage(Model model) {
        model.addAttribute("trainees", traineeService.getAllTrainees());
        model.addAttribute("trainee", new Trainee());
        return "trainee_management";
    }

    @PostMapping("/save")
    public String saveTrainee(@ModelAttribute Trainee trainee) {
        traineeService.saveTrainee(trainee);
        return "redirect:/trainees/management";
    }

    @GetMapping("/edit/{id}") //http://localhost:5070/trainees/edit/{id}
    public String editTrainee(@PathVariable Long id, Model model) {
        Optional<Trainee> trainee = traineeService.getTraineeById(id);
        if (trainee.isPresent()) {
            model.addAttribute("trainee", trainee.get());
            return "trainee_edit"; 
        }
        return "redirect:/trainees/management";
    }

    @PostMapping("/update/{id}")
    public String updateTrainee(@PathVariable Long id, @ModelAttribute Trainee traineeDetails) {
        traineeService.updateTrainee(id, traineeDetails);
        return "redirect:/trainees/management";
    }

    @PostMapping("/delete/{id}")
    public String deleteTrainee(@PathVariable Long id) {
        traineeService.deleteTrainee(id);
        return "redirect:/trainees/management";
    }
}