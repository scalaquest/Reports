plugins {
    id("com.diffplug.spotless")
    id("org.danilopianini.git-sensitive-semantic-versioning")
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
    minimumVersion.set("0.1.0")
    developmentIdentifier.set("dev")
    noTagIdentifier.set("archeo")
    fullHash.set(false)
    maxVersionLength.set(Int.MAX_VALUE)
    developmentCounterLength.set(2)
    version = computeGitSemVer()
}


tasks.register("generateVersionFile") {
    mkdir("build")
    File("$buildDir/version").writeText(version.toString())
}