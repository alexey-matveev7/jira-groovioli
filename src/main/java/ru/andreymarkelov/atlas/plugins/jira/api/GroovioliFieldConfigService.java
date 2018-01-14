package ru.andreymarkelov.atlas.plugins.jira.api;

public interface GroovioliFieldConfigService {
    void setScript(long fieldConfigId, String code);
    String getScript(long fieldConfigId);
}
