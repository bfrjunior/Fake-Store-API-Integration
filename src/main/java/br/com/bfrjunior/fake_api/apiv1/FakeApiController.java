package br.com.bfrjunior.fake_api.apiv1;

import br.com.bfrjunior.fake_api.business.FakeApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class FakeApiController {

    private final FakeApiService service;

}
