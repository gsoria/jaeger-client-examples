# jaeger-client-examples
This repo containt two projects to test jaeger-client-java. Both projects were created to test the integration with jaeger and identify the dependencies of jaeger-client-java.

## Dependencies
It is necessary to have the all-in-one jaeger docker image running:

```console
docker run -d --name jaeger   -e COLLECTOR_ZIPKIN_HTTP_PORT=9411   -p 5775:5775/udp   -p 6831:6831/udp   -p 6832:6832/udp   -p 5778:5778   -p 16686:16686   -p 14268:14268   -p 9411:9411   jaegertracing/all-in-one:1.6
```

Both projects assume the standard ports of jaeger.

## jaeger-client-java-example
A basic simple maven-project, based in this course https://github.com/yurishkuro/opentracing-tutorial/tree/master/java
but using the jaeger-client-java instead of uber dependencies. Send a example "hello" trace to jaeger with the parameter name.

## jaeger-android
A basic android project, which test the jaeger client via UDP and Http. Allows to specify the IP of the collector, and depending on the selection, send a trace using HttpSender or UdpSender.
Also, sets the brand and model tags with the information of collected from the phone.

<img width="400" alt="android" src="https://user-images.githubusercontent.com/5870211/45936328-0f5d2000-bf6a-11e8-96ac-15afc06e9ab1.png">

This is a trace sent with UDP protocol
<img width="1000" alt="udp" src="https://user-images.githubusercontent.com/5870211/45936330-0f5d2000-bf6a-11e8-8a01-cfaf1fee6661.png">

This is a trace sent with Http protocol
<img width="1000" alt="http" src="https://user-images.githubusercontent.com/5870211/45936331-0ff5b680-bf6a-11e8-8442-cbe78a359fe3.png">

Trace details, showing 3 spans and the logs and tags for each of them.
<img width="1000" alt="trace-details" src="https://user-images.githubusercontent.com/5870211/45936329-0f5d2000-bf6a-11e8-8f91-1a29f3924dd1.png">
