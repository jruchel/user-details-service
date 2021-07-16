package com.example.demo.services.external;

import com.example.demo.configs.FilterServiceConfig;
import com.example.demo.models.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsFilterService {

    private final RestTemplate restTemplate;
    private final FilterServiceConfig config;

    public List<UserDetails> filter(List<UserDetails> userDetails) {
        Set<String> userDetailsStrings = getFilterResponse(userDetails.stream().map(UserDetails::getMessage).collect(Collectors.toSet()));
        return mapResults(userDetails, userDetailsStrings);
    }

    private List<UserDetails> mapResults(List<UserDetails> initialList, Set<String> filterResponse) {
        List<UserDetails> result = new ArrayList<>();

        for (String s : filterResponse) {
            result.addAll(initialList.stream().filter(details -> details.getMessage().equals(s)).collect(Collectors.toList()));
        }

        return result;
    }

    private Set<String> getFilterResponse(Set<String> userDetails) {
        String url = String.format("http://%s:%s/filter", config.getHost(), config.getPort());
        RequestEntity<Set<String>> requestEntity = RequestEntity.post(url).body(userDetails);
        ParameterizedTypeReference<Set<String>> stringListTypeReference = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(requestEntity, stringListTypeReference).getBody();
    }

}
