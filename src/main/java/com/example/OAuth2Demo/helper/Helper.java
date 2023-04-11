package com.example.OAuth2Demo.helper;

import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Helper
{
    public static Map getUserData(OAuth2AuthenticationToken authentication, OAuth2AuthorizedClientService authorizedClientService)
    {
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());

        String userInfoEndpointUri = client.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
                .getTokenValue());
        HttpEntity entity = new HttpEntity("", headers);
        ResponseEntity<Map> response = restTemplate
                .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
        return response.getBody();

    }
}
