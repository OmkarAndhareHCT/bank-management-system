package com.humancloud.bms.employee_service.client;

import com.humancloud.bms.employee_service.exceptions.BranchNotFoundException;
import com.humancloud.bms.employee_service.exceptions.BranchServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@RequiredArgsConstructor
@Slf4j
public class BranchClient {

    private final RestClient branchRestClient;

    /**
     * Confirms the branch exists before we store a reference to it.
     * There is no database foreign key to do this for us.
     */
    public void assertBranchExists(Long branchId) {
        try {
            branchRestClient.get()
                    .uri("/api/v1/branches/{id}", branchId)
                    .retrieve()
                    .toBodilessEntity();

        } catch (HttpClientErrorException.NotFound ex) {
            throw new BranchNotFoundException(branchId);

        } catch (RestClientException ex) {
            // timeout, connection refused, 5xx from branch-service
            throw new BranchServiceUnavailableException(branchId, ex);
        }
    }
}