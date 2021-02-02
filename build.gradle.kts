plugins {
    id("com.diffplug.spotless")
    id ("org.danilopianini.git-sensitive-semantic-versioning")
}

repositories {
    jcenter()
}

spotless {
    // markdown format with Prettier
    format("styling") {
        target("**/*.md")

        // auto format markdown with to 80 characters
        prettier().configFile(rootDir.absolutePath + "/.prettierrc.yml")
    }
}

gitSemVer {
    version = computeGitSemVer() // THIS IS MANDATORY, AND MUST BE LAST IN BLOCK
}


tasks.register("generateVersionFile") {
    mkdir("build")
    File("${buildDir}/version").writeText(version.toString())
}