Lazybones Nebula-Plugin Template
================================

### Instructions

1. Install [gvm](http://gvmtool.net/) : `curl -s get.gvmtool.net | bash`
2. Install lazybones : `gvm install lazybones`
3. Edit `~/.lazybones/config.groovy` and paste this into it:
``
bintrayRepositories = [
    "nebula/templates",
    "pledbrook/lazybones-templates"
]
```
7. `lazybones create nebula-plugin <name-of-project>` or if you know the answers `lazybones create nebula-plugin -Pdescription='example description' -Ppluginname='gradle-example' -Ppackage=nebula.plugin.example -Ppluginclass=ExamplePlugin -Pname='First Last' -Pgithub=<github id> -Pemail='your@emailaddress' -Ptimezone='<integer timezone -8 is PST>' <name-of-project>`
8. Answer the prompted questions or provide values via command line
9. `cd <name-of-project>`
10. `git init`
11. `git remote add origin <github url>`
12. Go forth and develop your plugin.

### Instructions for developing

1. Install [gvm](http://gvmtool.net/) : `curl -s get.gvmtool.net | bash`
2. Install lazybones : `gvm install lazybones`
3. Clone our template: `git clone https://github.com/nebula-plugins/lazybones-nebula-plugin-template.git`
4. `cd lazybones-nebula-plugin-template`
5. Install the template: `./gradlew installTemplateNebulaPlugin`
6. Change directory to where you want to create the plugin project
7. `lazybones create nebula-plugin 0.1.0-SNAPSHOT <name-of-project>` or if you know the answers `lazybones create nebula-plugin 0.1.0-SNAPSHOT -Pdescription='example description' -Ppluginname='gradle-example' -Ppackage=nebula.plugin.example -Ppluginclass=ExamplePlugin -Pname='First Last' -Pgithub=<github id> -Pemail='your@emailaddress' -Ptimezone='<integer timezone -8 is PST>' <name-of-project>`
8. Answer the prompted questions or provide values via command line
9. `cd <name-of-project>`
10. `git init`
11. `git remote add origin <github url>`
12. Go forth and develop your plugin.

### Lazybones Info

You can find out more about creating lazybones templates on its [GitHub wiki][1].

[1]: https://github.com/pledbrook/lazybones/wiki/Template-developers-guide
