package com.harishkannarao.demo;

import com.harishkannarao.demo.controllers.CustomHeaderPageController;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class CustomeHeaderPageControllerTest {

    private final CustomHeaderPageController customHeaderPageController = new CustomHeaderPageController();

    @Test
    public void shouldReturnCustomHeaderFromRequest() {
        HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
        Mockito.when(mockHttpServletRequest.getHeader("my-custom-header"))
            .thenReturn("someValue");

        ModelAndView result = customHeaderPageController.displayCutomHeaderPage(mockHttpServletRequest);

        Assert.assertThat(result.getViewName(), Matchers.equalTo("customHeader"));
        Assert.assertThat(result.getModel().get("customHeaderValue"), Matchers.equalTo("someValue"));
    }

    @Test
    public void shouldReturnDefaultValue_givenNoInputHeader() {
        HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);

        ModelAndView result = customHeaderPageController.displayCutomHeaderPage(mockHttpServletRequest);

        Assert.assertThat(result.getViewName(), Matchers.equalTo("customHeader"));
        Assert.assertThat(result.getModel().get("customHeaderValue"), Matchers.equalTo("No Custom Header"));
    }
}
