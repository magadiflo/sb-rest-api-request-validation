package com.magadiflo.app.constraint.customizada;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Tomado del siguiente enlace:
//https://stackoverflow.com/questions/7555201/in-spring-mvc-validation-is-it-possible-to-show-only-one-error-message-per-fiel

@Target(ElementType.FIELD)
@Constraint(validatedBy = StringFieldValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringField {

    String message() default "Datos incorrectos en el campo";

    String messageNotEmpty() default "Campo vacío no permitido";

    String messageLength() default "Longitud del campo incorrecta";

    boolean notEmpty() default false;

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

/**
 * Ejemplo de cómo se debe usar esta anotación en particular,
 * por ejemplo en el campo name:
 *
 * @StringField(notEmpty = true, min = 3, max = 40, messageNotEmpty = "Campo no puede estar vaciitooo", messageLength = "La longitud es 3-40")
 * private String name;
 */