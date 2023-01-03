package com.harulab.adapfit.domain.recruitment.domain.repository;

import com.harulab.adapfit.domain.position.domain.Position;
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
    public List<Recruitment> searchRecruitment(Position position, String career, String employmentPattern) {
        return query.selectFrom(recruitment)
                .where(
                        positionEq(position),
                        careerEq(career),
                        employmentPatternEq(employmentPattern)
                )
                .orderBy(recruitment.id.desc())
                .fetch();
    }

    private BooleanExpression positionEq(Position position) {
        if (position != null) return recruitment.position.eq(position);
        return null;
    }

    private BooleanExpression careerEq(String career) {
        if (career.length() != 0) {
            return recruitment.career.stringValue().eq(career);
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
