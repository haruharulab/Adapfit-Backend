package com.harulab.adapfit.domain.recruitment.domain.repository;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.harulab.adapfit.domain.recruitment.domain.QRecruitment.recruitment;

@RequiredArgsConstructor
public class RecruitmentRepositoryImpl implements RecruitmentCustomRepository {

    private final JPAQueryFactory query;

    @Override
    public List<Recruitment> searchRecruitment(String jobGroup, Integer career, String employmentPattern) {
        BooleanBuilder builder = new BooleanBuilder();
        if (jobGroup != null) {
            builder.and(recruitment.jobGroup.stringValue().eq(jobGroup));
        }

        if (career != null) {
            if (career == 0) {
                builder.and(recruitment.career.eq(0));
            } else {
                builder.and(recruitment.career.gt(0));
            }
        }

        if (employmentPattern != null) {
            builder.and(recruitment.employmentPattern.stringValue().eq(employmentPattern));
        }

        return query.selectFrom(recruitment)
                .where(builder)
                .fetch();
    }
}
