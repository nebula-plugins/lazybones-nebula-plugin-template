def params = [:]

params.projectName = targetDir
params.year = Calendar.getInstance().get(Calendar.YEAR)

def guessPluginName = params.projectName.replaceAll(/-plugin/, '')
def shortPlugin = guessPluginName.replaceAll(/(nebula|gradle)/, '')
def guessPackageName = 'nebula.plugin.' + shortPlugin.replaceAll(/-/, '')
def guessPluginClass = shortPlugin.capitalize().replaceAll(/-(.)/) { it[1].toUpperCase() } + 'Plugin'

params.description = ask('Enter your plugin\'s description: ', '', 'description')
params.pluginName = ask("Enter the name for apply plugin: (${guessPluginName}): ", guessPluginName, 'pluginname')
params.packageName = ask("Enter the package name you want (${guessPackageName}): ", guessPackageName, 'package')
params.pluginClass = ask("Enter the class name of the plugin (${guessPluginClass}): ", guessPluginClass, 'pluginclass')
params.devName = ask('Name to add to developers block: ', '', 'name')
params.githubId = ask('Github id: ', '', 'github')
params.email = ask('Email address: ', '', 'email')
params.timezone = ask('Timezone, e.g -8 for PST: ', '0', 'timezone')

processTemplates '*.gradle', params
processTemplates 'README.md', params

def licenseText = '''\
/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */'''

def sourceDir = new File(targetDir, "src/main/groovy/${params.packageName.replaceAll(/\./, '/')}")
sourceDir.mkdirs()
def pluginSource = new File(sourceDir, "${params.pluginClass}.groovy")
pluginSource.text = """\
${licenseText}
package ${params.packageName}

import org.gradle.api.Plugin
import org.gradle.api.Project

class ${params.pluginClass} implements Plugin<Project> {
    @Override
    void apply(Project project) {
    }
}
"""

def resources = new File(targetDir,'src/main/resources/META-INF/gradle-plugins')
resources.mkdirs()
def propertyFile = new File(resources, "${params.pluginName}.properties")
propertyFile.text = """\
implementation-class=${params.packageName}.${params.pluginClass}
"""

def testDir = new File(targetDir, "src/test/groovy/${params.packageName.replaceAll(/\./, '/')}")
testDir.mkdirs()
def pluginTest = new File(testDir, "${params.pluginClass}Spec.groovy")
pluginTest.text = """\
${licenseText}
package ${params.packageName}

import nebula.test.ProjectSpec

class ${params.pluginClass}Spec extends ProjectSpec {
    @Override
    void getPluginName() {
        '${params.pluginName}'
    }
}
"""
