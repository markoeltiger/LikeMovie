package com.mark.likemovies.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "preidecteitem")
public class preidecteitem {
    @PrimaryKey
    public String id;

    @ColumnInfo(name = "rank")
        public String rank;
    @ColumnInfo(name = "rankUpDown")

    public String rankUpDown;
    @ColumnInfo(name = "title")

    public String title;
    @ColumnInfo(name = "fullTitle")

    public String fullTitle;
    @ColumnInfo(name = "year")

    public String year;
    @ColumnInfo(name = "image")

    public String image;
    @ColumnInfo(name = "crew")

    public String crew;
    @ColumnInfo(name = "imDbRating")

    public String imDbRating;
    @ColumnInfo(name = "imDbRatingCount")

    public String imDbRatingCount;
    @ColumnInfo(name = "liked")

    public boolean liked =false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRankUpDown() {
        return rankUpDown;
    }

    public void setRankUpDown(String rankUpDown) {
        this.rankUpDown = rankUpDown;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

    public String getImDbRatingCount() {
        return imDbRatingCount;
    }

    public void setImDbRatingCount(String imDbRatingCount) {
        this.imDbRatingCount = imDbRatingCount;
    }
}
