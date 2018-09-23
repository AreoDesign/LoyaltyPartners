INSTRUCTIONS FOR USE:

The application was created for LoyaltyPartners (Payback) company as a solution to coding life-case task
for recruitment purpose. The goal is described in DESCRIPTION.txt file (added to project files).
This application runs on standard port: 8080 and provides access via simple GUI (Swagger)
accessible under the URL: http://localhost:8080/swagger-ui.html, proceeding following steps might be useful
to make use of it.

1. open URL: http://localhost:8080/swagger-ui.html in your web browser

2. click "request-controller" to see all handled requests

    a. add      (with POST HTTP request method) - via this method we add the request into queue. In order to send
                                                  the request it is mandatory to fill in RequestBody, by providing:
                                                  the 'message' (content we pass into application = subject) and
                                                  the 'processType' (the way how we want to process the 'message').
                                                  There is a hint JSON format accessible under 'Example Value',
                                                  it is automatically transferred to requestDTO window and may be
                                                  modified subsequently by manual change in 'message'.
                                                  If processType does not match any known type - application returns
                                                  HTTP status code error '400' (Bad Request).
                                                  To see all known process types click 'Model' (just next to 'Example Value')
                                                  CAUTION processType IS CASE SENSITIVE!
                                                  To accept the choice - click 'Try it out!' button

    b. start    (with GET HTTP request method)  - via this method we start processing all the requests from the queue
                                                  on the process end we are informed about time elapsed (console view)

3. At the moment requests might be processed in 4 and ONLY 4 ways (or gives HTTP code 400 otherwise) - stored in:
    a. embedded H2 database accessible under the link: http://localhost:8080/h2. Database is automatically
       closed when the application is stopped and all data is removed.
    b. file name ARdesign.txt located into TEMP location (on Windows). The exact path is logged on console each time
       the save operation is done to a file. File stores all processed request messages even if the application is
       re-run. The file is not removed automatically on application shutdown.
    c. console with the content of request logged as well as a small developer gift.
    d. do nothing more than accepts the request.

SAMPLE REQUESTS:
{
  "message": "ja chce zapisać się do bazy danych",
  "processType": "DB"
}

{
  "message": "ja chce wyświetlić się w logu",
  "processType": "LOG"
}

{
  "message": "ja chce zapisać się do pliku",
  "processType": "FILE"
}

{
  "message": "ja chce zostać odrzucony!",
  "processType": "REJECT"
}