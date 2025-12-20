package com.school.school_management.Base.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractCommandExecutor<C extends Command, R extends CommandResult> implements CommandExecutor<C, R> {

    @Autowired
    protected ApplicationEventPublisher eventPublisher;
    @Override
    @Transactional
    public R execute(C command) {
        return this.process(command);
    }
    protected abstract R process(C command);
}
