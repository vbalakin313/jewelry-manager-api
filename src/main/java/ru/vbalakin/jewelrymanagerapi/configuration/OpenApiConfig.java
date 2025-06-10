package ru.vbalakin.jewelrymanagerapi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Jewelry Manager API",
                description = "API for managing jewelry and precious metals",
                version = "1.0.0",
                contact = @Contact(
                        name = "Balakin Vladimir",
                        email = "balakinvv00313@gmail.com"
                )
        )
)

public class OpenApiConfig {
}
