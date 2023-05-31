package com.example.courseproject;

import java.sql.Date;

public class visitorData {
    private Integer visitorId;
    private String fullName;
    private String genderName;
    private Date dateBirth;
    private Integer phoneNumber;
    private Integer seriesPassport;
    private Integer numberPassport;

    public visitorData(Integer visitorId, String fullName, String genderName, Date dateBirth, Integer phoneNumber, Integer seriesPassport, Integer numberPassport){
        this.visitorId = visitorId;
        this.fullName = fullName;
        this.genderName = genderName;
        this.dateBirth = dateBirth;
        this.phoneNumber = phoneNumber;
        this.seriesPassport = seriesPassport;
        this.numberPassport = numberPassport;
    }

    public Integer getVisitorId(){
        return visitorId;
    }

    public String getFullName(){
        return fullName;
    }

    public String getGenderName(){
        return genderName;
    }

    public Date getDateBirth(){
        return dateBirth;
    }

    public Integer getPhoneNumber(){
        return phoneNumber;
    }

    public Integer getSeriesPassport(){
        return seriesPassport;
    }

    public Integer getNumberPassport(){
        return numberPassport;
    }



}
