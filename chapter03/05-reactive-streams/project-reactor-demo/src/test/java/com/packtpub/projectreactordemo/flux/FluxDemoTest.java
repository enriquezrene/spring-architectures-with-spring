package com.packtpub.projectreactordemo.flux;

import org.junit.Assert;
import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class FluxDemoTest {

    @Test
    public void givenAListOfCapitalizedStrings_WhenTheFlatMapConvertsToUpperCaseTheStrings_ThenTheStringsAreInUpperCase() throws Exception {
        List<String> namesCapitalized = Arrays.asList("John", "Steve", "Rene");
        Iterator<String> namesCapitalizedIterator = namesCapitalized.iterator();
        Flux<String> fluxWithNamesCapitalized = Flux.fromIterable(namesCapitalized);

        Flux<String> fluxWithNamesInUpperCase = fluxWithNamesCapitalized
                .map(name -> name.toUpperCase());

        fluxWithNamesInUpperCase.subscribe(nameInUpperCase ->
                Assert.assertEquals(namesCapitalizedIterator.next().toUpperCase(), nameInUpperCase)
        );
    }
}
