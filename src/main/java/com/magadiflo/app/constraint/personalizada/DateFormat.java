package com.magadiflo.app.constraint.personalizada;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//Tomado de: https://examples.javacodegeeks.com/spring-boot-bean-validation-example/

@Target({ElementType.FIELD}) //Declara que esta restricción se puede utilizar en los campos de clase.
@Retention(RetentionPolicy.RUNTIME) //Indica que se trata de una anotación en tiempo de ejecución.
@Constraint(validatedBy = DateFormatValidator.class) //Especifica la clase que realizará la validación.
@Documented //Hace que este tipo anotado se incluya al generar documentación de Javadoc.
public @interface DateFormat {

    //El tipo anotado declara tres métodos predeterminados requeridos por la API de validación de Bean.

    String message() default "El formato de fecha debería ser yyyy-MM-dd"; //Nos permite especificar el mensaje de error predeterminado que se devuelve si la validación falla, en nuestro caso, "El formato de fecha debería ser yyyy-MM-dd".

    Class<?>[] groups() default { }; //Permite la especificación de grupos de validación  – e.g., @Age(groups=MALE).

    Class<? extends Payload>[] payload() default { }; //Se utiliza para transmitir información de metadatos

}
