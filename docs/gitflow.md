# Gitflow Workflow

## Cardinal Rules

1. **`develop`** must always be stable.
2. **`master`** will always be merged with all commits in `develop` at the end of the Epic - barring very rare exceptional cases.
3. **Test locally**.
4. **Code Reviews** are mandatory.
5. **Document** technical design in markdown and make it change controlled in Github.
6. **Ask for help** when stuck.

Read [this post](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow) to learn about Gitflow Workflow before moving on to the steps.

## Workflow

1. On your Jira Kanban view, click on one issue and copy the issue ID (e.g. HR-12).
2. Create a new feature branch
   Go to your local git and run

   ```sh
   git checkout develop
   git branch -b YOUR-NEW-BRANCH
   ```

   > In order for Jira to recognize and keep track of your issue, prefix your issue ID to your branch name followed by issue name in [Kebab case](https://en.wikipedia.org/wiki/Letter_case).
   >
   > For example:
   >
   > Issue ID: NR-12
   >
   > Issue name: Create gitflow doc
   >
   > -> Branch name: NR-12-create-gitflow-doc

3. Make changes and commit:

   Include only changes that are relevant to your issue:

   ```
   git status
   ... lists out the list of changed files ...
   git add filenames
   ```

   Commit changes

   ```sh
   git commit -m "YOUR-COMMIT-MESSAGE"
   ```

   > follow [Commit Message Guidelines](https://github.com/gracet-sg/nora/blob/develop/docs/commit-message-guideline.md) to commit the changes.

   Try pulling from **`develop`** and resolve conficts of they exist:

   ```sh
   git pull origin develop
   ```

   Push to git remote:

   ```sh
   git push origin YOUR-NEW-BRANCH
   ```

4. Create pull request:

   Go to your branch on Github, click on [Create Pull Request](https://docs.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request) to create a new pull request.

   Select **`develop`** as base, add at least 1 reviewer and submit pull request.

5. Housekeeping

   Try not to use the same "develop" branch on your local workspace. Once the bugfix or feature is complete, delete the local feature or bugfix branch.

## Useful commands

1. `git status` : This shows you the files that have been modified and are "unstaged"
2. `git branch -l` : This tells you which branch you are on currently Notice the \* next to the branch.
3. `git checkout branchname` : This switches to an existing branch. To access a remote branch, run `git fetch` first.
4. `git checkout -b branchname` : Creates a new branch locally
5. `git push origin branchname` : This pushes your commits to the remote branch associated
6. `git push -u origin branchname` : This creates a branch remotely. If it already exists, it will fail.

   [Read more about Agile](https://www.atlassian.com/agile)


## This doc is brought to you by Gracet.ai developer team (contact: gracet.ai)
