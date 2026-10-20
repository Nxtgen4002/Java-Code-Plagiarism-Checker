# Java Source Code Plagiarism Detection System

## Overview
A Java-based project designed to identify similarities between Java source code files using tokenization, fingerprinting, and Jaccard similarity.

## Features
- Compares two Java source files.
- Preprocesses source code by removing comments and normalizing identifiers.
- Generates fingerprints from token sequences.
- Calculates code similarity as a percentage.
- Classifies similarity into Low, Medium, and High risk levels.

## Technologies Used
- Java
- Java NIO
- Java Collections Framework
- Hashing and Jaccard Similarity

## Project Structure
```text
Source-Code-Plagiarism-Detection/
├── Main.java
├── Fingerprint.java
├── Similarity.java
├── A.java
├── B.java
└── README.md
```

## Purpose
This project demonstrates the application of programming fundamentals, file handling, hashing, and similarity algorithms to source-code analysis.

## Future Improvements
- Support comparison of multiple files.
- Highlight matching code fragments.
- Implement AST-based structural comparison.
- Generate detailed analysis reports.

## Disclaimer
This project is intended for educational purposes. Similarity scores indicate code resemblance and do not independently prove plagiarism.
