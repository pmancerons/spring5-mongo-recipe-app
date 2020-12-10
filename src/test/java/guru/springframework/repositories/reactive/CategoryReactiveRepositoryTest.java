package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest extends TestCase {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;


    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void findByDescription() throws Exception {
        Category cat = new Category();
        cat.setDescription("cat1");

        categoryReactiveRepository.save(cat).block();

        Mono<Category> uomMono = categoryReactiveRepository.findByDescription("cat1");

        assertEquals("cat1", uomMono.block().getDescription());
    }

    @Test
    public void findByDescriptionCount() throws Exception {
        Category cat = new Category();
        cat.setDescription("cat1");

        categoryReactiveRepository.save(cat).block();

        Mono<Category> uomMono = categoryReactiveRepository.findByDescription("cat1");

        assertNotNull(uomMono.block());
    }
}