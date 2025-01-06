package com.example.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class ReactiveTutorial {

    private Flux<String> testFlux(){
        List<String> languajes = List.of("jasa,", "iajsdi");
        return  Flux.fromIterable(languajes);

       // return Flux.just("jasa,", "iajsdi");
    }


    // ONE DATA
    private Mono<String> testMono(){
       // return Mono.just("java").log();
       // return Mono.justOrEmpty(null);
        return Mono.empty();
    }

    private Flux<String> testMap(){
        Flux<String> flux = Flux.just("java", "php","rust");
        return flux.map(s -> s.toUpperCase(Locale.ROOT));
    }
    private Flux<String> testFlatMap(){
        Flux<String> flux = Flux.just("java", "php","rust");
        return flux.flatMap(s -> Mono.just(s.toUpperCase(Locale.ROOT)));
    }Ç¨+

    private Flux<String> testSkip(){
        Flux<String> flux = Flux.just("java", "php","rust")
                .delayElements(Duration.ofSeconds(1));
        //return flux.skip(Duration.ofMillis(2010));
        //return flux.skip(2);
        return flux.skipLast(2);
    }

    private Flux<Integer> testCompleteSkip(){
        Flux<Integer> flux = Flux.range(1,20);
      //  return flux.skipWhile(integer -> integer < 10);
        return flux.skipUntil(integer -> integer < 10);
    }

    private Flux<Integer> testConcat(){
        Flux<Integer> flux1 = Flux.range(1,20);
        Flux<Integer> flux2 = Flux.range(101,20);
        return Flux.concat(flux1,flux2);
    }

    private Flux<Integer> testMerge(){
        Flux<Integer> flux1 = Flux.range(1,20)
                .delayElements(Duration.ofSeconds(1));
        Flux<Integer> flux2 = Flux.range(101,20);
        return Flux.concat(flux1,flux2);
    }
    private Flux<Tuple2<Integer, Integer>> testZip(){
        Flux<Integer> flux1 = Flux.range(1,20)
                .delayElements(Duration.ofSeconds(1));
        Flux<Integer> flux2 = Flux.range(101,20);
        return Flux.zip(flux1,flux2);
    }

    private Mono<List<Integer>> testCollect(){
        Flux<Integer> flux2 = Flux.range(101,20)
                .delayElements(Duration.ofMillis(1000));
        return flux2.collectList();
    }

    private Flux<List<Integer>> testBuffer(){
        Flux<Integer> flux2 = Flux.range(101,20)
                .delayElements(Duration.ofMillis(1000));
        // return flux2.buffer(3);
        return flux2.buffer(Duration.ofSeconds(3));
    }

    public static void main(String[] args) throws InterruptedException {
        imprimirNumeros(1,2,3,4,5);
        ReactiveTutorial reactiveTutorial = new ReactiveTutorial();
        reactiveTutorial.testMono()
                .subscribe(data->System.out.println(data));
        reactiveTutorial.testBuffer()
                .subscribe(System.out::println);
        List<Integer> output = reactiveTutorial.testCollect()
                .block();
        System.out.print(output);
        Thread.sleep(
                10_000
        );
    }

    private static void imprimirNumeros(int... numeros ) {
        for(var i=0; i < numeros.length; i++)
            System.out.println(numeros[i] + "");
        int edades[] = {5,4,5,3};
        for( int edad :edades){
            System.out.println("edad " + edad);
        }
    }
}
