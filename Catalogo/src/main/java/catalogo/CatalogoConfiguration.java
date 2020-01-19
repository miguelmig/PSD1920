package catalogo;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class CatalogoConfiguration extends Configuration {
    @NotEmpty
    public String template;

    public String defaultName = "Stranger";
}

