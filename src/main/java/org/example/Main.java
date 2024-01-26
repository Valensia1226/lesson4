package org.example;

import Exceptions.AmountException;
import Exceptions.CustomerException;
import Exceptions.ProductException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Main {
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Item> items = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();
    static ArrayList<Order> draft = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        customers.add(new Customer("customer1", 20, 123, Gender.Male));
        customers.add(new Customer("customer2", 21, 345, Gender.Female));
        items.add(new Item("item1", 10));
        items.add(new Item("item2", 20));
        items.add(new Item("item3", 30));
        items.add(new Item("item4", 40));
        items.add(new Item("item5", 50));


        try {
            orders.add(placeOrder(customers.get(0),items.get(0), 5));
            orders.add(placeOrder(customers.get(1),items.get(1), 5));
            orders.add(placeOrder(customers.get(0),items.get(2), 5));
            orders.add(placeOrder(new Customer("new cust", 50, 456, Gender.Male),items.get(0), 5));
            orders.add(placeOrder(customers.get(0),new Item("new item", 15), 5));
            orders.add(placeOrder(customers.get(0),items.get(2), -5));
        }
        catch (CustomerException e){
            System.out.println(e.getMessage());
        }
        catch (ProductException e){
            System.out.println(e.getMessage());
        }
        catch (AmountException e){
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Orders length: " + orders.size());
        }

    }
    public static Order placeOrder(Customer customer, Item item, int quantity) throws Exception {
        if (!customers.contains(customer))
            throw new CustomerException("org.example.Customer not found");
        if(!items.contains(item))
            throw new ProductException("org.example.Item not found");
        if(quantity < 0)
            throw new AmountException("Incorrect amount " + quantity);
        return new Order(customer, item, quantity);
    }

    public static void congratulations(ArrayList<Customer> customers){
        if (customers.isEmpty()) return;
        LocalDate date = LocalDate.now();

        if (date.getDayOfMonth() == 8 && date.getMonth().equals(Month.MARCH)){
            for(Customer customer: customers) {
                if (customer.getGender().equals(Gender.Female)) System.out.println("Поздравляем с 8 марта, " + customer.getName());
            }
        }
        else if (date.getDayOfMonth() == 23 && date.getMonth().equals(Month.FEBRUARY)){
            for(Customer customer: customers) {
                if (customer.getGender().equals(Gender.Male)) System.out.println("Поздравляем с 23 февраля, " + customer.getName());
            }
        }
        else if (date.getDayOfMonth() == 31 && date.getMonth().equals(Month.DECEMBER)){
            for(Customer customer: customers) {
                System.out.println("Поздравляем с Новым годом, " + customer.getName());
            }
        }
    }
}