package com.task.manager.modules.required.helpers;

import com.task.manager.modules.required.pagination.SearchQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


    public abstract class GatewayDefault <T> {
        protected PageRequest page(final SearchQuery aQuery){
            return PageRequest.of(
                    aQuery.page(),
                    aQuery.perPage(),
                    Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
            );
        }
    }
