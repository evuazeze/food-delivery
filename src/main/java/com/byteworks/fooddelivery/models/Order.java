package com.byteworks.fooddelivery.models;

import javax.persistence.*;

@Entity
@Table(name = "ordar")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    @OneToOne
    private Meal meal;

    private Boolean officeDelivery;

    private Boolean cardPayment;

    private Double totalCost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Boolean getOfficeDelivery() {
        return officeDelivery;
    }

    public void setOfficeDelivery(Boolean officeDelivery) {
        this.officeDelivery = officeDelivery;
    }

    public Boolean getCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(Boolean cardPayment) {
        this.cardPayment = cardPayment;
    }

    public Boolean isCardPayment() { return cardPayment; }

    public Boolean isOfficeDelivery() { return officeDelivery; }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
