package com.zjut.campusai.dto;

import java.util.Map;

public class WarningReport {
    private String stuName;
    private Map<String, Integer> courseScore;
    private int failAmount;
    private String riskLevel;

    public WarningReport() {
    }

    public WarningReport(String stuName, Map<String, Integer> courseScore, int failAmount, String riskLevel) {
        this.stuName = stuName;
        this.courseScore = courseScore;
        this.failAmount = failAmount;
        this.riskLevel = riskLevel;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Map<String, Integer> getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(Map<String, Integer> courseScore) {
        this.courseScore = courseScore;
    }

    public int getFailAmount() {
        return failAmount;
    }

    public void setFailAmount(int failAmount) {
        this.failAmount = failAmount;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}
