package com.packtpub.projectreactordemo.flux;

import org.junit.Assert;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FluxDemoTest {

    @Test
    public void givenAListOfElementsWhenTheSubscriptionHappensTheElementsAreObtainedOneByOne() throws Exception {
        List<Integer> digits = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Iterator<Integer> digitsIterator = digits.iterator();

        Flux<Integer> digitsFlux = Flux.fromIterable(digits);

        digitsFlux.subscribe(element ->
                Assert.assertEquals(digitsIterator.next(), element)
        );
    }
}
