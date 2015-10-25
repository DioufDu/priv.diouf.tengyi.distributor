package priv.diouf.tengyi.distributor.persistence.repositories;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.exceptions.CommonPersistenceException;

@Transactional
@NoRepositoryBean
public abstract class GeneralJpaRepository<TEntity, TEntity_, TId extends Serializable> extends
SimpleJpaRepository<TEntity, TId> {

	/*
	 * Fields
	 */

	private Type[] genericTypes;
	private ArrayList<SingularAttribute<TEntity, String>> canonicalMetamodelFields;
	protected Set<Class<?>> searchableTypes;
	protected EntityManager sharedEntityManager;

	/*
	 * Constructors
	 */

	public GeneralJpaRepository(Class<TEntity> domainClass, EntityManager sharedEntityManager) {
		super(domainClass, sharedEntityManager);
		this.sharedEntityManager = sharedEntityManager;
	}

	/*
	 * Advanced Features
	 */

	public List<TEntity> basicSearch(final String keyword) {
		return super.findAll(new Specification<TEntity>() {

			@Override
			public Predicate toPredicate(Root<TEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeClause = keyword;
				if (!keyword.startsWith("%")) {
					likeClause = "%" + likeClause;
				}
				if (!keyword.endsWith("%")) {
					likeClause = likeClause + "%";
				}
				// Filter
				ArrayList<Predicate> searchFilters = new ArrayList<Predicate>();
				for (SingularAttribute<TEntity, String> searchField : getCanonicalFields()) {
					searchFilters.add(cb.like(root.get(searchField), likeClause));
				}
				if (searchFilters.size() == 1) {
					return searchFilters.get(0);
				} else {
					Predicate searchPredicate = null;
					for (Predicate searchFilter : searchFilters) {
						if (searchPredicate == null) {
							searchPredicate = searchFilter;
						} else {
							searchPredicate = cb.or(searchPredicate, searchFilter);
						}
					}
					return searchPredicate;
				}
			}
		});
	}

	/*
	 * Enhancements to JPA Repository
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sap.moremobile.management.persistence.repositories.interfaces.GeneralJpaRepository#findAll(org.springframework.data.jpa.domain
	 * .Specification, org.springframework.data.domain.Sort, org.springframework.data.domain.Pageable)
	 */
	public Page<TEntity> findAll(Specification<TEntity> spec, Sort sort, Pageable pageable) {
		TypedQuery<TEntity> query = getQuery(spec, pageable);
		return pageable == null ? new PageImpl<TEntity>(getQuery(spec, sort).getResultList()) : readPage(query,
				pageable, spec);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sap.moremobile.management.persistence.repositories.interfaces.GeneralJpaRepository#save(java.lang.Object[])
	 */

	@SuppressWarnings("unchecked")
	public List<TEntity> save(TEntity... entities) {
		return this.save(Arrays.asList(entities));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sap.moremobile.management.persistence.repositories.interfaces.GeneralJpaRepository#saveAndFlush(java.lang.Object[])
	 */

	@SuppressWarnings("unchecked")
	public List<TEntity> saveAndFlush(TEntity... entities) {
		List<TEntity> result = this.save(Arrays.asList(entities));
		this.flush();
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sap.moremobile.management.persistence.repositories.interfaces.GeneralJpaRepository#saveAndFlush(java.lang.Iterable)
	 */

	public List<TEntity> saveAndFlush(Iterable<TEntity> entities) {
		List<TEntity> result = this.save(entities);
		this.flush();
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sap.moremobile.management.persistence.repositories.interfaces.GeneralJpaRepository#refresh(java.io.Serializable)
	 */

	public void refresh(TId id) {
		sharedEntityManager.refresh(super.findOne(id));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sap.moremobile.management.persistence.repositories.interfaces.GeneralJpaRepository#refresh(java.lang.Object)
	 */

	public void refresh(TEntity t) {
		sharedEntityManager.refresh(t);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sap.moremobile.management.persistence.repositories.interfaces.GeneralJpaRepository#refresh(java.io.Serializable[])
	 */

	@SuppressWarnings("unchecked")
	public void refresh(TId... ids) {
		for (TId id : ids) {
			refresh(id);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sap.moremobile.management.persistence.repositories.interfaces.GeneralJpaRepository#refresh(java.lang.Object[])
	 */

	@SuppressWarnings("unchecked")
	public void refresh(TEntity... entities) {
		for (TEntity entity : entities) {
			refresh(entity);
		}
	}

	/*
	 * Private & Protected Methods
	 */

	@SuppressWarnings("unchecked")
	protected Class<TEntity> getEntityType() {
		return (Class<TEntity>) this.getGenericTypes()[0];
	}

	@SuppressWarnings("unchecked")
	protected Class<TEntity_> getCanonicalMetadataType() {
		return (Class<TEntity_>) this.getGenericTypes()[1];
	}

	@SuppressWarnings("unchecked")
	protected Class<TId> getIdType() {
		return (Class<TId>) this.getGenericTypes()[2];
	}

	protected ArrayList<SingularAttribute<TEntity, String>> getAllCanonicalFields() {
		return this.getCanonicalFields(null);
	}

	protected ArrayList<SingularAttribute<TEntity, String>> getCanonicalFields() {
		return this.getCanonicalFields(getSearchableTypes());
	}

	@SuppressWarnings("unchecked")
	protected ArrayList<SingularAttribute<TEntity, String>> getCanonicalFields(Set<Class<?>> searchableTypes) {
		if (this.canonicalMetamodelFields == null)
			// TODO: Refactor with perfect lock
			synchronized (this) {
				Field[] canonicalMetadataFields = this.getCanonicalMetadataType().getDeclaredFields();
				this.canonicalMetamodelFields = new ArrayList<SingularAttribute<TEntity, String>>(
						canonicalMetadataFields.length);
				for (Field canonicalModelfield : canonicalMetadataFields) {
					if (Modifier.isStatic(canonicalModelfield.getModifiers())
							&& Modifier.isPublic(canonicalModelfield.getModifiers())
							&& Modifier.isVolatile(canonicalModelfield.getModifiers())
							&& canonicalModelfield.getType().isAssignableFrom(SingularAttribute.class)) {
						try {
							// Try search the field with same name in entity type
							Field entityField = this.getEntityType().getDeclaredField(canonicalModelfield.getName());
							if (searchableTypes == null || searchableTypes.isEmpty()) {
								canonicalMetamodelFields.add((SingularAttribute<TEntity, String>) (canonicalModelfield
										.get(null)));
							} else {
								for (Class<?> searchableType : getSearchableTypes()) {
									if (searchableType.isAssignableFrom(entityField.getType())) {
										canonicalMetamodelFields
												.add((SingularAttribute<TEntity, String>) (canonicalModelfield
														.get(null)));
									}
								}
							}
						} catch (NoSuchFieldException | SecurityException ex) {
							// Continue to the next field deal to the security reason.
							ex.printStackTrace();
							continue;
						} catch (ClassCastException ex) {
							continue;
						} catch (IllegalArgumentException | IllegalAccessException ex) {
							throw new CommonPersistenceException(
									"Invalid canonical metamodel for basic query, please check it.", ex);
						}
					}
				}
			}
		return this.canonicalMetamodelFields;
	}

	protected Set<Class<?>> getSearchableTypes() {
		if (this.searchableTypes == null) {
			synchronized (this) {
				this.searchableTypes = new HashSet<Class<?>>();
				this.searchableTypes.add(String.class);
				this.searchableTypes.add(Calendar.class);
				this.searchableTypes.add(Number.class);
				this.searchableTypes.add(Enum.class);
			}
		}
		return this.searchableTypes;
	}

	private Type[] getGenericTypes() {
		// TODO: Refactor with perfect lock
		if (this.genericTypes == null) {
			synchronized (this) {
				this.genericTypes = ((ParameterizedType) this.getClass().getGenericSuperclass())
						.getActualTypeArguments();
			}
		}
		return this.genericTypes;
	}
}
