## Approach:
As this project was quite small there was only a few decisions I want to note.

The first being using a map to store the tasks in the Workflow class. Here I felt it was sensible to use a map over an
array so that a constant lookup time is achieved and don't need to traverse each time a task is updated, so O(1) 
compared to O(n).

I have also covered the base cases on with the test cases due to the time constraints, it would be beneficial to cover 
more scenarios with them, such as having a circular dependency of tasks.

I have also not included the JSON mapper and stretch tasks due to the time constraints.