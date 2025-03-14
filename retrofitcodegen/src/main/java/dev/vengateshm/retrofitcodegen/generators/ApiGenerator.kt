package dev.vengateshm.retrofitcodegen.generators

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeSpec
import dev.vengateshm.retrofitcodegen.models.Config

object ApiGenerator {
    fun generateRetrofitInterface(
        config: Config,
        packageName: String,
        apiInterfaceName: String,
    ): FileSpec {
        val apiInterface =
            TypeSpec.interfaceBuilder(apiInterfaceName)
                .addModifiers(KModifier.PUBLIC)

        config.endpoints.forEach { endPoint ->
            val functionBuilder = FunSpec.builder(endPoint.name)
                .addModifiers(KModifier.ABSTRACT)
                .addModifiers(KModifier.SUSPEND) // ✅ Make function suspend
                .returns(TypeUtils.getType(endPoint.responseModel, packageName))

            val httpAnnotation =
                when (endPoint.method) {
                    "GET" -> "GET"
                    "POST" -> "POST"
                    "PUT" -> "PUT"
                    "DELETE" -> "DELETE"
                    else -> throw IllegalArgumentException("Unsupported HTTP method: ${endPoint.method}")
                }

            functionBuilder.addAnnotation(
                AnnotationSpec.builder(ClassName("retrofit2.http", httpAnnotation))
                    .addMember("%S", endPoint.path)
                    .build(),
            )

            val pathVariableRegex = "\\{(\\w+)}".toRegex()
            val pathVariables = pathVariableRegex.findAll(endPoint.path).map { it.groupValues[1] }

            pathVariables.forEach { pathVariable ->
                functionBuilder.addParameter(
                    ParameterSpec.builder(pathVariable, STRING)
                        .addAnnotation(
                            AnnotationSpec.builder(
                                ClassName("retrofit2.http", "Path")
                            )
                                .addMember("%S", pathVariable) // ✅ Adds @Path("id") annotation
                                .build(),
                        )
                        .build()
                )
            }

            // Add request body for POST or PUT
            if (endPoint.method in listOf("POST", "PUT") && endPoint.requestModel != null) {
                functionBuilder.addParameter(
                    ParameterSpec.builder(
                        "body",
                        TypeUtils.getType(endPoint.requestModel, packageName),
                    ).addAnnotation(ClassName("retrofit2.http", "Body"))
                        .build(),
                )
            }

            apiInterface.addFunction(functionBuilder.build())
        }

        return FileSpec.builder(packageName, apiInterfaceName)
            .addType(apiInterface.build())
            .build()
    }
}