package com.magadiflo.app.constraint.compuesta;

import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Email;
import java.lang.annotation.*;

//Anotación compuesta por dos anotaciones:
//@Email
//@Length

/**
 * Esta anotación se tomó de:
 * https://stackoverflow.com/questions/7555201/in-spring-mvc-validation-is-it-possible-to-show-only-one-error-message-per-fiel
 */

/**
 * @ReportAsSingleViolation, Una anotación de restricción que aloje esta anotación
 * devolverá el informe de error de anotación compuesta si alguna de las anotaciones
 * de composición falla.
 * Los informes de error de cada restricción de composición individual se ignoran.
 *
 * Nota: La evaluación de las restricciones compuestas se detiene en el primer error
 * de validación en caso de que la restricción de composición esté anotada
 * con @ReportAsSingleViolation.
 *
 * Atención: Esta anotación puede ser usada en el campo email de la clase User
 * reemplazando a las dos anotaciones de dicho campo @Email y @Length, ya que
 * en esta anotación ya los implementando y mostrando un mensaje genérico
 * para ambas validaciones
 */

@Constraint(validatedBy = {})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@Email(message = "Email is not valid") //Este mensaje será ignorado
@Length(min = 5, max = 50, message = "Email address must have 5-50 characteres") //Este mensaje será ignorado
@Documented
public @interface StringEmail {

    String message() default "Hay un error en el campo email"; //Este será el mensaje a retornar si falla @Email o @Length

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

}
