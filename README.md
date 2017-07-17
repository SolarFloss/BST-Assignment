# BST-Assignment
This is an assignment for my CSC 202 class where we had to create a Binary Search Tree to find restaurants from a google docs file


To get the data directly from the google doc sheet, I use a google script that turns the sheet data into a readable JSON format.

From there I was able to parse the data and make it indexable. For example: 

```
Restaurants restuarant = new Restaurants();
//The parameter for all the functions is the ID of the restaurant
//I am using ID 24 in this case

System.out.println(restaurant.getName(24));         //returns Fuji Sushi
System.out.println(restaurant.getZipCode(24));      //returns 63052
System.out.println(restuarant.getPhoneNumber(24));  //returns 6364648889
```



Full gist explanation here: https://gist.github.com/SolarFloss/eb97baab105fed817c469fa086616339

