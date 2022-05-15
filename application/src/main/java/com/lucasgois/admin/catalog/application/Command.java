package com.lucasgois.admin.catalog.application;

public interface Command <T, K> {
    K execute(T request);
}
