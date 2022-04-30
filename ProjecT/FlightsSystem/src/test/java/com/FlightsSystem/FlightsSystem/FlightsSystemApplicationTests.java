package com.FlightsSystem.FlightsSystem;

import com.FlightsSystem.FlightsSystem.Poco.Flight;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
@SpringBootTest
class FlightsSystemApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	void getFromFlightConttollerTest(){
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8080/Anonymous/flight/14"))
				.build();

		HttpResponse<String> response=null;

		try {
			response =client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		var expected =
				gson.fromJson(response.body(), Flight.class);

		System.out.println(expected);

		var Timestamp1 = java.sql.Timestamp.valueOf("2022-03-14 20:22:44");
		var Timestamp2 = java.sql.Timestamp.valueOf("2022-03-15 02:44:10");

		var current = new Flight(14,4,1,2,Timestamp1,Timestamp2,11);

		Assert.assertEquals(current,expected);
	}



}
