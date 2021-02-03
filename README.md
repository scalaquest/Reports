# ScalaQuest Final Reports

This is the entry point for PPS and LSS reports. We decided to set them in a
separate repository, as the structure of them begun to rise in complexity.

You can consult reports and other project documentation, either in Markdown, in
PDF of from the repo website.

## How generate report versions

To generate a release, open a new branch named `release/<versioncode>`, with
`<versioncode>` a code for the version respecting semantic versioning. Then,
create locally a new annotated tag, and push it remotely:

```shell
git checkout -b release/<versioncode>
git tag 0.2.4-dev+01 -a
git push --set-upstream origin release/<versioncode> --follow-tags
```
