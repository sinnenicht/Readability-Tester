# Readability-Tester

A program that evaluates a text and determines its readability based on several indexes.

Prerequisites
-------------

This program requires Java to compile and run.

Installation
------------

1. Download this repository and unzip the .zip file in your desired location.
2. Using the command line, navigate to \Readability-Test-master\src\readability.
3. Compile the program using the command `javac Main.java`.

Usage
-----

After Readability Tester has been compiled, it can be run from the command line by navigating to \Readability-Test-master\src and using the command `java readability.Main file`, where `file` is the file path and name of the file containing the text the Readability Tester should test. This file should be a .txt file.

The Readability Tester will then ask which tests should be run, offering the options ARI, FK, SMOG, CL, and all. The user should input these acronyms to select the corresponding test.

`ARI`: Automated Readability Index

`FK`: Flesch-Kincaid readability tests

`SMOG`: Simple Measure of Gobbledygook

`CL`: Coleman-Liau index

The Readability Tester will output the result for the select test. If `all` is selected, the results for all tests will be printed and the average score will be calculated.

Credits
-------

**Author:** Kate Jordan - [sinnenicht](https://github.com/sinnenicht)

This program is based on the Readability Test project on [Jet Brains Academy](https://hyperskill.org/projects/39?goal=7).

License
-------

This project is licensed under the GNU General Public License v3.0. See the [LICENSE](https://github.com/sinnenicht/Readability-Tester/blob/master/LICENSE) for details.
