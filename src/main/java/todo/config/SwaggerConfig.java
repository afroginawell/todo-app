package todo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置类，用于设置API文档的相关信息。
 * 该类提供了OpenAPI实例，以便生成和展示API文档。
 */
@Configuration
public class SwaggerConfig {
    /**
     * 创建并返回OpenAPI实例，包含API的基本信息和外部文档链接。
     *
     * @return OpenAPI 实例，包含API文档的相关配置信息
     */
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Apis")
                        .contact(new Contact()
                                .name("XGY")
                                .url("https://github.com/afroginawell")
                                .email("1519255512@qq.com"))
                        .description("API文档")
                        .version("v1")
                ).externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/afroginawell/todo-app"));
    }
}
