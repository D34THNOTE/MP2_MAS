package models.basic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Gallery {
    private String galleryName, address;

    private Set<Painting> paintings = new HashSet<>();

    public Gallery(String galleryName, String address) {
        setGalleryName(galleryName);
        setAddress(address);
    }

    public void addPainting(Painting painting) {
        // not allowing nulls or paintings already present in the set
        if(painting == null) throw new IllegalArgumentException("Passed painting cannot be null");
        if(paintings.contains(painting)) throw new IllegalArgumentException("Passed painting already exists in this gallery");

        paintings.add(painting);
        painting.setGallery(this); // back reference
    }

    public void removePainting(Painting painting) {
        if(painting == null) throw new IllegalArgumentException("Passed painting cannot be null");
        if(!paintings.contains(painting)) throw new IllegalArgumentException("Passed painting doesn't exists in this gallery");

        paintings.remove(painting);
        painting.setGallery(null);
    }

    public Set<Painting> getPaintings() {
        return Collections.unmodifiableSet(paintings);
    }


    public String getGalleryName() {
        return galleryName;
    }

    public void setGalleryName(String galleryName) {
        if(galleryName == null || galleryName.isBlank()) throw new IllegalArgumentException("Gallery name is required");

        this.galleryName = galleryName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address == null || address.isBlank()) throw new IllegalArgumentException("Address is required");

        this.address = address;
    }
}
