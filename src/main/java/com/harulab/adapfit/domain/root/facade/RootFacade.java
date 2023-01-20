package com.harulab.adapfit.domain.root.facade;

import com.harulab.adapfit.domain.root.domain.Root;
import com.harulab.adapfit.domain.root.domain.repository.RootRepository;
import com.harulab.adapfit.domain.log.global.exception.AdminNotFoundException;
import com.harulab.adapfit.domain.log.global.utils.RootUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RootFacade {

    private final RootRepository rootRepository;

    public Root getCurrentRoot() {
        return rootRepository.findByAuthId(RootUtil.getCurrentUser().getUsername())
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }
}
