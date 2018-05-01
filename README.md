How to Run:
1. Update Maven dependency
2. Run as TestNg Test
3. reports are generated in reports folder


How this test work:
1. This test reads data from a xls file - present in testdata folder
2. And converts xls data in to two dimensional array of hash table to data provider
3. hash table is passed as a parameter to test.


Login test:
1. test will run with two records.
	a. Assuming login details are valid 
	b. Assuming login details are invalid
	
Currency Test:
1. No data is required for this test
2. used Rest Assured to make a rest call
