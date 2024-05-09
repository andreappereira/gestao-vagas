package com.andreappereira.main.modules.company.controllers;

import static org.junit.Assert.assertTrue;


import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.andreappereira.main.exceptions.CompanyNotFoundException;
import com.andreappereira.main.modules.company.CompanyEntity;
import com.andreappereira.main.modules.company.repositories.CompanyRepository;
import com.andreappereira.main.modules.job.dto.CreateJobDTO;
import com.andreappereira.main.utils.TestUtils;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {
    @Value("${security.token.secret.company}")
    private String secretKey;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception{

        var company = CompanyEntity.builder()
        .description("COMPANY_DESCRIPTION")
        .document("COMPANY_DOCUMENT")
        .email("company@email.com")
        .password("123456789@Z0")
        .build();

        company = companyRepository.saveAndFlush(company);

        var createdJobDTO = CreateJobDTO.builder()
        .benefits("BENEFITS_TEST")
        .description("DESCRIPTION_TEST")
        .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtils.objectToJson(createdJobDTO))
        .header("Authorization", TestUtils.generateToken(company.getId(), secretKey)))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_not_be_able_to_create_a_new_apply_job_if_company_not_found() throws Exception {

        var createdJobDTO = CreateJobDTO.builder()
        .benefits("BENEFITS_TEST")
        .description("DESCRIPTION_TEST")
        .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtils.objectToJson(createdJobDTO))
        .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), secretKey)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
}
