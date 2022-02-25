package model;

public class Nutriment {
    private String name;
    private Double carbs;
    private Double fat;
    private Double protein;
    private Double nonSaturated;
    private Double saturated;
    private Double fibres;
    final double overallCal = 4.1;
    final double fatCal = 9.1;
    private double cKal;

    public Nutriment(String name, double carbs, double fat, double saturated, double nonSaturated, double protein,
            double fibres) {
        this.setName(name);
        this.setCarbs(carbs);
        this.setFat(fat);
        this.setProtein(protein);
        this.setSaturated(saturated);
        this.setNonSaturated(nonSaturated);
        this.setFibres(fibres);
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public Double getCarbs() {
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

    public Double getSaturated() {
        return saturated;
    }

    public void setSaturated(double saturated) {
        this.saturated = saturated;
    }

    public Double getNonSaturated() {
        return nonSaturated;
    }

    public void setNonSaturated(double nonSaturated) {
        this.nonSaturated = nonSaturated;
    }

    public Double getFibres() {
        return fibres;
    }

    public void setFibres(double fibres) {
        this.fibres = fibres;
    }

    public double calKiloCalories() {
        cKal = this.getFat() * fatCal + (this.getCarbs() + this.getProtein()) * overallCal;
        return cKal;
    }

    public Double getQCarbs(Double quantity) {
        Double qMacros = this.getCarbs() * (quantity / 100);
        return qMacros;
    }

    public Double getQFat(Double quantity) {
        Double qMacros = this.getFat() * (quantity / 100);
        return qMacros;
    }

    public Double getQProtein(Double quantity) {
        Double qMacros = this.getProtein() * (quantity / 100);
        return qMacros;
    }

    public Double getQSaturated(Double quantity) {
        Double qMacros = this.getSaturated() * (quantity / 100);
        return qMacros;
    }

    public Double getQUnsaturated(Double quantity) {
        Double qMacros = this.getNonSaturated() * (quantity / 100);
        return qMacros;
    }

    public Double getQFibres(Double quantity) {
        Double qMacros = this.getFibres() * (quantity / 100);
        return qMacros;
    }
}
