# reward
Reward project code

1. This is the Customer Awards Rewards Application .
2. Implemented with API Standards using :
	a. Maven Project 
	b. Swagger for documentation.
	c. Spring JPA
	e. lombok for reducing the boiler plate code .
	f. Test cases using Mockito

3. High Level Design :

     Controller --> Service Interface --> Service implementation --> JPA/CrudRepository 

	a. 2 Tables/Entity : Customer and Transaction Related 
		to each other with Customer Id as Primary and Secondary key
		
	b. 1 Model : Rewards 
	c. 1 Controller to handle 3 JSON requests : getAllRewards, getAllTransactions, getRewardsforId
	d. 1 Service to handle Business logic .
