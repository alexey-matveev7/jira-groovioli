package ru.andreymarkelov.atlas.plugins.jira.webwork;

import com.atlassian.jira.config.managedconfiguration.ManagedConfigurationItemService;
import com.atlassian.jira.web.action.admin.customfields.AbstractEditConfigurationItemAction;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import ru.andreymarkelov.atlas.plugins.jira.api.GroovioliFieldConfigService;

import javax.inject.Inject;

public class GroovioliScriptedFieldConfigurationEdit extends AbstractEditConfigurationItemAction{

    private final GroovioliFieldConfigService groovioliFieldConfigService;
    private String script;

    @Inject
    protected GroovioliScriptedFieldConfigurationEdit(@ComponentImport ManagedConfigurationItemService managedConfigurationItemService,
                                                      GroovioliFieldConfigService groovioliFieldConfigService) {
        super(managedConfigurationItemService);
        this.groovioliFieldConfigService = groovioliFieldConfigService;

    }

    protected String doExecute() throws Exception {
        long longFieldConfigId = this.getFieldConfigId();
        if (this.script == null) {
            this.script = groovioliFieldConfigService.getScript(longFieldConfigId);
        } else {
            groovioliFieldConfigService.setScript(longFieldConfigId, this.script);
        }

            return "input";
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
