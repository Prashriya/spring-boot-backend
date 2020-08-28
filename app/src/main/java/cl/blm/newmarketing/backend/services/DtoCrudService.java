package cl.blm.newmarketing.backend.services;

import java.util.Collection;

import org.springframework.lang.Nullable;

import com.querydsl.core.types.Predicate;

/**
 * An interface for implementing the basic CRUD service operations. Because it
 * extends QueryDslPredicateMapper, it's expected to comply to QueryDSL and
 * accept Predicate objects as filtering conditions.
 * 
 * @author Benjamin La Madrid <bg.lamadrid at gmail.com>
 * 
 * @param <T> The type class of the item.
 * @param <I> The identifier type class of the item.
 */
public interface DtoCrudService<T, I>
    extends QueryDslPredicateMapper {

  /**
   * Inserts and persists an item.
   *
   * @param dto The item to be created.
   *
   * @return The created item, with updated properties (most importantly its ID),
   *         or null if the item could not be created.
   */
  @Nullable
  public T create(T dto);

  /**
   * Queries a paged collection of items.
   *
   * @param pageSize  Number of items per page.
   * @param pageIndex Page index (0-based).
   * @param filters   Filtering conditions
   *
   * @return The requested collection of items. May be zero-sized.
   */
  public Collection<T> read(int pageSize, int pageIndex, @Nullable Predicate filters);

  /**
   * Updates an existing item.
   *
   * @param dto The item to be updated. Its identifying field must be present.
   *
   * @return The saved item, with updated properties, or null if the item was not
   *         found.
   */
  @Nullable
  public T update(T dto);

  /**
   * Updates an existing item.
   *
   * @param dto The item to be updated. Its identifying field may or may not be
   *            present, and can be different from the second method param.
   * @param id  The unique identifier of the item.
   *
   * @return The saved item, with updated properties, or null if the item was not
   *         found.
   */
  @Nullable
  public T update(T dto, I id);

  /**
   * Finds an item by its ID and deletes it.
   *
   * @param id Its unique identifier.
   *
   * @return true on success, false otherwise.
   */
  public boolean delete(I id);

  /**
   * Retrieves an item by its ID.
   *
   * @param id The unique identifier of the item.
   *
   * @return The requested item, or null if it was not found.
   */
  @Nullable
  public T find(I id);
}