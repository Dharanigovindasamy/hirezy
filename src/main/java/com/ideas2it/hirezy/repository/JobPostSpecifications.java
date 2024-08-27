package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.JobPost;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * This class provides specifications for querying job posts based on their state.
 * The `hasState` method returns a specification that filters job posts by the specified state.
 * If the `state` parameter is null, no filtering is applied.
 * @Author kishore
 */
public class JobPostSpecifications {
    public static Specification<JobPost> hasState(String state) {
        return (root, query, criteriaBuilder) ->
                state == null ? null : criteriaBuilder.equal(root.get("location").get("state"), state);
    }

    public static Specification<JobPost> hasCity(String city) {
        return (root, query, criteriaBuilder) ->
                city == null ? null : criteriaBuilder.equal(root.get("location").get("city"), city);
    }

    public static Specification<JobPost> hasJobCategoryName(String jobCategoryName) {
        return (root, query, criteriaBuilder) ->
                jobCategoryName == null ? null : criteriaBuilder.like(root.get("jobCategory").get("name"), "%" + jobCategoryName + "%");
    }

    public static Specification<JobPost> hasJobSubcategoryName(String jobSubcategoryName) {
        return (root, query, criteriaBuilder) ->
                jobSubcategoryName == null ? null : criteriaBuilder.like(root.get("jobSubcategory").get("name"), "%" + jobSubcategoryName + "%");
    }

    public static Specification<JobPost> hasCompanyName(String companyName) {
        return (root, query, criteriaBuilder) ->
                companyName == null ? null : criteriaBuilder.like(root.get("employer").get("companyName"), "%" + companyName + "%");
    }

    public static Specification<JobPost> hasCompanyType(String companyType) {
        return (root, query, criteriaBuilder) ->
                companyType == null ? null : criteriaBuilder.like(root.get("employer").get("companyType"), "%" + companyType + "%");
    }

    public static Specification<JobPost> hasIndustryType(String industryType) {
        return (root, query, criteriaBuilder) ->
                industryType == null ? null : criteriaBuilder.like(root.get("employer").get("industryType"), "%" + industryType + "%");
    }

    public static Specification<JobPost> hasExperience(Integer experience) {
        return (root, query, criteriaBuilder) ->
                experience == null ? null : criteriaBuilder.equal(root.get("experience"), experience);
    }

    public static  Specification<JobPost> hasKeySkills(List<String> keySkills) {
        return (root, query, criteriabuilder) -> {
            if (keySkills == null || keySkills.isEmpty()) {
                return null;
            }
            Predicate[] predicates = keySkills.stream()
                    .map(skill->criteriabuilder.isMember(skill,root.get("keySkills")))
                    .toArray(Predicate[]::new);
            return criteriabuilder.or(predicates);
        };
    }
}
