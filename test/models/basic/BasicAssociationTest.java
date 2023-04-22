package models.basic;

import models.basic.Gallery;
import models.basic.Painting;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicAssociationTest {

    private Gallery gallery1;
    private Gallery gallery2;

    private Painting painting1;
    private Painting painting2;
    private Painting painting3;
    private Painting painting4;

    @Before
    public void setup() {
        gallery1 = new Gallery("Gal1", "Norblina 23a Warszawa 03-684");
        gallery2 = new Gallery("Gal2", "Krawiecka 74 Warszawa 06-965");

        painting1 = new Painting("Waterfall", "Bonzo Capozo", 1500.00);
        painting2 = new Painting("Mountainfall From Sky", "Volt White", 845.00);
        painting3 = new Painting("Woods of Freedom", "Dadly", 256.00);
        painting4 = new Painting("Close Collision", "Dude Astray", 57.00);
    }

    @Test
    public void addPainting() {
        assertThrows(IllegalArgumentException.class, () -> gallery1.addPainting(null));

        gallery1.addPainting(painting1);
        assertTrue(gallery1.getPaintings().contains(painting1));
        assertThrows(IllegalArgumentException.class, () -> gallery1.addPainting(painting1));
        assertEquals(gallery1, painting1.getGallery());

        gallery1.addPainting(painting2);
        assertTrue(gallery1.getPaintings().contains(painting2));
        assertEquals(2, gallery1.getPaintings().size());

        Painting tempPainting = painting2;
        painting2 = new Painting("Replacement painting", "Me", 65.00);
        gallery1.removePainting(tempPainting);
        gallery1.addPainting(painting2);
        assertTrue(gallery1.getPaintings().contains(painting2));
        assertEquals(2, gallery1.getPaintings().size());
        assertFalse(gallery1.getPaintings().contains(tempPainting));

        // adding a painting to a new gallery without removing it from the old one
        gallery2.addPainting(painting1);
        assertFalse(gallery1.getPaintings().contains(painting1));
        assertTrue(gallery2.getPaintings().contains(painting1));
        assertEquals(1, gallery1.getPaintings().size());
        assertEquals(1, gallery2.getPaintings().size());
    }

    @Test
    public void removePainting() {
        assertThrows(IllegalArgumentException.class, () -> gallery1.removePainting(null));
        assertThrows(IllegalArgumentException.class, () -> gallery1.removePainting(painting1));

        gallery1.addPainting(painting1);
        assertTrue(gallery1.getPaintings().contains(painting1));

        gallery1.removePainting(painting1);
        assertFalse(gallery1.getPaintings().contains(painting1));
        assertNull(painting1.getGallery());

        gallery1.addPainting(painting1);
        gallery1.addPainting(painting2);
        assertEquals(2, gallery1.getPaintings().size());
        gallery1.removePainting(painting1);
        assertEquals(1, gallery1.getPaintings().size());
    }

    @Test
    public void setGalleryTest() {
        assertNull(painting1.getGallery());

        painting1.setGallery(gallery1);
        assertEquals(gallery1, painting1.getGallery());
        assertTrue(gallery1.getPaintings().contains(painting1));

        painting1.setGallery(null);
        assertNull(painting1.getGallery());
        assertFalse(gallery1.getPaintings().contains(painting1));

        // adding a painting to a new gallery without removing it from the old one
        painting1.setGallery(gallery1);
        painting1.setGallery(gallery2);
        assertEquals(gallery2, painting1.getGallery());
        assertFalse(gallery1.getPaintings().contains(painting1));
        assertTrue(gallery2.getPaintings().contains(painting1));

        painting2.setGallery(gallery2);
        assertTrue(gallery2.getPaintings().contains(painting2));
        assertTrue(gallery2.getPaintings().contains(painting1));
    }
}
