package ru.job4j.cars.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private byte[] photo;
    private boolean isSold;
    private LocalDateTime created;

    public Advertisement() {
    }

    public Advertisement(String description, Car car, User user, byte[] photo, boolean isSold, LocalDateTime created) {
        this.description = description;
        this.car = car;
        this.user = user;
        this.photo = photo;
        this.isSold = isSold;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Advertisement)) {
            return false;
        }
        Advertisement that = (Advertisement) o;
        return id == that.id && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }

    public static AdvertisementBuilder builder() {
        return new AdvertisementBuilder();
    }

    public static class AdvertisementBuilder {
        private String description;
        private Car car;
        private User user;
        private byte[] photo;
        private boolean isSold;
        private LocalDateTime created;

        AdvertisementBuilder() {
        }

        public AdvertisementBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AdvertisementBuilder car(Car car) {
            this.car = car;
            return this;
        }

        public AdvertisementBuilder user(User user) {
            this.user = user;
            return this;
        }

        public AdvertisementBuilder photo(byte[] photo) {
            this.photo = photo;
            return this;
        }

        public AdvertisementBuilder isSold(boolean isSold) {
            this.isSold = isSold;
            return this;
        }

        public AdvertisementBuilder create(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public Advertisement build() {
            Advertisement advertisement = new Advertisement();
            advertisement.setDescription(description);
            advertisement.setCar(car);
            advertisement.setUser(user);
            advertisement.setPhoto(photo);
            advertisement.setSold(isSold);
            advertisement.setCreated(created);
            if (user == null) {
                throw new IllegalArgumentException("user can't be null");
            }
            return advertisement;
        }
    }
}
