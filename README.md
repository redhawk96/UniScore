# UniScore

### Getting Started
* Clone the UniScore repository as follow, 
```
 git clone https://github.com/redhawk96/UniScore.git
```

* Open git-bash terminal and checkout to a new branch as you prefer(eg : UniScore-Local-Dev),
```
 git checkout -b UniScore-Local-Dev
```

* Open both projects with ecplise IDE, 
 ```
 File > Open Project from File System > locate projects from the local directory > Click finish
 ```

### Prerequisites

* [Ecplise 2019-12](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2019-12/R/eclipse-inst-win64.exe) - Editor for Java
* [Java SE Development Kit 8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) - Application run-time environment
* [XAMPP 7.4.3](https://www.apachefriends.org/index.html) - Apache server and MySQL database to support application functionality
* [Chrome 80.0.3987.132](https://www.google.com/chrome/) - Browser to manage MySQL database

### Installing

* Run Xampp and start Apache and MySQL services
* Type http://localhost/phpmyadmin/ on Chrome
* Create a new database called ``uniscoredb``
* Select ``uniscoredb`` and go to SQL tab then run the following script

  <details><summary>MySQL Script</summary>
  <p>

  ```
  --
  -- Table structure for table `activitylogs`
  --

  CREATE TABLE `activitylogs` (
    `activityId` int(11) NOT NULL,
    `activityBrief` text NOT NULL,
    `triggeredBy` varchar(255) NOT NULL,
    `triggeredOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
  ) 

  -- --------------------------------------------------------

  --
  -- Table structure for table `exams`
  --

  CREATE TABLE `exams` (
    `examId` int(11) NOT NULL,
    `examName` varchar(255) NOT NULL,
    `moduleId` varchar(255) NOT NULL,
    `duration` int(11) NOT NULL,
    `enrollmentKey` varchar(20) NOT NULL,
    `status` enum('Active','Disabled') NOT NULL DEFAULT 'Disabled',
    `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  ) 

  --
  -- Dumping data for table `exams`
  --

  INSERT INTO `exams` (`examId`, `examName`, `moduleId`, `duration`, `enrollmentKey`, `status`, `createdAt`, `updatedAt`) VALUES
  (1, 'Mid Term', 'CIS002', 40, 'cis2020', 'Active', '2020-03-09 21:42:30', '2020-03-09 21:42:30');

  -- --------------------------------------------------------

  --
  -- Table structure for table `grades`
  --

  CREATE TABLE `grades` (
    `grade` varchar(2) NOT NULL,
    `passMark` int(11) NOT NULL
  ) 

  --
  -- Dumping data for table `grades`
  --

  INSERT INTO `grades` (`grade`, `passMark`) VALUES
  ('A', 75),
  ('B', 65),
  ('C', 55),
  ('D', 45),
  ('E', 35);

  --
  -- Triggers `grades`
  --
  DELIMITER $$
  CREATE TRIGGER `grade_to_uppercase_trigger` BEFORE INSERT ON `grades` FOR EACH ROW SET NEW.grade = UPPER(NEW.grade)
  $$
  DELIMITER ;

  -- --------------------------------------------------------

  --
  -- Table structure for table `modules`
  --

  CREATE TABLE `modules` (
    `moduleId` varchar(255) NOT NULL,
    `moduleName` varchar(255) NOT NULL,
    `year` int(11) NOT NULL,
    `semester` int(11) NOT NULL,
    `teacherId` varchar(255) NOT NULL,
    `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  ) 

  --
  -- Dumping data for table `modules`
  --

  INSERT INTO `modules` (`moduleId`, `moduleName`, `year`, `semester`, `teacherId`, `createdAt`, `updatedAt`) VALUES
  ('CIS002', 'Comparative Integrated Systems', 3, 1, 'uditha@uniscore.com', '2020-03-09 21:41:16', '2020-03-09 21:41:16'),
  ('RM003', 'Research Methodologies', 3, 1, 'uditha@uniscore.com', '2020-03-09 21:41:16', '2020-03-09 21:41:16');

  -- --------------------------------------------------------

  --
  -- Table structure for table `questions`
  --

  CREATE TABLE `questions` (
    `questionId` int(11) NOT NULL,
    `examId` int(11) NOT NULL,
    `question` text NOT NULL,
    `option1` text NOT NULL,
    `option2` text NOT NULL,
    `option3` text NOT NULL,
    `option4` text NOT NULL,
    `answer` int(2) NOT NULL,
    `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedBy` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  ) 

  -- --------------------------------------------------------

  --
  -- Table structure for table `submissions`
  --

  CREATE TABLE `submissions` (
    `moduleId` varchar(255) NOT NULL,
    `studentId` varchar(255) NOT NULL,
    `examId` int(11) NOT NULL,
    `answerList` text NOT NULL,
    `overallScore` double NOT NULL,
    `grade` varchar(2) NOT NULL,
    `submittedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
  ) 

  -- --------------------------------------------------------

  --
  -- Table structure for table `users`
  --

  CREATE TABLE `users` (
    `userId` varchar(255) NOT NULL,
    `firstName` varchar(255) NOT NULL,
    `lastName` varchar(255) NOT NULL,
    `gender` enum('Male','Female') NOT NULL DEFAULT 'Male',
    `email` varchar(255) NOT NULL,
    `nic` varchar(12) NOT NULL,
    `phone` int(11) NOT NULL,
    `address` text NOT NULL,
    `role` enum('Student','Lecturer','Admin') NOT NULL DEFAULT 'Student',
    `registeredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `password` varchar(255) NOT NULL,
    `status` enum('Active','Disabled') NOT NULL DEFAULT 'Active'
  ) 

  --
  -- Dumping data for table `users`
  --

  INSERT INTO `users` (`userId`, `firstName`, `lastName`, `gender`, `email`, `nic`, `phone`, `address`, `role`, `registeredDate`, `password`, `status`) VALUES
  ('ishani@uniscore.com', 'Ishani', 'Welagedara', 'Female', 'ishani@uniscore.com', '950457789V', 715478965, 'No 4, Wayward Pl, Colombo 03', 'Student', '2020-03-10 03:00:00', '123456', 'Active'),
  ('subarshan@uniscore.com', 'subarshan', 'Thiyagarajah', 'Male', 'subarshan@uniscore.com', '950457410V', 770452014, 'No 8, Ward St, Colombo 02', 'Admin', '2020-03-10 03:00:00', '123456', 'Active'),
  ('uditha@uniscore.com', 'Uditha', 'Silva', 'Male', 'uditha@uniscore.com', '950452374V', 774102354, 'No 6, Park St Avenue, Colombo 01', 'Lecturer', '2020-03-10 03:00:00', '123456', 'Active');

  --
  -- Indexes for dumped tables
  --

  --
  -- Indexes for table `activitylogs`
  --
  ALTER TABLE `activitylogs`
    ADD PRIMARY KEY (`activityId`),
    ADD KEY `fk_activitylog_triggered_by` (`triggeredBy`) USING BTREE;

  --
  -- Indexes for table `exams`
  --
  ALTER TABLE `exams`
    ADD PRIMARY KEY (`examId`,`examName`,`moduleId`) USING BTREE,
    ADD KEY `fk_exam_module_id` (`moduleId`) USING BTREE;

  --
  -- Indexes for table `grades`
  --
  ALTER TABLE `grades`
    ADD PRIMARY KEY (`grade`);

  --
  -- Indexes for table `modules`
  --
  ALTER TABLE `modules`
    ADD PRIMARY KEY (`moduleId`),
    ADD KEY `fk_module_teacher_id` (`teacherId`) USING BTREE;

  --
  -- Indexes for table `questions`
  --
  ALTER TABLE `questions`
    ADD PRIMARY KEY (`questionId`),
    ADD KEY `fk_question_exam_id` (`examId`) USING BTREE;

  --
  -- Indexes for table `submissions`
  --
  ALTER TABLE `submissions`
    ADD PRIMARY KEY (`moduleId`,`studentId`,`examId`),
    ADD KEY `fk_submission_student_id` (`studentId`),
    ADD KEY `fk_submission_exam_id` (`examId`),
    ADD KEY `fk_submission_grade` (`grade`),
    ADD KEY `fk_submission_module_id` (`moduleId`);

  --
  -- Indexes for table `users`
  --
  ALTER TABLE `users`
    ADD PRIMARY KEY (`userId`);

  --
  -- AUTO_INCREMENT for dumped tables
  --

  --
  -- AUTO_INCREMENT for table `activitylogs`
  --
  ALTER TABLE `activitylogs`
    MODIFY `activityId` int(11) NOT NULL AUTO_INCREMENT;

  --
  -- AUTO_INCREMENT for table `exams`
  --
  ALTER TABLE `exams`
    MODIFY `examId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

  --
  -- AUTO_INCREMENT for table `questions`
  --
  ALTER TABLE `questions`
    MODIFY `questionId` int(11) NOT NULL AUTO_INCREMENT;

  --
  -- Constraints for dumped tables
  --

  --
  -- Constraints for table `activitylogs`
  --
  ALTER TABLE `activitylogs`
    ADD CONSTRAINT `fk_triggered_by` FOREIGN KEY (`triggeredBy`) REFERENCES `users` (`userId`);

  --
  -- Constraints for table `exams`
  --
  ALTER TABLE `exams`
    ADD CONSTRAINT `fk_module_id` FOREIGN KEY (`moduleId`) REFERENCES `modules` (`moduleId`) ON DELETE CASCADE ON UPDATE CASCADE;

  --
  -- Constraints for table `modules`
  --
  ALTER TABLE `modules`
    ADD CONSTRAINT `fk_teacher_id` FOREIGN KEY (`teacherId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

  --
  -- Constraints for table `questions`
  --
  ALTER TABLE `questions`
    ADD CONSTRAINT `fk_exam_id` FOREIGN KEY (`examId`) REFERENCES `exams` (`examId`) ON DELETE CASCADE ON UPDATE CASCADE;

  --
  -- Constraints for table `submissions`
  --
  ALTER TABLE `submissions`
    ADD CONSTRAINT `fk_submission_exam_id` FOREIGN KEY (`examId`) REFERENCES `exams` (`examId`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `fk_submission_grade` FOREIGN KEY (`grade`) REFERENCES `grades` (`grade`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `fk_submission_module_id` FOREIGN KEY (`moduleId`) REFERENCES `modules` (`moduleId`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `fk_submission_student_id` FOREIGN KEY (`studentId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;
  COMMIT;
  ```

  </p>
  </details>

* Inorder to start the server go to UniScore-Server project on ecplise and run the [UniScoreServer.java](https://github.com/redhawk96/UniScore/blob/UniScore-Server/src/connectivity/UniScoreServer.java) file as below 
```
UniScore-Server > src > connectivity > UniScoreServer.java > right-click > Run As > Java Application
```
   
* Then go to start the client go to UniScore-Client project on ecplise and run the [UniScoreClient.java](https://github.com/redhawk96/UniScore/blob/UniScore-Client/src/connectivity/UniScoreClient.java) file as below 
```
UniScore-Client > src > connectivity > UniScoreClient.java > right-click > Run As > Java Application
```
