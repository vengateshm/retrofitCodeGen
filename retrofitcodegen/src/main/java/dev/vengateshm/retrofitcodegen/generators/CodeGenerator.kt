package dev.vengateshm.retrofitcodegen.generators

import dev.vengateshm.retrofitcodegen.models.Config
import org.gradle.api.Project

object CodeGenerator {
    fun generateCode(
        project: Project,
        config: Config,
        packageName: String,
        dirName: String,
    ) {
        val outputDir = project.layout.buildDirectory.dir("generated/retrofitCodeGen")
            .get().asFile.also { it.mkdirs() }
        val fullPackageName = "$packageName.$dirName"

        config.models.forEach { (modelName, properties) ->
            val modelFile = ModelGenerator.generateModelClass(modelName, properties, fullPackageName)
            modelFile.writeTo(outputDir)
        }

        val apiServiceName = "${dirName.replaceFirstChar { it.uppercase() }}ApiService"
        val retrofitFile = ApiGenerator.generateRetrofitInterface(config, fullPackageName, apiServiceName)
        retrofitFile.writeTo(outputDir)

        println("RetrofitCodeGen: Code generation complete!")
    }
}