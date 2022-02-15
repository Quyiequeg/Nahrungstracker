package model;

public class Nutriment {
    private String name;
    private double carbs;
    private double fat;
    private double protein;
    final double overallCal = 4.1;
    final double fatCal = 9.1;
    private double cKal;

    public Nutriment(String name, double carbs, double fat, double protein) {
        this.setName(name);
        this.setCarbs(carbs);
        this.setFat(fat);
        this.setProtein(protein);
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double calcKiloCalories(double carbs, double fat, double protein) {
        cKal = fat * fatCal + (carbs + protein) * overallCal;
        return cKal;
    }
}
