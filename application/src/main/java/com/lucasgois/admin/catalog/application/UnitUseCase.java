package com.lucasgois.admin.catalog.application;


public abstract class UnitUseCase <IN, Void> implements Command<IN, Void> {

    public abstract Void execute(IN input);
}
