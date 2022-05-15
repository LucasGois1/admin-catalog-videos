package com.lucasgois.admin.catalog.domain.shared.pagination;

import java.util.List;

public record Pagination<T>(
    int currentPage,
    int perPage,
    long totalPages,
    List<T> items
    ){}
