package ru.andreymarkelov.atlas.plugins.jira.customfields;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.config.FieldConfigItemType;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;

public class GroovioliScriptedFieldConfigItemType implements FieldConfigItemType {
    @Override
    public String getDisplayName() {
        return "Groovioli Script";
    }

    @Override
    public String getDisplayNameKey() {
        return "Groovioli Script";
    }

    @Override
    public String getViewHtml(FieldConfig fieldConfig, FieldLayoutItem fieldLayoutItem) {
        return null;
    }

    @Override
    public String getObjectKey() {
        return "scriptedfieldconfig";
    }

    @Override
    public Object getConfigurationObject(Issue issue, FieldConfig fieldConfig) {
        return null;
    }

    @Override
    public String getBaseEditUrl() {
        return "EditGroovioliFieldConfig.jspa";
    }
}
