package ch.axa.its.punchclock.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.axa.its.punchclock.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, String> {
}
