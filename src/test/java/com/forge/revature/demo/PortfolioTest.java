package com.forge.revature.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forge.revature.controllers.PortfolioController;
import com.forge.revature.models.*;
import com.forge.revature.repo.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
public class PortfolioTest {
    private MockMvc mvc;

    @MockBean
    PortfolioRepo repo;

    @MockBean
    AboutMeRepo aboutMeRepo;

    @MockBean
    CertificationRepo certificationRepo;

    @MockBean
    EducationRepo educationRepo;

    @MockBean
    EquivalencyRepo equivalencyRepo;

    @MockBean
    GitHubRepo gitHubRepo;

    @MockBean
    HonorRepo honorRepo;

    @MockBean
    ProjectRepo projectRepo;

    @MockBean
    WorkExperienceRepo workExperienceRepo;

    @MockBean
    WorkHistoryRepo workHistoryRepo;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .standaloneSetup(new PortfolioController(repo))
            .build();
    }

    @Test
    void testGetall() throws Exception {
        System.out.println(repo);
        given(repo.findAll())
            .willReturn(new ArrayList<Portfolio>());

        mvc.perform(get("/portfolios"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testGetById() throws Exception{
        given(repo.findById(1)).willReturn(Optional.of(new Portfolio()));


        mvc.perform(get("/portfolios/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }
    @Test
    void testGetAllByUserId() throws Exception{
        given(repo.findAllByUserId(1)).willReturn(new ArrayList<Portfolio>());

        mvc.perform(get("/portfolios/users/all/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
    }

    @Test
    void testPost() throws Exception{
        Portfolio port = new Portfolio(1, "new portfilio", new User(1, "test", "user" , "test@email.com" , "password", false), false, false, false, "");
        
        given(repo.save(port)).willReturn(port);

        mvc.perform(post("/portfolios")
            .contentType("application/json;charset=utf-8")
            .content(new ObjectMapper().writeValueAsString(port)))

            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andReturn();
    }

    void testUpdate() throws Exception{
        Portfolio port = new Portfolio(1, "new portfilio", new User(1, "test", "user" , "test@email.com" , "password", false), false, false, false, "");
        Portfolio port2 = new Portfolio(1, "new portfilio name", new User(1, "test", "user" , "test@email.com" , "password", false), true, true, true, "feedback");
        Optional<Portfolio> returned = Optional.of(port);
        

        given(repo.findById(1)).willReturn(returned);

        mvc.perform(post("/portfolios/1")
            .contentType("application/json")
            .content(new ObjectMapper().writeValueAsString(port2)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
    }

    @Test
    void testdelete() throws Exception {
        Portfolio port = new Portfolio(1, "new portfilio", new User(1, "test", "user" , "test@email.com" , "password", false), false, false, false, "");
        Optional<Portfolio> returned = Optional.of(port);

        given(repo.findById(1)).willReturn(returned);

        mvc.perform(delete("/portfolios/1"))
            .andExpect(status().isOk());
    }

    @Test
    void testGetFullPortfolio() throws Exception {
        Optional<Portfolio> port = Optional.of(new Portfolio(1, "new portfilio",
                new User(1, "test", "user" , "test@email.com" , "password", false), false, false, false, ""));
        given(repo.findById(1)).willReturn(port);

        Optional<AboutMe> aboutMe = Optional.of(new AboutMe(port.get(), "bio", "email", "phone"));
        given(aboutMeRepo.findByPortfolioId(1)).willReturn(aboutMe);

        ArrayList<Certification> certification = new ArrayList<>();
        certification.add(new Certification(port.get(), "name", "certId", "issuedBy",
                new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-24"), "publicUrl"));
        given(certificationRepo.findAllByPortfolioId(1)).willReturn(certification);

        ArrayList<Education> education = new ArrayList<>();
        education.add(new Education(port.get(), "university", "degree", "graduationDate", 3.6, "logoUrl"));
        given(educationRepo.findAllByPortfolioId(1)).willReturn(education);

        ArrayList<Equivalency> equivalency = new ArrayList<>();
        equivalency.add(new Equivalency(1, "header", 5, port.get()));
        given(equivalencyRepo.findAllByPortfolioId(1)).willReturn(equivalency);

        ArrayList<GitHub> github = new ArrayList<>();
        github.add(new GitHub(1, "url", "image", port.get()));
        given(gitHubRepo.findByPortfolio(port.get())).willReturn(github);

        ArrayList<Honor> honor = new ArrayList<>();
        honor.add(new Honor(1, "title", "description", "dateReceived", "receivedFrom", port.get()));
        given(honorRepo.findByPortfolio(port.get())).willReturn(honor);

        ArrayList<Project> project = new ArrayList<>();
        project.add(new Project("name", "description", "responsibilities", "technologies", "repositoryUrl", port.get()));
        given(projectRepo.findByPortfolio_Id(1)).willReturn(project);

        ArrayList<WorkExperience> experience = new ArrayList<>();
        experience.add(new WorkExperience("employer", "title", "responsibilities", "description", "technologies", 
                new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-24"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-05"), port.get()));
        given(workExperienceRepo.findByPortfolio_Id(1)).willReturn(experience);

        ArrayList<WorkHistory> history = new ArrayList<>();
        history.add(new WorkHistory(1, "title", "employer", "responsibilities", "description", "tools", "startDate", "endDate", port.get()));
        given(workHistoryRepo.findByPortfolio(port.get())).willReturn(history);

        mvc.perform(get("/portfolios/full/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }
}
