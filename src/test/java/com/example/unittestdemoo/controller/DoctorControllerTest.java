package com.example.unittestdemoo.controller;

import com.example.unittestdemoo.Doctor;
import com.example.unittestdemoo.DoctorController;
import com.example.unittestdemoo.DoctorDto;
import com.example.unittestdemoo.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DoctorController.class)
public class DoctorControllerTest {

    private final static String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DoctorService doctorService;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        // given,arrange
        DoctorDto doctorDto = DoctorDto.builder().finCode("67890").name("Vusala").surname("Aghayeva").build();

        // when
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post("/unit")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(doctorDto)));

        // then
        ArgumentCaptor<Doctor> captor = ArgumentCaptor.forClass(Doctor.class);
        verify(doctorService, times(1)).save(captor.capture());
        actions.andExpect(status().isOk());
    }

    @Test
    void whenValidInput_thenReturns400() throws Exception {
        // given

        // when
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post("/unit")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString("test-value")));

        // then
        actions.andExpect(status().isBadRequest());
    }


    @Test
    void whenCallTumunuListele_thenReturns200() throws Exception {
        // given
//        DoctorDto doctorDto = DoctorDto.builder().finCode("67890").name("Vusala").surname("Aghayeva").build();

        Doctor doctor = Doctor.builder().id(1l).finCode("67890").name("Vusala").surname("Aghayeva").build();

        when(doctorService.getAll()).thenReturn(Arrays.asList(doctor));

        // when
        MvcResult mvcResult = mockMvc.perform(get("/unit")
                .accept(CONTENT_TYPE)).andReturn();


        // then
        String responseBody = mvcResult.getResponse().getContentAsString();
        verify(doctorService, times(1)).getAll();
        assertThat(objectMapper.writeValueAsString(Arrays.asList(doctor)))
                .isEqualToIgnoringWhitespace(responseBody);


//        List<Doctor> doctors = new ArrayList<>();
//        doctors.add(Doctor.builder().finCode("67890").name("Vusala").surname("Aghayeva").build());
//        given(doctorService.getAll()).willReturn(doctors);
//
//        // when -  action or the behaviour that we are going test
//        ResultActions response = mockMvc.perform(get("/unit"));
//
//        // then - verify the output
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.size()",
//                        is(doctors.size())));
    }


    @Test
    void whenCallTumunuListele_thenReturnsNoData() throws Exception {
        // given
        when(doctorService.getAll()).thenReturn(Collections.emptyList());

        // when
        MvcResult mvcResult = mockMvc.perform(get("/unit")
                .accept(CONTENT_TYPE)).andReturn();

        // then
        String responseBody = mvcResult.getResponse().getContentAsString();
        verify(doctorService, times(1)).getAll();
        assertThat(objectMapper.writeValueAsString(Collections.emptyList()))
                .isEqualToIgnoringWhitespace(responseBody);
    }

}

