package wavestone.automotive.parts.domain.reader.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import wavestone.automotive.parts.TestCarPartsApplication;
import wavestone.automotive.parts.domain.reader.service.ReaderService;
import wavestone.automotive.parts.model.entity.Part;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Import(TestCarPartsApplication.class)
class ReaderServiceTest {

    @Autowired
    ReaderService service;

    @Test
    void findAllParts() {
        Collection<Part> allParts = service.findAllParts();
        assertThat(allParts).isNotEmpty()
                .hasSize(6); //see DBTestDataLoader
    }

    @Test
    void findWithNullCriteria() {

    }
}