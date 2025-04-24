package com.ecom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ecom.controller.UserController;
import com.ecom.dto.UserRequestDTO;
import com.ecom.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(UserController.class)
public class UserControllerTest {
	

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
	
	void testRegisterUser() throws Exception {
        UserRequestDTO dto = new UserRequestDTO("Test User", "test@example.com", "Password123", "+911234567890", "USER");

        mockMvc.perform(post("/api/users/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(dto)))
            .andExpect(status().isCreated());
    }

}
