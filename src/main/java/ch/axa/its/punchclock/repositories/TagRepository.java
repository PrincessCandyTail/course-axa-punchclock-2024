package ch.axa.its.punchclock.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.axa.its.punchclock.domain.Tag;

public interface TagRepository extends CrudRepository<Tag, String> {

}
