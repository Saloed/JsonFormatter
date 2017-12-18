# About this project
Json Validation and Formatting server

Send json - receive formatted json or error message

Run
`docker build -t validation-service github.com/Saloed/JsonFormatter && docker run -t --rm -p 80:80 validation-service`

Send file to validation
`curl -s --data-binary @filename.json http://localhost`

Example of service response in case of error
`{
 "errorCode"  : 12345,
 "errorMessage" : ["Some error message"],
 "errorPlace" : ["line and column"],
 "resource": ["json string"],
 "request-id": ["id"]
}`