package ru.job4j.cars.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "photo")
    private byte[] photo;
    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;

    public Photo() {
    }

    public static Photo of(byte[] photo, Advertisement ad) {
        Photo ph = new Photo();
        ph.photo = photo;
        ph.advertisement = ad;
        return ph;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Photo photo = (Photo) o;
        return id == photo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
