package com.bicyclerental;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class BicycleStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "owningStation", cascade = CascadeType.ALL)
    private List<Bicycle> bicycles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bicycle> getBicycles() { return bicycles; }

    public void setBicycles(List<Bicycle> bicycles) { this.bicycles = bicycles; }

    public boolean addBicycle(Bicycle bicycle)
    {
        return bicycles.add(bicycle);
    }

    public boolean removeBicycle(Bicycle bicycle)
    {
        return bicycles.remove(bicycle);
    }
}
