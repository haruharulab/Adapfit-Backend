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
    public List<Recruitment> searchRecruitment(String jobGroup, String career, String employmentPattern) {
        System.out.println(employmentPattern.length());

        BooleanBuilder builder = new BooleanBuilder();
        if (jobGroup.length() != 0) {
            builder.and(recruitment.jobGroup.stringValue().eq(jobGroup));
        }

        if (career.length() != 0) {
            if (Integer.parseInt(career) == 0) {
                builder.and(recruitment.career.eq(0));
            } else {
                builder.and(recruitment.career.gt(0));
            }
        }

        if (employmentPattern.length() != 0) {
            builder.and(recruitment.employmentPattern.stringValue().eq(employmentPattern));
        }

        return query.selectFrom(recruitment)
                .where(builder)
                .fetch();
    }
}
