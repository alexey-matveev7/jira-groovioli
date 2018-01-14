package ru.andreymarkelov.atlas.plugins.jira.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.StringLength;
import net.java.ao.schema.Table;

@Preload
@Table("groovioliconfig")
public interface PluginConfigurationData extends Entity {
    @NotNull
    @StringLength(255)
    String getName();

    void setName(String var1);

    @StringLength(-1)
    String getValue();

    void setValue(String var1);

    @StringLength(-1)
    String getDefaultValue();

    void setDefaultValue(String var1);
}
