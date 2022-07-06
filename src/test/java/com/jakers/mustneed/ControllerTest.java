package com.jakers.mustneed;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {MustNeedApplication.class})
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void App_LoadProperties() throws Exception {
    }

    @Test
    public void ServerController_info() throws Exception {
        this.mockMvc.perform(get("/info")).andExpect(status().isOk());
    }

    @Test
    public void ServerController_check() throws Exception {
        //        this.mockMvc.perform(get("/check")).andExpect(status().isOk()).andDo(print());
        this.mockMvc.perform(get("/check")).andExpect(status().isOk());
    }
}