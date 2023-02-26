# Simple Task Manager Console App
### Demo project within EPAM Java_Global_MP_2022 to demonstrate abilities of usage NoSQL MongoDB database.
<hr>

### Technologies:
* MongoDB 6.0.4
* Spring 5.3.22
* Spring Data MongoDB 3.0.3.RELEASE
* Mongodb Driver Sync 4.0.5
* Jackson 2.13.4
* JUnit 4.13.2
* SL4FJ 2.0.3
* Lombok 1.18.24
<hr>

### Commands
* <b>display-tasks --all</b> - display on console all tasks.
* <b>display-tasks --overdue</b> - display overdue tasks.
* <b>display-tasks --all --category=category_name</b> - display all tasks with the specific category (query parameter).
* <b>display-subtasks --all --category=category_name</b> - display all subtasks related to tasks with the specific category (query parameter).