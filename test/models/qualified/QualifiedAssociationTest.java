package models.qualified;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QualifiedAssociationTest {

    Garage garage1, garage2;
    Car car1, car2, car3, car4;

    @Before
    public void setup() {
        garage1 = new Garage("Carzz", "Warszawa ul.Cybernetyczna 24");
        garage2 = new Garage("Parkings", "Warszawa ul.Nordlinga 59");

        car1 = new Car("213353B23", "Skoda Octavia");
        car2 = new Car("A23534349", "Opel Astra");
        car3 = new Car("AS3364598", "Jaguar XJ220");
        car4 = new Car("D34THNOTE", "BMW M3");
    }

    @After
    public void nuke() {
        List<Car> toRemove = new ArrayList<>(Car.getExtent());

        for(Car car : toRemove) {
            Car.removeCarFromExtent(car);
        }
    }

    @Test
    public void addCar() {
        assertThrows(IllegalArgumentException.class, () -> garage1.addCar(null));

        garage1.addCar(car1);
        assertTrue(garage1.getCars().containsValue(car1));
        assertThrows(IllegalArgumentException.class, () -> garage1.addCar(car1));
        assertEquals(car1, garage1.getCars().get(car1.getLicensePlate()));
        assertEquals(garage1, car1.getGarage());

        garage1.addCar(car2);
        assertTrue(garage1.getCars().containsValue(car2));
        assertEquals(2, garage1.getCars().size());

        assertThrows(IllegalArgumentException.class, () -> garage1.addCar(new Car("D34THNOTE", "Taken License")));

        Car tempAccount = car2;
        car2 = new Car("UNIQUEPLATE", "AM DB9");
        garage1.removeCar(tempAccount);
        garage1.addCar(car2);
        assertTrue(garage1.getCars().containsValue(car2));
        assertFalse(garage1.getCars().containsValue(tempAccount));
        assertEquals(2, garage1.getCars().size());

        // adding an existing account to a different bank
        assertEquals(0, garage2.getCars().size());
        garage2.addCar(car1);
        assertEquals(1, garage1.getCars().size());
        assertEquals(1, garage2.getCars().size());
    }

    @Test
    public void removeCar() {
        assertThrows(IllegalArgumentException.class, () -> garage1.removeCar(null));
        assertThrows(IllegalArgumentException.class, () -> garage1.removeCar(car1));

        garage1.addCar(car1);
        assertTrue(garage1.getCars().containsValue(car1));

        garage1.removeCar(car1);
        assertFalse(garage1.getCars().containsValue(car1));
        assertNull(car1.getGarage());

        garage1.addCar(car1);
        garage1.addCar(car2);
        assertEquals(2, garage1.getCars().size());
        garage1.removeCar(car1);
        assertEquals(1, garage1.getCars().size());

        assertThrows(IllegalArgumentException.class, () -> garage2.removeCar(car2));
    }

    @Test
    public void setGarageTest() {
        assertNull(car1.getGarage());

        car1.setGarage(garage1);
        assertEquals(garage1, car1.getGarage());
        assertTrue(garage1.getCars().containsValue(car1));

        car1.setGarage(null);
        assertNull(car1.getGarage());
        assertFalse(garage1.getCars().containsValue(car1));

        car1.setGarage(garage1);
        car1.setGarage(garage2);
        assertEquals(garage2, car1.getGarage());
        assertFalse(garage1.getCars().containsValue(car1));
        assertTrue(garage2.getCars().containsValue(car1));

        car2.setGarage(garage2);
        assertFalse(garage1.getCars().containsValue(car1));
        assertTrue(garage2.getCars().containsValue(car2));
        assertTrue(garage2.getCars().containsValue(car1));
        assertEquals(0, garage1.getCars().size());
        assertEquals(2, garage2.getCars().size());
    }

    @Test
    public void removeCarByIdTest() {
        garage1.addCar(car1);
        assertTrue(garage1.getCars().containsValue(car1));
        assertEquals(garage1, car1.getGarage());

        garage1.removeCarById(car1.getLicensePlate());

        assertFalse(garage1.getCars().containsValue(car1));
        assertNull(car1.getGarage());
    }

    @Test
    public void findCarByIdTest() {
        garage1.addCar(car1);
        garage1.addCar(car2);
        garage1.addCar(car3);
        garage1.addCar(car4);

        assertEquals(car1, garage1.findCarById(car1.getLicensePlate()));
        assertEquals(car2, garage1.findCarById(car2.getLicensePlate()));
        assertEquals(car3, garage1.findCarById(car3.getLicensePlate()));
        assertEquals(car4, garage1.findCarById(car4.getLicensePlate()));
    }

    @Test
    public void removeCarFromExtentByIdTest() {
        assertTrue(Car.getExtent().contains(car1));
        Car.removeCarFromExtentById(car1.getLicensePlate());
        assertFalse(Car.getExtent().contains(car1));
    }

    @Test
    public void removeCarFromExtentTest() {
        assertTrue(Car.getExtent().contains(car1));
        Car.removeCarFromExtent(car1);
        assertFalse(Car.getExtent().contains(car1));
    }
}
