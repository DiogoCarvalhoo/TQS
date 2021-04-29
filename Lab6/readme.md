### 1.
	a) Run:
		 docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9001:9000 sonarqube:latest
		 
	NOTA: Conflitos provaveis com o portainer.io . Soluçao -> Parar o portainer.io ou entao alterar a porta do sonar em sonar.properties
	
	d) User -> My Account -> Security, fill in name, generate token =
		00face6fc210689c54b2bf071bdbad7c48250c84
	
	e) Acedendo ao dashboard do SonarQube (http://127.0.0.1:9001) podemos verificar que foi adicionado o novo projeto a esta mesma dashboard e a sua verificaçao ocorreu com sucesso. Apesar de ter sido detetado 1 bug, a analise passou. 
	
	f)
	Issue					Problem Description				How to Solve

	-Bug (Critical)	|Save and re-use this "Random". 			   	|Random object should be global instead of being initialized inside a function

	-Code Smell (Major)  	|Refactor the code in order to  not assign to this loop counter from within the loop body 	|Move 'i++' to inside the for loop

	-Code Smell (Major)   	|Replace this use of System.out or System.err by a logger.	|Change  System.out() to logger.log()

	-Code Smell (Major)   	|Use assertEquals instead.	   				|Replace the use of assertTrue with assertEquals.

### 2.
	a) The techincal debt value in this project is 2h15m. This time is a estimative of the amount of time needed to fix all the bugs and code smells presented in this project.

	b) After correcting all major and critical problems, code coverage dropped.

	d) 	Uncovered Lines: 35

		Uncovered Conditions: 6
