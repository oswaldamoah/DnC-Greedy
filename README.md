# Divide And Conquer / Greedy Algorithms
A Java console based application that alloes a user to select an algorithm (either Divide and Conquer or Greedy algorithm) and provide necessary input to use the algorithm
## How to Compile and Run the Application

### 1. **Extract the `src.zip` File**

   - Extract the contents of `src.zip` to a directory of your choice.


   - The `src` directory contains various Java source files organized into packages representing different algorithms and utilities.

### 2. **Dependencies**

   - The application does not have external dependencies beyond the JDK standard libraries.

### 3. **Compilation and Execution**

   - **Using VSCode:**
     - Open VSCode and navigate to the `src` directory.
     - Ensure you have the Java Extension Pack installed for Java support.
     - To compile and run `UserInterface.java`, in your terminal, right-click on UserInterface.java and select  VSCode's `Run Java` feature with appropriate configurations.
  

     - Alternatively,
       - **Navigate to the `src` Directory**
       - In the terminal, change directory (`cd`) to the `src` directory where your Java files are located. For example:
         ```bash
         cd path/to/your/src
         ```
          Replace `path/to/your/src` with the actual path where you extracted the `src.zip`.

        - **Compile and Run Java Files**

        - After navigating to the `src` directory, you can compile and run your Java files. For example:
            ```bash
            javac ui/UserInterface.java
            java ui.UserInterface
            ```
         - `javac` is used to compile the Java source files.
         - `java` is used to execute the compiled Java application.



   - **Using Eclipse:**
     - Launch Eclipse IDE and create a new Java project.
     - Import the extracted `src` directory as the project.
     - Right-click on `UserInterface.java` under the `ui` package and select "Run As" > "Java Application" to execute it.

### 4. **Running the Application**

   - Once compiled and executed, a console-based user interface (UI) window will appear.
   - Follow the on-screen prompts to choose from a list of algorithms and provide necessary inputs as per each algorithm's requirements.



### 5. **Directory Structure**

   ```
   src/
   |-- algorithms/
   |   |-- DivideAndConquer/
   |   |   |-- QuickSort.java
   |   |   |-- MergeSort.java
   |   |   |-- ClosestPair.java
   |   |   |-- StrassenMatrixMultiplication.java
   |   |   |-- Quickhull.java
   |   |-- Greedy/
   |       |-- PrimsMST.java
   |       |-- TSP.java
   |       |-- KruskalsMST.java
   |       |-- Dijkstra.java
   |       |-- Huffman.java
   |-- interfaces/
   |   |-- Algorithm.java
   |-- models/
   |   |-- Point.java (For QuickHull algorithm)
   |   |-- Graph.java (For Graphing algorithms)
   |-- utils/
   |   |-- PerformanceTester.java
   |-- ui/
       |-- UserInterface.java (Run_ME!)
   ```

### 6. **Program Illustration**
![./illustration.gif](https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExeDNkdzRkeHRmYmliMDJwOGtzanV4YnFrYTd6dWZweDcyNmpxNjg3YSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/VyuAaYpGlsvxjbqSnm/giphy.gif)




