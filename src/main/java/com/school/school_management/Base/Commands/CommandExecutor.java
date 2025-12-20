package com.school.school_management.Base.Commands;

public interface CommandExecutor<C extends Command, R extends CommandResult> {
    R execute(C command);
}
