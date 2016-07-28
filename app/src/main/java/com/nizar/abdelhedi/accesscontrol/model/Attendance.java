package com.nizar.abdelhedi.accesscontrol.model;



/**
 * Created by abdelhedi on 18/07/2016.
 */
public class Attendance {


    Employee employee;
    private Duration monthlyWorkedHour;
    private Duration weeklyWorkedHour;
    private Duration dailyWorkedHour;
    private boolean employeeIsInsideCompany;
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Duration getMonthlyWorkedHour() {
        return monthlyWorkedHour;
    }

    public void setMonthlyWorkedHour(Duration monthlyWorkedHour) {
        this.monthlyWorkedHour = monthlyWorkedHour;
    }

    public Duration getWeeklyWorkedHour() {
        return weeklyWorkedHour;
    }

    public void setWeeklyWorkedHour(Duration weeklyWorkedHour) {
        this.weeklyWorkedHour = weeklyWorkedHour;
    }

    public Duration getDailyWorkedHour() {
        return dailyWorkedHour;
    }

    public void setDailyWorkedHour(Duration dailyWorkedHour) {
        this.dailyWorkedHour = dailyWorkedHour;
    }

    public boolean isEmployeeIsInsideCompany() {
        return employeeIsInsideCompany;
    }

    public void setEmployeeIsInsideCompany(boolean employeeIsInsideCompany) {
        this.employeeIsInsideCompany = employeeIsInsideCompany;
    }


}
