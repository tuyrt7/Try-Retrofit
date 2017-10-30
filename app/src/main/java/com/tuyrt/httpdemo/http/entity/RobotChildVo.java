package com.tuyrt.httpdemo.http.entity;

/**
 * Created by futao on 2017/10/27.
 */

public class RobotChildVo {

    /**
     * photo :
     * fullName :
     * gender :
     * birthday :
     * nationality :
     * nation :
     * religion :
     * roleType :
     * golden : 0
     * healthpoint : 0
     * hungry : 0
     */

    private String photo;
    private String fullName;
    private String gender;
    private String birthday;
    private String nationality;
    private String nation;
    private String religion;
    private String roleType;
    private int golden;
    private int healthpoint;
    private int hungry;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public int getGolden() {
        return golden;
    }

    public void setGolden(int golden) {
        this.golden = golden;
    }

    public int getHealthpoint() {
        return healthpoint;
    }

    public void setHealthpoint(int healthpoint) {
        this.healthpoint = healthpoint;
    }

    public int getHungry() {
        return hungry;
    }

    public void setHungry(int hungry) {
        this.hungry = hungry;
    }

    @Override
    public String toString() {
        return "RobotChildVo{" +
                "photo='" + photo + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", nationality='" + nationality + '\'' +
                ", nation='" + nation + '\'' +
                ", religion='" + religion + '\'' +
                ", roleType='" + roleType + '\'' +
                ", golden=" + golden +
                ", healthpoint=" + healthpoint +
                ", hungry=" + hungry +
                '}';
    }

}
