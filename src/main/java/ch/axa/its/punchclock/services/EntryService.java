package ch.axa.its.punchclock.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.axa.its.punchclock.domain.Entry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class EntryService {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  public void create(Entry entry) {
    this.entityManager.persist(entry);
  }

  public Entry read(String id) {
    return this.entityManager.find(Entry.class, id);
  }

  public List<Entry> index() {
    return this.entityManager.createQuery("SELECT e FROM Entry e", Entry.class).getResultList();
  }

  public void update(Entry entry) {
    this.entityManager.merge(entry);
  }

  public void delete(Entry entry) {
    this.entityManager.remove(entry);
  }
}
