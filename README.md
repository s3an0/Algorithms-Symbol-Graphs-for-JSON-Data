# Algorithms-Symbol-Graphs-for-JSON-Data
A project for an Algorithms class designed to scan in data from a JSON file. With it, a symbol graph is created and tasks are used on the created graph.

About:
This program utilizes a symbol graph to store data from a JSON file, which connects together several users based on tweets. With it we are able to find paths between users 
through breadth first search and depth first search.

Notes:
For Task 2 Problem 2, due to the nature of randomness when 
shuffling the data in a bag, it may take awhile for the path with
the institution for Lane and Rob to be found. If it looks like it isnt
found, please try to run it a few more times, it should work but just in 
case it could happen.

The empiracle analysis goes through a random sample of 100 starting ids and
target ids. It will print out all the paths as a result to show that they are indeed
working. The times for the BFS and DFS are printed at the very end so it is easier to 
see the differences, but to see the earlier task answers you will need to scroll up all
the way to the top of the terminal results.

The json-simple-1.1.jar was used for importing and parsing the JSON data.
If any issues appear with json, check if the library is being loaded properly.
