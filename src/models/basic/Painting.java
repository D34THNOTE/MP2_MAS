package models.basic;

public class Painting {
    private String paintingName,artistName;

    private double price;

    private Gallery gallery;

    public Painting(String paintingName, String artistName, double price) {
        setPaintingName(paintingName);
        setArtistName(artistName);
        setPrice(price);
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        Gallery tempGal = this.gallery;

        if(this.gallery == null && gallery != null) {
            // creating new association
            this.gallery = gallery;
            if(!gallery.getPaintings().contains(this)) {
                gallery.addPainting(this);
            }
        } else if(this.gallery != null && gallery == null) {
            // removing all associations
            if(tempGal.getPaintings().contains(this)) {
                tempGal.removePainting(this);
            }
            this.gallery = null;
        } else if(this.gallery != null && this.gallery != gallery) {
            // replacing the association
            if(tempGal.getPaintings().contains(this)) {
                tempGal.removePainting(this);
            }
            if(!gallery.getPaintings().contains(this)) {
                gallery.addPainting(this);
            }
            this.gallery = gallery;
        }
    }

    // --------------------------------------------------------------

    public String getPaintingName() {
        return paintingName;
    }

    public void setPaintingName(String paintingName) {
        if(paintingName == null || paintingName.isBlank()) throw new IllegalArgumentException("Painting's name is required");

        this.paintingName = paintingName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        if(artistName == null || artistName.isBlank()) throw new IllegalArgumentException("Artist's name is required");

        this.artistName = artistName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
