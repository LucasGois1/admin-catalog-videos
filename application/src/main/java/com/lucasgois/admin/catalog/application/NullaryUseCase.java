package com.lucasgois.admin.catalog.application;

public abstract class NullaryUseCase <OUT> implements Command<Void ,OUT> {

    public abstract OUT execute();
}
