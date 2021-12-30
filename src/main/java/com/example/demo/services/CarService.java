package com.example.demo.services;

import com.example.demo.dto.SaveCarRequest;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car getCarById(Integer id) throws Exception {
        return carRepository.findById(id).orElseThrow(() -> {
            return new Exception("Car not found");
        });
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car addCar(SaveCarRequest saveCarRequest){
        return carRepository.save(new Car(
                saveCarRequest.getName(),
                saveCarRequest.getPrice()
        ));
    }
}
