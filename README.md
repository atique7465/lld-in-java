**Low Level Design**

## Time tracker app
A command-line application that can be used to track time spent on different tasks, and generate reports.

### Features

- Ability to create and manage multiple tasks - add tasks and assign them to categories
- Ability to start and stop a timer for a specific task.
- Ability to record and track time spent on each task.
- Ability to auto-switch: starting a task when another is in progress automatically stops the previous task.
- Ability to view the total time spent on each task.
- Ability to generate reports on time spent on each task
- Ability to set reminders for task
- Ability to track time based on categories

### Commands

### Init by clearing persisted file data [Optional]
`time-tracker init`

#### Start logging time
`time-tracker start <task-name>`
`time-tracker start <task-name> <category-name>`
Note: If the task name is already in progress, this will be a no-op

#### Stop logging time
`time-tracker stop <task-name>` Stops the task with task-name

#### Show time
`time-tracker report-task` Shows the tasks and time spent on each task

`time-tracker report-category` Shows the categories and time spent on each category