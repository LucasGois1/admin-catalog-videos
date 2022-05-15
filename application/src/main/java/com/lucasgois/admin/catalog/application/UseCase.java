package com.lucasgois.admin.catalog.application;

public abstract class UseCase<IN, OUT> implements Command <IN, OUT>{

    public abstract OUT execute(IN input);
}
