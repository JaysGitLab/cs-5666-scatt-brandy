I. TO RUN THE COMMAND LINE PROTOTYPE
	Please run the "ScratchGrader.jar"
		i. navigate to the folder with the jar file
			a. follow prompt method
			 	java -jar ScratchGrader.jar
			b. provide a single argument to grade (variadic arguments not yet supported)
				java -jar SctachGrader.jar arg.sb2
		ii. follow the prompts (if you chose to not provide an argument)
				
				
II. TO RUN THE GUI BASED APPLICATION
	A minimal GUI exists, but not enough to warrant creating a standalone executable jar for it yet.
	
	
III. IMPORTING THE PROJECT TO ECLIPSE
	1. Clone the repository to your eclipse "git repositories" (press "control + 3" and type "git" to bring up this menu
		1. Get to the Git Repositories view
		2. Click "clone a repository"
		3. Provide the URL of the git repo
		4. Follow the steps and you should have a new repository in this view.
	2. Import the project into Eclipse
		1. Navigate to the git repositories view.
		2. Right click on the new SCATT repository,
			i. import existing eclipse projects
			ii. follow the steps
		3. Now you should have an eclipse project in the "project explorer" view
	3. Navigating branches
		1. Go to the git repositories view 
		2. Click on local branchs (you can create new ones here)
		3. When you switch branches, the eclipse project (in project explorer) should automatically update files
	
	
IV. USING THE PROJECT VIA COMMAND LINE
	This isn't recommended, but it is do-able. Navigate to the src folder. and type "make compile" to compile the 
	required java classes. Type "make test" to run the tests suite. Type "make clean" to clear the old .class files.