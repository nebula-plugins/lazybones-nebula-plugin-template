def params = [:]

params.description = ask('Enter your plugin\'s description: ', '', 'description')
def pluginName = ask('Enter the plugin name: ', '', 'pluginname')
def packageName = ask('Enter the package name you want: ', '', 'package')
def pluginClass = ask('Enter the class name of the plugin: ', '', 'pluginclass')
params.devName = ask('Name to add to developers block: ', '', 'name')
params.githubId = ask('Github id: ')
params.email = ask('Email address: ')
params.timezone = ask('Timezone, e.g -8 for PST: ', '0', 'timezone')

params.projectName = targetDir
params.year = Calendar.getInstance().get(Calendar.YEAR)

processTemplates '*.gradle', params

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

def sourceDir = new File(targetDir, "src/main/groovy/${packageName.replaceAll(/\./, '/')}")
sourceDir.mkdirs()
def pluginSource = new File(sourceDir, "${pluginClass}.groovy")
pluginSource.text = """\
${licenseText}
package ${packageName}

import org.gradle.api.Plugin
import org.gradle.api.Project

class ${pluginClass} implements Plugin<Project> {
    @Override
    void apply(Project project) {
    }
}
"""

def resources = new File(targetDir,'src/main/resources/META-INF/gradle-plugins')
resources.mkdirs()
def propertyFile = new File(resources, "${pluginName}.properties")
propertyFile.text = """\
implementation-class=${packageName}.${pluginClass}
"""

def testDir = new File(targetDir, "src/test/groovy/${packageName.replaceAll(/\./, '/')}")
testDir.mkdirs()
def pluginTest = new File(testDir, "${pluginClass}Spec.groovy")
pluginTest.text = """\
${licenseText}
package ${packageName}

import nebula.test.ProjectSpec

class ${pluginClass}Spec extends ProjectSpec {
    @Override
    void getPluginName() {
        '${pluginName}'
    }
}
"""
