package ch.axa.its.punchclock.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ch.axa.its.punchclock.domain.Entry;

public interface EntryRepository extends CrudRepository<Entry, String>, EntryRepositoryFilter {
    Iterable<Entry> findByCategory(@Param("categoryId") String categoryId);
    Iterable<Entry> findByTagsId(String tagId);

    @Query(value = "SELECT e FROM Entry e WHERE e.description LIKE %:descriptionSearch%")
    Iterable<Entry> searchByDescription(@Param("descriptionSearch") String descriptionSearch);
}
