4/23/2016:
	Neel	- Wrote MapReduce Program to calculate reviewscore of all reviews.
			- hadoop directory has hadoop program in it.
			*** in hadoop/post-processing you can find allReviews.out and hygieneReviews.out
			- allReviews: serialized hashmap of ALL reviews with review id as key and its sentiment score as values
			- hygieneReviews: serialized hashmap of ONLY HYGIENE RELATED reviews with review id as key and its sentiment score as values
			- you can read them through the following code
			
			FileInputStream fis1 = new FileInputStream("allReviews.out");
			ObjectInputStream ois1 = new ObjectInputStream(fis1);
			HashMap<String, Integer> allReviewsMap = (HashMap<String,Integer>) ois1.readObject();
			System.out.println("All Reviews Size : "+allReviewsMap.size());
			ois1.close();
			fis1.close();

			FileInputStream fis2 = new FileInputStream("hygieneReviews.out");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			HashMap<String, Integer> hygieneReviewsMap = (HashMap<String,Integer>) ois2.readObject();
			System.out.println("Hygiene Reviews Size : "+hygieneReviewsMap.size());
			ois2.close();
			fis2.close();


++ 4 / 12 
	Issues:
	1. Normalize categorical / boolean features
		FeatureNormalizer (currently all set to 'false', 0, etc)
	2. Empty values from Business.json
		BusinessParser / parseBusinessSet (handle return for null cases, currently 'false')
	3. Empty values from Review.json 
		ReviewParser/ buildReviewSetMap ()
	4. TextAnalysis	
		ReviewSet / getTextAnalysisScore + TextAnalyzer / isHygieneRelated

-----------------------------------------------------------------------------------------


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



