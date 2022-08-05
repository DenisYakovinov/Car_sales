package ru.job4j.cars.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModel carModel;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "advertisement_id")
    private List<Photo> photos = new ArrayList<>();

    @Column(name = "is_sold")
    private boolean isSold;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "car_owner")
    private int carOwner;

    @Column(name = "car_mileage")
    private int carMileage;

        @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    public Advertisement() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public int getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(int carOwner) {
        this.carOwner = carOwner;
    }

    public int getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(int carMileage) {
        this.carMileage = carMileage;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
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
        private boolean isSold;
        private LocalDateTime created;

        private LocalDate releaseDate;

        private int carOwner;

        private int carMileage;

        private int price;

        private Engine engine;

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

        public AdvertisementBuilder carOwner(int carOwner) {
            this.carOwner = carOwner;
            return this;
        }

        public AdvertisementBuilder carMileage(int carMileage) {
            this.carMileage = carMileage;
            return this;
        }

        public AdvertisementBuilder price(int price) {
            this.price = price;
            return this;
        }

        public AdvertisementBuilder engine(Engine engine) {
            this.engine = engine;
            return this;
        }

        public Advertisement build() {
            Advertisement advertisement = new Advertisement();
            advertisement.setDescription(description);
            advertisement.setCarModel(carModel);
            advertisement.setUser(user);
            advertisement.setSold(isSold);
            advertisement.setCreated(created);
            advertisement.setReleaseDate(releaseDate);
            advertisement.setCarMileage(carMileage);
            advertisement.setCarOwner(carOwner);
            advertisement.setPrice(price);
            advertisement.setEngine(engine);
            if (user == null) {
                throw new IllegalArgumentException("user can't be null");
            }
            return advertisement;
        }
    }
}
