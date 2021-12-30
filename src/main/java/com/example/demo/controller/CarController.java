package com.example.demo.controller;

import com.example.demo.Exception.NotFoundException;
import com.example.demo.dto.SaveCarRequest;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    List<Car> cars = new ArrayList<>(Arrays.asList(
       new Car("Ford", 200000),
            new Car("Ferrari", 200000),
            new Car("Bugatti", 200000)
    ));

    @Autowired
    CarService carService;

    @GetMapping
    ResponseEntity<List<Car>> getCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity getCar(@PathVariable Integer id) {
//        for (Car car : cars
//             ) {
//            if(car.getId() == id){
//                return cars.stream().filter(car1 -> car1.getId() == id).findFirst().get();
//            }
//        }

        try {
            return ResponseEntity.ok(carService.getCarById(id));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Car not found");
        }

    }

    @PostMapping
    ResponseEntity addCar(@RequestBody SaveCarRequest saveCarRequest){
        return ResponseEntity.ok(carService.addCar(saveCarRequest));
//        cars.add(car);
    }

    @DeleteMapping(path = "/{id}")
    void deleteCar(@PathVariable Integer id){
        for (int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getId() == id){
                cars.remove(i);
            }
        }
    }

}