package org.example;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherApp extends Application {

    private TextField locationInput;
    private Label weatherInfo;

    @Override
    public void start(Stage primaryStage) {
        locationInput = new TextField();
        Button getWeatherButton = new Button("Get Weather");
        weatherInfo = new Label();

        getWeatherButton.setOnAction(e -> getWeather());

        VBox root = new VBox(10, locationInput, getWeatherButton, weatherInfo);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void getWeather() {
        String location = locationInput.getText();
        try {
            String url = "http://localhost:8080/api/weather?location=" + location;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Raw JSON response: " + response.body());

            Weather weather = new Gson().fromJson(response.body(), Weather.class);

            if (weather != null && weather.getWeather() != null) {
                weatherInfo.setText(String.format("Location: %s\nTemperature: %.2f°C\nDescription: %s\nClouds: %d%%",
                        weather.getName(),
                        weather.getTemperature(),
                        weather.getWeather().getDescription(),
                        weather.getClouds()));
            } else {
                weatherInfo.setText("Weather data is incomplete.");
            }
        } catch (Exception e) {
            weatherInfo.setText("Error fetching weather data.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
