# Contribution Guide

Please make sure you read and understand this document before starting development on Camel Tooling as it helps us to merge your pull requests faster and keeps the commit history clean.

## Git configuration

For code review simplicity, all files should be pushed with LF for line-ending (Linux-style).
The file .gitattributes at the root of the repository should ensure that but it is recommended to set this option also in your Eclipse environment.
To configure Eclipse workspace preferences:

- Windows -> Preferences
- General -> Workspace
- In group "New text file line delimiters", ensure you select a Linux style behavior.

## Get the code

The easiest way to get started with the code is to [create your own fork](http://help.github.com/forking/) at GitHub, and then clone your fork:

    git clone git@github.com:<you>/camel-tooling-common.git
    cd camel-tooling-common
    git remote add upstream https://github.com/camel-tooling/camel-tooling-common.git

At any time, you can pull changes from the upstream and merge them onto your master:

    git checkout master               # switches to the 'master' branch
    git pull --rebase upstream master # fetches all 'upstream' changes and merges 'upstream/master' onto your 'master' branch
    git push origin                   # pushes all the updates to your fork, which should be in-sync with 'upstream'

The general idea is to keep your 'master' branch in-sync with the 'upstream/master'.

## Building Camel Tooling Common

This command will run the build:

    mvn clean verify

If you just want to check if things compile/build you can skip the tests by running:

    mvn clean verify -DskipTests

But *do not* push changes without having the new and existing unit tests pass!

## Contribute fixes and features

_Camel Tooling Common_ is open source, and we welcome anybody who wants to participate and contribute!

If you want to fix a bug or make any changes, please log an issue at GitHub [GitHub Issues](https://github.com/camel-tooling/camel-tooling-common/issues) describing the bug or new feature. Then we highly recommend making the changes on a topic branch named similar to the issue and/or containing the issue number. 

    git checkout -b 24_addSpecialFeature

After you're happy with your changes and a full build (with tests) runs successfully, commit your changes on your topic branch (with a meaningful comment).

    git commit -s -m "24 - added the feature described in issue #24"

Don't forget the code sign-off (_-s_) in the above comment or your PR will not be accepted.
Then it's time to check for any recent changes that were made in the official repository meanwhile:

    git checkout master               # switches to the 'master' branch
    git pull --rebase upstream master # fetches all 'upstream' changes and merges 'upstream/master' onto your 'master' branch
    git checkout 24_addSpecialFeature # switches to your topic branch
    git rebase upstream master        # re-applies your changes on top of the latest in master
                                      (i.e., the latest from master will be the new base for your changes)

If the pull grabbed a lot of changes, you should rerun your build with tests enabled to make sure your changes are still good.

You can then push your topic branch and its changes into your public fork repository:

    git push origin 24_addSpecialFeature         # pushes your topic branch into your public fork of Camel Tooling Common

And then [generate a pull-request](http://help.github.com/pull-requests/) where we can review the proposed changes, comment on them, discuss with you, and if everything is good then to merge the changes right into the official repository.


## Releases

To do a release we have to follow a few manual steps...

- change the version in the pom.xml to no longer contain _-SNAPSHOT_. 
- commit and push your change to a topic branch
- open a Pull Request for your change
- let someone from the team review and approve your PR
- merge the PR
- now we cut a tag

    git tag -a <version> -m "<some commit commeńt>"
    git push upstream <version>

- go to Travic CI and check that the tag is built fine and is deployed to central (https://travis-ci.org/camel-tooling/camel-tooling-common)

- fetch the latest upstream master sources and change the version in the pom.xml to the next development version. (for instance if you released 0.0.1 you want to switch to 0.0.2-SNAPSHOT)
- commit and push your change to a topic branch
- open a Pull Request for your change
- let someone from the team review and approve your PR
- merge the PR
