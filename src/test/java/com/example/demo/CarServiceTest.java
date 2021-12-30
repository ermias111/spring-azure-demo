package com.example.demo;


import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.services.CarService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @InjectMocks
    CarService carService;

    @Mock
    CarRepository carRepository;

    @Test
    public void testGetCar() throws Exception {
        Car car = new Car("Suzuki", 100000);

        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        Car res = carService.getCarById(1);
        Assert.assertEquals(res.getName(), car.getName());
    }

    @Test
    public void testGetAllCars() {
        List<Car> cars = Arrays.asList(
                new Car("AAAA", 120000),
                new Car("BBBB", 120000),
                new Car("CCCC", 120000)
        );

        when(carRepository.findAll()).thenReturn(cars);

        List<Car> carList = carService.getAllCars();

        Assert.assertEquals(cars, carList);
    }

    @Test
    public void testAddCar(){

    }
}
