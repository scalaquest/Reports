# ScalaQuest Final Reports

This is the entry point for PPS and LSS reports. We decided to set them in a
separate repository, as the structure of them begun to rise in complexity.

You can consult reports and other project documentation, either in Markdown, in
PDF of from the repo website.

## How generate report versions

Releases are auto-generated using semantic versioning, **at each merged PR on
main**. Just create a new git semantic-versioned tag when a major version is
required, like:

```
git tag 0.2.4 -a
```

Prereleases will be handled as well, without the need of manually creating more
tags.
