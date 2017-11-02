package com.tuyrt.httpdemo.http.entity;

/**
 * Created by futao on 20.017/11/2.
 */

public class GrowthValueVo {
    /**
     * motionScore : 0.0.0.0
     * musicScore : 0.0
     * mathScore : 0.0
     * scienceScore : 0.0
     * languageScore : 0.0
     * englishScore : 0.0
     * chinaScore : 0.0
     * houseworkScore : 0.0
     * habitScore : 0.0
     * gamScore : 0.0
     * skillScore : 0.0
     * totalScore : 0.0
     */

    private float motionScore;
    private float musicScore;
    private float mathScore;
    private float scienceScore;
    private float languageScore;
    private float englishScore;
    private float chinaScore;
    private float houseworkScore;
    private float habitScore;
    private float gamScore;
    private float skillScore;
    private float totalScore;

    @Override
    public String toString() {
        return "GrowthValueVo{" +
                "motionScore=" + motionScore +
                ", musicScore=" + musicScore +
                ", mathScore=" + mathScore +
                ", scienceScore=" + scienceScore +
                ", languageScore=" + languageScore +
                ", englishScore=" + englishScore +
                ", chinaScore=" + chinaScore +
                ", houseworkScore=" + houseworkScore +
                ", habitScore=" + habitScore +
                ", gamScore=" + gamScore +
                ", skillScore=" + skillScore +
                ", totalScore=" + totalScore +
                '}';
    }

    public float getMotionScore() {
        return motionScore;
    }

    public void setMotionScore(float motionScore) {
        this.motionScore = motionScore;
    }

    public float getMusicScore() {
        return musicScore;
    }

    public void setMusicScore(float musicScore) {
        this.musicScore = musicScore;
    }

    public float getMathScore() {
        return mathScore;
    }

    public void setMathScore(float mathScore) {
        this.mathScore = mathScore;
    }

    public float getScienceScore() {
        return scienceScore;
    }

    public void setScienceScore(float scienceScore) {
        this.scienceScore = scienceScore;
    }

    public float getLanguageScore() {
        return languageScore;
    }

    public void setLanguageScore(float languageScore) {
        this.languageScore = languageScore;
    }

    public float getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(float englishScore) {
        this.englishScore = englishScore;
    }

    public float getChinaScore() {
        return chinaScore;
    }

    public void setChinaScore(float chinaScore) {
        this.chinaScore = chinaScore;
    }

    public float getHouseworkScore() {
        return houseworkScore;
    }

    public void setHouseworkScore(float houseworkScore) {
        this.houseworkScore = houseworkScore;
    }

    public float getHabitScore() {
        return habitScore;
    }

    public void setHabitScore(float habitScore) {
        this.habitScore = habitScore;
    }

    public float getGamScore() {
        return gamScore;
    }

    public void setGamScore(float gamScore) {
        this.gamScore = gamScore;
    }

    public float getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(float skillScore) {
        this.skillScore = skillScore;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }
}
