package com.appx.stream;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;

public class StreamUtils {

    public static <A, B, C> Stream<C> zip(Stream<A> streamA, Stream<B> streamB, BiFunction<A, B, C> zipper) {
        Iterator<A> iteratorA = streamA.iterator();
        Iterator<B> iteratorB = streamB.iterator();
        Iterator<C> iteratorC = new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return iteratorA.hasNext() && iteratorB.hasNext();
            }

            @Override
            public C next() {
                return zipper.apply(iteratorA.next(), iteratorB.next());
            }
        };
        Spliterator<C> spliterator = Spliterators.spliteratorUnknownSize(iteratorC, Spliterator.ORDERED);
        return StreamSupport.stream(spliterator, false);
    }
}