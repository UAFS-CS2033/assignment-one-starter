# Assignment One:  Building a Simple Web Server for Static File Serving in Java

### Introduction to HTTP Requests and Web Servers

In this assignment, you will be introduced to the fundamentals of HTTP (Hypertext Transfer Protocol),client server applications, browsers, and web servers by implementing a basic web server in Java. Understanding how web servers handle HTTP requests and serve static files will improve your understanding of the HTTP protocol and the operation of web sites and applications.

#### Learning Objectives

1. **Understanding HTTP Protocol:**
   - Learn the basics of HTTP requests and responses.
   - Grasp the significance of different HTTP methods (GET, POST, etc.).
   - Increase your understanding of network protocols.

2. **Building a Web Server in Java:**
   - Gain hands-on experience in creating a simple web server.
   - Implement functionality for handling HTTP GET requests.
   - Design a web server that must adhere to the existing HTTP/1.1 protocol.
   - Learn how web servers serve static files like HTML, CSS, and images.
   - Understand the importance of MIME types in serving different file types.

### Instructions

#### Overview 

Create a Java Server that listens on port 8080. Implement a basic HHTP server that can accept incoming connections from browsers and handle *HTTP Get* requests.  Your server should process the HTTP Request (request headers) and send a proper HTTP response with the appropriate response headers and the requested file (if available). The document root for storing your and serving your files on the web is the **docroot directory** located in your starter files.

