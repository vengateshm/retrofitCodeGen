package dev.vengateshm.retrofitcodegen

import dev.vengateshm.retrofitcodegen.generators.CodeGenerator
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class RetrofitCodeGenPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension =
            project.extensions.create("retrofitCodeGenConfig", RetrofitCodeGenExtension::class.java)

        project.tasks.register("retrofitCodeGenSync") { task ->
            task.doLast {
                initCodeGeneration(project, extension)
            }
        }
    }

    private fun initCodeGeneration(
        project: Project,
        extension: RetrofitCodeGenExtension,
    ) {
        val configFilesFolder = File(extension.path)
        if (configFilesFolder.exists() && configFilesFolder.isDirectory) {
            val configFiles =
                configFilesFolder.listFiles { file ->
                    file.name.matches(Regex("^[a-zA-Z0-9_]+_config\\.json$"))
                }
            println("RetrofitCodeGen: Found ${configFiles?.size ?: 0} valid config files in $configFilesFolder")

            configFiles?.forEach { file ->
                val json = file.readText()
                val dirName = file.name.substringBefore("_config.json")
                try {
                    val config = parseConfig(json)
                    CodeGenerator.generateCode(project, config, extension.packageAlias, dirName)
                } catch (e: Exception) {
                    println("RetrofitCodeGen: Error processing file ${file.name}: ${e.message}")
                }
            }
        } else {
            println("RetrofitCodeGen: Config directory $configFilesFolder not found.")
        }
    }
}
