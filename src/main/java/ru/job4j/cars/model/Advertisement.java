package ru.job4j.cars.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModel carModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private byte[] photo;
    private boolean isSold;
    private LocalDateTime created;

    public Advertisement() {
    }

    public Advertisement(String description, CarModel carModel, User user, byte[] photo, boolean isSold, LocalDateTime created,
                         LocalDate releaseDate) {
        this.description = description;
        this.carModel = carModel;
        this.user = user;
        this.photo = photo;
        this.isSold = isSold;
        this.created = created;
        this.releaseDate = releaseDate;
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

    public CarModel getCar() {
        return carModel;
    }

    public void setCar(CarModel carModel) {
        this.carModel = carModel;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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
        private CarModel carModel;
        private User user;
        private byte[] photo;
        private boolean isSold;
        private LocalDateTime created;

        private LocalDate releaseDate;

        AdvertisementBuilder() {
        }

        public AdvertisementBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AdvertisementBuilder car(CarModel carModel) {
            this.carModel = carModel;
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

        public AdvertisementBuilder releaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Advertisement build() {
            Advertisement advertisement = new Advertisement();
            advertisement.setDescription(description);
            advertisement.setCar(carModel);
            advertisement.setUser(user);
            advertisement.setPhoto(photo);
            advertisement.setSold(isSold);
            advertisement.setCreated(created);
            advertisement.setReleaseDate(releaseDate);
            if (user == null) {
                throw new IllegalArgumentException("user can't be null");
            }
            return advertisement;
        }
    }
}
