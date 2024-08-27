package br.com.bfrjunior.fake_api.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "fake-api", url = "${fake-api.url:#{null}}")
public class FakeApiClient {
}
