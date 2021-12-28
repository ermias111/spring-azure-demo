package com.example.demo.controller;

import com.example.demo.Exception.NotFoundException;
import com.example.demo.model.Car;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    List<Car> cars = new ArrayList<>(Arrays.asList(
       new Car(1, "Ford", 200000),
            new Car(2, "Ferrari", 200000),
            new Car(3, "Bugatti", 200000)
    ));

    @GetMapping
    List<Car> getCars(){
        return cars;
    }

    @GetMapping(path = "/{id}")
    Car getCar(@PathVariable Integer id) throws NotFoundException {
        for (Car car : cars
             ) {
            if(car.getId() == id){
                return cars.stream().filter(car1 -> car1.getId() == id).findFirst().get();
            }
        }

        throw new NotFoundException("Not found");
    }

    @PostMapping
    void addCar(@RequestBody Car car){
        cars.add(car);
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