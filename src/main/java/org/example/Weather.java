package org.example;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("city_name")
    private String name;

    @SerializedName("temp")
    private double temperature;

    private WeatherDescription weather;
    private int clouds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public WeatherDescription getWeather() {
        return weather;
    }

    public void setWeather(WeatherDescription weather) {
        this.weather = weather;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public static class WeatherDescription {
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
