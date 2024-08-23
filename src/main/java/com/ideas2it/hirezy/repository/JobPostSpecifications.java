package com.ideas2it.hirezy.repository;

import org.springframework.data.jpa.domain.Specification;

/**
 * This class provides specifications for querying job posts based on their state.
 * The `hasState` method returns a specification that filters job posts by the specified state.
 * If the `state` parameter is null, no filtering is applied.
 * @Author kishore
 */
public class JobPostSpecifications {
    public static Specification<Object> hasState(String state) {
        return (root, query, criteriaBuilder) ->
                state == null ? null : criteriaBuilder.equal(root.get("location").get("state"), state);
    }

    public static Specification<Object> hasCity(String city) {
        return (root, query, criteriaBuilder) ->
                city == null ? null : criteriaBuilder.equal(root.get("location").get("city"), city);
    }

    public static Specification<Object> hasJobCategoryId(Long jobCategoryId) {
        return (root, query, criteriaBuilder) ->
                jobCategoryId == null ? null : criteriaBuilder.equal(root.get("jobCategory").get("id"), jobCategoryId);
    }

    public static Specification<Object> hasJobCategoryName(String jobCategoryName) {
        return (root, query, criteriaBuilder) ->
                jobCategoryName == null ? null : criteriaBuilder.like(root.get("jobCategory").get("name"), "%" + jobCategoryName + "%");
    }

    public static Specification<Object> hasJobSubcategoryId(Long jobSubcategoryId) {
        return (root, query, criteriaBuilder) ->
                jobSubcategoryId == null ? null : criteriaBuilder.equal(root.get("jobSubcategory").get("id"), jobSubcategoryId);
    }

    public static Specification<Object> hasJobSubcategoryName(String jobSubcategoryName) {
        return (root, query, criteriaBuilder) ->
                jobSubcategoryName == null ? null : criteriaBuilder.like(root.get("jobSubcategory").get("name"), "%" + jobSubcategoryName + "%");
    }

    public static Specification<Object> hasCompanyName(String companyName) {
        return (root, query, criteriaBuilder) ->
                companyName == null ? null : criteriaBuilder.like(root.get("employer").get("companyName"), "%" + companyName + "%");
    }

    public static Specification<Object> hasCompanyType(String companyType) {
        return (root, query, criteriaBuilder) ->
                companyType == null ? null : criteriaBuilder.like(root.get("employer").get("companyType"), "%" + companyType + "%");
    }

    public static Specification<Object> hasIndustryType(String industryType) {
        return (root, query, criteriaBuilder) ->
                industryType == null ? null : criteriaBuilder.like(root.get("employer").get("industryType"), "%" + industryType + "%");
    }
}
