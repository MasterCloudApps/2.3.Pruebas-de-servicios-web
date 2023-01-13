package es.codeurjc.test.web;

import com.intuit.karate.junit5.Karate;

public class WikipediaTest {

	@Karate.Test
    public Karate testSample() {
        return Karate.run("wikipedia").relativeTo(getClass());
    }
}







