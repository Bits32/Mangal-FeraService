package com.example.mangalfera.repository;

import com.example.mangalfera.model.Profile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomProfileRepositoryImpl implements CustomProfileRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Profile> findMatchingProfiles(Long currentUserId,
                                              Integer minAge,
                                              Integer maxAge,
                                              Integer minHeight,
                                              Integer maxHeight,
                                              String maritalStatus,
                                              String religion,
                                              String caste,
                                              String country,
                                              String state,
                                              String city,
                                              String manglik) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profile> query = cb.createQuery(Profile.class);
        Root<Profile> root = query.from(Profile.class);

        List<Predicate> predicates = new ArrayList<>();

        // Exclude current user
        predicates.add(cb.notEqual(root.get("user").get("id"), currentUserId));

        // Age
        if (minAge != null || maxAge != null) {
            Expression<LocalDate> dob = root.get("dateOfBirth");
            LocalDate today = LocalDate.now();
            if (minAge != null) {
                predicates.add(cb.lessThanOrEqualTo(dob, today.minusYears(minAge)));
            }
            if (maxAge != null) {
                predicates.add(cb.greaterThanOrEqualTo(dob, today.minusYears(maxAge + 1).plusDays(1)));
            }
        }

        // Height
        if (minHeight != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("heightInCm"), minHeight));
        }
        if (maxHeight != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("heightInCm"), maxHeight));
        }

        if (maritalStatus != null) {
            predicates.add(cb.equal(cb.lower(root.get("maritalStatus")), maritalStatus.toLowerCase()));
        }
        if (religion != null) {
            predicates.add(cb.equal(cb.lower(root.get("religion")), religion.toLowerCase()));
        }
        if (caste != null) {
            predicates.add(cb.equal(cb.lower(root.get("caste")), caste.toLowerCase()));
        }
        if (country != null) {
            predicates.add(cb.equal(cb.lower(root.get("country")), country.toLowerCase()));
        }
        if (state != null) {
            predicates.add(cb.equal(cb.lower(root.get("state")), state.toLowerCase()));
        }
        if (city != null) {
            predicates.add(cb.equal(cb.lower(root.get("city")), city.toLowerCase()));
        }
        if (manglik != null) {
            predicates.add(cb.equal(cb.lower(root.get("manglik")), manglik.toLowerCase()));
        }

        query.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Profile> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

}
