package com.demo.service;

import com.demo.dao.TraineeRepository;
import com.demo.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeService {
    
    @Autowired
    private TraineeRepository traineeRepository;


    public List<Trainee> getAllTrainees() {
        return traineeRepository.findAll();
    }

   
    public void saveTrainee(Trainee trainee) {
        traineeRepository.save(trainee);
    }

    
    public Optional<Trainee> getTraineeById(Long id) {
        return traineeRepository.findById(id);
    }


    public Trainee updateTrainee(Long id, Trainee traineeDetails) {
        Trainee trainee = traineeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainee not found with id: " + id));
        trainee.setFirstName(traineeDetails.getFirstName());
        trainee.setLastName(traineeDetails.getLastName());
        trainee.setEmail(traineeDetails.getEmail());
        trainee.setGender(traineeDetails.getGender());
        trainee.setPhone(traineeDetails.getPhone());
        return traineeRepository.save(trainee);
    }

    
    public void deleteTrainee(Long id) {
        traineeRepository.deleteById(id);
    }
}