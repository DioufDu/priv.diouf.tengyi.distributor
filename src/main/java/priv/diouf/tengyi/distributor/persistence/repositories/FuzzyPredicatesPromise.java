package priv.diouf.tengyi.distributor.persistence.repositories;

import javax.persistence.criteria.Predicate;

public abstract class FuzzyPredicatesPromise {

	public abstract boolean isValid();

	public abstract Predicate generate();

}
