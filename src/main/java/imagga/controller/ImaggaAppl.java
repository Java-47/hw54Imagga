package imagga.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import imagga.dto.ImaggaDto;

public class ImaggaAppl {
	static RestTemplate restTemplate = new RestTemplate();
	static final String ENDPOINT_URL = "https://api.imagga.com/v2/colors";
	static final String IMAGE_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/General_Thomas_Fairfax_%281612-1671%29_by_Robert_Walker_and_studio.jpg/800px-General_Thomas_Fairfax_%281612-1671%29_by_Robert_Walker_and_studio.jpg";
	static final String API_KEY = "acc_3ebee670f1a6077";
	static final String API_SECRET = "5a478965e8a2928dbf5ec36ced29c7cd";

	public static void main(String[] args) throws URISyntaxException {
		String url = ENDPOINT_URL + "?image_url=" + IMAGE_URL;

		String credentialsToEncode = API_KEY + ":" + API_SECRET;
		String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + basicAuth);

		RequestEntity<String> requestEntity = new RequestEntity<String>(headers, HttpMethod.GET, new URI(url));
		ResponseEntity<ImaggaDto> responseEntity = restTemplate.exchange(requestEntity, ImaggaDto.class);
		
		responseEntity.getBody().getResult().getColors().printColors();

	}

}
