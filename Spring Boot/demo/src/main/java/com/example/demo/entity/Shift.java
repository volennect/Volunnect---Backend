package com.example.demo.entity;

import java.time.LocalTime;

public class Shift {

    private LocalTime startTime;
    private LocalTime endTime;
    private String shiftTitle;
    private String shiftDescription;
    private int volunteerCount;

    public Shift(LocalTime startTime, LocalTime endTime, String shiftTitle, String shiftDescription, int volunteerCount) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftTitle = shiftTitle;
        this.shiftDescription = shiftDescription;
        this.volunteerCount = volunteerCount;
    }

    public Shift() {}

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getShiftTitle() {
        return shiftTitle;
    }

    public void setShiftTitle(String shiftTitle) {
        this.shiftTitle = shiftTitle;
    }

    public String getShiftDescription() {
        return shiftDescription;
    }

    public void setShiftDescription(String shiftDescription) {
        this.shiftDescription = shiftDescription;
    }

    public int getVolunteerCount() {
        return volunteerCount;
    }

    public void setVolunteerCount(int volunteerCount) {
        this.volunteerCount = volunteerCount;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", shiftTitle='" + shiftTitle + '\'' +
                ", shiftDescription='" + shiftDescription + '\'' +
                ", volunteerCount=" + volunteerCount +
                '}';
    }
}
