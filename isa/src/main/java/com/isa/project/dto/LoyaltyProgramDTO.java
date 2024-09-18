package com.isa.project.dto;

import com.isa.project.model.LoyaltyProgram;

public class LoyaltyProgramDTO {
    private int id;
    private double pointsForBronze;
    private double percentForBronze;
    private double pointsForSilver;
    private double percentForSilver;
    private double pointsForGold;
    private double percentForGold;

    public LoyaltyProgramDTO() {}

    public LoyaltyProgramDTO(int id, double pointsForBronze, double percentForBronze, double pointsForSilver, double percentForSilver, double pointsForGold, double percentForGold) {
        this.id = id;
        this.pointsForBronze = pointsForBronze;
        this.percentForBronze = percentForBronze;
        this.pointsForSilver = pointsForSilver;
        this.percentForSilver = percentForSilver;
        this.pointsForGold = pointsForGold;
        this.percentForGold = percentForGold;
    }

    public LoyaltyProgramDTO(LoyaltyProgram loyaltyProgram) {this(loyaltyProgram.getId(), loyaltyProgram.getPointsForBronze(), loyaltyProgram.getPercentForBronze(), loyaltyProgram.getPointsForSilver(), loyaltyProgram.getPercentForSilver(), loyaltyProgram.getPointsForGold(), loyaltyProgram.getPercentForGold());}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPointsForBronze() {
        return pointsForBronze;
    }

    public void setPointsForBronze(double pointsForBronze) {
        this.pointsForBronze = pointsForBronze;
    }

    public double getPercentForBronze() {
        return percentForBronze;
    }

    public void setPercentForBronze(double percentForBronze) {
        this.percentForBronze = percentForBronze;
    }

    public double getPointsForSilver() {
        return pointsForSilver;
    }

    public void setPointsForSilver(double pointsForSilver) {
        this.pointsForSilver = pointsForSilver;
    }

    public double getPercentForSilver() {
        return percentForSilver;
    }

    public void setPercentForSilver(double percentForSilver) {
        this.percentForSilver = percentForSilver;
    }

    public double getPointsForGold() {
        return pointsForGold;
    }

    public void setPointsForGold(double pointsForGold) {
        this.pointsForGold = pointsForGold;
    }

    public double getPercentForGold() {
        return percentForGold;
    }

    public void setPercentForGold(double percentForGold) {
        this.percentForGold = percentForGold;
    }
}
