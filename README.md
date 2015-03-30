# mapping.agnostic

OK... let's show that ESB's trying to sell and lock you into using their mapping technologies is the sure way to...

* Lock you into their platform & tools
* Reduce your opportunities to migrate
* Reduce your opportunities to merge, import other services or microservices

The alternative..

Mapping is an asynchronous & segregated API... starting with XML IN, XML OUT

Don't lock the mapping in the flows... push it out like a 'Translation' service;

A header will tell us the expectations, size & quality of service.

Bias your open source frameworks, but separate the 'implemenation' or lockin from the problem.. so we can use..

* DFDL
* DXSI
* Altova
* XQuery
* XSLT 1
* XSLT 2

And... we return a detailed 'MappingFault' depending on what went wrong that tells us when, where, what element,..

Quality of Service:
* How fast?
* How big? (Roughly)
* Split it??
* Duplicate it??
* Traceablity -- Unique Identifier
* Variables to apply? (Name/Value Set) to a chosen XSLT
* Mapping Rule Identifer


## Libraries Used
* ActiveMQ (Queue Framework)
* 
