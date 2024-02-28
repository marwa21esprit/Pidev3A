package models;

public class Avis {
    private int id;
    private String name;
    private String imagesrc;
    private String avis;
    public Avis(){}

    public Avis(int id, String name, String imagesrc, String avis) {
        this.id = id;
        this.name = name;
        this.imagesrc = imagesrc;
        this.avis = avis;
    }
    public Avis(String name, String imagesrc, String avis) {
        this.name = name;
        this.imagesrc = imagesrc;
        this.avis = avis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagesrc='" + imagesrc + '\'' +
                ", avis='" + avis + '\'' +
                '}';
    }
}
