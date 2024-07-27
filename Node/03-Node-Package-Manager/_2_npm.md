https://snyk.io/test/docker/node%3A20.11.1-alpine3.19

nvm -v 
node -v

npm --version
npm -v
npm help
npm install -h  // different ways to use npm install

npm help-search update  // search occurunce of "update" 

Package.json:
	- Manage dependencies
		- Express and its versions
	- Scripts
		- Initial build
		
Default configuration:
	- set:
		- npm config set init-author-name "Vishwas"
		- npm config set init-licensingx "Vishwas"
	- Read: 
		- read config: npm get init-license
	- Delete: 
		- npm config delete init-license
Create package.json? ->
	- npm init
		we have answer some questions. (author name, licensing,...)
	- npm init --yes
	- npm init --y
		questions wont come, by default to empty
	- We can set default answer for questions, so when we run --yes. so values will populate
		1	npm config set init-author-name "Vishwas"
		    npm config set init-licensingx "Vishwas"
		2. npm init --yes
		
Install package:
	npm install moment
	npm i moment
		- it will get installed in node_module
	npm install moment --save
	npm install moment -S
		- save will saves in package.json dependencies
	npm install --save-dev:
	npm install -D:
		- install in node_module and update devDependencies in package.json
	npm install moment -g
		- install package globall, not in node_module
UnInstall package:
	- npm uninstall moment --save
	- npm uninstall moment -g
	- npm remove/rm/un/uninstall <package-name>

List of packages:
	- npm list:
		show all parent child relationships of packages
	- npm list --depth 1
		--depth 1: show only 1 child of dependency
		--depth 0: does no show child at all
	npm list --global true --depth 0
		show global packages
	
Schematic versioning:
	lodash: "^4.16.1"
	- Major version number: 4
	- Minor: 16
	- Patch: 1
	
	Restrict Patch:
		- npm install lodash@3.3.0 --save
		  //installs specific version.
		  // lodash: "^3.3.0"
	Restrict Minor:
		4.14.*
		- npm install lodash@4.14 --save
		// lodash: "^4.14.2" // takes latest patch version
	Restrict Major:
		- npm install lodash@4 --save
		// lodash: "^4.16.1" // takes latest minor and patch version
	
Installing from package.json:
	  if we have package.json and dependencies are listed in this file.
	- npm install
	  It will install all listed dependencies in package.json
	 
	Restrict Major:
		"lodash": "^4.14.1"	 // 4.X.X : check for latest minor and patch
	Restrict Minor:
		"lodash": "~4.14.1"	 // 4.14.X
	Restrict Patch:
		"lodash": "4.14.1"
	No Restrictions:
		"lodash": "*"
	
Update Package:
	- npm update lodash --save
	  lodash updated to latest version
	- npm update --dev --save-dev 
		update all devDependencies to latest version
	- npm update	
		update all dependencies and devDependencies.
	- npm update -g
		update all global packages
	- npm update -g nodemon
		update n global packages
	- npm install npm@latest -g
		update npm to latest version

Delete unlisted package.json packages from node_module:
	package is not present in package.json but present in node_module
	- npm prune
	  removes additional packages
	  
Update Packages:
	- npm outdated	// show wanted and latest version
	- npm update	// update to wanted version
	- npm update -- latest