package com.school.school_management.Base.Commands;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

@Service
public class DefaultCommandDispatcher extends CommandDispatcher {
    private final Map<String, CommandExecutor> rawMap;
    public DefaultCommandDispatcher(Map<String, CommandExecutor> rawMap) {
        this.rawMap = rawMap;
    }

    @PostConstruct
    private void setup(){
        if (rawMap != null && !rawMap.isEmpty()) {
            for (CommandExecutor commandExecutor : rawMap.values()) {
                ParameterizedType type = (ParameterizedType)((Class) commandExecutor.getClass().getGenericSuperclass()).getGenericInterfaces()[0];//(ParameterizedType)commandExecutor.getClass().getGenericInterfaces()[0];
                Type command = type.getActualTypeArguments()[0];
                preparedMap.put((Class) command, commandExecutor);
            }
        }
    }

}