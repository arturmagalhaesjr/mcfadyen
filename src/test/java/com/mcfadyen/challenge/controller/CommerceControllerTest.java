package com.mcfadyen.challenge.controller;

import com.mcfadyen.challenge.model.CommerceItem;
import com.mcfadyen.challenge.model.Product;
import com.mcfadyen.challenge.model.ShoppingCart;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(CommerceController.class)

public class CommerceControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ShoppingCart shoppingCart;

    private Product createProduct () {
        Product p = new Product();
        p.setName("Test");
        p.setId(new Long(1));
        p.setPrice(new BigDecimal(200));
        p.setImage("URL Image");
        return p;
    }

    @Test
    public void givenProducts_whenGetProducts () throws Exception {

        Product p = createProduct();
        List<Product> allProducts = Arrays.asList(p);

        when(productRepository.findAll()).thenReturn(allProducts);
        mvc.perform(get("/products")).andExpect(status().isOk()).andExpect(content().string(containsString("Test")));
    }

    @Test
    public void givenShoppingCart_whenGetShoppingCart () throws Exception {
        ShoppingCart sp = new ShoppingCart();
        CommerceItem ci = new CommerceItem();
        ci.setProduct(createProduct());
        ci.setQuantity(1);
        sp.addCommerceItem(ci);
        mvc.perform(get("/shoppingcart")
                .flashAttr("shoppingCart", sp))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test")))
                .andExpect(content().string(containsString("amount")))
                .andExpect(content().string(containsString("commerceItems")))
                .andExpect(content().string(containsString("quantity")));
    }

    @Test
    public void givenShoppingCart_whenGetShoppingCartFewProducts () throws Exception {
        ShoppingCart sp = new ShoppingCart();
        Product p = createProduct();
        CommerceItem ci = new CommerceItem();
        ci.setProduct(p);
        ci.setQuantity(1);
        sp.addCommerceItem(ci);

        ci = new CommerceItem();
        ci.setProduct(p);
        ci.setQuantity(5);
        sp.addCommerceItem(ci);

        mvc.perform(get("/shoppingcart")
                .flashAttr("shoppingCart", sp))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test")))
                .andExpect(content().string(containsString("amount")))
                .andExpect(content().string(containsString("commerceItems")))
                .andExpect(content().string(containsString("quantity")));
    }

    @Test
    public void givenShoppingCart_whenPostCommerceItem () throws Exception {
        ShoppingCart sp = new ShoppingCart();

        Product p = createProduct();
        when(productRepository.findById(new Long(1))).thenReturn(Optional.of(p));

        mvc.perform(post("/shoppingcart/items")
                .param("product_id", "1")
                .param("quantity", "2")
                .flashAttr("shoppingCart", sp))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test")))
                .andExpect(content().string(containsString("amount")))
                .andExpect(content().string(containsString("quantity")));
    }

    @Test
    public void givenShoppingCart_whenDeleteCommerceItem () throws Exception {
        ShoppingCart sp = new ShoppingCart();
        Product p = createProduct();
        CommerceItem ci = new CommerceItem();
        ci.setProduct(p);
        ci.setQuantity(1);
        sp.addCommerceItem(ci);

        ci = new CommerceItem();
        ci.setProduct(p);
        ci.setQuantity(5);
        sp.addCommerceItem(ci);

        mvc.perform(delete("/shoppingcart/items/0")
                .flashAttr("shoppingCart", sp))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test")))
                .andExpect(content().string(containsString("amount")))
                .andExpect(content().string(containsString("quantity")));
    }

    @Test(expected = Exception.class)
    public void givenShoppingCart_whenInvalidCommerceItem () throws Exception {
        ShoppingCart sp = new ShoppingCart();
        Product p = createProduct();
        CommerceItem ci = new CommerceItem();
        ci.setProduct(p);
        ci.setQuantity(1);
        sp.addCommerceItem(ci);


        mvc.perform(delete("/shoppingcart/items/10")
                .flashAttr("shoppingCart", sp))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }


}
