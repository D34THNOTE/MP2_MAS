package models.qualified;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Garage {
    private String garageName, address;

    private Map<String, Car> cars = new HashMap<>();

    public Garage(String garageName, String address) {
        setGarageName(garageName);
        setAddress(address);
    }

    public void addCar(Car car) {
        if(car == null) throw new IllegalArgumentException("Account to add hasn't been selected");
        if(cars.containsKey(car.getLicensePlate())) throw new IllegalArgumentException("Account with this number already exists in this garage");

        cars.put(car.getLicensePlate(), car);
        car.setGarage(this);
    }

    public void removeCar(Car car) {
        if(car == null) throw new IllegalArgumentException("Car to remove hasn't been selected");
        if(!cars.containsKey(car.getLicensePlate())) throw new IllegalArgumentException("Chosen car doesn't exist in the garage");

        cars.remove(car.getLicensePlate(), car);
        car.setGarage(null);
    }

    public void removeCarById(String license) {
        if(license == null || license.isBlank()) throw new IllegalArgumentException("Passed license plate is empty");
        Car toRemove = cars.get(license);
        if(toRemove == null) throw new IllegalArgumentException("Chosen car doesn't exist in the garage");

        cars.remove(license, toRemove);
        toRemove.setGarage(null);
    }

    public Car findCarById(String license) {
        if(license == null || license.isBlank()) throw new IllegalArgumentException("Passed license plate is empty");
        Car toReturn = cars.get(license);
        if(toReturn == null) throw new IllegalArgumentException("Chosen car doesn't exist in the garage");

        return toReturn;
    }

    public Map<String, Car> getCars() {
        return Collections.unmodifiableMap(cars);
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        if(garageName == null || garageName.isBlank()) throw new IllegalArgumentException("Garage's name is required");
        this.garageName = garageName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address == null || address.isBlank()) throw new IllegalArgumentException("Garage's address is required");
        this.address = address;
    }


}
