package com.mark.likemovies.Models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

public class FullMovie {

    private String id;

    private String title;

    private String originalTitle;

    private String fullTitle;

    private String type;

    private String year;
    private String image;
    private String releaseDate;
    private String runtimeMins;
    private String runtimeStr;
    private String plot;
    private String plotLocal;
    private boolean plotLocalIsRtl;
    private String awards;
    private String directors;
    ArrayList< Object > directorList = new ArrayList < Object > ();
    private String writers;
    ArrayList < Object > writerList = new ArrayList < Object > ();
    private String stars;
    ArrayList < Object > starList = new ArrayList < Object > ();
    ArrayList < Object > actorList = new ArrayList < Object > ();
    private String fullCast = null;
    private String genres;
    ArrayList < Object > genreList = new ArrayList < Object > ();
    private String companies;
    ArrayList < Object > companyList = new ArrayList < Object > ();
    private String countries;
    ArrayList < Object > countryList = new ArrayList < Object > ();
    private String languages;
    ArrayList < Object > languageList = new ArrayList < Object > ();
    private String contentRating;
    private String imDbRating;
    private String imDbRatingVotes;
    private String metacriticRating;
    private String ratings = null;
    private String wikipedia = null;
    private String posters = null;
    private String images = null;
    private String trailer = null;
    BoxOffice BoxOfficeObject;
    private String tagline;
    private String keywords;
    ArrayList < Object > keywordList = new ArrayList < Object > ();
    ArrayList < Object > similars = new ArrayList < Object > ();
    private String tvSeriesInfo = null;
    private String tvEpisodeInfo = null;
    private String errorMessage = null;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public String getType() {
        return type;
    }

    public String getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getRuntimeMins() {
        return runtimeMins;
    }

    public String getRuntimeStr() {
        return runtimeStr;
    }

    public String getPlot() {
        return plot;
    }

    public String getPlotLocal() {
        return plotLocal;
    }

    public boolean getPlotLocalIsRtl() {
        return plotLocalIsRtl;
    }

    public String getAwards() {
        return awards;
    }

    public String getDirectors() {
        return directors;
    }

    public String getWriters() {
        return writers;
    }

    public String getStars() {
        return stars;
    }

    public String getFullCast() {
        return fullCast;
    }

    public String getGenres() {
        return genres;
    }

    public String getCompanies() {
        return companies;
    }

    public String getCountries() {
        return countries;
    }

    public String getLanguages() {
        return languages;
    }

    public String getContentRating() {
        return contentRating;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public String getImDbRatingVotes() {
        return imDbRatingVotes;
    }

    public String getMetacriticRating() {
        return metacriticRating;
    }

    public String getRatings() {
        return ratings;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getPosters() {
        return posters;
    }

    public String getImages() {
        return images;
    }

    public String getTrailer() {
        return trailer;
    }

    public BoxOffice getBoxOffice() {
        return BoxOfficeObject;
    }

    public String getTagline() {
        return tagline;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getTvSeriesInfo() {
        return tvSeriesInfo;
    }

    public String getTvEpisodeInfo() {
        return tvEpisodeInfo;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRuntimeMins(String runtimeMins) {
        this.runtimeMins = runtimeMins;
    }

    public void setRuntimeStr(String runtimeStr) {
        this.runtimeStr = runtimeStr;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setPlotLocal(String plotLocal) {
        this.plotLocal = plotLocal;
    }

    public void setPlotLocalIsRtl(boolean plotLocalIsRtl) {
        this.plotLocalIsRtl = plotLocalIsRtl;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public void setFullCast(String fullCast) {
        this.fullCast = fullCast;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setCompanies(String companies) {
        this.companies = companies;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

    public void setImDbRatingVotes(String imDbRatingVotes) {
        this.imDbRatingVotes = imDbRatingVotes;
    }

    public void setMetacriticRating(String metacriticRating) {
        this.metacriticRating = metacriticRating;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public void setPosters(String posters) {
        this.posters = posters;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setBoxOffice(BoxOffice boxOfficeObject) {
        this.BoxOfficeObject = boxOfficeObject;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setTvSeriesInfo(String tvSeriesInfo) {
        this.tvSeriesInfo = tvSeriesInfo;
    }

    public void setTvEpisodeInfo(String tvEpisodeInfo) {
        this.tvEpisodeInfo = tvEpisodeInfo;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
class BoxOffice {
    private String budget;
    private String openingWeekendUSA;
    private String grossUSA;
    private String cumulativeWorldwideGross;


    // Getter Methods

    public String getBudget() {
        return budget;
    }

    public String getOpeningWeekendUSA() {
        return openingWeekendUSA;
    }

    public String getGrossUSA() {
        return grossUSA;
    }

    public String getCumulativeWorldwideGross() {
        return cumulativeWorldwideGross;
    }

    // Setter Methods

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setOpeningWeekendUSA(String openingWeekendUSA) {
        this.openingWeekendUSA = openingWeekendUSA;
    }

    public void setGrossUSA(String grossUSA) {
        this.grossUSA = grossUSA;
    }

    public void setCumulativeWorldwideGross(String cumulativeWorldwideGross) {
        this.cumulativeWorldwideGross = cumulativeWorldwideGross;
    }
}