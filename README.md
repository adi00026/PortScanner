# PortScanner

This is a simple port scanner written in Java. All it can do now is scan a host for open ports. I intend on adding more functionality to the script soon.

## Usage

`java PortScanner [IP address] -p [port] / [start port]-[end port]`

Example:

`java PortScanner 127.0.1 -p 1024' will scan the port 1024 at 127.0.1
'java PortScanner 127.0.1 -p 1-1024' will scan ports 1 through 1024 (inclusive) at 127.0.1
