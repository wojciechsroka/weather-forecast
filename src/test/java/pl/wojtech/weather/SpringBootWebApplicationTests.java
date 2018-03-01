package pl.wojtech.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import pl.wojtech.weather.model.WeatherForecastDataBundle;
import pl.wojtech.weather.model.WeatherForecastDataSeries;
import pl.wojtech.weather.model.json.City;
import pl.wojtech.weather.model.json.WeatherForecastJSON;
import pl.wojtech.weather.service.WeatherService;

import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class SpringBootWebApplicationTests {

	public static final String EXISTING_CITY_NAME = "London";
	private static WireMockServer wireMockServer;

	private static ObjectMapper objectMapper;

	@Autowired
	WeatherService weatherService;

	@BeforeClass
	public static void init() throws JsonProcessingException {

		wireMockServer = new WireMockServer(5001);
		wireMockServer.start();

		configureFor("localhost", 5001);

		WeatherForecastJSON weatherForecastJSON = new WeatherForecastJSON();
		City city = new City();
		city.setName(EXISTING_CITY_NAME);
		weatherForecastJSON.setCity(city);
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		WeatherForecastDataBundle weatherForecastDataBundle = new WeatherForecastDataBundle();
		weatherForecastDataBundle.setWeatherForecastDataSeries(new HashMap<>());
		weatherForecastDataBundle.getWeatherForecastDataSeries().put("London",new WeatherForecastDataSeries());
		String jsonMessage = objectMapper.writeValueAsString(weatherForecastDataBundle);

		stubFor(get(urlEqualTo("/London/dataMap"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withHeader("Cache-Control", "no-cache").withBody(jsonMessage)));
	}

	@AfterClass
	public static void afterEnd() throws JsonProcessingException {
		wireMockServer.stop();
	}

	@Test
	public void testGettingValidData(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<WeatherForecastDataBundle> weatherForecastDataBundle = restTemplate.getForEntity("http://localhost:5001/London/dataMap", WeatherForecastDataBundle.class);
		assertNotNull(weatherForecastDataBundle);
		assertNotNull(weatherForecastDataBundle.getBody());
		assertNotNull(weatherForecastDataBundle.getBody().getWeatherForecastDataSeries().get("London"));
	}

	@Test(expected =org.springframework.web.client.HttpClientErrorException.class)
	public void testGettingInvalidData(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<WeatherForecastDataBundle> weatherForecastDataBundle = restTemplate.getForEntity("http://localhost:5001/NO_CITY/dataMap", WeatherForecastDataBundle.class);
	}

	@Test(expected =org.springframework.web.client.HttpClientErrorException.class)
	public void testGettingInvalidUrlData(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<WeatherForecastDataBundle> weatherForecastDataBundle = restTemplate.getForEntity("http://localhost:5001/dataMap", WeatherForecastDataBundle.class);
	}
	@Test
	public void contextLoads() {
	}

}
