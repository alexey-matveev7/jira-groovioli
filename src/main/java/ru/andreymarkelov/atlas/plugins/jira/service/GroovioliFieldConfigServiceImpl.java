package ru.andreymarkelov.atlas.plugins.jira.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import ru.andreymarkelov.atlas.plugins.jira.ao.PluginConfigurationData;
import ru.andreymarkelov.atlas.plugins.jira.api.GroovioliFieldConfigService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named
public class GroovioliFieldConfigServiceImpl implements GroovioliFieldConfigService {

    private final ActiveObjects ao;

    @Inject
    public GroovioliFieldConfigServiceImpl(@ComponentImport ActiveObjects ao) {
        this.ao = ao;
    }

    @Override
    public void setScript(long fieldConfigId, String code) {
        this.setConfigurationData(getScriptConfigKey(fieldConfigId), code);
    }

    @Override
    public String getScript(long fieldConfigId) {
        return this.getConfigurationData(getScriptConfigKey(fieldConfigId));
    }

    public void setConfigurationData(String configName, final String configValue) {
        this.ao.executeInTransaction(() -> {
            PluginConfigurationData previousValue;
            if ((previousValue = this.getByConfigKey(configName)) == null) {
                Map<String, Object> fields = new HashMap();
                fields.put("NAME", configName);
                fields.put("DEFAULT_VALUE", configValue);
                PluginConfigurationData value = (PluginConfigurationData)this.ao.create(PluginConfigurationData.class, fields);
                value.save();
            } else {
                previousValue.setDefaultValue(configValue);
                previousValue.save();
            }

            return null;
        });

    }

    private PluginConfigurationData getByConfigKey(String configKey) {
        PluginConfigurationData[] ents = (PluginConfigurationData[])this.ao.find(PluginConfigurationData.class, "NAME = ?", new Object[]{configKey});
        return ents != null && ents.length > 0 ? ents[0] : null;
    }


    public String getConfigurationData(String configName) {
        PluginConfigurationData v = this.getByConfigKey(configName);
        if (v != null) {
            return v.getValue() != null ? v.getValue() : v.getDefaultValue();
        } else {
            return null;
        }
    }

    private String getScriptConfigKey(long fieldConfigId) {
        return "groovioliscript." + fieldConfigId;
    }


}
