package models.qualified;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Car {
    private static List<Car> extent = new ArrayList<>();
    private String licensePlate;
    private String carName;

    private Garage garage;

    public Car(String licensePlate, String carName) {
        setLicensePlate(licensePlate);
        setCarName(carName);
        extent.add(this);
    }

    public static List<Car> getExtent() {
        return Collections.unmodifiableList(extent);
    }

    // private setter as ID cannot be changed
    private void setLicensePlate(String licensePlate) {
        if(licensePlate == null || licensePlate.isBlank()) throw new IllegalArgumentException("License plate is required");
        if(extent.stream().anyMatch(car -> (car.licensePlate.equals(licensePlate)))) throw new IllegalArgumentException("License plate has to be unique");

        this.licensePlate = licensePlate;
    }

    public static void removeCarFromExtent(Car car) {
        if(car == null) throw new IllegalArgumentException("Car for removal has to be chosen");
        if(!extent.contains(car)) throw new IllegalArgumentException("Chosen car doesn't exist in the system");

        extent.remove(car);
    }

    public static void removeCarFromExtentById(String licensePlate) {
        if(licensePlate == null || licensePlate.isBlank()) throw new IllegalArgumentException("Passed license plate is empty");
        Car toRemove = extent.stream().filter(car -> (car.licensePlate.equals(licensePlate))).findFirst().orElse(null);
        if(toRemove == null) throw new IllegalArgumentException("There is no car with such license plate");

        extent.remove(toRemove);
    }

    public void setGarage(Garage garage) {
        Garage tempBank = this.garage;

        if(this.garage == null && garage != null) {
            // creating new association
            this.garage = garage;
            if(!garage.getCars().containsValue(this)) {
                garage.addCar(this);
            }
        } else if(this.garage != null && garage == null) {
            // removing all associations
            if(tempBank.getCars().containsValue(this)) {
                tempBank.removeCar(this);
            }
            this.garage = null;
        } else if(this.garage != null && this.garage != garage) {
            // replacing the association
            if(tempBank.getCars().containsValue(this)) {
                tempBank.removeCar(this);
            }
            if(!garage.getCars().containsValue(this)) {
                garage.addCar(this);
            }
            this.garage = garage;
        }
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        if(carName == null || carName.isBlank()) throw new IllegalArgumentException("Car's name is required");
        this.carName = carName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Garage getGarage() {
        return garage;
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                ", carName='" + carName + '\'' +
                ", garage=" + garage +
                '}';
    }
}
