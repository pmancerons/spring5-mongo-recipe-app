package guru.springframework.repositories.reactive;

import guru.springframework.bootstrap.RecipeBootstrap;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;


import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest extends TestCase {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;


    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void findByDescription() throws Exception {
        UnitOfMeasure teaSpoon = new UnitOfMeasure();
        teaSpoon.setDescription("Teaspoon");

        unitOfMeasureReactiveRepository.save(teaSpoon).block();

        Mono<UnitOfMeasure> uomMono = unitOfMeasureReactiveRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon", uomMono.block().getDescription());
    }

    @Test
    public void findByDescriptionCup() throws Exception {

        UnitOfMeasure cup = new UnitOfMeasure();
        cup.setDescription("Cup");

        unitOfMeasureReactiveRepository.save(cup).block();

        Mono<UnitOfMeasure> uomMono = unitOfMeasureReactiveRepository.findByDescription("Cup");

        assertEquals("Cup", uomMono.block().getDescription());
    }
}