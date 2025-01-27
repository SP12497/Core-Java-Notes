# Videos: https://mega.nz/folder/VnhQFIpJ#MXpb285Rzpbnq2adkPVt9Q/folder/AjwESbbQ
<!-- https://www.freecodecamp.org/news/npm-cheat-sheet-most-common-commands-and-nvm/ -->
025: Package.json:
==================
- install npm: 
    - npm i -g npm@<version>

- Create package.json file:
    - npm init :
        this will ask basic question and then it will create package.json file
    - How to skip basic questions?
        -> npm init --yes


026 Installing a node package:
==============================
- npm i <package_name>  => package.json: {dependencies: {axios": "^1.6.2"}} 
    # it will show caret (^) symbol along with version number
    # in previous version, we need --save flag to save in package.json file, in newer version, we no longer need to specify

028 Package Dependencies:
=========================
- npm install mongoose
- ls node_modules:
    # In package.json, we only installed, underscore and mongoose, but in node_modules, will see more number of dependency folders.
    # Dependencies of underscore and mongoose will also show inside node_modules.
    # suppose, our project need axios version 1, but mongoose have dependency on axios^version2, then the structrure will be like below:
        - package.json
            dependencies {
                "axios": "^1.0.0",
                "mongoose": "^2.3.4"
            }
        - node_modules:
            - axios ^v1
            - mongoose ^2.3.4
                -- axios ^v2


029 NPM Packages and Source Control:
====================================
- We have to exclude node_module folder, when code is on source control repository (like github)
- we can create node_module dependencies again using: npm i
- To hide from github, mention node_module folder path in ".gitignore" file.

- git add .
- git commit -m "Our first commit"

030 Sematic Versioning:
=======================
mongoose: "^4.13.6" // Major.Minor.Patch

# Major (^):
    - ^2.3.4 => 2.x.x => any latest minor and patch versions are allowed, suppose, newer package is available, 2.6.7, then npm i will pick this newer version.
# Minor (~):
    - "~2.3.4" => 2.3.x : any latest patch version in allow. like, 2.3.4, 2.3.5...

# Fixed Version:
"2.3.4" : Will always get exact version.

031 Listing the Installed Packages:
===================================
There are 2 ways to check installed packages version:
    1. Go to node_modules, then go to package each package package.json file and check the version
    2. npm list: it will show tree of our dependency with package depends on.
    3. npm list --depth=0:
        - it will only show installed dependency, does not show their child dependencies which they depends on.

032 Viewing Registry Info for a Package:
========================================
- We can visit www.npmjs.com, to see meta data of the package
- npm view mongoose:
    - show package.json file of mongoose
    - npm view mongoose dependencies: it will show only dependencies.
    - npm view mongoose versions: all the release versions of mongoose.

033 Installing a specific version of a package:
===============================================
- npm i mongoose@2.4.2

034 Updating Local Packages:
============================
- npm updated: it will show all outdated packages with wanted (same major version with minor or patch upgrade) and latest version.
- npm update: it will update all the outdated package to Wanted version base on ^, ~ or fix.

How to install to latest version?
- install package which will do this for us:
    - npm i -g npm-check-updates // install
    - update packages to latest version:
        - npm-check-updates : this will upgrade packages to latest versions.
        - shortcut:
            -ncu
            - ncu -u: just upgrade the package.json as well not install the dependencies
            - npm i: this will install dependencies and update node_modules

035 Dev Dependencies:
=====================
- This dependencies are development dependencies, this should not go to production environment.
- npm i jshnt --save-dev:
    it came under devDependencies section in package.json but it will be available in node_module:
    "devDependencies": {
        "jshint":"^2.9.5"
    }

 - How to install only dependencies and skip devDependencies?
    - To install only dependencies and skip devDependencies, you can use the `--production` flag with the `npm install` command. Here's an example:
    ```
    npm install --production
    ```
    - This command will only install the dependencies specified in the `dependencies` section of your `package.json` file, excluding any packages listed under `devDependencies`.

036 Uninstalling a Package:
=========================== 
- npm uninstall mongoose
- npm un mongoose
    => mongoose will get removed from node_modules and package.json.

037 Working with Global Packages:
=================================
- Global packages: this are not specific to any file or project, we can access this packages in any location
- npm i -g npm
- npm install -g <package_name>
How to check outdated global packages:
    -> npm -g outdated
Uninstall global package:
    -> npm un -g <package-name>


038 Publishing a Package:
=========================
First create a account in npm and then add user locally:
    - npm adduser
Login:
    - npm login
        - Username:
        - Password:
        - Email:
Publish Package:
    - npm publish: // package name must be unique
    # Now we can use this package any any repo. by using (npm i <my-package-name>)


039 Updating a Published Package:
=================================
- Now, we have a package available in npm.js, suppose package name is [lion-lib : 1.0.0]
- and we added new minor feature, 
    - npm publish => throw error, because still version is 1.0.0
    - There are 2 ways to increase the version:
        1. manually update the version number in package.json file
        2. by using command prompt:
            - npm version major => change to 2.0.0
            - npm version minor => change to 1.1.0
            - npm version patch => change to 1.0.1
