package model;



public class Nutriment {
    private String name;
    private double carbs;
    private double fat;
    private double protein;
    private double nonSaturated;
    private double saturated;
    private double fibres;
    final double overallCal = 4.1;
    final double fatCal = 9.1;
    private double cKal;

    public Nutriment(String name, double carbs, double fat, double saturated, double nonSaturated, double protein, double fibres) {
        this.setName(name);
        this.setCarbs(carbs);
        this.setFat(fat);
        this.setProtein(protein);
        this.setSaturated(saturated);
        this.setNonSaturated(nonSaturated);
        this.setFibres(fibres);
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

    public double getSaturated() {
        return saturated;
    }

    public void setSaturated(double saturated) {
        this.saturated = saturated;
    }

    public double getNonSaturated() {
        return nonSaturated;
    }

    public void setNonSaturated(double nonSaturated) {
        this.nonSaturated = nonSaturated;
    }

    public double getFibres() {
        return fibres;
    }

    public void setFibres(double fibres) {
        this.fibres = fibres;
    }

    public double calKiloCalories(double carbs, double fat, double protein) {
        cKal = fat * fatCal + (carbs + protein) * overallCal;
        return cKal;
    }
}
