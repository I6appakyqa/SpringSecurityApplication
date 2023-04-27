package com.example.springsecurityapplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;
@Entity
@Table(name="person")
public class Person {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Логин не может быть пустым")
    @Size(min=5,max=100,message="Логин должен быть от 5 до 100 символов")
    @Column(name = "login")
    private String login;
    @NotEmpty(message = "Пароль не может быть пустым")
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @NotEmpty(message = "Обязательно укажите Ваше имя")
    @Column(name = "name_user")
    private String nameUser;
    @NotEmpty(message = "Нам необходим адресс для доставки")
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @NotEmpty(message = "Номер вашего телефона курьеру для связи с Вами")
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotEmpty(message = "Электронная почта")
    @Column(name = "email")
    private String email;

//    @ManyToMany() //Связь для заказов
//    @JoinTable(name = "person_order", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "order_number"))
//    private List<Order> order_person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(login, person.login) && Objects.equals(password, person.password);
    }
    @ManyToMany()
    @JoinTable(name = "product_cart", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<Order> orderList;

//    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
//    private List<OrderPerson> orderPersonList;
    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
