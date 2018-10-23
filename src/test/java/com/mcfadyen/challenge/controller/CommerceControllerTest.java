package com.mcfadyen.challenge.controller;

import com.mcfadyen.challenge.model.Product;
import com.mcfadyen.challenge.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(CommerceController.class)

public class CommerceControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void givenProducts_whenGetProducts_thenReturnJsonArray () throws Exception{

        Product p = new Product();
        p.setName("Test");
        p.setId(new Long(1));
        p.setImage("URL Image");

        List<Product> allProducts = Arrays.asList(p);

        when(productRepository.findAll()).thenReturn(allProducts);
        mvc.perform(get("/products")).andExpect(status().isOk()).andExpect(content().string(containsString("Test")));
    }
}
