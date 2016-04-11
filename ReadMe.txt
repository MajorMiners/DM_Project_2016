Important Notice:
	- handle exceptions inside functions. dont let any function to "throws xyzException".
	- 

Status:
	
	4/9/2016
		Neel-
		Isha-
		Kush-
		Joy-
		
	4/8/2016
		Neel-
			Changes in preprocessing
				- renamed hygiene_relation.java to HygieneIdentifier.java
				- added code for stemming and sentiment analysis
				- new output csv from that file will have sentiment as a new column.
				- maven build successful.
				- code running successfully but taking too long

			@TODO:
				- get the hygiene_related_reviews.csv ready.
				
		Isha-
		Kush-
		Joy-
			
	4/7/2016	
		Neel-
			Updated project structure, .gitignore and read me
			Changes in preprocessing/hygiene_relation.java
				- modularized the code
				- removed writer1. no need of 2 csv files. only 1 csv file that has hygiene related reviews.
				- checking each sentence of review and adding it to hygiene
				- if stars>=4 add to hygiene related review. We will consider it to be hygienic

			@TODO:
				- stemming words in reviews and dictionary before parsing.(primary)
				- do sentimental analysis.(secondary)

		Isha-

		Kush-

		Joy-
				
Download Link for Data Directory:
	@Isha- Please provide a google drive link to download data folder.
		- We all will use a common data folder.
		- In ur drive. 
		- Create a data folder. 
		- Give read write permission to we 4.
		- Add all "useful" CSVs etc from our datasets into that.
		- If anyone has any changes like renaming or folder structure. they can update it in the drive.

How to Run Project:
	@Joy- Can you please update how to build and run project via terminal.



