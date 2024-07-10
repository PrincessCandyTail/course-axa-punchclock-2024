package ch.axa.its.punchclock.services;

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
public class EntryServiceTest {

  @Inject
  private EntryService entryService;

  @Test
  public void testIfEntryCanBeSaved() {
      Entry entry = new Entry();
      entry.setCheckIn(LocalDateTime.now());
      entry.setCheckOut(LocalDateTime.now().plusHours(2));

      entryService.create(entry);

      assertEquals(entry.getCheckIn().truncatedTo(ChronoUnit.MICROS), entryService.read(entry.getId()).getCheckIn());
  }

  @Test
  public void testIfEntriesCanBeIndexed() {
      Entry entry1 = new Entry();
      entry1.setCheckIn(LocalDateTime.now());
      entry1.setCheckOut(LocalDateTime.now().plusHours(2));
      entryService.create(entry1);

      Entry entry2 = new Entry();
      entry2.setCheckIn(LocalDateTime.now().minusHours(1));
      entry2.setCheckOut(LocalDateTime.now());
      entryService.create(entry2);

      assertEquals(2, entryService.index().size());
  }
}
