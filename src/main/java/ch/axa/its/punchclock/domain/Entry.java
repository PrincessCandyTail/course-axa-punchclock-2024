package ch.axa.its.punchclock.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "entry")
@NamedQuery(query = "SELECT e FROM Entry e WHERE e.category.id = :categoryId", name = "Entry.findByCategory")
public class Entry {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @JsonProperty(access = Access.READ_ONLY)
  private String id;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(nullable = false)
  private LocalDateTime checkIn;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @NotNull
  @Column(nullable = false)
  private LocalDateTime checkOut;

  //@NotBlank(message = "Bitte Beschreibung eingeben!")
  @Column(nullable = true)
  private String description;

  @ManyToMany
  @JoinTable(
    name = "entry_tags",
    joinColumns = @JoinColumn(name = "entry_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private Set<Tag> tags = new HashSet<>();

  @ManyToOne
  private Category category;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(LocalDateTime checkIn) {
    this.checkIn = checkIn;
  }

  public LocalDateTime getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(LocalDateTime checkOut) {
    this.checkOut = checkOut;
  }

  @JsonIgnore
  @AssertTrue(message = "Ein- sollte vor dem Ausstempeln passieren.")
  public boolean isCheckOutAfterCheckIn() {
    if (checkIn == null || checkOut == null) {
      return true;
    }
    return checkIn.isBefore(checkOut);
  }

  public Set<Tag> getTags() {
    return tags;
  }

  public void setTags(Set<Tag> tags) {
    this.tags = tags;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}
