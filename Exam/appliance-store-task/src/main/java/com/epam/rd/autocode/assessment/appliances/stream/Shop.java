package com.epam.rd.autocode.assessment.appliances.stream;

import com.epam.rd.autocode.assessment.appliances.model.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Shop implements Add, Find, Sort {

    private final Set<Client> clients;
    private final Set<Employee> employees;
    private final Set<Appliance> appliances;
    private final Set<Order> orders;
    private final Set<Manufacturer> manufacturers;

    public Shop() {
        clients = new HashSet<>();
        employees = new HashSet<>();
        appliances = new HashSet<>();
        orders = new HashSet<>();
        manufacturers = new HashSet<>();
    }



    @Override
    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void addManufacturer(Manufacturer manufacturer) {
        manufacturers.add(manufacturer);
    }



    @Override
    public Manufacturer findManufacturerById(long id) {

        return manufacturers.stream()
                .filter(manufacturer -> manufacturer.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "Manufacturer with id=" + id + " was not found"
                        )
                );
    }

    @Override
    public Manufacturer findManufacturerByName(String name) {

        return manufacturers.stream()
                .filter(manufacturer ->
                        Objects.equals(manufacturer.getName(), name))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "Manufacturer with mane=" + name + " was not found"
                        )
                );
    }

    @Override
    public List<Order> findOrderByEmployee(Employee employee) {

        return orders.stream()
                .filter(order ->
                        Objects.equals(order.getEmployee(), employee))
                .collect(Collectors.toList());
    }

    @Override
    public Order findCheapestOrder() {

        return orders.stream()
                .min(Comparator.comparing(this::getOrderAmount))
                .orElseThrow(() ->
                        new RuntimeException("Order not found")
                );
    }

    @Override
    public Order findMostExpensiveOrder() {

        return orders.stream()
                .max(Comparator.comparing(this::getOrderAmount))
                .orElseThrow(() ->
                        new RuntimeException("Order not found")
                );
    }


    @Override
    public List<Manufacturer> sortManufacturersByName() {

        return manufacturers.stream()
                .sorted(
                        Comparator.comparing(
                                Manufacturer::getName,
                                Comparator.nullsLast(
                                        Comparator.naturalOrder()
                                )
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> sortOrderByClientId() {

        return orders.stream()
                .sorted(
                        Comparator.comparingLong(
                                order -> order.getClient().getId()
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Appliance> sortAppliancesByCategory() {

        return appliances.stream()
                .sorted(
                        Comparator.comparing(
                                Appliance::getCategory,
                                Comparator.nullsLast(
                                        Comparator.naturalOrder()
                                )
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> sortOrderByAmount() {

        return orders.stream()
                .sorted(
                        Comparator.comparing(this::getOrderAmount)
                )
                .collect(Collectors.toList());
    }


    private BigDecimal getOrderAmount(Order order) {

        return order.getAppliances()
                .values()
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}