package com.harulab.adapfit.domain.recruitment.domain.repository;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.harulab.adapfit.domain.recruitment.domain.QRecruitment.recruitment;

@RequiredArgsConstructor
public class RecruitmentRepositoryImpl implements RecruitmentCustomRepository {

    private final JPAQueryFactory query;

    @Override
    public List<Recruitment> searchRecruitment(String jobGroup, String career, String employmentPattern) {
        return query.selectFrom(recruitment)
                .where(
                        jogGroupEq(jobGroup),
                        careerEq(career),
                        employmentPatternEq(employmentPattern)
                )
                .fetch();
    }

    private BooleanExpression jogGroupEq(String jobGroup) {
        if (jobGroup.length() != 0) {
            return recruitment.jobGroup.stringValue().eq(jobGroup);
        }
        return null;
    }

    private BooleanExpression careerEq(String career) {
        if (career.length() != 0) {
            return Integer.parseInt(career) == 0 ?
                    recruitment.career.eq(0) : recruitment.career.gt(0);
        }
        return null;
    }

    private BooleanExpression employmentPatternEq(String employmentPattern) {
        if (employmentPattern.length() != 0) {
            return recruitment.employmentPattern.stringValue().eq(employmentPattern);
        }
        return null;
    }
}
