package ch.axa.its.punchclock.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import ch.axa.its.punchclock.domain.Entry;
import jakarta.inject.Inject;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class EntryRepositoryTest {

  @Inject
  private EntryRepository entryRepository;

  @Test
  public void testIfEntryCanBeSaved() {
      Entry entry = new Entry();
      entry.setCheckIn(LocalDateTime.now());
      entry.setCheckOut(LocalDateTime.now().plusHours(2));

      entryRepository.save(entry);

      assertEquals(entry.getCheckIn().truncatedTo(ChronoUnit.MICROS), entryRepository.findById(entry.getId()).get().getCheckIn());
  }

  @Test
  public void testIfEntriesCanBeIndexed() {
      Entry entry1 = new Entry();
      entry1.setCheckIn(LocalDateTime.now());
      entry1.setCheckOut(LocalDateTime.now().plusHours(2));
      entryRepository.save(entry1);

      Entry entry2 = new Entry();
      entry2.setCheckIn(LocalDateTime.now().minusHours(1));
      entry2.setCheckOut(LocalDateTime.now());
      entryRepository.save(entry2);

      assertEquals(2, entryRepository.findAll());
  }
}
