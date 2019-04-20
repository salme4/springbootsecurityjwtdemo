package com.salme4.springbootsecurityjwtdemo.security;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class HeaderTokenExtractorTest {
    private HeaderTokenExtractor extractor = new HeaderTokenExtractor();
    private String header;

    @Before
    public void setUp(){
        this.header = "Bearer arlksdjrldasjr.asdfoijalr.sdasdofjaefkl";
    }

    @Test
    public void testJwtExtract(){
        assertThat(extractor.extract(this.header)).isEqualTo("arlksdjrldasjr.asdfoijalr.sdasdofjaefkl");
    }
}