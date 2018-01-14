package ru.andreymarkelov.atlas.plugins.jira.customfields;

import com.atlassian.jira.issue.customfields.impl.CalculatedCFType;
import com.atlassian.jira.issue.fields.config.FieldConfigItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.issue.customfields.impl.TextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import ru.andreymarkelov.atlas.plugins.jira.groovioli.manager.ScriptManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class GroovioliScriptedField extends CalculatedCFType<Object, Object> {
    private static final Logger log = LoggerFactory.getLogger(GroovioliScriptedField.class);
    private final ScriptManager scriptManager;

    public GroovioliScriptedField(ScriptManager scriptManager) {
        super();
        this.scriptManager = scriptManager;

    }

    @Override
    public String getStringFromSingularObject(Object o) {
        return o != null ? o.toString() : "";
    }

    @Override
    public Object getSingularObjectFromString(String s) throws FieldValidationException {
        return s;
    }

    @Nullable
    @Override
    public Object getValueFromIssue(CustomField field, Issue issue) {
        if (issue == null) {
            log.error("Cannot calculate Groovioli field for null issue");
            return null;
        } else if (field == null) {
            log.error("Cannot calculate Groovioli field for unknown field");
            return null;
        } else {
            FieldConfig config = field.getRelevantConfig(issue);
            if (config == null) {
                log.debug(String.format("No config found for field >>%s<< in issue >>%s<< ", field.getName(), issue.getKey()));
                return null;
            } else {
              //  String configuredScript = this.configService.getScript(config.getId().longValue());
                return "field value";
            }
        }
    }

    public List<FieldConfigItemType> getConfigurationItemTypes() {
        List<FieldConfigItemType> list = super.getConfigurationItemTypes();
        list.add(new GroovioliScriptedFieldConfigItemType());
        return list;
    }


}