package priv.diouf.tengyi.distributor.persistence.repositories.account;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.common.models.enums.AccountType;
import priv.diouf.tengyi.distributor.persistence.models.Modification;
import priv.diouf.tengyi.distributor.persistence.models.Modification_;
import priv.diouf.tengyi.distributor.persistence.models.account.Account;
import priv.diouf.tengyi.distributor.persistence.models.account.Account_;
import priv.diouf.tengyi.distributor.persistence.models.account.Address_;
import priv.diouf.tengyi.distributor.persistence.models.account.Contact_;
import priv.diouf.tengyi.distributor.persistence.models.photo.AvatarPhotoGroup;
import priv.diouf.tengyi.distributor.persistence.models.photo.AvatarPhotoGroup_;
import priv.diouf.tengyi.distributor.persistence.repositories.GeneralJpaRepository;
import priv.diouf.tengyi.distributor.services.criterias.account.AccountAdvancedSearchCriteria;
import priv.diouf.tengyi.distributor.services.criterias.account.AccountBasicSearchCriteria;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
public class AccountRepository extends GeneralJpaRepository<Account, Account_, Long> {

	/*
	 * Constructors
	 */

	@Autowired
	public AccountRepository(EntityManager entityManager) {
		super(Account.class, entityManager);
	}

	/*
	 * Actions
	 */

	@Transactional(readOnly = true)
	public Map<AccountType, Long> generateStatistics() {
		// Parameters
		CriteriaBuilder cb = this.sharedEntityManager.getCriteriaBuilder();
		// Query for account count
		CriteriaQuery<Tuple> queryAccountCount = cb.createTupleQuery();
		Root<Account> rootAccountCount = queryAccountCount.from(Account.class);
		queryAccountCount
				// Group by "Account Type"
				.groupBy(rootAccountCount.get(Account_.type))
				// Select
				.select(cb.tuple(rootAccountCount.get(Account_.type), cb.count(rootAccountCount)));
		List<Tuple> queryAccountCountResult = this.sharedEntityManager.createQuery(queryAccountCount).getResultList();
		// Result
		TreeMap<AccountType, Long> futureAccountStatistics = new TreeMap<AccountType, Long>();
		for (Tuple tuple : queryAccountCountResult) {
			futureAccountStatistics.put((AccountType) tuple.get(0), (Long) tuple.get(1));
		}
		return futureAccountStatistics;
	}

	@Transactional(readOnly = true)
	public Account findOneWithAllDetails(final long id) {
		return super.findOne(new Specification<Account>() {
			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchInfoNode(root);
				return cb.equal(root.get(Account_.id), id);
			}
		});
	}

	@Transactional(readOnly = true)
	public Page<Account> findAll(final AccountBasicSearchCriteria criteria, final PageRequest pageRequest) {
		return super.findAll(new Specification<Account>() {
			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchInfoNode(root);
				// TODO
				if (criteria == null || StringUtils.isBlank(criteria.getKeyword())) {
					return null;
				}
				return cb.and(generateFuzzyPredicates(cb,
						// keywords
						criteria.getKeyword(),
						// Fields - Scalar Fields
						root.get(Account_.name), root.get(Account_.loginId), root.get(Account_.title),
						// Fields - Address
						root.get(Account_.address).get(Address_.overall),
						// Fields - Contact
						root.get(Account_.contact).get(Contact_.cellphone), root.get(Account_.contact).get(Contact_.telephone), root.get(
								Account_.contact).get(Contact_.fax), root.get(Account_.contact).get(Contact_.email), root.get(
										Account_.contact).get(Contact_.alternativePhone)));
			}
		}, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<Account> findAll(final AccountAdvancedSearchCriteria criteria, final PageRequest pageRequest) {
		return super.findAll(new Specification<Account>() {
			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				fetchInfoNode(root);
				// TODO
				return null;
			}
		}, pageRequest);
	}

	/*
	 * Private & Protected Methods
	 */

	private void fetchInfoNode(Root<Account> root) {
		root.fetch(Account_.address);
		root.fetch(Account_.contact);

		Fetch<Account, AvatarPhotoGroup> fetchAvatar = root.fetch(Account_.avatarPhotoGroup);
		fetchAvatar.fetch(AvatarPhotoGroup_.thumbnailPhoto);
		// fetchAvatar.fetch(AvatarPhotoGroup_.standardPhoto);
		fetchAvatar.fetch(AvatarPhotoGroup_.fullScreenPhoto);
		// fetchAvatar.fetch(AvatarPhotoGroup_.originalPhoto);
		Fetch<Account, Modification> fetchModification = root.fetch(Account_.modification);
		fetchModification.fetch(Modification_.createBy);
		fetchModification.fetch(Modification_.updateBy);
	}

	@SafeVarargs
	private final Predicate[] generateFuzzyPredicates(CriteriaBuilder cb, String keyword, Path<String>... fields) {
		if (fields == null || fields.length == 0 || StringUtils.isBlank(keyword)) {
			return new Predicate[1];
		}
		Predicate[] fuzzyPredicates = new Predicate[fields.length];
		for (int idx = 0; idx < fields.length; idx++) {
			fuzzyPredicates[idx] = cb.like(fields[idx], String.format("%%s%", keyword));
		}
		return fuzzyPredicates;
	}
}
